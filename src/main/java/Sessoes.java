import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Sessoes extends JFrame {
    private JPanel mainPanel;
    private JPanel leftButtons;

    private JButton addSessions;
    private JButton back;

    private JTable tableSessions;
    private JPanel navbarPanel;
    private JButton btnFilmes;
    private JButton btnSessoes;
    private JButton btnBar;
    private JButton btnBilheteira;
    private JButton btnConsulta;
    private JButton btnSalas;

    public Sessoes(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        loadSessionsIntoTable();

        back.addActionListener(e -> {
            new Filmes("Filmes").setVisible(true);  // ou outro form
            dispose();
        });

        addSessions.addActionListener(e -> {
            // Aqui podes abrir um form para adicionar nova sessão
            new AddSessao("Adicionar Sessão").setVisible(true);  // ou outro form
        });

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
    }

    private void loadSessionsIntoTable() {
        String[] colunas = {"Filme", "Data", "Hora", "Sala"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        List<Session> sessions = AppData.getInstance().getSessions();

        for (Session s : sessions) {
            model.addRow(new Object[]{
                    s.getMovie(),
                    s.getDate(),
                    s.getHora(),
                    s.getSala()
            });
        }

        tableSessions.setModel(model);
    }
}
