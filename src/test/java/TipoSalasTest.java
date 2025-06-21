import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TipoSalasTest {
    @Test
    public void testTicketsCountPerRoom() {

        Movie movie = new Movie(1, "Oppenheimer", "Drama", "USA", "Christopher Nolan", "Syncopy", "English", "None", 16, 180, 2023, 12.0);
        Session session = new Session(movie, "IMAX", "21:00", "2024-06-03");
        session.getReservedSeats().add(new Seat('C', 1));
        session.getReservedSeats().add(new Seat('C', 2));
        session.getReservedSeats().add(new Seat('C', 3));

        AppData.getInstance().getSessions().add(session);

        assertEquals(3, session.getReservedSeats().size());
    }
}
