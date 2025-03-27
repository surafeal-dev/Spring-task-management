package com.taskmanagment.TaskManagmentSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TeamMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamMemberId;
	@ManyToOne
	@JoinColumn(name="teamid")
    private Team team;
	@ManyToOne
	@JoinColumn(name="memberid")
    private User user;
	
	
	
	public TeamMember() {
		super();
	}



	public TeamMember(Team team, User user) {
		super();
		this.team = team;
		this.user = user;
	}



	public TeamMember(Long teamMemberId, Team team, User user) {
		super();
		this.teamMemberId = teamMemberId;
		this.team = team;
		this.user = user;
	}



	public Long getTeamMemberId() {
		return teamMemberId;
	}



	public void setTeamMemberId(Long teamMemberId) {
		this.teamMemberId = teamMemberId;
	}



	public Team getTeam() {
		return team;
	}



	public void setTeam(Team team) {
		this.team = team;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
