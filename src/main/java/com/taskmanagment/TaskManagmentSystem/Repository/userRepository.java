package com.taskmanagment.TaskManagmentSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagment.TaskManagmentSystem.Model.User;

public interface userRepository extends JpaRepository<User, Long> {
	
	  User findByEmailAndPassword(String email, String password);
	  User findByEmail(String email);
}
