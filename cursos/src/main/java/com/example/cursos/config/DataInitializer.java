package com.example.cursos.config;

// You would add this in a @Component, @Service, or @Configuration class
// to run on startup instead of data.sql, or use a CommandLineRunner
import com.example.cursos.model.*;
import com.example.cursos.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final TaskRepository taskRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final GradeRepository gradeRepository;


    public DataInitializer(UserRepository userRepository,CourseRepository courseRepository,
                           TaskRepository taskRepository,StudentCourseRepository studentCourseRepository,
                           GradeRepository gradeRepository) {
        this.userRepository = userRepository;
        this.courseRepository=courseRepository;
        this.taskRepository=taskRepository;
        this.studentCourseRepository=studentCourseRepository;
        this.gradeRepository=gradeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) { // Only insert if table is empty
           // Teacher juan = new Teacher();
           // juan.setName("Juan Pérez");
           // juan.setEmail("juan.perez@example.com");
           // juan.setUserType("TEACHER");
            // No need to set userType, Hibernate does it based on @DiscriminatorValue
            Teacher juan = new Teacher();
            juan.setName("Juan Pérez");
            juan.setEmail("juan.perez@example.com");
            juan.setUserType("TEACHER");

            Teacher maria = new Teacher();
            maria.setName("María García");
            maria.setEmail("maria.garcia@example.com");
            maria.setUserType("TEACHER");

            Student carlos = new Student();
            carlos.setName("Carlos López");
            carlos.setEmail("carlos.lopez@example.com");
            carlos.setUserType("STUDENT");

            Student ana = new Student();
            ana.setName("Ana Martínez");
            ana.setEmail("ana.martinez@example.com");
            ana.setUserType("STUDENT");

            Student pedro = new Student();
            pedro.setName("Pedro Sánchez");
            pedro.setEmail("pedro.sanchez@example.com");
            pedro.setUserType("STUDENT");

            userRepository.saveAll(Arrays.asList(juan, maria, carlos, ana, pedro));
            System.out.println("Users inserted.");

            // Insertar Usuarios (Teacher y Student)
            Teacher juans = new Teacher(); // Use Teacher class directly
            juan.setName("Juan Pérez");
            juan.setEmail("juan.perez@example.com");
            juans.setUserType("U");
            // No need to set userType, @DiscriminatorValue handles it

            Teacher marias= new Teacher(); // Use Teacher class directly
            maria.setName("María García");
            maria.setEmail("maria.garcia@example.com");
            // No need to set userType, @DiscriminatorValue handles it


            userRepository.saveAll(Arrays.asList(juan, maria));//, carlos, ana, pedro));
            System.out.println("Users inserted.");


            // 2. Insert Courses
            // Insertar Cursos
            Teacher teh=new Teacher();
            Course matematicasI = new Course();
            matematicasI.setName("Matemáticas I");
            matematicasI.setDescription("Curso introductorio de matemáticas");
            matematicasI.setTeacher((Teacher) juan);

            Course programacionAvanzada = new Course();
            programacionAvanzada.setName("Programación Avanzada");
            programacionAvanzada.setDescription("Tópicos avanzados de programación");
            programacionAvanzada.setTeacher((Teacher) maria);

            Course fisicaGeneral = new Course();
            fisicaGeneral.setName("Física General");
            fisicaGeneral.setDescription("Principios fundamentales de la física");
            fisicaGeneral.setTeacher((Teacher) juan);

            courseRepository.saveAll(Arrays.asList(matematicasI, programacionAvanzada, fisicaGeneral));
            System.out.println("Courses inserted.");

            // 3. Insert Tasks
            // Insertar Tareas (Tasks)
            Task tarea1Mat = new Task();
            tarea1Mat.setTitle("Tarea 1: Álgebra Lineal");
            tarea1Mat.setDescription("Resolver ejercicios del capítulo 3");
            tarea1Mat.setPublicationDate(LocalDate.of(2025, 7, 1));
            tarea1Mat.setCourse(matematicasI);

            Task proyectoFinalProg = new Task();
            proyectoFinalProg.setTitle("Proyecto Final: App Web");
            proyectoFinalProg.setDescription("Desarrollar una aplicación full-stack");
            proyectoFinalProg.setPublicationDate(LocalDate.of(2025, 6, 25));
            proyectoFinalProg.setCourse(programacionAvanzada);

            Task lectura1Fis = new Task();
            lectura1Fis.setTitle("Lectura 1: Movimiento");
            lectura1Fis.setDescription("Leer capítulos 1 y 2 del libro de texto");
            lectura1Fis.setPublicationDate(LocalDate.of(2025, 7, 3));
            lectura1Fis.setCourse(fisicaGeneral);

            Task tarea2Mat = new Task();
            tarea2Mat.setTitle("Tarea 2: Cálculo Diferencial");
            tarea2Mat.setDescription("Ejercicios de derivación");
            tarea2Mat.setPublicationDate(LocalDate.of(2025, 7, 8));
            tarea2Mat.setCourse(matematicasI);

            taskRepository.saveAll(Arrays.asList(tarea1Mat, proyectoFinalProg, lectura1Fis, tarea2Mat));
            System.out.println("Tasks inserted.");

            // 4. Insert StudentCourse (Enrollments)
            // Insertar Inscripciones de Estudiantes a Cursos (StudentCourse)
            StudentCourse carlosMatematicas = new StudentCourse();
            carlosMatematicas.setStudent(carlos);
            carlosMatematicas.setCourse(matematicasI);

            StudentCourse carlosProgramacion = new StudentCourse();
            carlosProgramacion.setStudent(carlos);
            carlosProgramacion.setCourse(programacionAvanzada);

            StudentCourse anaMatematicas = new StudentCourse();
            anaMatematicas.setStudent(ana);
            anaMatematicas.setCourse(matematicasI);

            StudentCourse anaFisica = new StudentCourse();
            anaFisica.setStudent(ana);
            anaFisica.setCourse(fisicaGeneral);

            StudentCourse pedroProgramacion = new StudentCourse();
            pedroProgramacion.setStudent(pedro);
            pedroProgramacion.setCourse(programacionAvanzada);

            studentCourseRepository.saveAll(Arrays.asList(carlosMatematicas, carlosProgramacion, anaMatematicas, anaFisica, pedroProgramacion));
            System.out.println("StudentCourse enrollments inserted.");

            // 5. Insert Grades
            // Insertar Calificaciones (Grades)
            Grade carlosMatematicasGrade = new Grade();
            carlosMatematicasGrade.setNota("85");
            carlosMatematicasGrade.setStudentCourse(carlosMatematicas);

            Grade carlosProgramacionGrade = new Grade();
            carlosProgramacionGrade.setNota("A-");
            carlosProgramacionGrade.setStudentCourse(carlosProgramacion);

            Grade anaMatematicasGrade = new Grade();
            anaMatematicasGrade.setNota("78");
            anaMatematicasGrade.setStudentCourse(anaMatematicas);

            Grade anaFisicaGrade = new Grade();
            anaFisicaGrade.setNota("B+");
            anaFisicaGrade.setStudentCourse(anaFisica);

            Grade pedroProgramacionGrade = new Grade();
            pedroProgramacionGrade.setNota("92");
            pedroProgramacionGrade.setStudentCourse(pedroProgramacion);

            gradeRepository.saveAll(Arrays.asList(carlosMatematicasGrade, carlosProgramacionGrade, anaMatematicasGrade, anaFisicaGrade, pedroProgramacionGrade));
            System.out.println("Grades inserted.");


        }
    }
}