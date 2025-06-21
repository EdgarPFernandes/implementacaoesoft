import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Sessoes extends JFrame {
    private JPanel mainPanel;

    private JButton addSessions;
    private JButton back;

    private JTable tableSessions;
    private JButton btnFilmes;
    private JButton btnSessoes;
    private JButton btnBar;
    private JButton btnBilheteira;
    private JButton btnConsulta;
    private JButton btnSalas;
    private JPanel navbarPanel;

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
        String[] colunas = {"Filme", "Data", "Hora", "Sala", "\uD83D\uDDD1\uFE0F"};
        DefaultTableModel model = new DefaultTableModel(null, colunas){

            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        List<Session> sessions = AppData.getInstance().getSessions();

        for (Session s : sessions) {
            model.addRow(new Object[]{
                    s.getMovie(),
                    s.getDate(),
                    s.getHora(),
                    s.getSala(),
                    "\uD83D\uDDD1\uFE0F"
            });
        }

        tableSessions.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = tableSessions.rowAtPoint(e.getPoint());
                int col = tableSessions.columnAtPoint(e.getPoint());

                if (col == 4) { // Coluna do emoji 🗑️
                    int confirm = JOptionPane.showConfirmDialog(
                            Sessoes.this,
                            "Tens a certeza que queres remover esta sessão?",
                            "Remover Sessão",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        String filme = tableSessions.getValueAt(row, 0).toString();
                        String data = tableSessions.getValueAt(row, 1).toString();
                        String hora = tableSessions.getValueAt(row, 2).toString();
                        String sala = tableSessions.getValueAt(row, 3).toString();

                        List<Session> sessions = AppData.getInstance().getSessions();
                        sessions.removeIf(s ->
                                s.getMovie().equals(filme) &&
                                        s.getDate().equals(data) &&
                                        s.getHora().equals(hora) &&
                                        s.getSala().equals(sala)
                        );

                        AppData.getInstance().saveDataSessions();
                        loadSessionsIntoTable();
                    }
                }
            }
        });

        setSize(1600, 700);
        tableSessions.setModel(model);
        tableSessions.setRowHeight(30);
    }
}
