package com.taskmanagment.TaskManagmentSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagment.TaskManagmentSystem.Model.Friends;

public interface FriendsRepository extends JpaRepository<Friends, Long> {

	List<Friends> findByReceiverId(long id);
	List<Friends> findByInitiatorId(long id);
	Void deleteByInitiatorIdAndReceiverId(long id1,long id2);
}
