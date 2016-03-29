package com.tvajjala.second.level.cache;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.jolbox.bonecp.BoneCPDataSource;
import com.mysql.jdbc.Connection;
import com.tvajjala.named.query.Employee;

public class SecondLevelCacheExample {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = getSessionFactory();

        final Session session1 = sessionFactory.openSession();
        final Session session2 = sessionFactory.openSession();

        session1.get(Employee.class, 1l);
        session1.close();

        session2.get(Employee.class, 1l);
        session2.close();

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
        properties.put("hibernate.cache.use_second_level_cache", "true");
        properties.put("hibernate.cache.use_query_cache", "true");
        properties.put("hibernate.cache.region.factory_class", org.hibernate.cache.ehcache.EhCacheRegionFactory.class.getName());
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
        configuration.addAnnotatedClass(Employee.class);
        configuration.addProperties(getProperties());
        final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
