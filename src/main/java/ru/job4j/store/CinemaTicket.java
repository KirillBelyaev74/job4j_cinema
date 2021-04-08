package ru.job4j.store;

import org.apache.log4j.Logger;
import ru.job4j.model.Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CinemaTicket extends DataBasePool implements StoreTicket {

    private static final Logger logger = Logger.getLogger(CinemaAccount.class);

    private static final class Lazy {
        private static final CinemaTicket INST = new CinemaTicket();
    }

    public static CinemaTicket instOf() {
        return CinemaTicket.Lazy.INST;
    }

    @Override
    public void saveTicket(Ticket ticket) {
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into ticket(row, cell, account_id) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, ticket.getRow());
            preparedStatement.setInt(2, ticket.getCell());
            preparedStatement.setInt(3, ticket.getAccountId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            logger.error("Method saveTicket fail mistake: " + throwables + ". Arguments ticket " + ticket);
        }
    }

    @Override
    public List<Ticket> getAllBuyTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("select * from ticket")) {
                tickets = this.addTicketsInList(resultSet);
            }
        } catch (SQLException throwables) {
            logger.error("Method getAllBuyTickets fail mistake: " + throwables);
        }
        return tickets;
    }

    @Override
    public List<Ticket> getAllTheTicketsOfTheAccount(String accountId) {
        int id = 0;
        try {
            id = Integer.parseInt(accountId);
        } catch (NumberFormatException e) {
            logger.error("Casting type incorrectly " + e + ". Argument accountId" + accountId);
        }
        List<Ticket> tickets = new ArrayList<>();
        try {
            try (Connection connection = pool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select * from ticket where account_id = ?")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    tickets = this.addTicketsInList(resultSet);
                }
            }
        } catch (SQLException throwables) {
            logger.error("Method getAllTheTicketsOfTheAccount fail mistake: " + throwables + ". Argument accountId" + accountId);
        }
        return tickets;
    }

    private List<Ticket> addTicketsInList(ResultSet resultSet) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        while (resultSet.next()) {
            tickets.add(
                    new Ticket(resultSet.getInt("row"),
                            resultSet.getInt("cell"),
                            resultSet.getInt("account_id")));
        }
        return tickets;
    }
}
