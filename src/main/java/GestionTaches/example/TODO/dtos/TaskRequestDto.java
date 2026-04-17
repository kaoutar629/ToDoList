package GestionTaches.example.TODO.dtos;

import GestionTaches.example.TODO.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TaskRequestDto {
    @NotBlank
    private String title;
    private String description;
    @NotNull
    TaskStatus status;
}
