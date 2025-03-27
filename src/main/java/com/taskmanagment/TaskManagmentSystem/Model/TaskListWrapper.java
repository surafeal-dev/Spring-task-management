package com.taskmanagment.TaskManagmentSystem.Model;

import java.util.List;

public class TaskListWrapper {
	private List<Task> taskList;

    // Getters and setters
	
	
	
    public List<Task> getTaskList() {
        return taskList;
    }

    public TaskListWrapper(List<Task> taskList) {
		super();
		this.taskList = taskList;
	}

	public TaskListWrapper() {
		super();
	}

	public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    
    
}
