package sistemapoo.controller;

import sistemapoo.dao.CategoriaDAO;
import sistemapoo.model.Categoria;

public class CategoriaController {
    private final CategoriaDAO dao = new CategoriaDAO();
    
    public void adicionarCategoria(String nome) {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        dao.salvar(categoria);
    }
}