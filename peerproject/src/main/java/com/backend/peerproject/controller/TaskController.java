package com.backend.peerproject.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.peerproject.entity.Task;
import com.backend.peerproject.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("{id}")
    public ResponseEntity<Task> getById(@PathVariable("id") Long id) {
        return taskService.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return taskService.getAll();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task item) {
        return taskService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> update(@PathVariable("id") Long id, @RequestBody Task item) {
        return taskService.update(id, item);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        return taskService.delete(id);
    }

}
