## Task Manager
### Task Manager Part - 1 
Task Manager Project structure 
<br> 1 . Create the Task 
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
  
  ### Task Manager Part -2 
  
  1 . saveTask() operation , TaskServiceImp  implements TaskService TaskRepository extends from the CrudRepository - https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html 
 TaskServiceImp
 ```java
   @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }
}
```
2 . saveTask() operation TaskController side  
```java
  //post mapping the url 
  @PostMapping("/save")
    public Task saveTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }
 ```

3. application.properties H2 SQL setting
```
spring.datasource.name= tasks
spring.jpa.show-sql=true

spring.h2.console.enabled= true
spring.h2.console.path= /h2-console
```
4 . H2 SQL adding the tasks TaskApplication 

```java
@Bean
	CommandLineRunner runner(TaskService taskService){
		return args->{
			taskService.saveTask(new Task("Work For Spring", LocalDate.now(),false));
			taskService.saveTask(new Task("Work For Math", LocalDate.now().plus(10, ChronoUnit.DAYS),false));
			taskService.saveTask(new Task("Go to Supermarket", LocalDate.now().plus(5,ChronoUnit.DAYS),false));
		};
	}
 ```
 5 . Running the h2-SQL database on http://localhost:8080/h2-console/ JDBC URL : jdbc:h2:mem:tasks 
 <br> 6 . The Result is 
 ```
 [
    {
        id: 1,
        name: "Work For Spring",
        dueDate: "04/25/2018",
        complated: false
    },
    {
        id: 2,
        name: "Work For Math",
        dueDate: "05/05/2018",
        complated: false
    },
    {
        id: 3,
        name: "Go to Supermarket",
        dueDate: "04/30/2018",
        complated: false
    }
]
```
<br> CarService add for exercise
```
[
	{
		id: 4,
		model: "Opel Corsa",
		plaka: "12KL12",
		driverName: "Oliver",
		modelYear: 2010
	},
	{
		id: 5,
		model: "Mercedes GLA",
		plaka: "XCVR312",
		driverName: "Teressa",
		modelYear: 2017
	},
	{
		id: 6,
		model: "Renault Megan",
		plaka: "123UV1234",
		driverName: "Namık",
		modelYear: 2005
	},
	{
		id: 7,
		model: "Skoda Kodiak",
		plaka: "14mn34",
		driverName: "Demi",
		modelYear: 2017
	}
]
```
 
