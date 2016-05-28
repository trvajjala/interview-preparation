package com.innominds.persistence.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innominds.persistence.domain.AuthorityEntity;

/**
 * The Interface AuthorityRepository do the crud operations internally by property name.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Serializable> {

    /**
     * Find by authority.
     *
     * @param authority
     *            the authority
     * @return the authority entity
     */
    AuthorityEntity findByAuthority(String authority);
}
