package ru.job4j.servlets;

import ru.job4j.hall.Hall;
import ru.job4j.model.Ticket;
import ru.job4j.store.CinemaTicket;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Hall hall = new Hall();
        List<Ticket> emptyHall = hall.getAllEmptyHall(5, 5);
        List<Ticket> buyOfTickets = CinemaTicket.instOf().getAllBuyTickets();
        if (!buyOfTickets.isEmpty()) {
            emptyHall = hall.checkHall(emptyHall, buyOfTickets);
        }
        ObjectMapper mapper = new ObjectMapper();
        String string = mapper.writeValueAsString(emptyHall);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("json");
        resp.getWriter().write(string);
    }
}
