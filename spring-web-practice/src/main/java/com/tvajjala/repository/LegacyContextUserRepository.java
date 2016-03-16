package com.tvajjala.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tvajjala.domain.UserEntity;

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

}
