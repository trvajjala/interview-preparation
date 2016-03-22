package com.tvajjala.inheritance.singletable;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.jolbox.bonecp.BoneCPDataSource;
import com.mysql.jdbc.Connection;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class InheritanceSingleTableExample {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = getSessionFactory();

        final Session session = sessionFactory.openSession();

        session.beginTransaction();

        final Vehicle vehicle = new Vehicle();
        vehicle.setName("GenericVehicle");

        final TwoWheeler bike = new TwoWheeler();
        bike.setName("HeroBike");
        bike.setSteeringHandler("SOME Handler");

        final FourWheeler car = new FourWheeler();
        car.setName("Ford");
        car.setSteeringWheel("Some WHeel");
        session.save(vehicle);
        session.save(bike);
        session.save(car);
        final FourWheeler car2 = new FourWheeler();
        car2.setName("Hyundai");
        car2.setSteeringWheel("SKKSKKSk");
        session.save(car2);
        // it creates single table with new column called discriminator column(DTYPE)
        // and it contains all the columns from different entities .
        // discriminator=a characteristic which enables people or things to be distinguished from one another.
        //
        session.getTransaction().commit();
        // sessionFactory.close();

    }

    private static SessionFactory sessionFactory;

    static Properties getProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        // properties.put("hibernate.connection.autocommit", "false");
        // properties.put("connection.autocommit", "false");
        properties.put("hibernate.connection.datasource", dataSource());
        // ISOLATION LEVEL
        properties.put("hibernate.connection.isolation", Connection.TRANSACTION_READ_UNCOMMITTED);

        return properties;
    }

    static DataSource dataSource() {
        // instantiate, configure and return production DataSource
        final BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setIdleConnectionTestPeriodInMinutes(1);
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        dataSource.setCloseOpenStatements(true);
        dataSource.setIdleMaxAgeInMinutes(1);
        dataSource.setDefaultTransactionIsolation("SERIALIZABLE");// NOT WORKING
        dataSource.setMaxConnectionsPerPartition(100);
        dataSource.setMinConnectionsPerPartition(50);
        dataSource.setPartitionCount(1);
        dataSource.setAcquireIncrement(50);
        return dataSource;
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

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
