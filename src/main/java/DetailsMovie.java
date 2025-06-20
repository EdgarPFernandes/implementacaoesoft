import javax.swing.*;

public class DetailsMovie extends JFrame {
    private JPanel mainPanel;

    public DetailsMovie(Movie movie) {
        setTitle("Detalhes do Filme: " + movie.getTitle());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText(
                "Título: " + movie.getTitle() + "\n" +
                        "Género: " + movie.getGenre() + "\n" +
                        "País: " + movie.getCountry() + "\n" +
                        "Realizador: " + movie.getDirector() + "\n" +
                        "Estudio: " + movie.getStudio() + "\n" +
                        "Idioma: " + movie.getLanguages() + "\n" +
                        "Legendas: " + movie.getSubtitles() + "\n" +
                        "Idade: " + movie.getAge() + "+\n" +
                        "Duração: " + movie.getDuration() + " min\n" +
                        "Ano: " + movie.getYear() + "\n" +
                        "ID: " + movie.getId()
        );

        add(new JScrollPane(area));
    }
}
