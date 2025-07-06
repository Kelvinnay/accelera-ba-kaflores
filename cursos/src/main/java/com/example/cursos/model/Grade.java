package com.example.cursos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "GRADES")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nota;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_course_id")
    private StudentCourse studentCourse;

}
