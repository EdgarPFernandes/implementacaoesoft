import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessaoTestCase {

    @Test
    public void testSessionCreation() {
        Movie m = new Movie(1, "Test", "Drama", "PT", "Alguém", "Estúdio", "PT", "EN", 12, 90, 2023, 10000.0);
        Session s = new Session(m, "A1", "21:00", "20-06-2024");

        assertEquals("A1", s.getSala());
        assertEquals("21:00", s.getHora());
        assertEquals("Test", s.getMovie());
    }
}
