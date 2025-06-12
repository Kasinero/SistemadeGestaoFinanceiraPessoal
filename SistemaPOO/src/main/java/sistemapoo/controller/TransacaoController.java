package sistemapoo.controller;

import sistemapoo.dao.CategoriaDAO;
import sistemapoo.dao.TransacaoDAO;
import sistemapoo.model.Categoria;
import sistemapoo.model.Transacao;
import java.util.Date;

public class TransacaoController {
    private final TransacaoDAO dao = new TransacaoDAO();
    
    
    public void criarTransacao(double valor, String tipo, String categoriaNome, String descricao, Date data) {
        Transacao transacao = new Transacao();
        transacao.setValor(valor);
        transacao.setTipo(tipo);
        transacao.setData(data); 
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoria = categoriaDAO.buscarPorNome(categoriaNome);
        if (categoria != null) {
            transacao.setCategoria(categoria);
        } else {
            System.out.println("Categoria não encontrada: " + categoriaNome);
            return;
        }
        
        transacao.setDescricao(descricao);
        dao.salvar(transacao);
    }
}
