package com.tvajjala.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvajjala.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
