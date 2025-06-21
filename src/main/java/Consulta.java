import javax.swing.*;
import java.awt.*;

public class Consulta extends JFrame {
    private JPanel mainPanel;
    private JPanel navbarPanel;
    private JButton btnFilmes;
    private JButton btnSessoes;
    private JButton btnBar;
    private JButton btnBilheteira;
    private JButton btnConsulta;
    private JButton btnSalas;
    private JPanel rightLabels;
    private JPanel leftButtons;
    private JButton btnTop10;
    private JButton btnTipoBilhete;
    private JButton btnTipoSala;
    private JButton btnDadosFilme;
    private JPanel middlePanel;
    private JButton btnFiltros;
    private JTextField filmeMaisVendido;
    private JTextField generoMaisAssitido;
    private JTextField lucroMedioFilme;
    private JTextField retabilidadeSala;
    private JTextField taxeMediaSala;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Consulta("Consulta").setVisible(true));
    }

    public Consulta(String title) throws HeadlessException {
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

        btnTop10.addActionListener(e -> {
            // Implement the action for Top 10 button
            new Top10Filmes("Top 10 Filmes").setVisible(true);
            dispose();
        });

        btnTipoBilhete.addActionListener(e -> {
            // Implement the action for Tipo Bilhete button
            new TipoBilhetes("Tipo Bilhetes").setVisible(true);
            dispose();
        });

        btnTipoSala.addActionListener(e -> {
            // Implement the action for Tipo Sala button
            new TipoSalas("Tipo Sala").setVisible(true);
            dispose();
        });

        btnDadosFilme.addActionListener(e -> {
            // Implement the action for Dados Filme button
            new DadosFilmes("Dados Filme").setVisible(true);
            dispose();
        });

        // Set the initial size and location of the frame
        setSize(1600, 700);
        setLocationRelativeTo(null); // Center the frame on the screen
    }
}
