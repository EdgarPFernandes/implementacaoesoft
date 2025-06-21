import javax.swing.*;
import java.awt.*;

public class DetailsSala extends JFrame {
    private Sala sala;
    public JPanel mainPanel;

    private JLabel nomeLabel;
    private JLabel larguraLabel;
    private JLabel comprimentoLabel;
    private JLabel somLabel;
    private JLabel tipoSalaLabel;
    private JLabel tipoLugaresLabel;
    private JLabel especialLabel;
    private JLabel ativaLabel;

    private JButton editarButton;
    private JButton voltarButton;

    public DetailsSala(Sala sala) {
        this.sala = sala;

        setTitle("Detalhes da Sala");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels
        nomeLabel = new JLabel("Nome: " + sala.getNome());
        larguraLabel = new JLabel("Largura: " + sala.getLargura());
        comprimentoLabel = new JLabel("Comprimento: " + sala.getComprimento());
        somLabel = new JLabel("Tipo de Som: " + sala.getTipoSom());
        tipoSalaLabel = new JLabel("Tipo de Sala: " + sala.getTipoSala());
        tipoLugaresLabel = new JLabel("Tipo de Lugares: " + sala.getTipoLugares());
        especialLabel = new JLabel("Características Especiais: " + sala.getCaracEspecial());
        ativaLabel = new JLabel("Estado: " + (sala.isAtiva() ? "Ativa" : "Inativa"));

        // Botões
        editarButton = new JButton("Editar Sala");
        voltarButton = new JButton("Voltar atrás");

        // Painel de conteúdo
        JPanel infoPanel = new JPanel(new GridLayout(8, 1, 5, 5));


        infoPanel.add(nomeLabel);
        infoPanel.add(larguraLabel);
        infoPanel.add(comprimentoLabel);
        infoPanel.add(somLabel);
        infoPanel.add(tipoSalaLabel);
        infoPanel.add(tipoLugaresLabel);
        infoPanel.add(especialLabel);
        infoPanel.add(ativaLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editarButton);
        buttonPanel.add(voltarButton);

        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Ação: editar
        editarButton.addActionListener(e -> {
            new ConfigurarSala(sala).setVisible(true);
            dispose(); // Fecha esta janela
        });

        // Ação: voltar
        voltarButton.addActionListener(e -> dispose());
    }
}
