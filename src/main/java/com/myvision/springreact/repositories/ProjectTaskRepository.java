package com.myvision.springreact.repositories;

import com.myvision.springreact.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    List<ProjectTask> findByProjectIdentifierOrderByPriority(String backlog_id);

    ProjectTask findByProjectSequence(String sequence);
}
