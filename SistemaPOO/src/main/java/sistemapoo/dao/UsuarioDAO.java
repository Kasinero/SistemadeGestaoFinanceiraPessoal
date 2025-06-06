package sistemapoo.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sistemapoo.model.Usuario;
import sistemapoo.util.HibernateUtil;

public class UsuarioDAO {
    public Usuario autenticar(String email, String senha) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> query = session.createQuery(
                "FROM Usuario WHERE email = :email AND senha = :senha", Usuario.class);
            query.setParameter("email", email);
            query.setParameter("senha", senha);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}