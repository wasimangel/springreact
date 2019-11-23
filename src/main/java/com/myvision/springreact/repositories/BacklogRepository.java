package com.myvision.springreact.repositories;

import com.myvision.springreact.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long> {


    Backlog getBacklogByProjectIdentifier(String projectIdentifier);
}
