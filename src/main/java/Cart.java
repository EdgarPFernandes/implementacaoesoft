import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    public void clear() {
        items.clear();
    }

    public double getTotal() {
        return items.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
}
