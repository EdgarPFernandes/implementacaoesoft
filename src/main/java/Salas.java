import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Salas extends JFrame {
    private JPanel mainPanel;
    private JPanel middlePanel;
    private JPanel navBar;

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

    private JButton nextPageButton;
    private JButton previousPageButton;
    private JButton addSalaButton;

    private JButton btnFilmes;
    private JButton btnSessoes;
    private JButton btnBar;
    private JButton btnBilheteira;
    private JButton btnConsulta;
    private JButton btnSalas;

    private JButton[][] salaButtons = new JButton[4][4];
    private final int ITEMS_PER_PAGE = 16;
    private int currentPage = 0;

    public Salas(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        setupButtons();
        updateButtonLabels();

        previousPageButton.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                updateButtonLabels();
            }
        });

        nextPageButton.addActionListener(e -> {
            int maxPages = (int) Math.ceil((double) getSalas().size() / ITEMS_PER_PAGE);
            if (currentPage < maxPages - 1) {
                currentPage++;
                updateButtonLabels();
            }
        });

        addSalaButton.addActionListener(e -> {
            new AddSala("Adicionar Sala").setVisible(true);
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

    private void setupButtons() {
        JButton[] flat = {
                button11, button12, button13, button14,
                button21, button22, button23, button24,
                button31, button32, button33, button34,
                button41, button42, button43, button44
        };

        for (int i = 0; i < flat.length; i++) {
            int row = i / 4;
            int col = i % 4;
            salaButtons[row][col] = flat[i];

            final int index = i;
            flat[i].addActionListener(e -> {
                int salaIndex = currentPage * ITEMS_PER_PAGE + index;
                List<Sala> salas = getSalas();
                if (salaIndex < salas.size()) {
                    Sala selected = salas.get(salaIndex);
                    new DetailsSala(selected).setVisible(true);
                    dispose();
                }
            });
        }
    }

    private void updateButtonLabels() {
        List<Sala> salas = getSalas();
        int start = currentPage * ITEMS_PER_PAGE;

        for (int i = 0; i < 16; i++) {
            int index = start + i;
            int row = i / 4;
            int col = i % 4;
            JButton btn = salaButtons[row][col];

            if (index < salas.size()) {
                Sala s = salas.get(index);
                btn.setText(s.getNome());
                btn.setEnabled(true);
            } else {
                btn.setText("");
                btn.setEnabled(false);
            }
        }
    }

    private List<Sala> getSalas() {
        return AppData.getInstance().getSalas();
    }
}