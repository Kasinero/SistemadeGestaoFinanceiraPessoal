package sistemapoo.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sistemapoo.model.Transacao;
import sistemapoo.util.HibernateUtil;

public class TransacaoDAO {
    public void salvar(Transacao transacao) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(transacao);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public List<Transacao> listarPorPeriodo(Date inicio, Date fim) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Transacao> query = session.createQuery(
                "FROM Transacao WHERE data BETWEEN :inicio AND :fim", Transacao.class);
            query.setParameter("inicio", inicio);
            query.setParameter("fim", fim);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}