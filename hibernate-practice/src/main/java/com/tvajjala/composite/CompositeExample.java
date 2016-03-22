package com.tvajjala.composite;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class CompositeExample {

    public static void main(String[] args) {
        final SessionFactory sessionFactory = getSessionFactory();

        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        final Department department = new Department();
        department.setId(new DepartmentKey(1l, "ECE"));
        department.setName("Electronics and Communication Engineernig");

        session.save(department);

        final Department department2 = new Department();
        department2.setId(new DepartmentKey(1l, "EEE"));
        department2.setName("Electronics and Communication Engineernig");
        session.save(department2);
        session.getTransaction().commit();
        session.close();

        // sessionFactory.close();
    }

    private static SessionFactory sessionFactory;

    static Properties getProperties() {
        final Properties properties = new Properties();

        properties.put("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "admin");
        properties.put("hibernate.connection.driver_calss", "com.mysql.jdbc.Driver");

        return properties;
    }

    static {

        final Configuration configuration = new Configuration();

        configuration.addProperties(getProperties());
        configuration.addAnnotatedClass(Department.class);
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        sessionFactory = configuration.buildSessionFactory(builder.build());

    }

    static SessionFactory getSessionFactory() {

        return sessionFactory;
    }

}
