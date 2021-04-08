package ru.job4j.store;

import ru.job4j.model.Ticket;

import java.util.List;

public interface StoreTicket {

    void saveTicket(Ticket ticket);
    List<Ticket> getAllBuyTickets();
    List<Ticket> getAllTheTicketsOfTheAccount(String accountId);

}
