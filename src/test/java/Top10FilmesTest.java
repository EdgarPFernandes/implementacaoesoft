import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class Top10FilmesTest {
    @Test
    public void testTop10MoviesTicketCount() {

        Movie movie1 = new Movie(1, "Movie A", "Action", "USA", "Director A", "Studio A", "English", "None", 13, 120, 2023, 10.0);
        Session session = new Session(movie1, "Sala 1", "18:00", "2024-06-01");
        session.getReservedSeats().add(new Seat('A', 1));
        session.getReservedSeats().add(new Seat('A', 2));

        AppData.getInstance().getSessions().add(session);

        int total = session.getReservedSeats().size();

        assertEquals(2, total);
    }
}
