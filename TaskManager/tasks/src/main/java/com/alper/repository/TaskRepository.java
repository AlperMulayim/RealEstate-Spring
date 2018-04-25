package com.alper.repository;

import com.alper.domain.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Alper on 25.04.2018.
 */
public interface TaskRepository extends CrudRepository<Task,Long> {
}
