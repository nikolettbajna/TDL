package com.qa.tdl_project.presistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.tdl_project.presistence.domain.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

}
