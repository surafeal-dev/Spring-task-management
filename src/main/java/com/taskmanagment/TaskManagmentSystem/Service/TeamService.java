package com.taskmanagment.TaskManagmentSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagment.TaskManagmentSystem.Model.Project;
import com.taskmanagment.TaskManagmentSystem.Model.Team;
import com.taskmanagment.TaskManagmentSystem.Repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository repo;
	public List<Team> listAll(){
		return repo.findAll();
		
	}
	
	public void save(Team team) {
		
		repo.save(team);
		
	}
	
}
