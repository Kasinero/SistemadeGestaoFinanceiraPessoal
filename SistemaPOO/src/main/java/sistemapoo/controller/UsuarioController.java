package sistemapoo.controller;

import sistemapoo.dao.UsuarioDAO;
import sistemapoo.model.Usuario;

public class UsuarioController {
    private final UsuarioDAO dao = new UsuarioDAO();
    
    public boolean login(String email, String senha) {
        return dao.autenticar(email, senha) != null;
    }
}