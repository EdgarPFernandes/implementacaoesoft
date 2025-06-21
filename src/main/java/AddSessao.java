import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddSessao extends JFrame {
    private JPanel mainPanel;
    private JComboBox<Movie> movieComboBox;
    private JComboBox<Sala> salaComboBox;

    private JTextField dateField;
    private JTextField timeField;

    private JButton confirmButton;
    private JButton cancelButton;

    public AddSessao(String title) throws HeadlessException {
        super(title);
        setContentPane(mainPanel);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Preencher o combo box com filmes existentes
        List<Movie> filmes = AppData.getInstance().getMovies();
        for (Movie m : filmes) {
            movieComboBox.addItem(m);
        }

        // Preencher o combo box de salas ativas
        List<Sala> salas = AppData.getInstance().getSalas();
        boolean foundAtiva = false;
        for (Sala sa : salas) {
            if (sa.isAtiva()) {
                salaComboBox.addItem(sa);
                foundAtiva = true;
            }
        }

        if (!foundAtiva) {
            JOptionPane.showMessageDialog(this, "Não há salas ativas disponíveis. Cria ou ativa uma sala primeiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        confirmButton.addActionListener(e -> {
            Movie filme = (Movie) movieComboBox.getSelectedItem();
            String data = dateField.getText();
            String hora = timeField.getText();
            Sala room = (Sala) salaComboBox.getSelectedItem();

            if (filme == null || data.isEmpty() || hora.isEmpty() || room == null) {
                JOptionPane.showMessageDialog(this, "Preenche todos os campos.");
                return;
            }

            Session nova = new Session(filme, data, hora, room.getNome());
            AppData.getInstance().getSessions().add(nova);
            AppData.getInstance().saveDataSessions();
            JOptionPane.showMessageDialog(this, "Sessão adicionada!");
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());
    }
}
