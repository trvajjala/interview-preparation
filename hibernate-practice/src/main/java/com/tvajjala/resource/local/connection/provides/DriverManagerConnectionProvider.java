package com.tvajjala.resource.local.connection.provides;

import java.sql.Connection;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

/**
 * <b> The Driver Manager Connection Provider offers a rudimentary DataSource wrapper for the configured database driver.<br>
 * You should only use it for test scenarios since it doesn’t offer a professional connection pooling mechanism. </b> There is not connection pool related
 * properties we passed here
 *
 * @author ThirupathiReddy V
 *
 */
public class DriverManagerConnectionProvider {

    private static SessionFactory sessionFactory;

    static Properties getProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "admin");
        properties.put("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        // Isolation level
        properties.put("hibernate.connection.isolation", Connection.TRANSACTION_SERIALIZABLE);

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
