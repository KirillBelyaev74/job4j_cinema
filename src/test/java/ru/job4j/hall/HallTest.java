package ru.job4j.hall;

import org.junit.Test;
import ru.job4j.model.Ticket;

import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HallTest {

    @Test
    public void whenHallHas4rowsAnd5Cells() {
        Hall hall = new Hall();
        List<Ticket> emptyHall = hall.getAllEmptyHall(4, 5);
        Ticket one = new Ticket(4, 5, 0);
        Ticket two = new Ticket(5, 6, 0);
        Ticket three = new Ticket(0, 0, 0);
        assertTrue(emptyHall.contains(one));
        assertFalse(emptyHall.contains(two));
        assertFalse(emptyHall.contains(three));
    }

    @Test
    public void whenBuyCell() {
        Hall hall = new Hall();
        List<Ticket> emptyHall = hall.getAllEmptyHall(3, 3);
        Ticket one = new Ticket(1, 1, 10);
        Ticket two = new Ticket(2, 2, 20);
        List<Ticket> buyCell = List.of(one, two);
        List<Ticket> fullHall = hall.checkHall(emptyHall, buyCell);
        assertTrue(fullHall.contains(one));
        assertTrue(fullHall.contains(two));
    }
}
