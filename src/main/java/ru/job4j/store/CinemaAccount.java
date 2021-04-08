package ru.job4j.store;

import org.apache.log4j.Logger;
import ru.job4j.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CinemaAccount extends DataBasePool implements StoreAccount {

    private static final Logger logger = Logger.getLogger(CinemaAccount.class);

    private static final class Lazy {
        private static final CinemaAccount INST = new CinemaAccount();
    }

    public static CinemaAccount instOf() {
        return CinemaAccount.Lazy.INST;
    }

    @Override
    public Account saveAccount(Account account) {
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into account(name, email, phone) VALUES (initcap(?), lower(?), ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.setString(3, account.getPhone());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    account.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException throwables) {
            logger.error("Method saveAccount fail mistake: " + throwables + ". Arguments account " + account.toString());
        }
        return account;
    }

    @Override
    public Account getAccount(String email, String phone) {
        Account account = null;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from account where email = lower(?) and phone = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, phone);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    account = new Account(
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("phone"));
                    account.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException throwables) {
            logger.error("Method getAccount fail mistake: " + throwables + ". Arguments email " + email + " and phone " + phone);
        }
        return account;
    }
}
