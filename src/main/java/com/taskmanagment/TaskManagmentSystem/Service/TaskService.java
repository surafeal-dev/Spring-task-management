package com.taskmanagment.TaskManagmentSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagment.TaskManagmentSystem.Model.Task;
import com.taskmanagment.TaskManagmentSystem.Repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository repo;
	
public void save(Task task) {
		
		repo.save(task);
		
	}
public List<Task> findTasks(long id){
	return repo.findByProjectId(id);
}

public void delete(long Id) {
	repo.deleteById(Id);
}

public void deleteRowsByForeignKeyId(Long id) {
	
	List<Task> deletabletasks=repo.findByProjectId(id);
	
	for (Task task : deletabletasks) {
		repo.deleteById((long)task.getTask_id());
	}
	
    
}

}
