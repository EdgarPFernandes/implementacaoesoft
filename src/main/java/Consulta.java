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
    private JPanel rightButtons;
    private JLabel rentabilidadeSalaLbl;
    private JPanel leftButtons;
    private JButton btnTop10;
    private JButton btnTipoBilhete;
    private JButton btnTipoSala;
    private JButton btnDadosFilme;
    private JPanel middlePanel;
    private JLabel filmeMaisLbl;
    private JLabel generoMaisLbl;
    private JLabel lucroMedioFilmeLbl;
    private JLabel taxaMediaOcupacaoLbl;
    private JButton button1;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Consulta("Consulta").setVisible(true));
    }

    public Consulta(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
    }
}
