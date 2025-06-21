import javax.swing.*;
import java.awt.*;

public class DetailsMovie extends JFrame {
    private Movie movie;
    private JPanel mainPanel;

    private JLabel titleLabel;
    private JLabel genreLabel;
    private JLabel countryLabel;
    private JLabel directorLabel;
    private JLabel studioLabel;
    private JLabel languageLabel;
    private JLabel subtitlesLabel;
    private JLabel ageLabel;
    private JLabel durationLabel;
    private JLabel yearLabel;
    private JLabel idLabel;

    private JButton voltarButton;
    private JButton configurarButton;

    public DetailsMovie(Movie movie) {
        this.movie = movie;

        setTitle("Detalhes do Filme");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels
        titleLabel = new JLabel("Título: " + movie.getTitle());
        genreLabel = new JLabel("Género: " + movie.getGenre());
        countryLabel = new JLabel("País: " + movie.getCountry());
        directorLabel = new JLabel("Realizador: " + movie.getDirector());
        studioLabel = new JLabel("Estúdio: " + movie.getStudio());
        languageLabel = new JLabel("Idioma: " + movie.getLanguages());
        subtitlesLabel = new JLabel("Legendas: " + movie.getSubtitles());
        ageLabel = new JLabel("Idade: " + movie.getAge() + "+");
        durationLabel = new JLabel("Duração: " + movie.getDuration() + " min");
        yearLabel = new JLabel("Ano: " + movie.getYear());
        idLabel = new JLabel("ID: " + movie.getId());

        // Painel com grid para detalhes
        JPanel infoPanel = new JPanel(new GridLayout(11, 1, 5, 5));
        infoPanel.add(titleLabel);
        infoPanel.add(genreLabel);
        infoPanel.add(countryLabel);
        infoPanel.add(directorLabel);
        infoPanel.add(studioLabel);
        infoPanel.add(languageLabel);
        infoPanel.add(subtitlesLabel);
        infoPanel.add(ageLabel);
        infoPanel.add(durationLabel);
        infoPanel.add(yearLabel);
        infoPanel.add(idLabel);

        //Botão Configurar
        configurarButton = new JButton("Configurar");
        configurarButton.addActionListener(e -> {
            new ConfigurarMovie(movie).setVisible(true);
            dispose();
        });

        // Botão voltar
        voltarButton = new JButton("Voltar atrás");
        voltarButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(voltarButton);
        buttonPanel.add(configurarButton);

        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
