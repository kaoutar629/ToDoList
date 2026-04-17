package GestionTaches.example.TODO.controllers;

import GestionTaches.example.TODO.dtos.TaskRequestDto;
import GestionTaches.example.TODO.dtos.TaskResponseDto;
import GestionTaches.example.TODO.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskResponseDto> getByid(@PathVariable Long id){
        var task = taskService.getById(id);
        if(task == null){return ResponseEntity.notFound().build();}
            return ResponseEntity.ok(task);
    }
    @GetMapping
    List<TaskResponseDto> getStatus(@RequestParam(required = false) String status){
        if(status != null){return taskService.getStatus(status);}
        return taskService.getAllTasks();
    }
    @PostMapping
    TaskResponseDto createTask(@RequestBody @Valid TaskRequestDto dto){
        return taskService.createTask(dto);
    }
    @PutMapping("/{id}")
    TaskResponseDto updateTask(@RequestBody TaskResponseDto dto , @PathVariable Long id){
        return taskService.updateTask(dto,id);
    }
    @PatchMapping("/{id}")
    public TaskResponseDto updateTitle(@PathVariable Long id,
                                       @RequestBody Map<String, Object> updates) {
        return taskService.updatePartial(id, updates);
    }
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id){
        taskService.deleteById(id);
    }
}
