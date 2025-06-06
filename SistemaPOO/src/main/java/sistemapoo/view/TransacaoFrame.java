package sistemapoo.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import sistemapoo.controller.TransacaoController;
import sistemapoo.dao.CategoriaDAO;
import sistemapoo.model.Categoria;

public class TransacaoFrame extends JFrame {

    private JComboBox<String> cmbTipo;
    private JTextField txtValor;
    private JComboBox<String> cmbCategoria;
    private JFormattedTextField txtData;
    private JTextArea txtDescricao;
    private JButton btnSalvar;
    private JButton btnVoltar;

    public TransacaoFrame() {
        configurarJanela();
        initComponentes();
        carregarCategorias();
    }

    private void configurarJanela() {
        setTitle("Cadastro de Transação");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponentes() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Nova Transação", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(46, 125, 50));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        Font fonteLabel = new Font("Segoe UI", Font.PLAIN, 16);
        Font fonteCampo = new Font("Segoe UI", Font.PLAIN, 14);

        formPanel.add(criarLabel("Tipo:", fonteLabel));
        cmbTipo = new JComboBox<>(new String[]{"Receita", "Despesa"});
        cmbTipo.setFont(fonteCampo);
        formPanel.add(cmbTipo);

        formPanel.add(criarLabel("Valor (R$):", fonteLabel));
        txtValor = new JTextField();
        txtValor.setFont(fonteCampo);
        formPanel.add(txtValor);

        formPanel.add(criarLabel("Categoria:", fonteLabel));
        cmbCategoria = new JComboBox<>();
        cmbCategoria.setFont(fonteCampo);
        formPanel.add(cmbCategoria);

        formPanel.add(criarLabel("Data:", fonteLabel));
        try {
            MaskFormatter maskFormatter = new MaskFormatter("##/##/####");
            maskFormatter.setPlaceholderCharacter('_');
            txtData = new JFormattedTextField(maskFormatter);
            txtData.setFont(fonteCampo);
            formPanel.add(txtData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        formPanel.add(criarLabel("Descrição:", fonteLabel));
        JScrollPane scrollPane = new JScrollPane();
        txtDescricao = new JTextArea(3, 20);
        txtDescricao.setFont(fonteCampo);
        txtDescricao.setLineWrap(true);
        scrollPane.setViewportView(txtDescricao);
        formPanel.add(scrollPane);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        btnVoltar = criarBotao("Voltar", new Color(120, 120, 120));
        btnSalvar = criarBotao("Salvar", new Color(46, 125, 50));

        buttonPanel.add(btnVoltar);
        buttonPanel.add(btnSalvar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        btnVoltar.addActionListener(e -> {
            new MainMenuFrame().setVisible(true);
            this.dispose();
        });

        btnSalvar.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(txtValor.getText());
                String tipo = (String) cmbTipo.getSelectedItem();
                String categoriaNome = (String) cmbCategoria.getSelectedItem();
                String descricao = txtDescricao.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date data = sdf.parse(txtData.getText());

                TransacaoController transacaoController = new TransacaoController();
                transacaoController.criarTransacao(valor, tipo, categoriaNome, descricao, data);

                JOptionPane.showMessageDialog(this, "Transação salva com sucesso!");
                new MainMenuFrame().setVisible(true);
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar transação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(mainPanel);
    }

    private void carregarCategorias() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Categoria> categorias = categoriaDAO.listarTodas();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        if (categorias != null) {
            for (Categoria cat : categorias) {
                model.addElement(cat.getNome());
            }
        }
        cmbCategoria.setModel(model);
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