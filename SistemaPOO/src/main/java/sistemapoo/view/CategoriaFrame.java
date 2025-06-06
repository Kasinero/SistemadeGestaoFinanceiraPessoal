package sistemapoo.view;

import java.awt.*;
import javax.swing.*;
import sistemapoo.controller.CategoriaController;

public class CategoriaFrame extends JFrame {

    private JList<String> listCategorias;
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnRemover;
    private JButton btnVoltar;
    private DefaultListModel<String> model;

    public CategoriaFrame() {
        configurarJanela();
        initComponentes();
    }

    private void configurarJanela() {
        setTitle("Gerenciamento de Categorias");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponentes() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Categorias", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(46, 125, 50));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        topButtonPanel.setBackground(Color.WHITE);

        btnAdicionar = criarBotao("Adicionar", new Color(46, 125, 50));
        btnEditar = criarBotao("Editar", new Color(33, 97, 140));
        btnRemover = criarBotao("Remover", new Color(198, 40, 40));

        topButtonPanel.add(btnAdicionar);
        topButtonPanel.add(btnEditar);
        topButtonPanel.add(btnRemover);

        mainPanel.add(topButtonPanel, BorderLayout.NORTH);

        model = new DefaultListModel<>();
        listCategorias = new JList<>(model);
        listCategorias.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listCategorias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listCategorias);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        btnVoltar = criarBotao("Voltar", new Color(120, 120, 120));
        bottomPanel.add(btnVoltar);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        CategoriaController categoriaController = new CategoriaController();

        btnAdicionar.addActionListener(e -> {
            String novaCategoria = JOptionPane.showInputDialog(this, "Nome da nova categoria:");
            if (novaCategoria != null && !novaCategoria.trim().isEmpty()) {
                model.addElement(novaCategoria.trim());
                categoriaController.adicionarCategoria(novaCategoria.trim());
            }
        });

        btnEditar.addActionListener(e -> {
            int index = listCategorias.getSelectedIndex();
            if (index != -1) {
                String novoNome = JOptionPane.showInputDialog(this, "Editar categoria:", model.getElementAt(index));
                if (novoNome != null && !novoNome.trim().isEmpty()) {
                    model.set(index, novoNome.trim());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma categoria para editar");
            }
        });

        btnRemover.addActionListener(e -> {
            int index = listCategorias.getSelectedIndex();
            if (index != -1) {
                model.remove(index);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma categoria para remover");
            }
        });

        btnVoltar.addActionListener(e -> {
            new MainMenuFrame().setVisible(true);
            this.dispose();
        });

        add(mainPanel);
    }

    private JButton criarBotao(String texto, Color corFundo) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(corFundo);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(120, 35));
        return botao;
    }
}