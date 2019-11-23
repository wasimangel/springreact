package com.myvision.springreact.web;


import com.myvision.springreact.domain.Project;
import com.myvision.springreact.services.MapValidationErrorService;
import com.myvision.springreact.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    private MapValidationErrorService validationErrorService;


    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProject();

    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@Valid @PathVariable String projectId) {
//        ResponseEntity<?> errormap = validationErrorService.mapValidationService(result);
//        if (errormap != null) return errormap;

        return new ResponseEntity<>(projectService.getProjectByProjectIdentifier(projectId), HttpStatus.OK);

    }


    @PostMapping("")
    public ResponseEntity<?> createOrUpdateProject(@Valid @RequestBody Project project, BindingResult result) {


        ResponseEntity<?> errormap = validationErrorService.mapValidationService(result);
        if (errormap != null) return errormap;
        projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);

    }


    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Project with Id '" + projectId + "' Is Deleted", HttpStatus.OK);
    }
}
