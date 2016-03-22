package com.tvajjala.one.to.many.relations;

import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.jolbox.bonecp.BoneCPDataSource;
import com.mysql.jdbc.Connection;

public class OneToManyExample {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = getSessionFactory();

        final Patient patient = new Patient();
        patient.setName("SomePatient");

        final Report report = new Report();
        report.setGeneratedOn(new Date());
        report.setName("BloodReport");
        report.setPatient(patient);

        patient.getReports().add(report);
        final Report report1 = new Report();
        report1.setGeneratedOn(new Date());
        report1.setName("SugarReport");
        report1.setPatient(patient);
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(patient);
        session.getTransaction().commit();
        session.close();
        //
        session = sessionFactory.openSession();

        final Report report2 = (Report) session.get(Report.class, 1l);

        System.out.println(report2.getName());
        System.out.println("------");

        System.out.println(report2.getPatient());
        System.out.println(report2.getPatient().getReports());

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
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Report.class);
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
