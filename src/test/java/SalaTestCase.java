import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaTestCase {

    @Test
    public void testSalaNomeChange() {
        Sala sala = new Sala("A1", 50,50, "Dolby", "IMAX", "Reclináveis", "Ar condicionado");
        sala.setNome("A2");
        assertEquals("A2", sala.getNome());
    }

    @Test
    public void testSalaIsActiveByDefault() {
        Sala s = new Sala("A3", 100,50, "Dolby", "Normal", "Fixos", "Nenhuma");
        assertTrue(s.isAtiva());
    }
}
