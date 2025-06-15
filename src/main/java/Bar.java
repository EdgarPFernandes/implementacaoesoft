import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Bar extends JFrame {
    private JPanel mainPanel;
    private JPanel Header;
    private JPanel leftButtons;
    private JPanel cart;
    private JPanel middlePanel;
    private JButton addProduct;

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

    private JButton[][] productButtons = new JButton[4][4];
    private int currentPage = 0;
    private final int ITEMS_PER_PAGE = 16;

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

        // Add Product Button
        addProduct.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter product name:");
            if (name != null && !name.trim().isEmpty()) {
                String priceInput = JOptionPane.showInputDialog(this, "Enter product price:");
                try {
                    double price = Double.parseDouble(priceInput);
                    AppData.getInstance().getProducts().add(new Product(name.trim(), price));
                    AppData.getInstance().saveData(); // <-- Save right after adding
                    updateButtonLabels();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price.");
                }
            }
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

            final int index = i; // 👈 this is the fix

            flatButtons[i].addActionListener(e -> {
                String text = flatButtons[index].getText(); // now valid
                if (!text.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Clicked: " + text);
                }
            });
        }
    }

    private List<Product> getProducts() {
        return AppData.getInstance().getProducts();
    }

    private void updateButtonLabels() {
        List<Product> products = getProducts();
        int start = currentPage * ITEMS_PER_PAGE;

        for (int i = 0; i < 16; i++) {
            int index = start + i;
            int row = i / 4;
            int col = i % 4;
            JButton button = productButtons[row][col];

            if (index < products.size()) {
                Product p = products.get(index);
                button.setText("<html><center>" + p.getProductName() + "<br>$" + String.format("%.2f", p.getPrice()) + "</center></html>");
                button.setEnabled(true);
            } else {
                button.setText("");
                button.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bar("Bar").setVisible(true));
    }
}
