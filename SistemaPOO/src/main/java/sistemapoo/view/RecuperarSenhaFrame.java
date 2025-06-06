package sistemapoo.view;

import java.awt.*;
import javax.swing.*;

public class RecuperarSenhaFrame extends JFrame {

    private JTextField txtEmail;
    private JButton btnEnviar;
    private JButton btnCancelar;

    public RecuperarSenhaFrame() {
        configurarJanela();
        initComponentes();
    }

    private void configurarJanela() {
        setTitle("Recuperação de Senha");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponentes() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(245, 245, 245));
        
        JLabel lblTitulo = new JLabel("Recuperar Senha", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(46, 125, 50));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        JPanel panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEmail.setBackground(new Color(245, 245, 245));
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtEmail = new JTextField(20);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelEmail.add(lblEmail);
        panelEmail.add(txtEmail);
        formPanel.add(panelEmail);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));
        
        btnCancelar = criarBotao("Cancelar", new Color(120, 120, 120));
        btnEnviar = criarBotao("Enviar", new Color(46, 125, 50));
        
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnEnviar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Ações
        btnCancelar.addActionListener(e -> dispose());
        btnEnviar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Instruções enviadas para: " + txtEmail.getText(),
                "Email Enviado", 
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
        
        add(mainPanel);
    }
    
    private JButton criarBotao(String texto, Color corFundo) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(corFundo);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(120, 40));
        return botao;
    }
}