package ru.job4j.model;

import java.util.Objects;

public class Ticket {

    private int row;
    private int cell;
    private int accountId;

    public Ticket(int row, int cell, int accountId) {
        this.row = row;
        this.cell = cell;
        this.accountId = accountId;
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return row == ticket.row && cell == ticket.cell && accountId == ticket.accountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, cell, accountId);
    }

    @Override
    public String toString() {


        return "Ticket { "
                + "row = " + row
                + ", cell = " + cell
                + ", accountId = " + accountId
                + '}' + System.lineSeparator();
    }
}
