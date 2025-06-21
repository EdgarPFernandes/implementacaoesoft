import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TipoBilhetesTest {
    @Test
    public void testMostSoldTicketTypeBasedOnReservedSeats() {

        Movie movie = new Movie(1, "Avatar", "Sci-Fi", "USA", "James Cameron", "Lightstorm Entertainment", "English", "None", 12, 162, 2023, 15.0);
        Session session = new Session(movie, "Sala Azul", "20:00", "2024-06-02");
        Seat seat1 = new Seat('B', 1);
        Seat seat2 = new Seat('B', 2);
        session.getReservedSeats().add(seat1);
        session.getReservedSeats().add(seat2);

        Ticket t1 = new Ticket("Bilhete", 10.0, seat1, session, "adulto");
        Ticket t2 = new Ticket("Bilhete", 10.0, seat2, session, "adulto");

        AppData.getInstance().getSessions().add(session);
        AppData.getInstance().getTicketType().add(t1);
        AppData.getInstance().getTicketType().add(t2);

        Map<String, Integer> count = new HashMap<>();

        for (Seat seat : session.getReservedSeats()) {
            for (Ticket ticket : AppData.getInstance().getTicketType()) {
                if (ticket.getSeat().equals(seat) && ticket.getSession().equals(session)) {
                    String type = ticket.getTicketType();
                    count.put(type, count.getOrDefault(type, 0) + 1);
                    break;
                }
            }
        }

        assertEquals(2, count.get("adulto").intValue());
    }
}
