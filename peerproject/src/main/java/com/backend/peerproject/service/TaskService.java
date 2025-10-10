package com.backend.peerproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.peerproject.entity.Task;
import com.backend.peerproject.entity.User;
import com.backend.peerproject.repository.TaskRepository;
import com.backend.peerproject.repository.UserRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Task> getById(Long id) {
        Optional<Task> existingItemOptional = taskRepository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Task>> getAll() {
        try {
            List<Task> items = new ArrayList<Task>();

            taskRepository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Task> create(Task item, Long user_id) {
        try {
            Optional<User> userInDb = userRepository.findById(user_id);
            User user = userInDb.get();
            user.addTask(item);
            item.setUser(user);
            Task savedItem = taskRepository.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<Task> update(Long task_id, Task item) {// , Long user_id, Task item) {
        Optional<Task> existingTaskOptional = taskRepository.findById(task_id);
        // Optional<User> existingUserOptional = userRepository.findById(user_id);

        if (existingTaskOptional.isPresent()) {// && existingUserOptional.isPresent()) {

            Task existingTask = existingTaskOptional.get();
            // User existingUser = existingUserOptional.get();
            existingTask.setTask_name(item.getTask_name());
            existingTask.setTask_description(item.getTask_description());
            existingTask.setTask_is_completed(item.getTask_is_completed());
            return new ResponseEntity<>(taskRepository.save(existingTask), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> delete(Long id) {
        try {
            taskRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
