package ru.job4j.store;

import org.junit.Test;
import ru.job4j.model.Account;

import static org.junit.Assert.assertTrue;

public class CinemaAccountTest {

    @Test
    public void whenSaveAccountThenGetTheAccount() {
        CinemaAccount cinemaAccount = CinemaAccount.instOf();
        Account account = new Account("kirill", "kirill@", "10");
        account = cinemaAccount.saveAccount(account);
        Account result = cinemaAccount.getAccount("kirill@", "10");
        assertTrue(result.getName().equalsIgnoreCase(account.getName()));
    }
}