package BaseUtil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by admin on 2016/8/29.
 */
public class HibernateUtil {
    private final SessionFactory sessionFactory;
    public HibernateUtil(String configureFile) {
        try {
            Configuration configuration = new Configuration().configure(configureFile);    //.addResource("/hibernate.cfg.xml");
            ServiceRegistryBuilder builder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.buildServiceRegistry());
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}