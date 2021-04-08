package ru.job4j.servlets;

import org.json.JSONObject;
import ru.job4j.model.Account;
import ru.job4j.model.Ticket;
import ru.job4j.store.CinemaAccount;
import ru.job4j.store.CinemaTicket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Payment extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Account account = CinemaAccount.instOf().getAccount(email, phone);
        if (account == null) {
            account = new Account(name, email, phone);
            account = CinemaAccount.instOf().saveAccount(account);
        }

        String[] rows = req.getParameter("rows").split(",");
        String[] cells = req.getParameter("cells").split(",");
        for (int index = 0; index != rows.length || index != cells.length; index++) {
            Ticket ticket = new Ticket(Integer.parseInt(rows[index]), Integer.parseInt(cells[index]), account.getId());
            CinemaTicket.instOf().saveTicket(ticket);
        }

        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            JSONObject jsObject = new JSONObject();
            jsObject.put("rows", rows);
            jsObject.put("cells", cells);
            writer.println(jsObject.toString());
        }
    }
}
