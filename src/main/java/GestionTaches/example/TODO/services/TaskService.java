package GestionTaches.example.TODO.services;

import GestionTaches.example.TODO.dtos.TaskRequestDto;
import GestionTaches.example.TODO.dtos.TaskResponseDto;
import GestionTaches.example.TODO.enums.TaskStatus;
import GestionTaches.example.TODO.mappers.TaskMapper;
import GestionTaches.example.TODO.repositories.TaskRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;



@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
   TaskService(TaskRepository taskRepository,TaskMapper taskMapper){
       this.taskMapper =taskMapper;
       this.taskRepository = taskRepository;
   }
   //read
    public List<TaskResponseDto> getAllTasks(){
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }
    public TaskResponseDto  getById(Long id){
      var task =taskRepository.findById(id)
              .orElseThrow(()->new RuntimeException("id not found"));
     return taskMapper.toDto(task);
    }
    public List<TaskResponseDto> getStatus(String status){
       TaskStatus taskStatus =TaskStatus.valueOf(status);
        return taskRepository.findByStatus(taskStatus)
               .stream()
                .map(taskMapper::toDto)
                .toList();

    }
    //create
    public TaskResponseDto createTask(TaskRequestDto dto){
        var task = taskMapper.toEntity(dto);
        task.setRef(UUID.randomUUID().toString());
        var saved=taskRepository.save(task);
        return taskMapper.toDto(saved);
    }
    //update
    public TaskResponseDto updateTask(TaskResponseDto dto,Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found!"));
        task.setTitle(dto.getTitle());
        task.setStatus(dto.getStatus());
        var updated = taskRepository.save(task);
        return taskMapper.toDto(updated);
    }
   public TaskResponseDto updatePartial(Long id , Map<String,Object> updates){
       var task =taskRepository.findById(id)
               .orElseThrow(()->new RuntimeException("notFound"));
       if(updates.containsKey("title")){
           task.setTitle((String)updates.get("title"));

       }

       if (updates.containsKey("description")) {
           task.setDescription((String) updates.get("description"));
       }

       if (updates.containsKey("status")) {
           task.setStatus(TaskStatus.valueOf((String) updates.get("status")));
       }
       var saved = taskRepository.save(task);
       return taskMapper.toDto(saved);
   }
    //delete
        public void deleteById(Long id ){
            if(!taskRepository.existsById(id))
                throw new RuntimeException("Task not found");
            taskRepository.deleteById(id);
        }
    }


/*createTask(TaskRequestDTO dto)crée et sauvegardeupdateTask(Long id, TaskRequestDTO dto)met à jourdeleteTask(Long id)supprime*/