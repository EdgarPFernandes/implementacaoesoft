import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddSessao extends JFrame {
    private JPanel mainPanel;
    private JComboBox<Movie> movieComboBox;

    private JTextField dateField;
    private JTextField timeField;
    private JTextField roomField;

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

        confirmButton.addActionListener(e -> {
            Movie filme = (Movie) movieComboBox.getSelectedItem();
            String data = dateField.getText();
            String hora = timeField.getText();
            String sala = roomField.getText();

            if (filme == null || data.isEmpty() || hora.isEmpty() || sala.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preenche todos os campos.");
                return;
            }

            Session nova = new Session(filme, data, hora, sala);
            AppData.getInstance().getSessions().add(nova);
            AppData.getInstance().saveDataSessions();
            JOptionPane.showMessageDialog(this, "Sessão adicionada!");
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());
    }
}
