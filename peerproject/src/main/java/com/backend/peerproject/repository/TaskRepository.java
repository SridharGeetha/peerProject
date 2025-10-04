package com.backend.peerproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.peerproject.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
