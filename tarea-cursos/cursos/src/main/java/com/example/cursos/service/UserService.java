package com.example.cursos.service;

import com.example.cursos.dto.user.CreateUserRequestDto;
import com.example.cursos.model.Student;
import com.example.cursos.model.Teacher;
import com.example.cursos.model.User;
import com.example.cursos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(CreateUserRequestDto user){
        User responset =null;
        if (user.getUserType().equals("TEACHER")) {
            User teacher = new Teacher();
            teacher.setName(user.getName());
            teacher.setEmail(user.getEmail());
            //teacher.setUserType(user.getUserType());
            responset = userRepository.save(teacher);
        }else {
            User student = new Student();
            student.setName(user.getName());
            student.setEmail(user.getEmail());
            //student.setUserType(user.getUserType());
            responset = userRepository.save(student);

        }
        return responset;

    }
}
