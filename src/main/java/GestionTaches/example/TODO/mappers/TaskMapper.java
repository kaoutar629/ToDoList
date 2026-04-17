package GestionTaches.example.TODO.mappers;

import GestionTaches.example.TODO.dtos.TaskRequestDto;
import GestionTaches.example.TODO.dtos.TaskResponseDto;
import GestionTaches.example.TODO.entities.Task;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TaskMapper {
     Task toEntity(TaskRequestDto requestDto);
     TaskResponseDto toDto(Task task);

}
