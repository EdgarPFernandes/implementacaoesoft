import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartTestCase {

    @Test
    public void testAddProductAndGetTotal() {
        Cart cart = new Cart();
        Product cocacola = new BarProduct("Coca-cola", 2.5, 10,1,0.6);
        Product fanta = new BarProduct("Fanta", 3.0, 15,4,1.2);

        cart.addProduct(cocacola);
        cart.addProduct(cocacola);
        cart.addProduct(fanta);


        assertEquals(2, cart.getItems().get(cocacola));
        assertEquals(1, cart.getItems().get(fanta));
    }

    @Test
    public void testClearCart() {
        Cart cart = new Cart();
        cart.addProduct(new Product("Pipocas", 3.0));
        cart.clear();
        assertEquals(0.0, cart.getTotal(), 0.001);
        assertTrue(cart.getItems().isEmpty());
    }
}
