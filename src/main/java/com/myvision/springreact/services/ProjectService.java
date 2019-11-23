package com.myvision.springreact.services;


import com.myvision.springreact.domain.Backlog;
import com.myvision.springreact.domain.Project;
import com.myvision.springreact.exceptions.ProjectIdException;
import com.myvision.springreact.repositories.BacklogRepository;
import com.myvision.springreact.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    BacklogRepository backlogRepository;


//    all method will be here to start

    public Project saveOrUpdateProject(Project project) {

        try {

            project.setProjectIdentifier(project.getProjectIdentifier().trim().toUpperCase());


            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().trim().toUpperCase());
            }

            if (project.getId() != null) {
                Backlog backlog = backlogRepository.getBacklogByProjectIdentifier(project.getProjectIdentifier().trim().toUpperCase());
                project.setBacklog(backlog);

            }
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project id '" + project.getProjectIdentifier().trim().toUpperCase() + "' Already Exist");
        }
    }


    public Project getProjectByProjectIdentifier(String projectIdentifier) {


        Project project = projectRepository.findProjectByProjectIdentifier(projectIdentifier.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project for  " + projectIdentifier + " this ID  Not Exist");

        }

        return project;
    }

    public Iterable<Project> findAllProject() {
        Iterable<Project> projectList = projectRepository.findAll();

        return projectList;
    }


    public void deleteProjectByIdentifier(String projectIdentifier) {
        Project project = projectRepository.findProjectByProjectIdentifier(projectIdentifier.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project for  " + projectIdentifier + " this ID  Not Exist");

        }
        projectRepository.delete(project);
    }
}
