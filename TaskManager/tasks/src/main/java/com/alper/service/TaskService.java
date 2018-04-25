package com.alper.service;

import com.alper.domain.Task;
import org.springframework.stereotype.Service;

/**
 * Created by Alper on 25.04.2018.
 */
@Service
public interface TaskService {
  Iterable<Task> listTasks();
  Task saveTask(Task task);
}

