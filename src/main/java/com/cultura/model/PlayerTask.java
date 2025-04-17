package com.cultura.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PlayerTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Task task;

    private boolean completed;

    private LocalDateTime completedAt;

      // Constructors
      public PlayerTask() {}

      public PlayerTask(Player player, Task task) {
          this.player = player;
          this.task = task;
          this.completed = true;
          this.completedAt = LocalDateTime.now();
      }

      //Setters and Getters
      public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Task getTask() {
        return task;
    }
    
    public void setTask(Task task) {
        this.task = task;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
    
}