package com.example.cursos.service;

import com.example.cursos.dto.user.CreateUserRequestDto;
import com.example.cursos.model.Student;
import com.example.cursos.model.Teacher;
import com.example.cursos.model.User;
import com.example.cursos.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_shouldSaveTeacherWhenUserTypeIsTeacher() {
        // Given
        CreateUserRequestDto createUserRequestDto = getDto("TEACHER");

        Teacher mockTeacher = new Teacher();
        mockTeacher.setId(1);
        mockTeacher.setName("John Doe");
        mockTeacher.setEmail("john.doe@example.com");

        when(userRepository.save(any(Teacher.class))).thenReturn(mockTeacher);

        // When
        User savedUser = userService.saveUser(createUserRequestDto);

        // Then
        assertEquals("John Doe", savedUser.getName());
        assertEquals("john.doe@example.com", savedUser.getEmail());
        assertTrue(savedUser instanceof Teacher);
        verify(userRepository).save(any(Teacher.class));
    }

    @Test
    void saveUser_shouldSaveStudentWhenUserTypeIsStudent() {
        // Given
        CreateUserRequestDto createUserRequestDto = getDto("STUDENT");

        Student mockStudent = new Student();
        mockStudent.setId(2);
        mockStudent.setName("Jane Smith");
        mockStudent.setEmail("jane.smith@example.com");

        when(userRepository.save(any(Student.class))).thenReturn(mockStudent);

        // When
        User savedUser = userService.saveUser(createUserRequestDto);

        // Then
        assertEquals("Jane Smith", savedUser.getName());
        assertEquals("jane.smith@example.com", savedUser.getEmail());
        assertTrue(savedUser instanceof Student);
        verify(userRepository).save(any(Student.class));
    }

    @Test
    void saveUser_shouldSaveStudentByDefaultWhenUserTypeIsOther() {
        // Given
        CreateUserRequestDto createUserRequestDto = getDto("ADMIN");
        Student mockStudent = new Student();
        mockStudent.setId(3);
        mockStudent.setName("Bob Johnson");
        mockStudent.setEmail("bob.johnson@example.com");

        when(userRepository.save(any(Student.class))).thenReturn(mockStudent);

        // When
        User savedUser = userService.saveUser(createUserRequestDto);

        // Then
        assertEquals("Bob Johnson", savedUser.getName());
        assertEquals("bob.johnson@example.com", savedUser.getEmail());
        assertTrue(savedUser instanceof Student); // Expecting student as default
        verify(userRepository).save(any(Student.class));
    }

     public CreateUserRequestDto getDto(String tipo){
         CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
         if("TEACHER".equals(tipo)){
             createUserRequestDto = new CreateUserRequestDto();
             createUserRequestDto.setName("John Doe");
             createUserRequestDto.setEmail("john.doe@example.com");
             createUserRequestDto.setUserType("TEACHER");
         }
         if("STUDENT".equals(tipo)){
             createUserRequestDto = new CreateUserRequestDto();
             createUserRequestDto.setName("Jane Smith");
             createUserRequestDto.setEmail("jane.smith@example.com");
             createUserRequestDto.setUserType("STUDENT");
         }
         if("ADMIN".equals(tipo)){
             createUserRequestDto = new CreateUserRequestDto();
             createUserRequestDto.setName("Bob Johnson");
             createUserRequestDto.setEmail("bob.johnson@example.com");
             createUserRequestDto.setUserType("ADMIN"); // Neither TEACHER nor STUDENT
         }
        return createUserRequestDto;
     }

}
