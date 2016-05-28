package com.innominds.persistence.repository;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.cfg.Environment;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import com.innominds.persistence.domain.UserEntity;

/**
 * This will inject Proxy instead of actual entity manager.<br>
 * This is not working and throwing error as Not allowed to create transaction on shared EntityManager
 *
 * @author ThirupathiReddy V
 */
@Repository
@Transactional
public class LegacyContextUserRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void saveUser(UserEntity userEntity) {

        entityManager.getTransaction().begin();
        entityManager.persist(userEntity);
        entityManager.getTransaction().commit();
    }

    public void checkIsolationLevel() {

        final Session session = (Session) entityManager.getDelegate();
        session.doWork(new Work() {

            @Override
            public void execute(Connection connection) throws SQLException {

                System.out.println("EM : " + Environment.isolationLevelToString(connection.getTransactionIsolation()));

            }
        });

    }

}
