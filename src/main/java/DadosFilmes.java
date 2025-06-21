import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class DadosFilmes extends JFrame {
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
    private JTable dadosFilmesTable;

    public DadosFilmes(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        dadosFilmesTable.setModel(new DefaultTableModel(
                new Object[]{"Nome Filme", "Licenciamento", "Lucro Gerado"}, 0
        ));

        populateDadosFilmesTable();

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
            new Top10Filmes("Top 10 Filmes").setVisible(true);
            dispose();
        });

        btnTipoBilhete.addActionListener(e -> {
            new TipoBilhetes("Tipo Bilhetes").setVisible(true);
            dispose();
        });

        btnTipoSala.addActionListener(e -> {
            new TipoSalas("Tipo Salas").setVisible(true);
            dispose();
        });

        btnDadosFilme.addActionListener(e -> {
            new DadosFilmes("Dados Filme").setVisible(true);
            dispose();
        });

        setSize(1600, 700);
        setLocationRelativeTo(null);
    }

    private void populateDadosFilmesTable() {
        Map<String, Double> lucroMap = new HashMap<>();

        for (Movie movie : AppData.getInstance().getMovies()) {
            double receita = 0;
            for (Session session : AppData.getInstance().getSessions()) {
                if (session.getMovie().equals(movie.getTitle())) {
                    receita += session.getReservedSeats().size() * 10.0;
                }
            }
            double lucro = receita - movie.getPrecoLicenciamento();
            lucroMap.put(movie.getTitle(), lucro);
        }

        List<Map.Entry<String, Double>> sorted = new ArrayList<>(lucroMap.entrySet());
        sorted.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        DefaultTableModel model = (DefaultTableModel) dadosFilmesTable.getModel();
        /*
        for (int i = 0; i < Math.min(10, sorted.size()); i++) {
            Movie movie = AppData.getInstance().getMovieByTitle(sorted.get(i).getKey());
            double lucro = sorted.get(i).getValue();
            String licenca = movie.getDataInicioLicenca() + " até " + movie.getDataFimLicenca();
            model.addRow(new Object[]{movie.getTitle(), licenca, lucro});
        }
        */
    }

}
