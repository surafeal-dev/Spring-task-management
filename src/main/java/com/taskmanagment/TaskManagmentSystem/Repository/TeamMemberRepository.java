package com.taskmanagment.TaskManagmentSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagment.TaskManagmentSystem.Model.Project;
import com.taskmanagment.TaskManagmentSystem.Model.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

	List<TeamMember> findByUserId(long id);
}
