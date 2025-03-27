package com.taskmanagment.TaskManagmentSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.taskmanagment.TaskManagmentSystem.Model.Team;
import com.taskmanagment.TaskManagmentSystem.Model.TeamMember;
import com.taskmanagment.TaskManagmentSystem.Model.User;
import com.taskmanagment.TaskManagmentSystem.Service.TeamMemberService;
import com.taskmanagment.TaskManagmentSystem.Service.TeamService;
import com.taskmanagment.TaskManagmentSystem.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/team")
public class TeamController {

private final TeamService teamservice;
private final TeamMemberService teammemberservice;
private final UserService userservice;
	
	public TeamController(TeamService teamservice,TeamMemberService teammemberservice,UserService userservice) {
		
		this.teamservice=teamservice;
		this.teammemberservice=teammemberservice;
		this.userservice= userservice;
	}
	
	@GetMapping("/createnewteam")
	public ModelAndView createnewteam(Model model) {
	
		model.addAttribute("userlist",userservice.listAll());
		model.addAttribute("team",new Team());
		ModelAndView modelandview=new ModelAndView();
		modelandview.setViewName("createteam");
		return modelandview;
	}
	
	@PostMapping("/saveteam")
    public ModelAndView saveTeam(@ModelAttribute("team") Team team,@RequestParam("member") String[] member, HttpSession session) {
		ModelAndView modelandview=new ModelAndView();
		try {
		System.out.println("done");
		System.out.println(team.getTeam_name());
		
		teamservice.save(team);
		User loggeduser = new User();
		loggeduser.setId((long) session.getAttribute("userid"));
		TeamMember currentuser= new TeamMember();
		currentuser.setUser(loggeduser);
		currentuser.setTeam(team);
		teammemberservice.save(currentuser);
		
		for (String string : member) {
			User user = new User();
			user.setId(Long.parseLong(string));
			TeamMember teammember= new TeamMember();
			System.out.println("data:"+string);
			teammember.setUser(user);
			teammember.setTeam(team);
			teammemberservice.save(teammember);
		}
		
		
		modelandview.setViewName("redirect:/projects/newproject");
        return modelandview;
        }catch(Exception e) {
        	// Log the exception or handle it accordingly
            e.printStackTrace();
            modelandview.setViewName("error"); // Set the view name to the error template
       
        }
		return modelandview;
	}
	
	
}
