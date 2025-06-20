import javax.swing.*;

public class Bilheteira extends JFrame {
    private JPanel Header;
    private JPanel leftButtons;
    private JButton barButton;
    private JPanel middlePanel;
    private JButton button11;
    private JButton button14;
    private JButton button44;
    private JButton button41;
    private JButton button34;
    private JButton button31;
    private JButton button24;
    private JButton button21;
    private JButton button13;
    private JButton button23;
    private JButton button33;
    private JButton button43;
    private JButton button12;
    private JButton button22;
    private JButton button32;
    private JButton button42;
    private JButton nextPageButton;
    private JButton previousPageButton;
    private JPanel cart;
    private JPanel mainPanel;
    private JLabel carrinhoLbl;
    private JPanel cartInfo;
    private JList list1;
    private JLabel totalLbl;
    private JTextField totalValue;
    private JButton cancelButton;
    private JButton confirmButton;
    private JComboBox movieFilter;

    public Bilheteira(String title) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
}
