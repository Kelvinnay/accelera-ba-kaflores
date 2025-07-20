package com.example.cursos.repository;

import com.example.cursos.model.Task;
import com.example.cursos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
