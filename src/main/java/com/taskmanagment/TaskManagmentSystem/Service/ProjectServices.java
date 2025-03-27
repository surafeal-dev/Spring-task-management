package com.taskmanagment.TaskManagmentSystem.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagment.TaskManagmentSystem.Model.Catagory;
import com.taskmanagment.TaskManagmentSystem.Model.Project;
import com.taskmanagment.TaskManagmentSystem.Model.Team;
import com.taskmanagment.TaskManagmentSystem.Model.TeamMember;
import com.taskmanagment.TaskManagmentSystem.Model.User;
import com.taskmanagment.TaskManagmentSystem.Repository.ProjectRepository;
import com.taskmanagment.TaskManagmentSystem.Repository.TeamMemberRepository;


@Service
public class ProjectServices {

	@Autowired
	private ProjectRepository repo;
	
	@Autowired
	private TeamMemberRepository memberrepo;
	
	
	
	public List<Project> listAll(){
		return repo.findAll();
		
	}
	
	public Project get(long Id) {
		return repo.findById(Id).get();
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	};
	
	public void save(Project project) {
		
		repo.save(project);
		
	}
	public List<Project> findProjects(long id) {
		
		List<TeamMember> teamsmember = memberrepo.findByUserId(id);
		List<Project> allprojects = repo.findAll();
		List<Project> foundprojects=  new ArrayList<>();
		
		
		for (Project project : allprojects) {
			Team team1=new Team();
			team1=project.getTeam();
			User user = new User();
			user=project.getUser();
			if(project.getCatagory()==Catagory.Team){
				System.out.println(team1.getTeam_name()+" Team Entered");
				for (TeamMember teammember : teamsmember) {
					
					
					Team team2=new Team();
					team2=teammember.getTeam();
					
					if(team1.getTeam_id()==team2.getTeam_id()) {
						foundprojects.add(project);
						System.out.println(team1.getTeam_name()+"Team Exited");
					}
				}
				
			}else if(project.getCatagory()==Catagory.Individual && user.getId()==id) {
				System.out.println("Individual Entered "+user.getId()+user.getFirstname());
				foundprojects.add(project);
			}
			
		}
		
		return foundprojects;
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
}
