package com.tvajjala.web.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * Spring Data JPA associates the implementation class with the interface because the implementation’s name is based on the name of the interface. The Impl
 * postfix is only the default, though. If you’d prefer to use some other postfix, you need to specify it when configuring @EnableJpaRepositories by setting the
 * repository-ImplementationPostfix attribute:
 *
 * @EnableJpaRepositories( basePackages="com.habuma.spittr.db", repositoryImplementationPostfix="Helper")
 * @author ThirupathiReddy V
 *
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", repositoryImplementationPostfix = "Helper", transactionManagerRef = "transactionManager", basePackages = { "com.tvajjala.repository" })
@EnableTransactionManagement
@ImportResource(value = { "classpath:cache-context.xml" })
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        // instantiate, configure and return production DataSource
        final BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setIdleConnectionTestPeriodInMinutes(1);
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        dataSource.setCloseOpenStatements(true);
        dataSource.setIdleMaxAgeInMinutes(1);
        dataSource.setMaxConnectionsPerPartition(100);
        dataSource.setMinConnectionsPerPartition(50);
        dataSource.setPartitionCount(1);
        dataSource.setAcquireIncrement(50);
        return dataSource;
    }

    // @Bean// this is hibernate sesionFactory
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        final LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setPackagesToScan(new String[] { "com.tvajjala.domain" });
        final Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        sfb.setHibernateProperties(props);
        return sfb;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        // em.setMappingResources("META-INF/orm.xml"); // for auditing AuditingEntityListener
        em.setPackagesToScan(new String[] { "com.tvajjala.domain" });

        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setJpaProperties(additionalJpaProperties());
        em.setPersistenceUnitName("JPA-PU");
        em.afterPropertiesSet();
        return em.getObject();
    }

    Properties additionalJpaProperties() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop"); // TODO: create-drop,create,none
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.ejb.naming_strategy", DatabaseNamingStrategy.class.getName());// fully qualifed name a string
        return properties;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        return adapter;
    }

    /**
     * It’s important to understand that @PersistenceUnit and @PersistenceContext aren’t Spring annotations;<br>
     * they’re provided by the JPA specification. In order for Spring to understand them and inject an EntityManagerFactory or EntityManager,<br>
     * Spring’s PersistenceAnnotationBeanPostProcessor must be configured. <br>
     * If you’re already using <context:annotation-config> or <context:component-scan>,<br>
     * then you’re good to go because those configuration elements automatically register a PersistenceAnnotationBeanPostProcessor bean.
     *
     * @return PersistenceAnnotationBeanPostProcessor
     */

    @Bean
    public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

}
