import java.util.LinkedList;

public class Cart {

    private Double total;
    private LinkedList<Product> productList;

    public Cart() {
        this.total = 0.0;
        this.productList = new LinkedList<>();
    }


    public void emptyCart(){
        this.productList.clear();
        this.total = 0.0;
    }

    public void updateTotal(Double produtValue) {
        this.total += produtValue;
    }

    public void addListaProdutos(Product product) {
        this.productList.add(product);
    }

    public LinkedList<Product> getProductList() {
        return productList;
    }

    public Double getTotal() {
        return total;
    }
}
