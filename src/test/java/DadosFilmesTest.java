import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DadosFilmesTest {
    @Test
    public void testMovieProfitCalculation() {


        Movie movie = new Movie(1, "Interstellar", "Sci-Fi", "", "", "", "", "", 0, 0, 2024, 100.0);
        AppData.getInstance().getMovies().add(movie);

        Session session = new Session(movie, "Sala VIP", "22:00", "2024-06-04");
        Seat seat1 = new Seat('D', 1);
        Seat seat2 = new Seat('D', 2);
        session.getReservedSeats().add(seat1);
        session.getReservedSeats().add(seat2);

        AppData.getInstance().getSessions().add(session);

        // Assume fixed ticket price: 10.0
        double receita = session.getReservedSeats().size() * 10.0;
        double lucro = receita - movie.getPrecoLicenciamento();

        assertEquals(-80.0, lucro);  // (2*10.0) - 100.0 = -80.0
    }
}
