package com.tvajjala.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tvajjala.domain.UserEntity;

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

}
