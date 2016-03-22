package com.tvajjala.inheritance.perclass;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class TablePerClassExample {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = getSessionFactory();
        final Session session = sessionFactory.openSession();

        session.beginTransaction();
        final Vehicle vehicle = new Vehicle();
        session.save(vehicle);

        final TwoWheeler bike = new TwoWheeler();
        bike.setSteeringHandle("Steering Handle");
        session.save(bike);

        final FourWheeler car = new FourWheeler();
        car.setSteeringWheel("Sterring Wheel");
        session.save(car);

        session.getTransaction().commit();
        session.close();

    }

    private static SessionFactory sessionFactory;

    static Properties getProperties() {
        final Properties properties = new Properties();

        properties.put("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());

        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "admin");
        properties.put("hibernate.connection.driver_calss", "com.mysql.jdbc.Driver");

        return properties;
    }

    static {

        final Configuration configuration = new Configuration();

        configuration.addProperties(getProperties());
        configuration.addAnnotatedClass(Vehicle.class);
        configuration.addAnnotatedClass(TwoWheeler.class);
        configuration.addAnnotatedClass(FourWheeler.class);
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        sessionFactory = configuration.buildSessionFactory(builder.build());

    }

    static SessionFactory getSessionFactory() {

        return sessionFactory;
    }

}
