package com.example.cursos.repository;

import com.example.cursos.model.Course;
import com.example.cursos.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

    @Query("SELECT g FROM Grade g JOIN g.studentCourse sc JOIN sc.student s JOIN sc.course c WHERE s.id = :studentId AND c.id = :courseId")
    List<Grade> findGradesByStudentIdAndCourseId(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);
}