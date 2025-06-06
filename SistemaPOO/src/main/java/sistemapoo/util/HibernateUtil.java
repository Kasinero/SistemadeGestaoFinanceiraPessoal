package sistemapoo.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
   private static SessionFactory sessionFactory;
   private static StandardServiceRegistry registry;

   static {
      try {
         registry = new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml")
            .build();
         Metadata metadata = new MetadataSources(registry)
            .addAnnotatedClass(sistemapoo.model.Usuario.class)
            .addAnnotatedClass(sistemapoo.model.Transacao.class)
            .addAnnotatedClass(sistemapoo.model.Categoria.class)
            .getMetadataBuilder()
            .build();
         sessionFactory = metadata.getSessionFactoryBuilder().build();
      } catch (Exception ex) {
         ex.printStackTrace();
         if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
         }
         throw new ExceptionInInitializerError("Erro ao inicializar o Hibernate: " + ex.getMessage());
      }
   }

   public static SessionFactory getSessionFactory() {
      if (sessionFactory == null) {
         throw new IllegalStateException("SessionFactory não foi inicializado.");
      }
      return sessionFactory;
   }

   public static void shutdown() {
      if (sessionFactory != null) {
         sessionFactory.close();
      }
      if (registry != null) {
         StandardServiceRegistryBuilder.destroy(registry);
      }
   }
}