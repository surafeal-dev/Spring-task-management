package com.taskmanagment.TaskManagmentSystem.Model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Long id;
	private String project_name;
	@Enumerated(EnumType.STRING)
    private Catagory catagory;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate due_date;
	@Enumerated(EnumType.STRING)
	private ProjectStatus project_status;
	@ManyToOne
	@JoinColumn(name="project_leader")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="team")
	private Team team;
	
	
	public Project() {
		super();
	}


	public Project(Long id, String project_name, Catagory catagory, LocalDate due_date, ProjectStatus project_status,
			User user, Team team) {
		super();
		this.id = id;
		this.project_name = project_name;
		this.catagory = catagory;
		this.due_date = due_date;
		this.project_status = project_status;
		this.user = user;
		this.team = team;
	}


	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getProject_name() {
		return project_name;
	}


	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}


	public Catagory getCatagory() {
		return catagory;
	}


	public void setCatagory(Catagory catagory) {
		this.catagory = catagory;
	}


	public LocalDate getDue_date() {
		return due_date;
	}


	public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
	}


	public ProjectStatus getProject_status() {
		return project_status;
	}


	public void setProject_status(ProjectStatus project_status) {
		this.project_status = project_status;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Team getTeam() {
		return team;
	}


	public void setTeam(Team team) {
		this.team = team;
	}
	
	
	
}

