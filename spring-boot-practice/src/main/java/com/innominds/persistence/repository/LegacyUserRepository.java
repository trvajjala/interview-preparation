package com.innominds.persistence.repository;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.cfg.Environment;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import com.innominds.persistence.domain.UserEntity;

/**
 * here we always calling createEntityManager class. if you need to entity manager object to handle calls this approach works
 *
 * @author ThirupathiReddy V
 *
 */

@Repository
@Transactional
public class LegacyUserRepository {

    @PersistenceUnit
    EntityManagerFactory emf;

    public void saveUser(UserEntity userEntity) {

        final EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(userEntity);
        em.getTransaction().commit();
    }

    public void checkIsolationLevel() {

        final EntityManager em = emf.createEntityManager();

        final Session session = (Session) em.getDelegate();
        session.doWork(new Work() {

            @Override
            public void execute(Connection connection) throws SQLException {

                System.out.println("EMF: " + Environment.isolationLevelToString(connection.getTransactionIsolation()));

            }
        });

    }

}
