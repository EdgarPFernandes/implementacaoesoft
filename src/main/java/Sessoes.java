import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Sessoes extends JFrame {
    private JPanel mainPanel;
    private JPanel Header;
    private JPanel leftButtons;

    private JButton addSessions;
    private JButton back;

    private JTable tableSessions;

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
