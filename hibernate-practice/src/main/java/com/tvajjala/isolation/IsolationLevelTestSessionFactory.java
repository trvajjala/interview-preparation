package com.tvajjala.isolation;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * This class creates driver manager session factory with isolation level as an argument
 * 
 * @author ThirupathiReddy V
 *
 */
public class IsolationLevelTestSessionFactory {

    static Properties getProperties(int isolationLevel) {
        final Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "admin");
        properties.put("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        // Isolation level
        properties.put("hibernate.connection.isolation", isolationLevel);

        return properties;
    }

    public static SessionFactory getSessionFactory(int isolationLevel) {
        final Configuration configuration = new Configuration();
        configuration.addProperties(getProperties(isolationLevel));
        configuration.addAnnotatedClass(com.tvajjala.domain.User.class);
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

}
