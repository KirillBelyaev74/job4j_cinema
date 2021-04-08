package ru.job4j.store;

import ru.job4j.model.Account;

public interface StoreAccount {

    Account saveAccount(Account account);
    Account getAccount(String email, String phone);
}
