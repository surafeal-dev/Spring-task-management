package com.taskmanagment.TaskManagmentSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.taskmanagment.TaskManagmentSystem.Model.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findByProjectId(long id);
	
	
}
