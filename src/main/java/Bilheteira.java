import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private JButton btnFilmes;
    private JButton btnSessoes;
    private JButton btnBar;
    private JButton btnBilheteira;
    private JButton btnConsulta;
    private JButton btnSalas;

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

        cancelButton.addActionListener(e -> {
            cart.clear();
            updateCartDisplay();
        });

        confirmButton.addActionListener(e -> {
            if (cart.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Cart is empty",
                        "No Items",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                // Process payment
                double total = cart.getTotal();
                JOptionPane.showMessageDialog(this,
                        String.format("Total: %.2f€\nPayment processed!", total),
                        "Purchase Complete",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear cart
                cart.clear();
                updateCartDisplay();

                // Force refresh of seat display if needed
                if (selectedMovie != null) {
                    updateSessionButtons();
                }
            }
        });

        barButton.addActionListener(e -> {
            new Bar("Bar",cart).setVisible(true);
            dispose(); // closes Bar window
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

        setupButtons();
        updateMovieButtons();
    }

    public Bilheteira(String title, Cart cart) {
        super(title);
        this.cart = cart;
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

        cancelButton.addActionListener(e -> {
            cart.clear();
            updateCartDisplay();
        });

        confirmButton.addActionListener(e -> {
            if (cart.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Cart is empty",
                        "No Items",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                // Process payment
                double total = cart.getTotal();
                JOptionPane.showMessageDialog(this,
                        String.format("Total: %.2f€\nPayment processed!", total),
                        "Purchase Complete",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear cart
                cart.clear();
                updateCartDisplay();

                // Force refresh of seat display if needed
                if (selectedMovie != null) {
                    updateSessionButtons();
                }
            }
        });

        barButton.addActionListener(e -> {
            new Bar("Bar",cart).setVisible(true);
            dispose(); // closes Bar window
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

        setupButtons();
        updateMovieButtons();
        updateCartDisplay();
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
                if (currentSessions == null || currentSessions.isEmpty()) {
                    selectedMovie = null;
                }
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
                        currentSessions = new ArrayList<>(AppData.getInstance().getSessions().stream()
                                .filter(s -> {
                                    boolean match = s.getMovie() != null &&
                                            s.getMovie().equals(selectedMovie.getTitle());
                                    System.out.println("Comparing '" + s.getMovie() + "' with '" +
                                            selectedMovie.getTitle() + "': " + match);
                                    return match;
                                })
                                .toList());

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
                        showTicketTypeDialog(session); // This launches the new 3-option dialog
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


    private void showTicketTypeDialog(Session selectedSession) {
        JDialog dialog = new JDialog(this, "Select Ticket Type", true);
        dialog.setLayout(new GridLayout(3, 1, 10, 10));
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);

        JButton childBtn = new JButton("Criança: 5€");
        JButton adultBtn = new JButton("Adulto: 7€");
        JButton studentBtn = new JButton("Estudante: 6€");

        childBtn.addActionListener(e -> {
            dialog.dispose();
            showSeatSelection(selectedSession, "Criança", 5.0);
        });

        adultBtn.addActionListener(e -> {
            dialog.dispose();
            showSeatSelection(selectedSession, "Adulto", 7.0);
        });

        studentBtn.addActionListener(e -> {
            dialog.dispose();
            showSeatSelection(selectedSession, "Estudante", 6.0);
        });

        dialog.add(childBtn);
        dialog.add(adultBtn);
        dialog.add(studentBtn);
        dialog.setVisible(true);
    }

    private void showSeatSelection(Session session, String ticketType, double price) {
        Sala sala = findSalaByNumber(session.getSala());

        // Add validation for room dimensions
        int rows = Math.min(sala.getComprimento(), 8); // Limit to 8 rows max
        int cols = Math.min(sala.getLargura(), 10);    // Limit to 10 columns max

        JDialog dialog = new JDialog(this, "Select Seats - " + sala.getNome(), true);
        dialog.setLayout(new BorderLayout());

        // Screen representation
        JPanel screenPanel = new JPanel();
        screenPanel.add(new JLabel("TELA", JLabel.CENTER));
        screenPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        dialog.add(screenPanel, BorderLayout.NORTH);

        // Seat grid with scroll pane
        JPanel seatPanel = new JPanel(new GridLayout(0, cols, 5, 5)); // 0 means variable rows
        JScrollPane scrollPane = new JScrollPane(seatPanel);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        List<Seat> selectedSeats = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char rowChar = (char) ('A' + row);
                int seatNum = col + 1;
                Seat seat = new Seat(rowChar, seatNum);
                JButton seatBtn = new JButton(seat.getSeatCode());

                // Check if seat is reserved
                if (session.isSeatReserved(seat)) {
                    seatBtn.setBackground(Color.RED);
                    seatBtn.setEnabled(false); // Disable reserved seats
                } else {
                    seatBtn.setBackground(Color.GREEN);
                    seatBtn.setEnabled(true);
                }

                seatBtn.setOpaque(true);
                seatBtn.setBorderPainted(false);

                seatBtn.addActionListener(e -> {
                    if (seatBtn.getBackground() == Color.GREEN) {
                        seatBtn.setBackground(Color.YELLOW);
                        selectedSeats.add(seat);
                    } else if (seatBtn.getBackground() == Color.YELLOW) {
                        seatBtn.setBackground(Color.GREEN);
                        selectedSeats.remove(seat);
                    }
                });

                seatPanel.add(seatBtn);
            }
        }

        dialog.add(scrollPane, BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel();
        JButton confirmBtn = new JButton("Confirmar");
        confirmBtn.addActionListener(e -> {
            if (!selectedSeats.isEmpty()) {
                createTickets(selectedSeats, session, ticketType, price);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog,
                        "Selecione pelo menos um lugar",
                        "Nenhum lugar selecionado",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        controlPanel.add(confirmBtn);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private Sala findSalaByNumber(String salaNumber) {
        return AppData.getInstance().getSalas().stream()
                .filter(s -> s.getNome().equals(salaNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sala not found: " + salaNumber));
    }

    private void createTickets(List<Seat> seats, Session session, String ticketType, double price) {
        for (Seat seat : seats) {
            session.reserveSeat(seat);

            Ticket ticket = new Ticket(
                    String.format("%s (%s - %s %s)",
                            ticketType,
                            seat.getSeatCode(),
                            session.getSala(),
                            session.getHora()),
                    price,
                    seat,
                    session,
                    ticketType // Add the ticket type here
            );
            cart.addProduct(ticket);
        }

        // Reset state
        selectedMovie = null;
        currentSessions = new ArrayList<>();
        currentPage = 0;

        // Update UI
        updateMovieButtons();
        updateCartDisplay();

        JOptionPane.showMessageDialog(this,
                String.format("%d %s tickets added to cart", seats.size(), ticketType),
                "Tickets Added",
                JOptionPane.INFORMATION_MESSAGE);
    }


    // In Bilheteira.java - show each ticket separately
    private void updateCartDisplay() {
        DefaultListModel<String> model = new DefaultListModel<>();
        double total = 0.0;

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product p = entry.getKey();
            int quantity = entry.getValue();

            if (p instanceof Ticket) {
                // For tickets, show type and details
                Ticket ticket = (Ticket)p;
                for (int i = 0; i < quantity; i++) {
                    model.addElement(String.format("%s - %s (Seat: %s, Sala: %s)",
                            ticket.getTicketType(),
                            "€" + String.format("%.2f", ticket.getPrice()),
                            ticket.getSeat().getSeatCode(),
                            ticket.getSession().getSala()));
                    total += ticket.getPrice();
                }
            } else {
                // For other products (from Bar)
                double subtotal = p.getPrice() * quantity;
                model.addElement(quantity + "x " + p.getProductName() + " - €" + String.format("%.2f", subtotal));
                total += subtotal;
            }
        }

        list1.setModel(model);
        totalValue.setText("€" + String.format("%.2f", total));
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bilheteira("Bilheteira").setVisible(true));
    }

}
