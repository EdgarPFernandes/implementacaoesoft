import javax.swing.*;

public class Stock extends JFrame {
    private JPanel Header;
    private JPanel leftButtons;
    private JButton barButton;
    private JButton addProductButton;
    private JPanel lowStock;
    private JPanel middlePanel;
    private JButton button1;
    private JPanel mainPanel;

    public Stock(String title) {
            super(title);
            setContentPane(mainPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
    }
}
