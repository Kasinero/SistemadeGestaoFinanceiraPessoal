package sistemapoo.view;

import java.awt.*;
import javax.swing.*;

public class ResumoFrame extends JFrame {

    private JLabel lblSaldo;
    private JLabel lblReceitas;
    private JLabel lblDespesas;
    private JComboBox<String> cmbPeriodo;
    private JButton btnAtualizar;
    private JButton btnVoltar;

    public ResumoFrame() {
        configurarJanela();
        initComponentes();
    }

    private void configurarJanela() {
        setTitle("Resumo Financeiro");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponentes() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Título
        JLabel lblTitulo = new JLabel("Resumo Financeiro", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(46, 125, 50));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        // Controles
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        controlPanel.setBackground(Color.WHITE);

        JLabel lblPeriodo = new JLabel("Período:");
        lblPeriodo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        controlPanel.add(lblPeriodo);

        cmbPeriodo = new JComboBox<>(new String[]{"Este mês", "Últimos 30 dias", "Últimos 90 dias"});
        cmbPeriodo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        controlPanel.add(cmbPeriodo);

        btnAtualizar = criarBotao("Atualizar", new Color(46, 125, 50));
        controlPanel.add(btnAtualizar);

        mainPanel.add(controlPanel, BorderLayout.NORTH);

        // Resumo
        JPanel resumoPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        resumoPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        resumoPanel.setBackground(Color.WHITE);

        Font fonteLabel = new Font("Segoe UI", Font.BOLD, 18);
        Font fonteValor = new Font("Segoe UI", Font.PLAIN, 18);

        resumoPanel.add(criarLabel("Saldo Atual:", fonteLabel));
        lblSaldo = criarLabel("R$ 2.850,00", fonteValor);
        resumoPanel.add(lblSaldo);

        resumoPanel.add(criarLabel("Total Receitas:", fonteLabel));
        lblReceitas = criarLabel("R$ 3.000,00", fonteValor);
        resumoPanel.add(lblReceitas);

        resumoPanel.add(criarLabel("Total Despesas:", fonteLabel));
        lblDespesas = criarLabel("R$ 150,00", fonteValor);
        resumoPanel.add(lblDespesas);

        mainPanel.add(resumoPanel, BorderLayout.CENTER);

        // Botão Voltar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        btnVoltar = criarBotao("Voltar", new Color(120, 120, 120));
        buttonPanel.add(btnVoltar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ações
        btnAtualizar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Dados atualizados!"));
        btnVoltar.addActionListener(e -> {
            new MainMenuFrame().setVisible(true);
            this.dispose();
        });

        add(mainPanel);
    }
    
    private JLabel criarLabel(String texto, Font fonte) {
        JLabel label = new JLabel(texto);
        label.setFont(fonte);
        label.setHorizontalAlignment(SwingConstants.CENTER);
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