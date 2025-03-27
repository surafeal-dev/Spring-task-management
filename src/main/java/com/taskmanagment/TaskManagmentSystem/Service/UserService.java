package com.taskmanagment.TaskManagmentSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagment.TaskManagmentSystem.Model.User;
import com.taskmanagment.TaskManagmentSystem.Repository.userRepository;

@Service
public class UserService{

	@Autowired
	private userRepository repo;
		
	public List<User> listAll(){
		return repo.findAll();
		
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	};
	
	public void save(User user) {
		
		repo.save(user);
		
	}
	
	  public User finduser(String email, String password) {
		  return repo.findByEmailAndPassword(email, password);
	  }
	  public User searchforuser(String email) {
		  return repo.findByEmail(email);
	  }
	
	  public User find(long id) {
		  return repo.findById(id).get();
	  }
}
