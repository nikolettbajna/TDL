package com.qa.tdl_project.presistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.tdl_project.presistence.domain.ToDoList;

@Repository
public interface ToDoListRepo extends JpaRepository<ToDoList, Long> {

}
