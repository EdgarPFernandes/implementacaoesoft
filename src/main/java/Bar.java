import javax.swing.*;
import java.awt.*;

public class Bar extends JFrame {

    private JPanel mainPanel;
    private JPanel Header;

    public Bar(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);

        pack();
    }

    public static void main(String[] args) {
        new Bar("Bar").setVisible(true);
    }
}
