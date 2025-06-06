package sistemapoo.view;

import java.awt.*;
import javax.swing.*;

public class UsuarioFrame extends JFrame {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField pwdSenha;
    private JPasswordField pwdConfirmarSenha;
    private JButton btnSalvar;
    private JButton btnVoltar; // Alterado para Voltar

    public UsuarioFrame() {
        configurarJanela();
        initComponentes();
    }

    private void configurarJanela() {
        setTitle("Cadastro de Usuário");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponentes() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Cadastro de Usuário", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(46, 125, 50));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        Font fonteLabel = new Font("Segoe UI", Font.PLAIN, 16);
        Font fonteCampo = new Font("Segoe UI", Font.PLAIN, 14);

        formPanel.add(criarLabel("Nome:", fonteLabel));
        txtNome = new JTextField();
        txtNome.setFont(fonteCampo);
        formPanel.add(txtNome);

        formPanel.add(criarLabel("Email:", fonteLabel));
        txtEmail = new JTextField();
        txtEmail.setFont(fonteCampo);
        formPanel.add(txtEmail);

        formPanel.add(criarLabel("Senha:", fonteLabel));
        pwdSenha = new JPasswordField();
        pwdSenha.setFont(fonteCampo);
        formPanel.add(pwdSenha);

        formPanel.add(criarLabel("Confirmar Senha:", fonteLabel));
        pwdConfirmarSenha = new JPasswordField();
        pwdConfirmarSenha.setFont(fonteCampo);
        formPanel.add(pwdConfirmarSenha);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        btnVoltar = criarBotao("Voltar", new Color(120, 120, 120)); // Alterado para Voltar
        btnSalvar = criarBotao("Salvar", new Color(46, 125, 50));
        
        buttonPanel.add(btnVoltar);
        buttonPanel.add(btnSalvar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        btnVoltar.addActionListener(e -> {
            new MainMenuFrame().setVisible(true); // Volta ao menu principal
            this.dispose();
        });
        
        btnSalvar.addActionListener(e -> {
            String senha = new String(pwdSenha.getPassword());
            String confirmar = new String(pwdConfirmarSenha.getPassword());
            
            if (!senha.equals(confirmar)) {
                JOptionPane.showMessageDialog(this, 
                    "As senhas não coincidem!", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            dispose();
        });

        add(mainPanel);
    }
    
    private JLabel criarLabel(String texto, Font fonte) {
        JLabel label = new JLabel(texto);
        label.setFont(fonte);
        return label;
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