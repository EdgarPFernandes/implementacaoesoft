import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Bilheteira extends JFrame {
    private JPanel Header;
    private JPanel leftButtons;
    private JButton barButton;
    private JPanel middlePanel;
    private JButton button11;
    private JButton button14;
    private JButton button44;
    private JButton button41;
    private JButton button34;
    private JButton button31;
    private JButton button24;
    private JButton button21;
    private JButton button13;
    private JButton button23;
    private JButton button33;
    private JButton button43;
    private JButton button12;
    private JButton button22;
    private JButton button32;
    private JButton button42;
    private JButton nextPageButton;
    private JButton previousPageButton;
    private JPanel cartPanel;
    private JPanel mainPanel;
    private JLabel carrinhoLbl;
    private JPanel cartInfo;
    private JList list1;
    private JLabel totalLbl;
    private JTextField totalValue;
    private JButton cancelButton;
    private JButton confirmButton;
    private JComboBox movieFilter;

    private Cart cart;
    private JButton[][] movieButtons = new JButton[4][4];
    private int currentPage = 0;
    private final int ITEMS_PER_PAGE = 16;
    private Movie selectedMovie = null;
    private List<Session> currentSessions = new ArrayList<>();



    public Bilheteira(String title) {
        super(title);
        this.cart = new Cart();
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        // DEBUG: Verify loaded data
        System.out.println("\n=== LOADED DATA VERIFICATION ===");
        System.out.println("Movies:");
        AppData.getInstance().getMovies().forEach(m ->
                System.out.println("- " + m.getTitle())
        );
        System.out.println("\nSessions:");
        AppData.getInstance().getSessions().forEach(s ->
                System.out.println("- " + s.getMovie() + " at " + s.getHora())
        );

        setupButtons();
        updateMovieButtons();
    }

    public Bilheteira(String title, Cart cart) {
        super(title);
        this.cart = new Cart();
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        // DEBUG: Verify loaded data
        System.out.println("\n=== LOADED DATA VERIFICATION ===");
        System.out.println("Movies:");
        AppData.getInstance().getMovies().forEach(m ->
                System.out.println("- " + m.getTitle())
        );
        System.out.println("\nSessions:");
        AppData.getInstance().getSessions().forEach(s ->
                System.out.println("- " + s.getMovie() + " at " + s.getHora())
        );

        setupButtons();
        updateMovieButtons();
    }

    private void setupButtons() {
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

            // In the button action listener:
            flatButtons[i].addActionListener(e -> {
                if (selectedMovie == null) {
                    // Movie selected
                    int movieIndex = currentPage * ITEMS_PER_PAGE + index;
                    List<Movie> movies = AppData.getInstance().getMovies();
                    if (movieIndex < movies.size()) {
                        selectedMovie = movies.get(movieIndex);

                        // DEBUG: Print all available sessions
                        System.out.println("\n=== DEBUG INFORMATION ===");
                        System.out.println("All sessions in system:");
                        AppData.getInstance().getSessions().forEach(s ->
                                System.out.println("Movie: '" + s.getMovie() + "' | Time: " + s.getHora() + " | Sala: " + s.getSala())
                        );

                        // Filter sessions
                        currentSessions = AppData.getInstance().getSessions().stream()
                                .filter(s -> {
                                    boolean match = s.getMovie() != null &&
                                            s.getMovie().equals(selectedMovie.getTitle());
                                    System.out.println("Comparing '" + s.getMovie() + "' with '" +
                                            selectedMovie.getTitle() + "': " + match);
                                    return match;
                                })
                                .toList();

                        System.out.println("Found " + currentSessions.size() + " matching sessions");

                        currentPage = 0;
                        updateSessionButtons();

                        // DEBUG: Force UI update
                        revalidate();
                        repaint();
                    }
                } else {
                    // Session selected
                    int sessionIndex = currentPage * ITEMS_PER_PAGE + index;
                    if (sessionIndex < currentSessions.size()) {
                        Session session = currentSessions.get(sessionIndex);
                        JOptionPane.showMessageDialog(this,
                                "Sessão escolhida:\n" +
                                        "Sala: " + session.getSala() + "\n" +
                                        "Hora: " + session.getHora());

                        selectedMovie = null;
                        currentPage = 0;
                        updateMovieButtons();
                    }
                }
            });
        }
    }


    private void updateMovieButtons() {
        List<Movie> movies = AppData.getInstance().getMovies();
        int start = currentPage * ITEMS_PER_PAGE;

        for (int i = 0; i < 16; i++) {
            int index = start + i;
            int row = i / 4;
            int col = i % 4;
            JButton button = movieButtons[row][col];

            if (index < movies.size()) {
                button.setText(movies.get(index).getTitle());
                button.setEnabled(true);
            } else {
                button.setText("");
                button.setEnabled(false);
            }
        }

        // Force UI update
        middlePanel.revalidate();
        middlePanel.repaint();
    }

    private void updateSessionButtons() {
        // Clear all buttons first
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                movieButtons[row][col].setText("");
                movieButtons[row][col].setEnabled(false);
            }
        }

        // Update with session information
        for (int i = 0; i < currentSessions.size() && i < 16; i++) {
            int row = i / 4;
            int col = i % 4;
            Session s = currentSessions.get(i);
            movieButtons[row][col].setText("<html>" + s.getHora() + "<br/>Sala " + s.getSala() + "</html>");
            movieButtons[row][col].setEnabled(true);
        }

        // Force UI update
        // Force UI update
        middlePanel.revalidate();
        middlePanel.repaint();
    }






    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bilheteira("Bilheteira").setVisible(true));
    }

}
