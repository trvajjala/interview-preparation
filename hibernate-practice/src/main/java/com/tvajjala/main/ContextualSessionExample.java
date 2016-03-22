package com.tvajjala.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.tvajjala.domain.User;

/**
 * http://docs.jboss.org/hibernate/orm/3.3/reference/en/html/architecture.html#architecture-current-session
 *
 * Hibernate getCurrentSession() methods works with the below property value<br>
 * hibernate.current_session_context_class=jta|thread|managed
 *
 * @author ThirupathiReddy V
 *
 */
public class ContextualSessionExample {

    public static SessionFactory getSessionFactory() {

        final Configuration configuration = new Configuration().configure();
        configuration.addPackage("com.tvajjala.domain");
        configuration.addAnnotatedClass(com.tvajjala.domain.User.class);
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "admin");
        System.out.println("Using dialect " + org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());
        configuration.setProperty("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        // session_context_class is jta then transaction manager lookup class need to set
        // configuration.setProperty("hibernate.transaction.manager_lookup_class", TransactionManagerLookup.);

        // This below property used to create session per context// jta, thread, managed
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty(" hibernate.connection.pool_size", "10");

        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        return configuration.buildSessionFactory(builder.build());

    }

    public static void main(String[] args) {

        final SessionFactory sessionFactory = getSessionFactory();

        final Session session = sessionFactory.getCurrentSession();

        final Transaction transaction = session.getTransaction();

        transaction.begin();
        final User user = new User();
        user.setUsername("FLUSHING FROM FIRST LEVEL CACHE ");

        session.persist(user);

        session.flush();
        transaction.commit();

        // sessionFactory.close();
    }

}
