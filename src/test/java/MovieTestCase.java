import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTestCase {

    @Test
    public void testMovieCreation() {
        Movie movie = new Movie(1, "Sinner", "Horror", "EUA", "Ryan Coogler", "WB", "EN", "PT", 16,120,2025);

        assertEquals("Sinner", movie.getTitle());
        assertEquals("Horror", movie.getGenre());
        assertEquals(120, movie.getDuration());
    }

    @Test public void testMovieUpdate() {
        Movie m = new Movie(1, "Old Title", "Ação", "Brasil", "João", "X Filmes", "PT", "EN", 14, 100, 2022);

        m.setMovieName("New Title");
        m.setMovieGenre("Drama");
        m.setOrigin("Portugal");
        m.setDirector("Maria");
        m.setStudio("CinemaPro");
        m.setLanguages("EN");
        m.setSubtitles("FR");
        m.setAge(18);
        m.setDuration(110);
        m.setMovieYear(2023);

        assertEquals("New Title", m.getTitle());
        assertEquals("Drama", m.getGenre());
        assertEquals("Portugal", m.getCountry());
        assertEquals("Maria", m.getDirector());
        assertEquals("CinemaPro", m.getStudio());
        assertEquals("EN", m.getLanguages());
        assertEquals("FR", m.getSubtitles());
        assertEquals(18, m.getAge());
        assertEquals(110, m.getDuration());
        assertEquals(2023, m.getYear());
    }
}
