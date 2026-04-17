package GestionTaches.example.TODO.dtos;

import GestionTaches.example.TODO.enums.TaskStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TaskResponseDto {
    Long id;
    private String title;
    private String description;
    TaskStatus status;
}
