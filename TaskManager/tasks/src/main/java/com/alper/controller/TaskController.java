package com.alper.controller;

import com.alper.domain.Task;
import com.alper.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alper on 25.04.2018.
 */
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

    @PostMapping("/save")
    public Task saveTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }
}
