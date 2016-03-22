package com.tvajjala.resource.local.connection.provides;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * If you are using an external DataSource (e.g. maybe through JNDI), then you need to set the transaction isolation at the external DataSource level.<br>
 * hibernate doesn't override the Isolation level. <br>
 * in this case you need to set isolation level on your dataSource API level.
 *
 * @author ThirupathiReddy V
 *
 */
public class ExternalDataSourceConnectionProvider {

    private static SessionFactory sessionFactory;

    static Properties getProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class.getName());
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
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

            System.out.println("ISOLATION LEVEL NOT OVERRIDEN BY THE HIBERNATE ? " + Environment.isolationLevelToString(connection.getTransactionIsolation()));
        });

        session.close();
        sessionFactory.close();
    }

}
