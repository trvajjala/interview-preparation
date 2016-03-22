package com.tvajjala.hql;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.jolbox.bonecp.BoneCPDataSource;
import com.mysql.jdbc.Connection;

public class HQLExample {

    public static void main(String[] args) {
        final SessionFactory sessionFactory = getSessionFactory();
        final Session session = sessionFactory.openSession();
        // session.beginTransaction();

        for (int i = 0; i < 10; i++) {
            final Employee employee = new Employee();
            employee.setName("SOME" + i);
            session.save(employee);
        }

        Query query = session.createQuery("FROM LogicalEmployee WHERE id>6");

        for (final Object emp : query.list()) {
            System.out.println(emp);
        }

        final int maxCount = 10;
        final int pageSize = 5;
        int start = 0;

        do {// pagination
            query = session.createQuery("FROM LogicalEmployee ");
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
            start = start + pageSize;
            System.out.println(query.list());
        } while (maxCount > start);

        session.close();

        sessionFactory.close();

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
        configuration.addAnnotatedClass(Employee.class);

        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
