## Task Manager
### Task Manager Part - 1 
1 . Create the Task 
```java
@Entity
@Data
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dueDate;
    private boolean complated;
}
```
<br> 2 . Task Repository extends CrudRepository

```java 
public interface TaskRepository extends CrudRepository<Task,Long> {}
```

<br> 3 . TaskService implementation, TaskService has the same methods on the TaskController
```java
public interface TaskService {
  Iterable<Task> taskList();
}
```
<br> 4 . Implementing the TaskServiceImp , this service private member TaskRepository
```java
 //generate the task repository for service
    private TaskRepository taskRepository;

    public TaskServiceImp(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Iterable<Task> taskList() {
        return taskRepository.findAll();
    }
  ```
  <br> 5 . Now we add the TaskService to TaskController
  
```java
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping("/")
    public Iterable<Task> listTasks(){
        //call the taskservice listTasks
        return taskService.listTasks();
    }

}
  ```
 
