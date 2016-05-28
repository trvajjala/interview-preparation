package com.innominds.persistence.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.innominds.persistence.domain.EndpointEntity;

/**
 *
 * @author ThirupathiReddy V
 *
 */
@Repository
public interface EndpointsRepository extends JpaRepository<EndpointEntity, Serializable> {

    @Query("FROM  EndpointEntity a WHERE a.regex=true")
    List<EndpointEntity> getRegexActivites();

    EndpointEntity findByEndpoint(String endpoint);

    @Modifying
    @Query("UPDATE EndpointEntity p SET p=?1 WHERE p.id=?2")
    void updateAuthorites(EndpointEntity endpointEntity, Long id);

}
