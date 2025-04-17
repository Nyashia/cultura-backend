package com.cultura.repository;

import com.cultura.model.PlayerTask;
import com.cultura.model.Player;
import com.cultura.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerTaskRepository extends JpaRepository<PlayerTask, Long> {
    Optional<PlayerTask> findByPlayerAndTask(Player player, Task task);
}
