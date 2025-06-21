import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTestCase {
    @Test
    public void testConstructorAndGetters() {
        Movie movie = new Movie(1, "Sinner", "Horror", "EUA", "Ryan Coogler", "WB", "EN", "PT", 16,120,2025, 35.2);
        Session session = new Session(movie, "Sala 2", "19:00", "2025-06-21");
        Seat seat = new Seat('C', 7);
        Ticket ticket = new Ticket("Sinner Ticket", 10.5, seat, session, "Adult");

        assertEquals("Sinner Ticket", ticket.getProductName());
        assertEquals(10.5, ticket.getPrice());
        assertEquals("Adult", ticket.getTicketType());
        assertEquals(session, ticket.getSession());
        assertEquals(seat, ticket.getSeat());
        assertEquals("C7", seat.getSeatCode());
    }

    @Test
    public void testToStringOutput() {
        Movie m = new Movie(1, "Old Title", "Ação", "Brasil", "João", "X Filmes", "PT", "EN", 14, 100, 2022, 35.2);

        Session session = new Session(m, "Sala IMAX", "21:30", "2025-06-22");
        Seat seat = new Seat('A', 10);
        Ticket ticket = new Ticket("Dune 2 Ticket", 14.0, seat, session, "Student");

        String expected = "Dune 2 Ticket - 14.0€ (Seat: A10, Sala: Sala IMAX, Time: 21:30)";
        assertEquals(expected, ticket.toString());
    }
}
