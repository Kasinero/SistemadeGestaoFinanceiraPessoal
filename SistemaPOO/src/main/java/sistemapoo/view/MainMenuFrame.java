package sistemapoo.view;

import java.awt.*;
import javax.swing.*;

public class MainMenuFrame extends JFrame {

    private JButton btnTransacoes;
    private JButton btnHistorico;
    private JButton btnResumo;
    private JButton btnCategorias;
    private JButton 

    public MainMenuFrame() {
        configurarJanela();
        initComponentes();
    }

    private void configurarJanela() {
        setTitle("Sistema Financeiro - Menu Principal");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponentes() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(46, 125, 50));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        // Layout 2x2 para os 4 botões principais
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        centerPanel.setBackground(Color.WHITE);

        btnTransacoes = criarBotaoMenu("Transações", "Adicionar receitas/despesas");
        btnHistorico = criarBotaoMenu("Histórico", "Consultar transações");
        btnResumo = criarBotaoMenu("Resumo", "Ver resumo financeiro");
        btnCategorias = criarBotaoMenu("Categorias", "Gerenciar categorias");
        
        centerPanel.add(btnTransacoes);
        centerPanel.add(btnHistorico);
        centerPanel.add(btnResumo);
        centerPanel.add(btnCategorias);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Painel para o botão Sair
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        southPanel.setBackground(Color.WHITE);
        btnSair = criarBotaoMenu("Sair", "Encerrar sessão");
        southPanel.add(btnSair);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Ações
        btnTransacoes.addActionListener(e -> {
            new TransacaoFrame().setVisible(true);
            this.dispose();
        });
        
        btnHistorico.addActionListener(e -> {
            new HistoricoFrame().setVisible(true);
            this.dispose();
        });
        
        btnResumo.addActionListener(e -> {
            new ResumoFrame().setVisible(true);
            this.dispose();
        });
        
        btnCategorias.addActionListener(e -> {
            new CategoriaFrame().setVisible(true);
            this.dispose();
        });
        
        btnSair.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        add(mainPanel);
    }
    
    private JButton criarBotaoMenu(String titulo, String tooltip) {
        JButton botao = new JButton("<html><center><b>" + titulo + "</b></center></html>");
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        botao.setToolTipText(tooltip);
        botao.setBackground(new Color(240, 240, 240));
        botao.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        return botao;
    }
}
