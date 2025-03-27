package com.taskmanagment.TaskManagmentSystem.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.taskmanagment.TaskManagmentSystem.Model.Project;
import com.taskmanagment.TaskManagmentSystem.Model.ProjectStatus;
import com.taskmanagment.TaskManagmentSystem.Model.Status;
import com.taskmanagment.TaskManagmentSystem.Model.Task;
import com.taskmanagment.TaskManagmentSystem.Model.TaskListWrapper;
import com.taskmanagment.TaskManagmentSystem.Model.Team;
import com.taskmanagment.TaskManagmentSystem.Model.TeamMember;
import com.taskmanagment.TaskManagmentSystem.Model.User;
import com.taskmanagment.TaskManagmentSystem.Service.TeamMemberService;
import com.taskmanagment.TaskManagmentSystem.Service.ProjectServices;
import com.taskmanagment.TaskManagmentSystem.Service.TaskService;
import com.taskmanagment.TaskManagmentSystem.Service.TeamService;
import com.taskmanagment.TaskManagmentSystem.Service.UserService;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/projects")
public class ProjectController {

	private final UserService userservice;
	private final ProjectServices projectservice;
	private final TeamService teamservice;
	private final TaskService taskservice;
	private final TeamMemberService teammemberservice;
	
	
	public ProjectController(UserService userservice,ProjectServices projectservice,TeamService teamservice,TaskService taskservice,TeamMemberService teammemberservice) {
		this.userservice=userservice;
		this.projectservice=projectservice;
		this.teamservice=teamservice;
		this.taskservice=taskservice;
		this.teammemberservice=teammemberservice;
	}
	
	@GetMapping//(/PROJECTS)
	public ModelAndView projects(Model model, HttpSession session) {
		 ModelAndView modelAndView = new ModelAndView();
		 List<Project> projectList = projectservice.findProjects((long)session.getAttribute("userid"));
		 model.addAttribute("projectList",projectList);
		 modelAndView.setViewName("projectview");
		 return modelAndView;
	}
	
	@GetMapping("/newproject")
	public ModelAndView newproject(Model model, HttpSession session) {
		
//		model.addAttribute("taskList", new TaskListWrapper());
		model.addAttribute("project",new Project());
		List<Team> team = new ArrayList<>();
		List<TeamMember> teammembers = new ArrayList<>();
		teammembers=teammemberservice.listmembersbyid((long)session.getAttribute("userid"));
		for (TeamMember teamMember : teammembers) {
			team.add(teamMember.getTeam());
		}
		
		model.addAttribute("teamlist",team);
		ModelAndView modelandview=new ModelAndView();
		modelandview.setViewName("newproject");
		return modelandview;
	}
	
	@PostMapping("/saveproject")
    public ModelAndView saveItems(@ModelAttribute("project") Project project,@RequestParam("dynamicInput") String[] dynamicInputs, HttpSession session) {
        
		
		System.out.println("done");
		System.out.println(project.getProject_name());
		System.out.println(project.getCatagory());
		System.out.println(project.getDue_date());

		User user = new User();
		project.setProject_status(ProjectStatus.Pending);
		user.setId((long) session.getAttribute("userid"));
		project.setUser(user);
		projectservice.save(project);
		
		if (project.getTeam() != null) {
			Team team=project.getTeam(); 
		System.out.println(team.getTeam_id()+". "+team.getTeam_name());
		}else {
			System.out.println("null");
		}
		
        for (String string : dynamicInputs) {
        	Task task=new Task();
			System.out.println("data:"+string);
			task.setProject(project);
			task.setTask_description(string);
			task.setStatus(Status.Unfinished);
			
			taskservice.save(task);
		}
		ModelAndView modelandview=new ModelAndView();
		modelandview.setViewName("redirect:/projects");
        return modelandview;
    }
	
	
	@GetMapping("/viewprojectandtask/{id}")
	public ModelAndView viewproject(Model model, HttpSession session,@PathVariable long id) {
		
		ModelAndView modelandview = new ModelAndView("projectpresentaiton");
		Project project = projectservice.get(id);
		List<Task> tasks=taskservice.findTasks(id);
		
		
		model.addAttribute("proj", project);
		model.addAttribute("tasks", tasks);
		
	return  modelandview;
	}
	
	@PostMapping("/saveprogress/{id}")
    public ModelAndView saveprogress(@PathVariable long id, @RequestParam(name="tasks",required = false) String[] checkedtasks, HttpSession session) {
        
		List<Task> tasks = taskservice.findTasks(id);
		Project project=projectservice.get(id);
		boolean finished=true;
		
		if(project.getProject_status()!=ProjectStatus.Expired) {
			

			if(checkedtasks!=null) {
			for (Task task : tasks) {
				
				System.out.println("value:"+id+"."+task);
				
				
				for (String checkedtask : checkedtasks) {
					
						if(task.getTask_id()==Long.parseLong(checkedtask)) {
							task.setStatus(Status.Finished);
							taskservice.save(task);
							break;
						}else {
							task.setStatus(Status.Unfinished);
							taskservice.save(task);
							
						}
					
					}
				
				if(task.getStatus()==Status.Unfinished) {
					finished=false;
				}
				
				}
			
			}else {
				for (Task task : tasks) {
					task.setStatus(Status.Unfinished);
					taskservice.save(task);
					finished=false;
				}
			}
			
			
			if(finished) {
				project.setProject_status(ProjectStatus.Completed);
				projectservice.save(project);
				System.out.println("Set to Complete");
			}else {
				project.setProject_status(ProjectStatus.Pending);
				projectservice.save(project);
				System.out.println("Set to Pending");
			}
			
			
		}
		
		
		ModelAndView modelandview=new ModelAndView();
		modelandview.setViewName("redirect:/projects");
        return modelandview;
	}
	
	@GetMapping("/edit/{id}")
    public ModelAndView editproject(@PathVariable long id, HttpSession session,Model model) {
	
		ModelAndView modelandview = new ModelAndView("editproject");
		Project project = projectservice.get(id);
		List<Task> tasks=taskservice.findTasks(id);
		
		
		model.addAttribute("project",new Project());
		List<Team> teamlist = new ArrayList<>();
		List<TeamMember> teammembers = new ArrayList<>();
		teammembers=teammemberservice.listmembersbyid((long)session.getAttribute("userid"));
		for (TeamMember teamMember : teammembers) {
			teamlist.add(teamMember.getTeam());
		}
		
		model.addAttribute("teamlist",teamlist);
		
		
		Team currentteam=new Team();
		
		model.addAttribute("project", project);
		model.addAttribute("tasks",tasks);
		if(project.getTeam()==null) {
			currentteam.setTeam_id(0L);
		}else {
			currentteam=project.getTeam();
		}
		model.addAttribute("currentteam",currentteam);
		
		return  modelandview;
	}
	
	@PostMapping("/savechanges/{id}")
    public ModelAndView savechange(@PathVariable long id,@ModelAttribute("project") Project project,@ModelAttribute("currentteam") Team team,@RequestParam("dynamicInput") String[] dynamicInputs, HttpSession session) {
 
		Project overwrittenproj =projectservice.get(id);
		overwrittenproj.setProject_name(project.getProject_name());
		overwrittenproj.setCatagory(project.getCatagory());
		overwrittenproj.setDue_date(project.getDue_date());
		if(team.getTeam_id()!=null) {
			overwrittenproj.setTeam(team);
		}else {
			overwrittenproj.setTeam(null);
		}
		
		
		System.out.println(project.getId());
		System.out.println(project.getProject_name());
		System.out.println(project.getCatagory());
		System.out.println(project.getDue_date());
		
		System.out.println(team.getTeam_id());
		System.out.println(team.getTeam_name());
		
		List<Task> overwritetask= taskservice.findTasks(id);
		List<Task> newtasks= new ArrayList<>();
		for (String task : dynamicInputs) {
			Task sampletask= new Task();
			sampletask.setTask_description(task);
			sampletask.setStatus(Status.Unfinished);
			sampletask.setProject(overwrittenproj);
			newtasks.add(sampletask);
		}
		
		int insertedtasks=0;
		boolean projectcompleted=true;
		
		for (int i = 0; i < newtasks.size(); i++) {
			
//			
//		    System.out.println(taskB.getTask_id());
//		    System.out.println(taskB.getTask_description());
//		    
		    Task taskA = newtasks.get(i);
//		    
//		    
//		    System.out.println(taskB.getTask_id());
//		    System.out.println(taskB.getTask_description());
		    
		    if(i==overwritetask.size() || i>overwritetask.size()) {//if the new tasks are larger
		    	taskservice.save(taskA);
		    	projectcompleted=false;
				overwrittenproj.setProject_status(ProjectStatus.Pending);
		    }else {//overwrite tasks in the same location
		    	Task taskB = overwritetask.get(i);
		    	
		    	if(!taskB.getTask_description().trim().equals(taskA.getTask_description().trim())) {
		    		System.out.println(taskA.getTask_description());
		    		System.out.println(taskB.getTask_description());
		    		taskB.setStatus(Status.Unfinished);
		    		projectcompleted=false;
		    		overwrittenproj.setProject_status(ProjectStatus.Pending);
		    	}
		    	if(taskB.getStatus()==Status.Unfinished) {//if it's not a substituted task yet not completed
		    		projectcompleted=false;
		    	}
		    	
		    	taskB.setTask_description(taskA.getTask_description());
		    	taskservice.save(taskB);
		    	
		    }
		    insertedtasks++;
		}
		
		//delete excess tasks
		if(overwritetask.size()>newtasks.size()) {
			for (int i = insertedtasks; i < overwritetask.size(); i++) {
				Task taskB = overwritetask.get(i);
				taskservice.delete(taskB.getTask_id());
			}
		}
		if(projectcompleted) {
			overwrittenproj.setProject_status(ProjectStatus.Completed);
		}else {
			overwrittenproj.setProject_status(ProjectStatus.Pending);
		}
		projectservice.save(overwrittenproj);
		ModelAndView modelandview=new ModelAndView();
		modelandview.setViewName("redirect:/projects");
        return modelandview;
//	}
//	
        }
	
	@RequestMapping("/delete/{id}")
	public ModelAndView deleteEmployee(@PathVariable long id) {
		
		
		taskservice.deleteRowsByForeignKeyId(id);
		projectservice.delete(id);
		ModelAndView modelandview=new ModelAndView();
		modelandview.setViewName("redirect:/projects");
        return modelandview;
		
	}
	
}
