import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BarProductTestCase {

    @Test
    public void testConstructorAndGetters() {
        BarProduct pipocas = new BarProduct("Pipocas", 4.0, 100.0, 20.0, 2.5);

        assertEquals(4.0, pipocas.getPrice());
        assertEquals(100.0, pipocas.getStock());
        assertEquals(20.0, pipocas.getMinStock());
        assertEquals(2.5, pipocas.getSupplierPrice());
    }

    @Test
    public void testSetters() {
        BarProduct agua = new BarProduct("Agua", 1.5, 50.0, 10.0, 0.2);

        agua.setStock(45.0);
        agua.setMinStock(15.0);
        agua.setSupplierPrice(5.5);

        assertEquals(45.0, agua.getStock());
        assertEquals(15.0, agua.getMinStock());
        assertEquals(5.5, agua.getSupplierPrice());
    }
}
