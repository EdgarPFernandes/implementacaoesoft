import javax.swing.*;
import java.awt.*;

public class ConfigurarSala extends JFrame {
    private JPanel mainPanel;

    private Sala sala;
    private JTextField nomeField;
    private JTextField larguraField;
    private JTextField comprimentoField;
    private JTextField somField;
    private JTextField tipoSalaField;
    private JTextField tipoLugaresField;
    private JTextField especialField;
    private JCheckBox ativaCheck;
    private JButton guardarButton;
    private JButton cancelarButton;

    public ConfigurarSala(Sala sala) {
        this.sala = sala;

        setTitle("Configurar Sala");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));

        nomeField = new JTextField(sala.getNome());
        larguraField = new JTextField(String.valueOf(sala.getLargura()));
        comprimentoField = new JTextField(String.valueOf(sala.getComprimento()));
        somField = new JTextField(sala.getTipoSom());
        tipoSalaField = new JTextField(sala.getTipoSala());
        tipoLugaresField = new JTextField(sala.getTipoLugares());
        especialField = new JTextField(sala.getCaracEspecial());
        ativaCheck = new JCheckBox("Sala Ativa", sala.isAtiva());

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Largura:"));
        formPanel.add(larguraField);
        formPanel.add(new JLabel("Comprimento:"));
        formPanel.add(comprimentoField);
        formPanel.add(new JLabel("Tipo de Som:"));
        formPanel.add(somField);
        formPanel.add(new JLabel("Tipo de Sala:"));
        formPanel.add(tipoSalaField);
        formPanel.add(new JLabel("Tipo de Lugares:"));
        formPanel.add(tipoLugaresField);
        formPanel.add(new JLabel("Características Especiais:"));
        formPanel.add(especialField);
        formPanel.add(new JLabel("Estado:"));
        formPanel.add(ativaCheck);

        // Botões
        JPanel buttonPanel = new JPanel();
        guardarButton = new JButton("Guardar");
        cancelarButton = new JButton("Cancelar");

        buttonPanel.add(guardarButton);
        buttonPanel.add(cancelarButton);

        // Adicionar tudo à janela
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ação: guardar alterações
        guardarButton.addActionListener(e -> {
            try {
                sala.setNome(nomeField.getText().trim());
                sala.setLargura(Integer.parseInt(larguraField.getText().trim()));
                sala.setComprimento(Integer.parseInt(comprimentoField.getText().trim()));
                sala.setTipoSom(somField.getText().trim());
                sala.setTipoSala(tipoSalaField.getText().trim());
                sala.setTipoLugares(tipoLugaresField.getText().trim());
                sala.setCaracEspecial(especialField.getText().trim());
                sala.setAtiva(ativaCheck.isSelected());

                AppData.getInstance().saveDataSalas();
                JOptionPane.showMessageDialog(this, "Sala atualizada com sucesso!");
                new Salas("Gestão de Salas").setVisible(true);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Capacidade deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dispose());
    }
}

