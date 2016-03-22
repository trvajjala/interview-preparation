package com.tvajjala.annotations;

import java.sql.Connection;
import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.tvajjala.domain.AnnoTypes;

public class AnnotationExample {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = getSessionFactory();

        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        final AnnoTypes annoTypes = new AnnoTypes();
        annoTypes.setDob(new Date());
        annoTypes.setTimeOfBirth(new Date());
        annoTypes.setWholeDate(new Date());
        annoTypes.setDescription("SomeDesc.............");
        session.save(annoTypes);
        session.getTransaction().commit();
        session.clear();// it clears the first level cache
        final AnnoTypes annoTypes2 = (AnnoTypes) session.get(AnnoTypes.class, annoTypes.getId());

        System.out.println(annoTypes2.getDescription());

    }

    static Properties getSettings() {

        final Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "admin");
        properties.put("hibernate.connection.isolation", Connection.TRANSACTION_READ_COMMITTED);

        return properties;
    }

    public static SessionFactory getSessionFactory() {

        final Configuration configuration = new Configuration();
        configuration.addProperties(getSettings());
        configuration.addAnnotatedClass(AnnoTypes.class);
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(getSettings());

        return configuration.buildSessionFactory(builder.build());
    }
}
