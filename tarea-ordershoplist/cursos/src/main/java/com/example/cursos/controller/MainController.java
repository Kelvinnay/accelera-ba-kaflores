package com.example.cursos.controller;

import com.example.cursos.dto.course.CursoDetailsResponseDto;
import com.example.cursos.dto.course.CursoResponseDto;
import com.example.cursos.dto.user.CreateUserRequestDto;
import com.example.cursos.dto.user.ListUserDetailsResponseDto;
import com.example.cursos.dto.user.ListUserResponseDto;
import com.example.cursos.model.Course;
import com.example.cursos.model.Student;
import com.example.cursos.model.Teacher;
import com.example.cursos.model.User;
import com.example.cursos.repository.CourseRepository;
import com.example.cursos.repository.UserRepository;
import com.example.cursos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/main")
public class MainController {

    UserService userService;
    UserRepository userRepository;
    CourseRepository courseRepository;

    @Autowired
    MainController(UserService userService,UserRepository userRepository,CourseRepository courseRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody CreateUserRequestDto user) {
        User responset =userService.saveUser(user);
        return new ResponseEntity<>(responset, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListUserResponseDto> getAll() {
        List<User> lstUserResponse = userRepository.findAll();
        ListUserResponseDto user;
        List<ListUserDetailsResponseDto> lstDetalle = new ArrayList<>();
        ListUserDetailsResponseDto detalle;
        for(User i :lstUserResponse){
            detalle = new ListUserDetailsResponseDto(i.getName(),i.getEmail(),i.getUserType());
            lstDetalle.add(detalle);
            System.out.println();

        }
        user = new ListUserResponseDto(lstDetalle);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/cursos")
    public ResponseEntity<CursoResponseDto> getCursos() {
        //System.out.println(courseRepository.findCoursesJPQL().get(0).getName());
        List<Course> lstResponse = courseRepository.findAll();
        CursoResponseDto cursoResponseDto ;
        List<CursoDetailsResponseDto> lstDetalle = new ArrayList<>();
        CursoDetailsResponseDto detalle;
        for(Course i :lstResponse){
            detalle = new CursoDetailsResponseDto(i.getName(),i.getDescription());
            lstDetalle.add(detalle);
            System.out.println();
        }
        cursoResponseDto = new CursoResponseDto(lstDetalle);


        return new ResponseEntity<>(cursoResponseDto, HttpStatus.OK);
    }

}
