import javax.swing.*;
import java.awt.*;

public class AddMovie extends JFrame {
    private JTextField titleField;
    private JTextField genreField;
    private JTextField countryField;
    private JTextField directorField;
    private JTextField studioField;
    private JTextField languageField;
    private JTextField subtitlesField;
    private JTextField ageField;
    private JTextField durationField;
    private JTextField yearField;

    private JButton confirmButton;
    private JButton cancelButton;

    public AddMovie(String title) throws HeadlessException {
        setTitle(title);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(11, 2, 5, 5));

        // Adiciona ao painel
        formPanel.add(new JLabel("Título:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Género:"));
        formPanel.add(genreField);
        formPanel.add(new JLabel("País de origem:"));
        formPanel.add(countryField);
        formPanel.add(new JLabel("Realizador:"));
        formPanel.add(directorField);
        formPanel.add(new JLabel("Produtora:"));
        formPanel.add(studioField);
        formPanel.add(new JLabel("Idioma:"));
        formPanel.add(languageField);
        formPanel.add(new JLabel("Legendas:"));
        formPanel.add(subtitlesField);
        formPanel.add(new JLabel("Classificação etária:"));
        formPanel.add(ageField);
        formPanel.add(new JLabel("Duração (min):"));
        formPanel.add(durationField);
        formPanel.add(new JLabel("Ano:"));
        formPanel.add(yearField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ação: confirmar
        confirmButton.addActionListener(e -> {
            try {
                String movieTitle = titleField.getText();
                String genre = genreField.getText();
                String country = countryField.getText();
                String director = directorField.getText();
                String studio = studioField.getText();
                String language = languageField.getText();
                String subtitles = subtitlesField.getText();
                int age = Integer.parseInt(ageField.getText());
                int duration = Integer.parseInt(durationField.getText());
                int year = Integer.parseInt(yearField.getText());

                int nextId = AppData.getInstance().getMovies().size() + 1;

                Movie movie = new Movie(nextId, movieTitle, genre, country, director, studio, language, subtitles, age, duration, year);
                AppData.getInstance().getMovies().add(movie);
                AppData.getInstance().saveDataMovies();

                JOptionPane.showMessageDialog(this, "Filme adicionado com sucesso!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verifica os campos numéricos (idade, duração, ano).", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dispose());
    }
}