import javax.swing.*;

public class PaginaInicial {
    public JPanel mainPanel;
    public JPanel navbarPanel;
    public JButton btnFilmes;
    public JButton btnSessoes;
    public JButton btnBar;
    public JButton btnBilheteira;
    public JButton btnConsulta;
    public JButton btnSalas;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PaginaInicial");
            frame.setContentPane(new PaginaInicial().mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1600, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
