import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PaginaInicial extends JFrame{
    public JPanel mainPanel;
    public JPanel navbarPanel;
    public JButton btnFilmes;
    public JButton btnSessoes;
    public JButton btnBar;
    public JButton btnBilheteira;
    public JButton btnConsulta;
    public JButton btnSalas;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaginaInicial("Pagina Inicial").setVisible(true));
    }

    public PaginaInicial(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        btnBar.addActionListener(e -> {
            new Bar("Bar").setVisible(true);
            dispose();
        });

        btnBilheteira.addActionListener(e -> {
            new Bilheteira("Bilheteira").setVisible(true);
            dispose();
        });

        btnFilmes.addActionListener(e -> {
            new Filmes("Filmes").setVisible(true);
            dispose();
        });

        btnSessoes.addActionListener(e -> {
            new Sessoes("Sessoes").setVisible(true);
            dispose();
        });

        btnConsulta.addActionListener(e -> {
            new Consulta("Consulta").setVisible(true);
            dispose();
        });

        btnSalas.addActionListener(e -> {
            new Salas("Salas").setVisible(true);
            dispose();
        });

        // Set the initial size and location of the frame
        setSize(1600, 700);
        setLocationRelativeTo(null); // Center the frame on the screen

    }
}
