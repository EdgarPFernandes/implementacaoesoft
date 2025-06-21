import javax.swing.*;
import java.awt.*;

public class ConfigurarMovie extends JFrame {
    private JPanel mainPanel;
    private Movie movie;

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

    private JButton guardarButton;
    private JButton cancelarButton;

    public ConfigurarMovie(Movie movie) {
        this.movie = movie;

        setTitle("Editar Filme");
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));

        // Campos preenchidos com os dados atuais
        titleField = new JTextField(movie.getTitle());
        genreField = new JTextField(movie.getGenre());
        countryField = new JTextField(movie.getCountry());
        directorField = new JTextField(movie.getDirector());
        studioField = new JTextField(movie.getStudio());
        languageField = new JTextField(movie.getLanguages());
        subtitlesField = new JTextField(movie.getSubtitles());
        ageField = new JTextField(String.valueOf(movie.getAge()));
        durationField = new JTextField(String.valueOf(movie.getDuration()));
        yearField = new JTextField(String.valueOf(movie.getYear()));

        // Adicionar campos ao formulário
        formPanel.add(new JLabel("Título:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Género:"));
        formPanel.add(genreField);
        formPanel.add(new JLabel("País de Origem:"));
        formPanel.add(countryField);
        formPanel.add(new JLabel("Realizador:"));
        formPanel.add(directorField);
        formPanel.add(new JLabel("Estúdio:"));
        formPanel.add(studioField);
        formPanel.add(new JLabel("Idioma:"));
        formPanel.add(languageField);
        formPanel.add(new JLabel("Legendas:"));
        formPanel.add(subtitlesField);
        formPanel.add(new JLabel("Classificação Etária:"));
        formPanel.add(ageField);
        formPanel.add(new JLabel("Duração (min):"));
        formPanel.add(durationField);
        formPanel.add(new JLabel("Ano:"));
        formPanel.add(yearField);

        // Botões
        guardarButton = new JButton("Guardar");
        cancelarButton = new JButton("Cancelar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guardarButton);
        buttonPanel.add(cancelarButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ação: guardar alterações
        guardarButton.addActionListener(e -> {
            try {
                movie.setMovieName(titleField.getText().trim());
                movie.setMovieGenre(genreField.getText().trim());
                movie.setOrigin(countryField.getText().trim());
                movie.setDirector(directorField.getText().trim());
                movie.setStudio(studioField.getText().trim());
                movie.setLanguages(languageField.getText().trim());
                movie.setSubtitles(subtitlesField.getText().trim());
                movie.setAge(Integer.parseInt(ageField.getText().trim()));
                movie.setDuration(Integer.parseInt(durationField.getText().trim()));
                movie.setMovieYear(Integer.parseInt(yearField.getText().trim()));

                AppData.getInstance().saveDataMovies();
                JOptionPane.showMessageDialog(this, "Filme atualizado com sucesso!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verifica os campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dispose());
    }
}
