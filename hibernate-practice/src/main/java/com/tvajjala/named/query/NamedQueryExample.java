package com.tvajjala.named.query;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jolbox.bonecp.BoneCPDataSource;
import com.mysql.jdbc.Connection;

public class NamedQueryExample {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = getSessionFactory();

        final Session session = sessionFactory.openSession();

        for (int i = 0; i < 100; i++) {
            final Employee employee = new Employee();
            employee.setName("SOMENAME" + i);
            employee.setDescription("Some Description" + i);
            session.save(employee);

        }

        Query query = session.getNamedQuery("Employee.byId");
        query.setParameter("id", 1l);
        System.out.println(query.list());

        System.out.println("-----------------");
        query = session.getNamedQuery("Employee.Named.byId");
        query.setParameter("id", 1l);
        System.out.println(query.list());

        System.out.println("-----------------");
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq("id", 1l)).add(Restrictions.eq("name", "SOMENAME"));
        System.out.println(criteria.list());

        System.out.println("-----------------");
        criteria = session.createCriteria(Employee.class);
        criteria.setProjection(Projections.property("id")).addOrder(Order.desc("id"));
        System.out.println(criteria.list());

        System.out.println("------------------");

        // create by example will take example object and returns similar kind of obejcts
        final Employee emp = new Employee();
        emp.setName("SOMENAME1%");
        criteria = session.createCriteria(Employee.class).add(Example.create(emp).enableLike());

        System.out.println(criteria.list());

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
