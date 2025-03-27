package com.taskmanagment.TaskManagmentSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagment.TaskManagmentSystem.Model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
