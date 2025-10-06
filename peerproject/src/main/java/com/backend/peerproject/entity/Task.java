package com.backend.peerproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task_peerproject")
public class Task {
    private Long task_id;
    private String task_name;
    private String task_description;
    private boolean task_is_completed;
    
    @ManyToOne
    @JoinColumn(name = "user_id") 
    private User user;

    public boolean getTask_is_completed() {
        return task_is_completed;
    }
}
