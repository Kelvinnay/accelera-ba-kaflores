package com.example.cursos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "GRADES")
public class Grade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nota;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_course_id")
    private StudentCourse studentCourse;

}
