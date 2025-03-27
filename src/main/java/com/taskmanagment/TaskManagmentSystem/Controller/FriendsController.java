package com.taskmanagment.TaskManagmentSystem.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.taskmanagment.TaskManagmentSystem.Model.Friends;
import com.taskmanagment.TaskManagmentSystem.Model.Friendship_Status;
import com.taskmanagment.TaskManagmentSystem.Model.User;
import com.taskmanagment.TaskManagmentSystem.Service.FriendsService;
import com.taskmanagment.TaskManagmentSystem.Service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/friends")
public class FriendsController {

	private final FriendsService friendsService ;;
	private final UserService userservice;

	public FriendsController(FriendsService friendsService,UserService userservice) {
		this.userservice = userservice;
		this.friendsService= friendsService;
	}
	
	public User search(String email){
		return userservice.searchforuser(email);
	}
	
	@PostMapping(path="/search")
	public ModelAndView createfriendship(@RequestParam("dynamicInput") String dynamicInput,Model model, HttpSession session) {
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("FriendsSearch");
		
//		Friends friend = new Friends();
		User user1= new User();
		User user2=search(dynamicInput);
		user1.setId((long)session.getAttribute("userid"));
		model.addAttribute("suser", user2);
		
		
		List<User> Userfriends = new ArrayList<>();
		List<Friends> friends=friendsService.searchfriends((long) session.getAttribute("userid"));
		
		for (Friends friend : friends) {
			User user=friend.getInitiator();
			if(user.getId()==(long) session.getAttribute("userid")) {
				user=friend.getReceiver();
			}
			
			Userfriends.add(user);
			System.out.println("initiator: "+user.getFirstname());
		}
		
		model.addAttribute("friends", Userfriends);
		
//		
//		friend.setInitiator(user1);
//		friend.setReceiver(user2);
//		friend.setFriendship_status(Friendship_Status.Pending);
//		
//		
//		friendsService.savefriend(friend);
		
		
		
		return modelAndView;
		
	}
	@GetMapping
	public ModelAndView findfriends(Model model, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("FriendsHome");
		List<User> Userfriends = new ArrayList<>();
		List<Friends> friends=friendsService.searchfriends((long) session.getAttribute("userid"));
		
		for (Friends friend : friends) {
			User user=friend.getInitiator();
			if(user.getId()==(long) session.getAttribute("userid")) {
				user=friend.getReceiver();
			}
			
			Userfriends.add(user);
			System.out.println("initiator: "+user.getFirstname());
		}
		
		model.addAttribute("friends", Userfriends);
		return modelAndView;
		
	}
	
	@GetMapping(path="/sendrequest/{id}")
	public ModelAndView createfriendship(Model model, HttpSession session,@PathVariable long id) {
	
		Friends friend = new Friends();
		User user1= new User();
		User user2=userservice.find(id);
		user1.setId((long)session.getAttribute("userid"));
		model.addAttribute("suser", user2);
		friend.setInitiator(user1);
		friend.setReceiver(user2);
		friend.setFriendship_status(Friendship_Status.Pending);
		
		
		friendsService.savefriend(friend);

		
		List<User> Userfriends = new ArrayList<>();
		List<Friends> friends=friendsService.searchfriends((long) session.getAttribute("userid"));
		
		for (Friends f : friends) {
			User user=f.getInitiator();
			if(user.getId()==(long) session.getAttribute("userid")) {
				user=f.getReceiver();
			}
			
			Userfriends.add(user);
			System.out.println("initiator: "+user.getFirstname());
		}
		
		model.addAttribute("friends", Userfriends);
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("FriendsSearch");
		return modelandview;
	}
	
	@GetMapping(path="/delete/{id}")
	public ModelAndView deletefriendship(Model model,@PathVariable long id,@ModelAttribute("suser")User user2, HttpSession session) {
		
//		friendsService.deletefriendship(8, 11);
		
		
		List<User> Userfriends = new ArrayList<>();
		List<Friends> friends=friendsService.searchfriends((long) session.getAttribute("userid"));
		for (Friends f : friends) {
			User user=f.getInitiator();
			User user1=f.getReceiver();
			if(user1.getId()==id && user.getId()==(long) session.getAttribute("userid")) {
				friendsService.delete(f);
			}else if(user.getId()==id && user1.getId()==(long) session.getAttribute("userid")) {
				friendsService.delete(f);
			}
		}
		
		List<Friends> friends1=friendsService.searchfriends((long) session.getAttribute("userid"));
		for (Friends f : friends1) {
			User user=f.getInitiator();
			if(user.getId()==(long) session.getAttribute("userid")) {
				user=f.getReceiver();
			}
			
			Userfriends.add(user);
			System.out.println("initiator: "+user.getFirstname());
		}
		model.addAttribute("suser", user2);
		model.addAttribute("friends", Userfriends);
		ModelAndView modelandview = new ModelAndView();
		modelandview.setViewName("redirect:/friends");
		return modelandview;
	
	}
	
	
}
