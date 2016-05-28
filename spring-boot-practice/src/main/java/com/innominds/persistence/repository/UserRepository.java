package com.innominds.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.innominds.persistence.domain.UserEntity;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByUsername(String username);

    @Modifying
    @Query("UPDATE UserEntity a SET a=?1 WHERE a.id=?2")
    public void updateAuthorities(UserEntity userEntity, Long id);
}
