package GestionTaches.example.TODO.repositories;

import GestionTaches.example.TODO.entities.Task;
import GestionTaches.example.TODO.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByStatus(TaskStatus status);
}
