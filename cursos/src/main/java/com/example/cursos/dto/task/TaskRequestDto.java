package com.example.cursos.dto.task;

import com.example.cursos.model.Course;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@Getter
@Setter
public class TaskRequestDto {
    String title;
    String description;
    LocalDate publicationDate;
    Course course;

}
