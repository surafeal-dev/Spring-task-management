package com.taskmanagment.TaskManagmentSystem.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Friends {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "friendship_id")
	private Long id ;
	@Enumerated(EnumType.STRING)
	private Friendship_Status friendshipstatus;
	
	@ManyToOne
	@JoinColumn(name="friendshipinitiator")
	private User initiator;
	
	@ManyToOne
	@JoinColumn(name="friendshipreceiver")
	private User receiver ;

	
	
	
	public Friends() {
		super();
	}

	public Friends(Friendship_Status friendshipstatus, User initiator, User receiver) {
		super();
		this.friendshipstatus = friendshipstatus;
		this.initiator = initiator;
		this.receiver = receiver;
	}




	public Friends(Long id, Friendship_Status friendship_status, User initiator, User receiver) {
		super();
		this.id = id;
		this.friendshipstatus = friendshipstatus;
		this.initiator = initiator;
		this.receiver = receiver;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Friendship_Status getFriendshipstatus() {
		return friendshipstatus;
	}

	public void setFriendship_status(Friendship_Status friendship_status) {
		this.friendshipstatus = friendship_status;
	}

	public User getInitiator() {
		return initiator;
	}

	public void setInitiator(User initiator) {
		this.initiator = initiator;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
	
	
	
}
