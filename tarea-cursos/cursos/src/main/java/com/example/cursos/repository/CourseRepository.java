package com.example.cursos.repository;

import com.example.cursos.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c.name,c.description  FROM Course c")
    List<Course> findCoursesJPQL();
}