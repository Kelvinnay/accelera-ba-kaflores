package com.example.cursos.service;

import com.example.cursos.dto.task.GradeResponseDto;
import com.example.cursos.model.Course;
import com.example.cursos.model.Grade;
import com.example.cursos.model.Student;
import com.example.cursos.model.StudentCourse;
import com.example.cursos.repository.GradeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTest {
    @Mock
    GradeRepository gradeRepository;

    @InjectMocks
    GradeService gradeService;

    @BeforeAll
    static void initAll(){
        System.out.println("Validando");
    }

    @Test
    void testGetAllGradesForStudents() throws Exception{
        //ARRANGE

        List<Grade> lstGradeMock = new ArrayList<>();
        GradeResponseDto gradeResponseDto = new GradeResponseDto();
        gradeResponseDto.setName("nombre");
        when(gradeRepository.findGradesByStudentIdAndCourseId(anyInt(),anyInt())).thenReturn(lstGradeMock);
        //when(gradeService.getAllGradesForStudent(anyInt(),anyInt())).thenReturn(gradeResponseDto);

        //ACT
        GradeResponseDto actualResponse = gradeService.getAllGradesForStudent(1,1);
        //GradeResponseDto actualResponseDto = actualResponse.get(0);

        //ASSERT
        //assertEquals("nombre",actualResponse.getName());
        assertNotNull(actualResponse);
    }

    /**
     * Test case: Verify the behavior when the repository returns a list of grades.
     * The service's current implementation maps only the last grade in the list.
     */
    @Test
    void testGetAllGradesForStudent_ReturnsLastGradeInList() {
        // --- ARRANGE ---

        // 1. Create mock data for the entities (Grade, StudentCourse, Student, Course)
        // We set up two mock grades to ensure the service processes both,
        // but ultimately returns the data from the second one.

        // Grade 1 Data (will be overwritten)
        Student student1 = new Student();
        student1.setId(10);
        student1.setEmail("alice@example.com");
        student1.setName("Alice");

        Course course1 = new Course();
        course1.setId(20);
        course1.setName("History");

        StudentCourse sc1 = new StudentCourse();
        sc1.setStudent(student1);
        sc1.setCourse(course1);

        Grade grade1 = new Grade();
        grade1.setId(100);
        grade1.setNota("75");
        grade1.setStudentCourse(sc1);

        // Grade 2 Data (the expected data in the final DTO)
        Student student2 = new Student();
        student2.setId(15);
        student2.setEmail("bob@example.com");
        student2.setName("Bob");

        Course course2 = new Course();
        course2.setId(25);
        course2.setName("Math");

        StudentCourse sc2 = new StudentCourse();
        sc2.setStudent(student2);
        sc2.setCourse(course2);

        Grade grade2 = new Grade();
        grade2.setId(101);
        grade2.setNota("90.5");
        grade2.setStudentCourse(sc2);

        List<Grade> mockGradeList = new ArrayList<>();
        mockGradeList.add(grade1);
        mockGradeList.add(grade2);

        // 2. Mock the repository call to return our list
        when(gradeRepository.findGradesByStudentIdAndCourseId(1, 1))
                .thenReturn(mockGradeList);

        // --- ACT ---
        GradeResponseDto resultDto = gradeService.getAllGradesForStudent(1, 1);

        // --- ASSERT ---
        assertNotNull(resultDto);

        // Verify the DTO contains the data from the LAST Grade (grade2)
        assertEquals(grade2.getNota(), resultDto.getNota());
        assertEquals(student2.getEmail(), resultDto.getEmail());
        assertEquals(student2.getName(), resultDto.getName());
        assertEquals(course2.getName(), resultDto.getCurso());

        // Verify the complex 'codigo' string generation from the last grade
        String expectedCodigo = "idEstudiante = 15 ,idCurso = 25 ,idNota = 101";
        assertEquals(expectedCodigo, resultDto.getCodigo());

        // Verify that the repository method was called with the correct parameters
        verify(gradeRepository).findGradesByStudentIdAndCourseId(1, 1);
    }

}
