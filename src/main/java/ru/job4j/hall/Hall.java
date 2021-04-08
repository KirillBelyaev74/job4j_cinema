package ru.job4j.hall;

import ru.job4j.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class Hall {

    public List<Ticket> getAllEmptyHall(int row, int cell) {
        List<Ticket> tickets = new ArrayList<>();
        for (int indexRow = 1; indexRow <= row; indexRow++) {
            for (int indexCell = 1; indexCell <= cell; indexCell++) {
                tickets.add(new Ticket(indexRow, indexCell, 0));
            }
        }
        return tickets;
    }

    public List<Ticket> checkHall(List<Ticket> emptyHall, List<Ticket> buyCell) {
        for (int right = 0; right != buyCell.size(); right++) {
            for (int left = 0; left != emptyHall.size(); left++) {
                Ticket empty = emptyHall.get(left);
                Ticket buy = buyCell.get(right);
                if (buy.getRow() == empty.getRow()
                    && buy.getCell() == empty.getCell()) {
                    emptyHall.set(left, buy);
                }
            }
        }
        return emptyHall;
    }
}
