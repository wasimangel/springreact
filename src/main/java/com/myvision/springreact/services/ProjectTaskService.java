package com.myvision.springreact.services;

import com.myvision.springreact.domain.Backlog;
import com.myvision.springreact.domain.ProjectTask;
import com.myvision.springreact.exceptions.ProjectNotFoundException;
import com.myvision.springreact.repositories.BacklogRepository;
import com.myvision.springreact.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        // exception : project not found


        try {
            // PTs to be added to a specific project , project != null, BL exits

            Backlog backlog = backlogRepository.getBacklogByProjectIdentifier(projectIdentifier);
            // set back log to project backlog
            projectTask.setBacklog(backlog);
            // we want our project sequence to be like this : IDPRO-1

            Integer BackLogSequence = backlog.getPTSecquence();
            // update the BL seq
            BackLogSequence++;

            // set backlog seq
            backlog.setPTSecquence(BackLogSequence);
//        add Sequence to project task
            projectTask.setProjectSequence(projectIdentifier + "-" + BackLogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            // Initial priority when priority is null

            if (projectTask.getPriority() == 0 || projectTask.getPriority() == null) {
                projectTask.setPriority(0);
            }
//        Initial status  when status is null
            if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
                projectTask.setStatus("TO_DO");
            }
            return projectTaskRepository.save(projectTask);
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project Not Found");
        }

    }


    public Iterable<ProjectTask> findByBacklogId(String backlog_id) {

        Backlog backlog = backlogRepository.getBacklogByProjectIdentifier(backlog_id);

        if (backlog == null) {
            throw new ProjectNotFoundException("Project Not Found. Please Try Again");
        }
        List<ProjectTask> projectTaskList = projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
//        if (projectTaskList.isEmpty()) {
//            throw new ProjectNotFoundException("Project Not Found");
//        } else
            {

            return projectTaskList;
        }
    }


    public ProjectTask getProjectTask(String backlog_id, String pt_id) {

        // make sure we are searching for existing backlog

        Backlog backlog = backlogRepository.getBacklogByProjectIdentifier(backlog_id);

        if (backlog == null) {
            throw new ProjectNotFoundException("Project Not Found. Please Try Again");
        }

        // make sure we are searching for existing task

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if (projectTask == null) {
            throw new ProjectNotFoundException("Project Task Not Found");
        }
        // make sure that the backlog_id and projectTask id belongs to same project

        if (!projectTask.getProjectIdentifier().toUpperCase().equals(backlog_id)) {
            throw new ProjectNotFoundException("Project Not Found");
        }

        return projectTask;
    }

    public ProjectTask updateProjectTask(ProjectTask updatedTask, String backlog_id, String pt_id) {

        getProjectTask(backlog_id, pt_id);
        return projectTaskRepository.save(updatedTask);
    }

    public void deleteProjectTask(String backlog_id, String pt_id) {

        // Backlog backlog = backlogRepository.getBacklogByProjectIdentifier(backlog_id);

        ProjectTask projectTask = getProjectTask(backlog_id, pt_id);
//        List<ProjectTask> pts = backlog.getProjectTasks();
//        pts.remove(projectTask);
//        backlogRepository.save(backlog);

        projectTaskRepository.delete(projectTask);

    }
}
