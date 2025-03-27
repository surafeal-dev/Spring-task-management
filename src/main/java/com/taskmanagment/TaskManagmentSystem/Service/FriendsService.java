package com.taskmanagment.TaskManagmentSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagment.TaskManagmentSystem.Model.Friends;
import com.taskmanagment.TaskManagmentSystem.Repository.FriendsRepository;

@Service
public class FriendsService {

	@Autowired
	private FriendsRepository repo;
	
	public void savefriend(Friends friend) {
		repo.save(friend);
	}

	public List<Friends> searchfriends(long id){
		
		List<Friends> friends1= repo.findByInitiatorId(id);
		List<Friends> friends2= repo.findByReceiverId(id);
		friends1.addAll(friends2);
		return friends1;
	}
	public void delete(Friends friend) {
		repo.delete(friend);;
	}
	
	
	
}
