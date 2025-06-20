import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class Bar extends JFrame {
    private JPanel mainPanel;
    private JPanel Header;
    private JPanel leftButtons;
    private JPanel cartPanel;
    private JPanel middlePanel;

    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button31;
    private JButton button32;
    private JButton button33;
    private JButton button34;
    private JButton button41;
    private JButton button42;
    private JButton button43;
    private JButton button44;

    private JButton previousPageButton; // Previous page
    private JButton nextPageButton; // Next page
    private JButton viewStockButton;
    private JButton bilheteiraButton;
    private JLabel carrinhoLbl;
    private JPanel cartInfo;
    private JList list1;
    private JLabel totalLbl;
    private JTextField totalValue;
    private JButton cancelButton;
    private JButton confirmButton;

    private JButton[][] productButtons = new JButton[4][4];
    private int currentPage = 0;
    private final int ITEMS_PER_PAGE = 16;
    private Cart cart = new Cart();

    public Bar(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        // Setup product button grid
        setupProductButtons();

        // Show first page of products
        updateButtonLabels();

        // Pagination: Previous
        previousPageButton.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                updateButtonLabels();
            }
        });

        // Pagination: Next
        nextPageButton.addActionListener(e -> {
            int maxPages = (int) Math.ceil((double) getProducts().size() / ITEMS_PER_PAGE);
            if (currentPage < maxPages - 1) {
                currentPage++;
                updateButtonLabels();
            }
        });

        cancelButton.addActionListener(e -> clearCart());
        confirmButton.addActionListener(e -> {
            if (cart.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(this, "O carrinho está vazio.");
            } else {
                for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();

                    // Ensure it's a BarProduct before updating stock
                    if (product instanceof BarProduct) {
                        BarProduct bp = (BarProduct) product;
                        double newStock = bp.getStock() - quantity;
                        bp.setStock(Math.max(0, newStock));
                    }
                }
                JOptionPane.showMessageDialog(this, "Compra efetuada!");
                clearCart();
                updateButtonLabels();
            }
        });

        viewStockButton.addActionListener(e -> {
            new Stock("Stock").setVisible(true);
            dispose(); // closes Bar window
        });

        bilheteiraButton.addActionListener(e -> {
            new Bilheteira("Bilheteira").setVisible(true);
            dispose(); // closes Bar window
        });


    }

    private void setupProductButtons() {
        JButton[] flatButtons = {
                button11, button12, button13, button14,
                button21, button22, button23, button24,
                button31, button32, button33, button34,
                button41, button42, button43, button44
        };

        for (int i = 0; i < flatButtons.length; i++) {
            int row = i / 4;
            int col = i % 4;
            productButtons[row][col] = flatButtons[i];

            final int index = i;

            flatButtons[i].addActionListener(e -> {
                int productIndex = currentPage * ITEMS_PER_PAGE + index;
                List<BarProduct> products = getProducts();

                if (productIndex < products.size()) {
                    Product selected = products.get(productIndex);
                    cart.addProduct(selected);
                    updateCartDisplay();
                }
            });
        }
    }

    private List<BarProduct> getProducts() {
        return AppData.getInstance().getBarProducts().stream()
                .filter(p -> p.getStock() > 0)
                .toList();
    }


    private void updateButtonLabels() {
        List<BarProduct> products = getProducts();
        int start = currentPage * ITEMS_PER_PAGE;

        for (int i = 0; i < 16; i++) {
            int index = start + i;
            int row = i / 4;
            int col = i % 4;
            JButton button = productButtons[row][col];

            if (index < products.size()) {
                Product p = products.get(index);
                button.setText("<html><center>" + p.getProductName() + "<br>€" + String.format("%.2f", p.getPrice()) + "</center></html>");
                button.setEnabled(true);
            } else {
                button.setText("");
                button.setEnabled(false);
            }
        }
    }

    private void clearCart() {
        cart.clear();
        list1.setModel(new DefaultListModel<>());
        totalValue.setText("€0.00");
    }

    private void updateCartDisplay() {
        DefaultListModel<String> model = new DefaultListModel<>();
        double total = 0.0;

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product p = entry.getKey();
            int quantity = entry.getValue();
            double subtotal = p.getPrice() * quantity;
            model.addElement(quantity + "x " + p.getProductName() + " - $" + String.format("%.2f", subtotal));
            total += subtotal;
        }

        list1.setModel(model);
        totalValue.setText("€" + String.format("%.2f", total));
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bar("Bar").setVisible(true));
    }
}
