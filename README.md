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

## Angular Install 
### Install 
https://cli.angular.io/ command : npm install -g @angular/cli
### Adding to project 
 1 . Add the project :  ng new frontend ( to main directory ) 
 <br>2 . Generate Component : ng g c tasks 
 <br>3 . Generate tasks-add : ng g c tasks/task-add
 <br>4 . Generate task-list : ng g c tasks/task-list
 ### Adding BootStrap 
 Installing the bootstrap 
 ```
 npm install --save bootstrap
 ```
 add this part to angular-cli.json 
   ```
   "styles": [
        "../node_modules/bootstrap/dist/css/bootsrap.min.css",
        "styles.css"
      ],
```

### Task Manager Part - 3 
## Developing the Frontend
1 . Create the app.component.html  puts the <app-tasks>  task.component.html task component
```
<div class="container">
    <app-tasks></app-tasks>
</div>
```
2 . Create the tasks.component.html  - includes the app-task-add and app-task-list

```
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">Tasks</div>
            <div class="panel-body">
                <div class="panel-content"><app-tasks-add></app-tasks-add></div>
                <div class="panel-content"><app-tasks-list></app-tasks-list></div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
```

3 . Create the app-tasks-add input component 
```
<div class="form-group">
    <input type="text" class="form-control" placeholder="Add New Task">
</div>
```

4. Create the app-tasks-list list component 
```
<div class="list-group">
    <a href="#" class="list-group-item">First item</a>
    <a href="#" class="list-group-item">Second item</a>
    <a href="#" class="list-group-item">Third item</a>
</div>
````
5. Create the tasks.model.ts this is the model 
```
export class Task{
    public id: number;
    public name: string;
    public complated : boolean;
    public dueDate : string;


    constructor(id:number, name:string, complated:boolean, dueDate:string) {
        this.id = id;
        this.name = name;
        this.complated = complated;
        this.dueDate = dueDate;
    }
}
```

6 . Genereate the tasks-list.components.ts 
<br> tasks: Task[]=[] array of tasks 
```
import { Component, OnInit } from '@angular/core';
import {Task} from "../task.model";


@Component({
  selector: 'app-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.css']
})
export class TasksListComponent implements OnInit {


    tasks: Task[] =[];

    constructor() { }

    ngOnInit() {
        this.tasks.push(new Task(1,"Go to Schhool",true,"07/08/18"));
        this.tasks.push(new Task(1,"Format the PC",true,"07/05/18"));
        this.tasks.push(new Task(1,"Take a ticket for Flight",true,"07/12/18"));
    }
    
     getDueDateLabel(task:Task){
        return task.complated ? 'label-success' : 'label-primary';
    }

    onTaskChange(event,task){
        console.log("Task has changed");
    }

}
```
7 . update the task-list.component to list dynamically 
```
<ul class ="list-group">
    <li *ngFor="let task of tasks" class="list-group-item">
        <div class="task-checkbox">
            <input type="checkbox"
                   class="list-child"
                   (change)="onTaskChange($event,task)"
                   [checked]="task.completed">

            <span ngClass= "{{task.completed ? 'name completed' : 'name'}}" > {{ task.name}} </span>
            <span class= "label  {{ getDueDateLabel(task)}} pull-right"> {{task.dueDate}} </span>
        </div>
    </li>
</ul>
```
 ### Task Manager Part - 3 
 Connecting the frontend to server ( frontend  localhost:4200 , server localhost:8800).
 <br> 1 . Creating the file task.service.ts uses HttpClient
 ```
 import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class TaskService{

    constructor(private http: HttpClient){

    }

    getTasks(){
        return this.http.get("/api/tasks");
    }
}
````
<br> 2 . Add the TaskService and HttpClientModule to app.module.ts
```

@NgModule({
  declarations: [
    AppComponent,
    TasksComponent,
    TasksAddComponent,
    TasksListComponent
  ],
    imports: [
        BrowserModule,
        HttpClientModule
    ],
  providers: [TaskService],
  bootstrap: [AppComponent]
})
export class AppModule { }
```
<br> 3 . Update the task-list.component.ts This is the task list frontend updater 
```
export class TasksListComponent implements OnInit {


    tasks: Task[] =[];

    constructor( private taskService:TaskService) { }

    ngOnInit() {
        return this.taskService.getTasks()
            .subscribe(
                (tasks: any[] )=>{
                    this.tasks = tasks;
                },
                (error)=>console.log(error)
            );
    }

    getDueDateLabel(task:Task){
        return task.complated ? 'label-success' : 'label-primary';
    }

    onTaskChange(event,task){
        console.log("Task has changed");
    }

}
```
<br> 4 . Create a proxy-conf.json on frontend folder  proxy settings for connection 
```
{
    "/api": {
        "target": "http://localhost:8080",
        "secure": false
    }
}
```

<br> 5 . Update the package.json to start the new proxy setting
```
 "start": "ng serve --proxy-config proxy-conf.json",
```
<br> 6 .  Start the app  - > npm start  Now frontend connected to the server


 ### Task Manager Part - 4 Send Info to Server
 Check the selected task done ( input from frontend sending to server ) 
 <br> This is done by http.post() operation  tasks.service.ts update 
 ```
  checkTask(task: Task,checked: boolean){
        task.complated = checked;
        return this.http.post('/api/tasks/save',task);
   }
 ```
 update the task-list.component.ts onTaskChange()   Now user check sended to server
 ```
    onTaskChange(event,task){
        console.log("Task has changed");
        this.taskService.checkTask(task,event.target.checked).subscribe();
    }
  ```   

  ### Task Manager Part -5 Input from User From
  Get the input from user and send to the server. 
  <br>1 .  task-add.component.html udate with keyup.enter ( enter key detect ) 
  
```
<div class="form-group">
    <input
        type="text"
        class="form-control"
        placeholder="Add New Task"
        (keyup.enter)="onTaskAdd($event)">
</div>
```
 <br> 2 . task.service.ts add the addTask() method for sending server the task.
 
```
 addTask(task:Task){
      return this.http.post('/api/tasks/save',task);
  }
    
 ```
 
 <br> 3 . task-add.component.ts update onTaskAdd() method add for getting input value 
 <br for this operation ``` event.target.value``` is the input value.
 <br> take parameter as a taskService on constructor. 
 
 ```
 export class TasksAddComponent implements OnInit {

  constructor(private  taskService : TaskService) { }

  ngOnInit() {
  }

  onTaskAdd(event){
    let task:Task = new Task(event.target.value,false,"11/12/18");

    this.taskService.addTask(task).subscribe(
        (newTask : Task)=>{
            this.addTaskValue = ' ';
            this.taskService.onTaskAdded.emit(newTask);
        }
    )
  }
 ```
 <br> 4 . on the task-list.component.ts side subscribe the adding list
 ```
  this.taskService.onTaskAdded.subscribe(
         (task:Task)=>this.tasks.push(task)
    );
   ```
 
 
 
 
 
