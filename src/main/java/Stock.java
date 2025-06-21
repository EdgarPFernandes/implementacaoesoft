import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Stock extends JFrame {
    private JPanel Header;
    private JPanel leftButtons;
    private JButton barButton;
    private JButton addProductButton;
    private JPanel lowStock;
    private JPanel middlePanel;
    private JButton editProduct;
    private JPanel mainPanel;
    private JLabel lowStocklbl;
    private JList lowStockList;
    private JTable stockTable;
    private JButton previousPageBtn;
    private JButton nextPageBtn;

    private final int ITEMS_PER_PAGE = 34;
    private int currentPage = 0;

    public Stock(String title) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        updateLowStockList();

        // Initialize table model
        stockTable.setModel(new DefaultTableModel(
                new Object[]{"Nome Produto", "Quantidade em Stock", "Preço compra", "Preço venda"}, 0
        ));

        editProduct.setVisible(false);
        stockTable.getSelectionModel().addListSelectionListener(e -> {
            boolean rowSelected = stockTable.getSelectedRow() != -1;
            editProduct.setVisible(rowSelected);
        });

        // Load first page
        updateTable();

        // Navigation buttons
        previousPageBtn.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                updateTable();
            }
        });

        nextPageBtn.addActionListener(e -> {
            int totalPages = (int) Math.ceil((double) AppData.getInstance().getBarProducts().size() / ITEMS_PER_PAGE);
            if (currentPage < totalPages - 1) {
                currentPage++;
                updateTable();
            }
        });

        barButton.addActionListener(e -> {
            new Bar("Bar").setVisible(true);
            dispose();
        });

        addProductButton.addActionListener(e -> {
            showProductDialog(null);
            updateLowStockList();
            updateTable();
        });

        editProduct.addActionListener(e -> {
            int selectedRow = stockTable.getSelectedRow();
            if (selectedRow == -1) return;

            // Retrieve product from the table model (assumes same order as in AppData)
            int modelIndex = selectedRow + currentPage * ITEMS_PER_PAGE; // If you're paginating
            List<BarProduct> products = AppData.getInstance().getBarProducts();
            if (modelIndex >= products.size()) return;

            BarProduct product = products.get(modelIndex);

            showProductDialog(product);
            updateTable();
            updateLowStockList();
        });


    }

    private void updateTable() {
        DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
        model.setRowCount(0); // Clear existing rows

        List<BarProduct> products = AppData.getInstance().getBarProducts();
        int start = currentPage * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, products.size());

        for (int i = start; i < end; i++) {
            BarProduct bp = products.get(i);
            model.addRow(new Object[]{
                    bp.getProductName(),
                    bp.getStock(),
                    String.format("€%.2f", bp.getSupplierPrice()),
                    String.format("€%.2f", bp.getPrice())
            });
        }
    }

    private void showProductDialog(BarProduct existingProduct) {
        JTextField nameField = new JTextField(existingProduct != null ? existingProduct.getProductName() : "");
        JTextField stockField = new JTextField(existingProduct != null ? String.valueOf(existingProduct.getStock()) : "");
        JTextField minStockField = new JTextField(existingProduct != null ? String.valueOf(existingProduct.getMinStock()) : "");
        JTextField supplierField = new JTextField(existingProduct != null ? String.valueOf(existingProduct.getSupplierPrice()) : "");
        JTextField priceField = new JTextField(existingProduct != null ? String.valueOf(existingProduct.getPrice()) : "");

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nome Produto:"));
        panel.add(nameField);
        panel.add(new JLabel("Quantidade:"));
        panel.add(stockField);
        panel.add(new JLabel("Quantidade Mínima:"));
        panel.add(minStockField);
        panel.add(new JLabel("Preço Compra:"));
        panel.add(supplierField);
        panel.add(new JLabel("Preço Venda:"));
        panel.add(priceField);

        int result = JOptionPane.showConfirmDialog(this, panel, existingProduct == null ? "Adicionar Produto" : "Editar Produto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                double stock = Double.parseDouble(stockField.getText());
                double minStock = Double.parseDouble(minStockField.getText());
                double supplierPrice = Double.parseDouble(supplierField.getText());
                double price = Double.parseDouble(priceField.getText());

                if (existingProduct != null) {
                    existingProduct.setProductName(name);
                    existingProduct.setStock(stock);
                    existingProduct.setMinStock(minStock);
                    existingProduct.setSupplierPrice(supplierPrice);
                    existingProduct.setPrice(price);
                } else {
                    BarProduct newProduct = new BarProduct(name, price, stock, minStock, supplierPrice);
                    AppData.getInstance().getBarProducts().add(newProduct);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor insira valores válidos.");
            }
        }
    }


    private void updateLowStockList() {
        DefaultListModel<String> model = new DefaultListModel<>();

        for (BarProduct product : AppData.getInstance().getBarProducts()) {
            if (product.getStock() < product.getMinStock()) {
                model.addElement(product.getProductName() + " - Stock: " + product.getStock());
            }
        }

        lowStockList.setModel(model);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Stock("Stock").setVisible(true));
    }
}
