import javax.swing.*;
import java.awt.*;
import java.util.List;
//
public class Filmes extends JFrame {
    private JPanel mainPanel;
    private JPanel Header;
    private JPanel leftButtons;
    private JPanel middlePanel;

    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button31;
    private JButton button32;
    private JButton button33;
    private JButton button34;
    private JButton button41;
    private JButton button42;
    private JButton button43;
    private JButton button44;

    private JButton add_movie;
    private JButton nextPageButton;
    private JButton previousPageButton;
    private JButton viewSessions; // botão para ver sessões

    private JButton[][] movieButtons = new JButton[4][4];
    private int currentPage = 0;
    private final int ITEMS_PER_PAGE = 16;

    public Filmes(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        // Setup movie button grid
        setupMovieButtons();

        // Show first page of movies
        updateButtonLabels();

        // Botão: página anterior
        previousPageButton.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                updateButtonLabels();
            }
        });

        // Botão: próxima página
        nextPageButton.addActionListener(e -> {
            int maxPages = (int) Math.ceil((double) getMovies().size() / ITEMS_PER_PAGE);
            if (currentPage < maxPages - 1) {
                currentPage++;
                updateButtonLabels();
            }
        });

        // Botão: Ver Sessões
        viewSessions.addActionListener(e -> {
            new Sessoes("Sessões").setVisible(true);
            dispose(); // fecha janela atual
        });

        add_movie.addActionListener(e -> {
            new AddMovie("Adicionar Filme").setVisible(true);
        });
    }

    private List<Movie> getMovies() {
        return AppData.getInstance().getMovies();
    }

    private void setupMovieButtons() {
        JButton[] flatButtons = {
                button11, button12, button13, button14,
                button21, button22, button23, button24,
                button31, button32, button33, button34,
                button41, button42, button43, button44
        };

        for (int i = 0; i < flatButtons.length; i++) {
            int row = i / 4;
            int col = i % 4;
            movieButtons[row][col] = flatButtons[i];

            final int index = i;

            flatButtons[i].addActionListener(e -> {
                int movieIndex = currentPage * ITEMS_PER_PAGE + index;
                List<Movie> movies = getMovies();

                if (movieIndex < movies.size()) {
                    Movie selected = movies.get(movieIndex);
                    new MovieDetails(selected).setVisible(true);
                }
            });
        }
    }

    private void updateButtonLabels() {
        List<Movie> movies = getMovies();
        int start = currentPage * ITEMS_PER_PAGE;

        for (int i = 0; i < 16; i++) {
            int index = start + i;
            int row = i / 4;
            int col = i % 4;
            JButton button = movieButtons[row][col];

            if (index < movies.size()) {
                Movie m = movies.get(index);
                button.setText("<html><center>" + m.getTitle() + "<br>" + m.getGenre() + "</center></html>");
                button.setEnabled(true);
            } else {
                button.setText("");
                button.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Filmes("Filmes").setVisible(true));
    }
}
