package com.cultura.controller;

import com.cultura.model.Player;
import com.cultura.model.Task;
import com.cultura.model.PlayerTask;
import com.cultura.repository.PlayerTaskRepository;
import com.cultura.repository.PlayerRepository;
import com.cultura.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PlayerTaskRepository playerTaskRepository;

    // Registering a new player
    @PostMapping("/register")
    public ResponseEntity<String> registerPlayer(@RequestBody Player player) {
        if (playerRepository.findByUsername(player.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken!");
        }

        playerRepository.save(player);
        return ResponseEntity.ok("Player registered successfully!");
    }

    // Login a player
    @PostMapping("/login")
    public ResponseEntity<String> loginPlayer(@RequestBody Player player) {
        if (playerRepository.findByUsername(player.getUsername()).isPresent()) {
            return ResponseEntity.ok("Login successful!");
        }

        return ResponseEntity.badRequest().body("Invalid username!");
    }

    // Update player's last location
    @PutMapping("/updateLocation")
    public ResponseEntity<String> updateLocation(@RequestParam String username, @RequestParam String location) {
        Player player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        player.setLastLocation(location);
        playerRepository.save(player);
        return ResponseEntity.ok("Location updated successfully!");
    }

    // Complete a task
    @PostMapping("/{playerId}/tasks/{taskId}/complete")
    public ResponseEntity<String> completeTask(
            @PathVariable Long playerId,
            @PathVariable Long taskId) {
        Optional<Player> playerOpt = playerRepository.findById(playerId);
        Optional<Task> taskOpt = taskRepository.findById(taskId);

        if (playerOpt.isEmpty() || taskOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Player player = playerOpt.get();
        Task task = taskOpt.get();

        Optional<PlayerTask> existing = playerTaskRepository.findByPlayerAndTask(player, task);

        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("Task already completed!");
        }

        PlayerTask playerTask = new PlayerTask(player, task);
        playerTaskRepository.save(playerTask);

        return ResponseEntity.ok("Task completed successfully!");
    }
}
