import javax.swing.*;
import java.awt.*;

public class AddSala extends JFrame {
    private JPanel mainPanel;
    private JPanel buttonsPanel;
    private JPanel textPanel;
    private JPanel requisitosPanel;

    private JTextField nomeField;
    private JTextField capacidadeField;
    private JTextField somField;
    private JTextField salaField;
    private JTextField lugaresField;
    private JTextField especialField;

    private JButton confirmationButton;
    private JButton cancelButton;

    public AddSala(String title) {
        super(title);
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Criar campos


        // Criar botões

        // Criar e configurar painéis
        JPanel textPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        textPanel.add(new JLabel("Nome:"));
        textPanel.add(nomeField);
        textPanel.add(new JLabel("Capacidade:"));
        textPanel.add(capacidadeField);
        textPanel.add(new JLabel("Tipo de Som:"));
        textPanel.add(somField);
        textPanel.add(new JLabel("Tipo de Sala:"));
        textPanel.add(salaField);
        textPanel.add(new JLabel("Tipo de Lugares:"));
        textPanel.add(lugaresField);
        textPanel.add(new JLabel("Características Especiais:"));
        textPanel.add(especialField);

        JPanel requisitosPanel = new JPanel();
        requisitosPanel.add(confirmationButton);
        requisitosPanel.add(cancelButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(requisitosPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        // Ação: confirmar
        confirmationButton.addActionListener(e -> {
            try {
                String nome = nomeField.getText().trim();
                int capacidade = Integer.parseInt(capacidadeField.getText().trim());
                String tipoSom = somField.getText().trim();
                String tipoSala = salaField.getText().trim();
                String tipoLugares = lugaresField.getText().trim();
                String caracEspecial = especialField.getText().trim();

                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O nome da sala é obrigatório.");
                    return;
                }

                Sala nova = new Sala(nome, capacidade, tipoSom, tipoSala, tipoLugares, caracEspecial);
                AppData.getInstance().getSalas().add(nova);
                AppData.getInstance().saveDataSalas();

                JOptionPane.showMessageDialog(this, "Sala adicionada com sucesso!");
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Capacidade inválida. Insere um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação: cancelar
        cancelButton.addActionListener(e -> dispose());
    }
}
