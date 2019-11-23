package com.myvision.springreact.web;

import com.myvision.springreact.domain.ProjectTask;
import com.myvision.springreact.services.MapValidationErrorService;
import com.myvision.springreact.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {


    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrorService validationErrorService;


    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String backlog_id) {


        ResponseEntity<?> errormap = validationErrorService.mapValidationService(result);
        if (errormap != null) return errormap;
        ProjectTask returnValue = projectTaskService.addProjectTask(backlog_id, projectTask);
        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectTasks(@Valid @PathVariable String backlog_id) {
        return projectTaskService.findByBacklogId(backlog_id);
    }


    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTaskByPTSequence(@Valid @PathVariable String backlog_id, @PathVariable String pt_id) {
        ProjectTask projectTask = projectTaskService.getProjectTask(backlog_id, pt_id);

        return new ResponseEntity<>(projectTask, HttpStatus.OK);
    }


    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask updatedProjectTask, BindingResult result, @PathVariable String backlog_id, @PathVariable String pt_id) {
        ResponseEntity<?> errormap = validationErrorService.mapValidationService(result);
        if (errormap != null) return errormap;
        ProjectTask projectTask = projectTaskService.updateProjectTask(updatedProjectTask, backlog_id, pt_id);
        return new ResponseEntity<>(projectTask, HttpStatus.OK);
    }


    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@Valid @PathVariable String backlog_id, @PathVariable String pt_id) {
                projectTaskService.deleteProjectTask(backlog_id,pt_id);
        return new ResponseEntity<>("Project Task with id " + pt_id + " has been deleted", HttpStatus.OK);
    }


}
