package com.myvision.springreact.repositories;


import com.myvision.springreact.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    Iterable<Project> findAllById(Iterable<Long> longs);

    @Override
    Iterable<Project> findAll();

    Project findProjectByProjectIdentifier(String projectIdentifier);
}
