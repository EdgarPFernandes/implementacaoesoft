import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TipoBilhetes extends JFrame {
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
    private JTable tipoBilhetesTable;

    public TipoBilhetes(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        tipoBilhetesTable.setModel(new DefaultTableModel(
                new Object[]{"Nome Filme", "Tipo Bilhetes Mais Vendidos"}, 0
        ));

        populateTipoBilhetesTable();

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

    private void populateTipoBilhetesTable() {
        Map<String, Map<String, Integer>> movieTypeCounts = new HashMap<>();

        List<Ticket> tickets = AppData.getInstance().getTicketType();
        List<Session> sessions = AppData.getInstance().getSessions();

        for (Session session : sessions) {
            String movieTitle = session.getMovie();

            for (Seat seat : session.getReservedSeats()) {
                for (Ticket ticket : tickets) {
                    if (ticket.getSession().equals(session) && ticket.getSeat().equals(seat)) {
                        String type = ticket.getTicketType();
                        movieTypeCounts.putIfAbsent(movieTitle, new HashMap<>());
                        Map<String, Integer> typeMap = movieTypeCounts.get(movieTitle);
                        typeMap.put(type, typeMap.getOrDefault(type, 0) + 1);
                        break;
                    }
                }
            }
        }

        List<Map.Entry<String, String>> topList = new ArrayList<>();
        Map<String, Integer> typeCounts = new HashMap<>();

        for (var entry : movieTypeCounts.entrySet()) {
            String movie = entry.getKey();
            Map<String, Integer> typeMap = entry.getValue();

            String maxType = Collections.max(typeMap.entrySet(), Map.Entry.comparingByValue()).getKey();
            int count = typeMap.get(maxType);
            topList.add(Map.entry(movie, maxType));
            typeCounts.put(movie, count);
        }

        topList.sort((a, b) -> typeCounts.get(b.getKey()) - typeCounts.get(a.getKey()));

        DefaultTableModel model = (DefaultTableModel) tipoBilhetesTable.getModel();
        for (int i = 0; i < Math.min(10, topList.size()); i++) {
            var entry = topList.get(i);
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
    }
}
