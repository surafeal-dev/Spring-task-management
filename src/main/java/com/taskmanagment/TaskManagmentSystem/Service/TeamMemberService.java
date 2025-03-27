package com.taskmanagment.TaskManagmentSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagment.TaskManagmentSystem.Model.TeamMember;
import com.taskmanagment.TaskManagmentSystem.Repository.TeamMemberRepository;

@Service
public class TeamMemberService {

	@Autowired
	private TeamMemberRepository repo;
	
	public List<TeamMember> listAll(){
		return repo.findAll();
		
	}
	
	public void save(TeamMember teammeber) {
		
		repo.save(teammeber);
		
	}
	
	public List<TeamMember> listmembersbyid(long id){
		return repo.findByUserId(id);
		
	}
	
}
