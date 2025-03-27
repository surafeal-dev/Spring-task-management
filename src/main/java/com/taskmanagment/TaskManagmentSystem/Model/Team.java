package com.taskmanagment.TaskManagmentSystem.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long team_id;
	private String team_name;
	public Team() {
		super();
	}
	public Team(Long team_id, String team_name) {
		super();
		this.team_id = team_id;
		this.team_name = team_name;
	}
	public Team(String team_name) {
		super();
		this.team_name = team_name;
	}
	public Long getTeam_id() {
		return team_id;
	}
	public void setTeam_id(Long team_id) {
		this.team_id = team_id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	
	
	
}
