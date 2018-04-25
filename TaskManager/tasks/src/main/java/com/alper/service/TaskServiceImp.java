package com.alper.service;

import com.alper.domain.Task;
import com.alper.repository.TaskRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Alper on 25.04.2018.
 */
@Service
public class TaskServiceImp implements TaskService {

    //generate the task repository for service
    private TaskRepository taskRepository;

    public TaskServiceImp(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Iterable<Task> listTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }
}
