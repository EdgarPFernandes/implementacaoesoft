public class BarProduct extends Product {
    private double stock;
    private double minStock;
    private double supplierPrice;

    public BarProduct(String productName, Double price, double stock, double minStock, double supplierPrice) {
        super(productName, price);
        this.stock = stock;
        this.minStock = minStock;
        this.supplierPrice = supplierPrice;
    }


    public double getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(double supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getMinStock() {
        return minStock;
    }

    public void setMinStock(double minStock) {
        this.minStock = minStock;
    }
}
