package sistemapoo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sistemapoo.model.Categoria;
import sistemapoo.util.HibernateUtil;
import java.util.List;

public class CategoriaDAO {
    public void salvar(Categoria categoria) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(categoria);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Categoria buscarPorNome(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Categoria> query = session.createQuery("FROM Categoria WHERE nome = :nome", Categoria.class);
            query.setParameter("nome", nome);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Categoria> listarTodas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Categoria> query = session.createQuery("FROM Categoria", Categoria.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}