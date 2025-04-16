package com.cultura.controller;

import com.cultura.model.Player;
import com.cultura.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    //Registering a new player
    @PostMapping("/register")
    public ResponseEntity<String> registerPlayer(@RequestBody Player player) {
        if (playerRepository.findByUsername(player.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken!");
        }

        playerRepository.save(player);
        return ResponseEntity.ok("Player registered successfully!");
    }

    //Login a player (SIMPLE WORKING USAGE - TO UPGRADE LATER)
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
}