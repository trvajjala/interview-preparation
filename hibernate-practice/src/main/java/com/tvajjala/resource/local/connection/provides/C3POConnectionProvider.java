package com.tvajjala.resource.local.connection.provides;

import java.sql.Connection;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

/**
 * we need to provide the driver configuration settings and Hibernate instantiate the C3P0 connection pool on our behalf. just add connection pool size that you
 * want.
 *
 * @author ThirupathiReddy V
 *
 */
public class C3POConnectionProvider {

    private static SessionFactory sessionFactory;

    static Properties getProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "admin");
        properties.put("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.c3p0.min_size", 1);
        properties.put("hibernate.c3p0.max_size", 5);
        // ISOLATION LEVEL
        properties.put("hibernate.connection.isolation", Connection.TRANSACTION_READ_UNCOMMITTED);

        return properties;
    }

    static {
        final Configuration configuration = new Configuration();
        configuration.addProperties(getProperties());
        configuration.addAnnotatedClass(com.tvajjala.domain.User.class);
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void main(String[] args) {

        final SessionFactory sessionFactory = getSessionFactory();

        final Session session = sessionFactory.openSession();

        session.doWork(connection -> {

            System.out.println(Environment.isolationLevelToString(connection.getTransactionIsolation()));
        });

        session.close();
        sessionFactory.close();
    }

}
