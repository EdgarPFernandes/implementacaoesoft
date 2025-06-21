import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class TipoSalas extends JFrame {
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
    private JTable tipoSalasTable;

    public TipoSalas(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        tipoSalasTable.setModel(new DefaultTableModel(
                new Object[]{"Nome Sala", "Quantidade de Bilhetes Vendidos"}, 0
        ));

        populateTipoSalasTable();

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
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void populateTipoSalasTable() {
        Map<String, Integer> salaMap = new HashMap<>();
        for (Ticket ticket : AppData.getInstance().getTicketType()) {
            String sala = ticket.getSession().getSala();
            salaMap.put(sala, salaMap.getOrDefault(sala, 0) + 1);
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(salaMap.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        DefaultTableModel model = (DefaultTableModel) tipoSalasTable.getModel();
        for (int i = 0; i < Math.min(10, sorted.size()); i++) {
            model.addRow(new Object[]{sorted.get(i).getKey(), sorted.get(i).getValue()});
        }
    }
}
