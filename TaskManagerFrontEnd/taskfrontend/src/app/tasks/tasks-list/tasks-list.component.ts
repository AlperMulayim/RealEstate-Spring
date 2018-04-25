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
