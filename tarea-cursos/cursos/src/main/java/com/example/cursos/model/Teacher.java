package com.example.cursos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("TEACHER")
//@Table(name = "TEACHERS")
public class Teacher extends User implements Serializable {
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    List<Course> courses;
}
