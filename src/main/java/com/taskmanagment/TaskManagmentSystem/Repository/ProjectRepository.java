package com.taskmanagment.TaskManagmentSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagment.TaskManagmentSystem.Model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
//	List<Project> findByUserId(long id);
}
