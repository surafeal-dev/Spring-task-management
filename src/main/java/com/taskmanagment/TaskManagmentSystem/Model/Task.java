package com.taskmanagment.TaskManagmentSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long task_id;
	private String task_description;
	@Enumerated(EnumType.STRING)
    private Status status;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;

	
	
	public Task() {
		super();
	}

	public Task(String task_description, Status status, Project project) {
		super();
		this.task_description = task_description;
		this.status = status;
		this.project = project;
	}



	public Task(Long task_id, String task_description, Status status, Project project) {
		super();
		this.task_id = task_id;
		this.task_description = task_description;
		this.status = status;
		this.project = project;
	}

	public Long getTask_id() {
		return task_id;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	public String getTask_description() {
		return task_description;
	}

	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
	
}
