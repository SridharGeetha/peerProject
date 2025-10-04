package com.backend.peerproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.peerproject.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
