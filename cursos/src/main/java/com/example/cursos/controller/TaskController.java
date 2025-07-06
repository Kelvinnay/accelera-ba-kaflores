package com.example.cursos.controller;

import com.example.cursos.dto.task.GradeResponseDto;
import com.example.cursos.dto.task.TaskRequestDto;
import com.example.cursos.dto.user.CreateUserRequestDto;
import com.example.cursos.model.Grade;
import com.example.cursos.model.Task;
import com.example.cursos.model.User;
import com.example.cursos.repository.GradeRepository;
import com.example.cursos.repository.TaskRepository;
import com.example.cursos.repository.UserRepository;
import com.example.cursos.service.GradeService;
import com.example.cursos.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    TaskRepository taskRepository;
    GradeRepository gradeRepository;
    GradeService gradeService;

    @Autowired
    TaskController(TaskRepository taskRepository, GradeRepository gradeRepository,GradeService gradeService) {
        this.taskRepository = taskRepository;
        this.gradeRepository = gradeRepository;
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<Task> saveTask(@RequestBody TaskRequestDto task) {
        Task tarea = new Task();
        tarea.setTitle(task.getTitle());
        tarea.setDescription(task.getDescription());
        tarea.setPublicationDate(task.getPublicationDate());

        Task responset =taskRepository.save(tarea);
        return new ResponseEntity<>(responset, HttpStatus.OK);
    }

    @GetMapping("/grade/{studentId}/{courseId}")
    public ResponseEntity<GradeResponseDto> getAllGradesForStudent(@PathVariable("studentId") Integer studentId,@PathVariable("courseId") Integer courseId) {
        System.out.println("studentId,courseId "+studentId+" ,"+ courseId);

        //List<Grade> lstUserResponse = gradeRepository.findGradesByStudentIdAndCourseId(studentId,courseId);
        GradeResponseDto grades = gradeService.getAllGradesForStudent(studentId,courseId);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @PostMapping("/grade")
    public ResponseEntity<GradeResponseDto> agregarNotas(@PathParam("idNota") Integer idNota,@PathParam("newNota") String newNota) {
        System.out.println("ID_NOTA = "+idNota);
        Optional<Grade> grade = gradeRepository.findById(idNota);

        if (grade.isPresent()) {
            Grade gradeToUpdate = grade.get();
            gradeToUpdate.setNota(newNota); // Update the 'nota' field
            Grade updatedGrade = gradeRepository.save(gradeToUpdate); // Save the changes
            GradeResponseDto grades = gradeService.getAllGradesForStudent(updatedGrade.getStudentCourse().getStudent().getId(),
                    updatedGrade.getStudentCourse().getCourse().getId());

            return new ResponseEntity<>(grades, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 404 if grade not found
        }
    }
}
