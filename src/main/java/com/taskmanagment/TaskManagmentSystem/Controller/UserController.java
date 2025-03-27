package com.taskmanagment.TaskManagmentSystem.Controller;

import java.util.List;

import org.springframework.ui.Model;

import com.taskmanagment.TaskManagmentSystem.Model.User;
import com.taskmanagment.TaskManagmentSystem.Service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class UserController {
	
	private final UserService service;
	
	public UserController(UserService service) {
		this.service=service;
	}
	
	@GetMapping("/view")
	public ModelAndView view(Model model) {
		
		 ModelAndView modelAndView = new ModelAndView();
		 
		 try {
	            List<User> listUsers=service.listAll();
	            model.addAttribute("listUsers",listUsers);
	            modelAndView.setViewName("home"); // Set the view name to the Thymeleaf template
	        } catch (Exception e) {
	            // Log the exception or handle it accordingly
	            e.printStackTrace();
	            modelAndView.setViewName("error"); // Set the view name to the error template
	        }

	        return modelAndView;
	}
	
	@GetMapping("/")
	public ModelAndView viewHomePage(Model model) {
		
		 ModelAndView modelAndView = new ModelAndView();
		 
		 try {
	            modelAndView.setViewName("home_template"); // Set the view name to the Thymeleaf template
	        } catch (Exception e) {
	            // Log the exception or handle it accordingly
	            e.printStackTrace();
	            modelAndView.setViewName("error"); // Set the view name to the error template
	        }

	        return modelAndView;
	}
	
	@GetMapping("/signin")
	public ModelAndView signin(Model model) {
		
		 ModelAndView modelAndView = new ModelAndView();
		 
		 try {
			 model.addAttribute("searchuser", new User());
	            modelAndView.setViewName("signin"); // Set the view name to the Thymeleaf template
	        } catch (Exception e) {
	            // Log the exception or handle it accordingly
	            e.printStackTrace();
	            modelAndView.setViewName("error"); // Set the view name to the error template
	        }

	        return modelAndView;
	}
	
	@RequestMapping("/searchforuser")
	public ModelAndView searchuser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		
		 ModelAndView modelAndView = new ModelAndView();
		 
		 try {
			 User user = service.finduser(email, password);
			 if (user != null) {
		            // User found, proceed with authentication
				 session.setAttribute("userid", user.getId());
				 session.setAttribute("userfullname", user.getFirstname()+" "+user.getLastname());
				 session.setAttribute("useremail", user.getEmail());
				 modelAndView.setViewName("redirect:/projects");
		            return modelAndView; // Redirect to projectview page
		        } else {
		            // User not found or invalid credentials, handle accordingly
		        	modelAndView.setViewName("redirect:/signin?error");
		            return modelAndView; // Redirect to signin page with error query parameter
		           
		        }
			
	        } catch (Exception e) {
	            // Log the exception or handle it accordingly
	            e.printStackTrace();
	            modelAndView.setViewName("error"); // Set the view name to the error template
	        }

	        return modelAndView;
	}
	
	@GetMapping("/createaccount")
	public ModelAndView createaccount(Model model) {
		
		 ModelAndView modelAndView = new ModelAndView();
		 
		 try {
			 	model.addAttribute("newuser", new User());
	            modelAndView.setViewName("createaccount"); // Set the view name to the Thymeleaf template
	        } catch (Exception e) {
	            // Log the exception or handle it accordingly
	            e.printStackTrace();
	            modelAndView.setViewName("error"); // Set the view name to the error template
	        }

	        return modelAndView;
	}
	
	@PostMapping("/save")
	public ModelAndView saveUser(@ModelAttribute("newuser") User user, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			
			
			
			 User existinguser = service.searchforuser(user.getEmail());
			 if (existinguser == null) {
		            // User not found, proceed with creating user
				 service.save(user);
				 modelAndView.setViewName("projectview");
				 session.setAttribute("userid", user.getId());
				 session.setAttribute("userfullname", user.getFirstname()+" "+user.getLastname());
				 session.setAttribute("useremail", user.getEmail());
				 modelAndView.setViewName("redirect:/projects");
		            return modelAndView; // Redirect to projectview page
		        } else {
		            // User not found or invalid credentials, handle accordingly
		        	modelAndView.setViewName("redirect:/createaccount?error");
		            return modelAndView; // Redirect to signin page with error query parameter
		           
		        }
			
	        } catch (Exception e) {
	            // Log the exception or handle it accordingly
	            e.printStackTrace();
	            modelAndView.setViewName("error"); // Set the view name to the error template
	        }
		
		return modelAndView;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable long id){
		ModelAndView modelAndView =  new ModelAndView();
		service.deleteById(id);
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
	@GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        // Invalidating the session
        session.invalidate();
        // Redirecting to the login page
        ModelAndView modelAndView =  new ModelAndView();
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	
}
