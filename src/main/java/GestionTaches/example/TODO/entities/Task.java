package GestionTaches.example.TODO.entities;

import GestionTaches.example.TODO.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Task {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long id;
    private String ref;
    @Column(nullable = false)
    private  String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private LocalDateTime createdAt;
@PrePersist
    public void onCreate(){
    this.createdAt =LocalDateTime.now();
}
}
