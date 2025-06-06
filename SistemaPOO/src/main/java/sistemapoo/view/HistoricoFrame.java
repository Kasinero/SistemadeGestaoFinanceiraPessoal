package sistemapoo.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import sistemapoo.dao.TransacaoDAO;
import sistemapoo.model.Transacao;

public class HistoricoFrame extends JFrame {

    private JTable tblTransacoes;
    private JComboBox<String> cmbFiltroTipo;
    private JComboBox<String> cmbFiltroCategoria;
    private JTextField txtDataInicio;
    private JTextField txtDataFim;
    private JButton btnFiltrar;
    private JButton btnVoltar;

    public HistoricoFrame() {
        configurarJanela();
        initComponentes();
        carregarTransacoes();
    }

    private void configurarJanela() {
        setTitle("Histórico de Transações");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponentes() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Histórico de Transações", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(46, 125, 50));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        JPanel filterPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        filterPanel.setBackground(Color.WHITE);

        Font fonteLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fonteCampo = new Font("Segoe UI", Font.PLAIN, 14);

        filterPanel.add(criarLabel("Tipo:", fonteLabel));
        cmbFiltroTipo = new JComboBox<>(new String[]{"Todos", "Receita", "Despesa"});
        cmbFiltroTipo.setFont(fonteCampo);
        filterPanel.add(cmbFiltroTipo);

        filterPanel.add(criarLabel("Categoria:", fonteLabel));
        cmbFiltroCategoria = new JComboBox<>(new String[]{"Todas", "Alimentação", "Transporte", "Lazer", "Salário"});
        cmbFiltroCategoria.setFont(fonteCampo);
        filterPanel.add(cmbFiltroCategoria);

        filterPanel.add(criarLabel("Data Início:", fonteLabel));
        txtDataInicio = new JTextField();
        txtDataInicio.setFont(fonteCampo);
        filterPanel.add(txtDataInicio);

        filterPanel.add(criarLabel("Data Fim:", fonteLabel));
        txtDataFim = new JTextField();
        txtDataFim.setFont(fonteCampo);
        filterPanel.add(txtDataFim);

        btnFiltrar = criarBotao("Filtrar", new Color(46, 125, 50));
        filterPanel.add(btnFiltrar);

        btnVoltar = criarBotao("Voltar", new Color(120, 120, 120));
        filterPanel.add(btnVoltar);

        mainPanel.add(filterPanel, BorderLayout.NORTH);

        String[] colunas = {"Data", "Tipo", "Categoria", "Valor", "Descrição"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblTransacoes = new JTable(model);
        tblTransacoes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblTransacoes.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tblTransacoes);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        btnFiltrar.addActionListener(e -> filtrarTransacoes());
        btnVoltar.addActionListener(e -> {
            new MainMenuFrame().setVisible(true);
            this.dispose();
        });

        add(mainPanel);
    }

    private void carregarTransacoes() {
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        List<Transacao> transacoes = transacaoDAO.listarPorPeriodo(null, null);
        DefaultTableModel model = (DefaultTableModel) tblTransacoes.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (transacoes != null) {
            for (Transacao t : transacoes) {
                model.addRow(new Object[]{
                    sdf.format(t.getData()),
                    t.getTipo(),
                    t.getCategoria().getNome(),
                    String.format("%.2f", t.getValor()),
                    t.getDescricao()
                });
            }
        }
    }

    private void filtrarTransacoes() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date inicio = txtDataInicio.getText().isEmpty() ? null : sdf.parse(txtDataInicio.getText());
            Date fim = txtDataFim.getText().isEmpty() ? null : sdf.parse(txtDataFim.getText());
            String tipo = cmbFiltroTipo.getSelectedItem().toString();
            String categoria = cmbFiltroCategoria.getSelectedItem().toString();

            TransacaoDAO transacaoDAO = new TransacaoDAO();
            List<Transacao> transacoes = transacaoDAO.listarPorPeriodo(inicio, fim);
            DefaultTableModel model = (DefaultTableModel) tblTransacoes.getModel();
            model.setRowCount(0);

            if (transacoes != null) {
                for (Transacao t : transacoes) {
                    if ((tipo.equals("Todos") || t.getTipo().equals(tipo)) &&
                        (categoria.equals("Todas") || t.getCategoria().getNome().equals(categoria))) {
                        model.addRow(new Object[]{
                            sdf.format(t.getData()),
                            t.getTipo(),
                            t.getCategoria().getNome(),
                            String.format("%.2f", t.getValor()),
                            t.getDescricao()
                        });
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Filtro aplicado!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao aplicar filtro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
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
        return botao;
    }
}