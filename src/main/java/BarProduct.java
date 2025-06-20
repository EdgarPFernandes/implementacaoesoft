public class BarProduct extends Product {
    private double stock;

    public BarProduct(String productName, Double price, double stock) {
        super(productName, price);
        this.stock = stock;
    }

    public double getStock() {
        return stock;
    }
}
