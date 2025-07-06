package com.example.cursos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@DiscriminatorValue("TEACHER")
//@Table(name = "TEACHERS")
public class Teacher extends User {
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    List<Course> courses;
}
