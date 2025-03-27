package com.taskmanagment.TaskManagmentSystem.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.taskmanagment.TaskManagmentSystem.Model.Project;
import com.taskmanagment.TaskManagmentSystem.Model.ProjectStatus;

import java.time.LocalDate;
import java.util.List;

@Component
public class ProjectExpirationScheduler {

    private final ProjectServices projectService;

    public ProjectExpirationScheduler(ProjectServices projectService) {
        this.projectService = projectService;
    }

    // Run every day at midnight
    @Scheduled(cron = "0 * * * * ?")
    public void updateExpiredProjects() {
        List<Project> projects = projectService.listAll();
        LocalDate currentDate = LocalDate.now();

        for (Project project : projects) {
//        	System.out.println("Due Date:"+project.getDue_date());
//        	System.out.println("Current Date:"+currentDate);
            if (project.getDue_date().isBefore(currentDate) && project.getProject_status() == ProjectStatus.Pending) {
                project.setProject_status(ProjectStatus.Expired);;
                projectService.save(project);
            }
        }
    }
}
