package sistemapoo.view;

import java.awt.*;
import javax.swing.*;
import sistemapoo.controller.UsuarioController;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField pwdSenha;
    private JButton btnEntrar;
    private JButton btnCadastro;
    private JButton btnEsqueciSenha;

    public LoginFrame() {
        configurarJanela();
        initComponentes();
    }

    private void configurarJanela() {
        setTitle("Bem vindo ao Sistema Financeiro! Faça seu Login");
        setSize(600, 450);
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponentes() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(245, 245, 245));

        JLabel lblTitulo = new JLabel("Login do Sistema", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(46, 125, 50));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelUsuario.setBackground(new Color(245, 245, 245));
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUsuario = new JTextField(20);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelUsuario.add(lblUsuario);
        panelUsuario.add(txtUsuario);
        formPanel.add(panelUsuario);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel panelSenha = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSenha.setBackground(new Color(245, 245, 245));
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        pwdSenha = new JPasswordField(20);
        pwdSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelSenha.add(lblSenha);
        panelSenha.add(pwdSenha);
        formPanel.add(panelSenha);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        btnEsqueciSenha = new JButton("Esqueci minha senha");
        btnEsqueciSenha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnEsqueciSenha.setForeground(new Color(46, 125, 50));
        btnEsqueciSenha.setContentAreaFilled(false);
        btnEsqueciSenha.setBorderPainted(false);
        btnEsqueciSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(btnEsqueciSenha);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        buttonPanel.setBackground(new Color(245, 245, 245));

        btnCadastro = criarBotao("Cadastrar", new Color(46, 125, 50));
        btnEntrar = criarBotao("Entrar", new Color(33, 97, 140));

        buttonPanel.add(btnCadastro);
        buttonPanel.add(btnEntrar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        btnEntrar.addActionListener(e -> {
            // Validação removida - entra direto no menu principal
            new MainMenuFrame().setVisible(true);
            this.dispose();
        });

        btnCadastro.addActionListener(e -> {
            new UsuarioFrame().setVisible(true);
            this.dispose();
        });

        btnEsqueciSenha.addActionListener(e -> {
            new RecuperarSenhaFrame().setVisible(true);
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

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
