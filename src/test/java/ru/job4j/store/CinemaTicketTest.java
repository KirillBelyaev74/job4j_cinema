package ru.job4j.store;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Account;
import ru.job4j.model.Ticket;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CinemaTicketTest {

    private CinemaTicket cinemaTicket;
    private Account account;

    @Before
    public void start() {
        cinemaTicket = CinemaTicket.instOf();
        account =
                CinemaAccount.instOf().saveAccount(
                        new Account("kirill", "kirill@", "10"));
        cinemaTicket.saveTicket(new Ticket(1, 1, account.getId()));
        cinemaTicket.saveTicket(new Ticket(1, 2, account.getId()));
        cinemaTicket.saveTicket(new Ticket(1, 3, account.getId()));
    }

    @Test
    public void whenSaveTicketsThenGetAllTheTickets() {
        List<Ticket> tickets = cinemaTicket.getAllBuyTickets();
        assertThat(tickets.size(), is(3));
    }

    @Test
    public void whenSaveTicketsThenGetAllTicketsByAccountId() {
        List<Ticket> tickets = cinemaTicket.getAllTheTicketsOfTheAccount(String.valueOf(account.getId()));
        assertThat(tickets.size(), is(3));
    }
}