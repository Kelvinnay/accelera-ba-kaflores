package com.example.cursos.repository;

import com.example.cursos.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
}