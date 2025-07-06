
-- Insertar Usuarios (Teacher y Student)
INSERT INTO users (name, email, user_type) VALUES ('Juan Pérez', 'juan.perez@example.com', 'TEACHER');
INSERT INTO users (name, email, user_type) VALUES ('María García', 'maria.garcia@example.com', 'TEACHER');
INSERT INTO users (name, email, user_type) VALUES ('Carlos López', 'carlos.lopez@example.com', 'STUDENT');
INSERT INTO users (name, email, user_type) VALUES ('Ana Martínez', 'ana.martinez@example.com', 'STUDENT');
INSERT INTO users (name, email, user_type) VALUES ('Pedro Sánchez', 'pedro.sanchez@example.com', 'STUDENT');

-- Obtener IDs para facilitar las inserciones (en un script real, usarías subconsultas o los ID generados)
-- SELECT id FROM USERS WHERE email = 'juan.perez@example.com'; -- Assuming ID 1
-- SELECT id FROM USERS WHERE email = 'maria.garcia@example.com'; -- Assuming ID 2
-- SELECT id FROM USERS WHERE email = 'carlos.lopez@example.com'; -- Assuming ID 3
-- SELECT id FROM USERS WHERE email = 'ana.martinez@example.com'; -- Assuming ID 4
-- SELECT id FROM USERS WHERE email = 'pedro.sanchez@example.com'; -- Assuming ID 5

-- Insertar Cursos
INSERT INTO COURSES (name, descriptions, teacher_id) VALUES ('Matemáticas I', 'Curso introductorio de matemáticas', 1); -- Taught by Juan Pérez
INSERT INTO COURSES (name, descriptions, teacher_id) VALUES ('Programación Avanzada', 'Tópicos avanzados de programación', 2); -- Taught by María García
INSERT INTO COURSES (name, descriptions, teacher_id) VALUES ('Física General', 'Principios fundamentales de la física', 1); -- Taught by Juan Pérez

-- Insertar Tareas (Tasks)
INSERT INTO TASKS (title, description, publication_date, course_id) VALUES ('Tarea 1: Álgebra Lineal', 'Resolver ejercicios del capítulo 3', '2025-07-01', 1);
INSERT INTO TASKS (title, description, publication_date, course_id) VALUES ('Proyecto Final: App Web', 'Desarrollar una aplicación full-stack', '2025-06-25', 2);
INSERT INTO TASKS (title, description, publication_date, course_id) VALUES ('Lectura 1: Movimiento', 'Leer capítulos 1 y 2 del libro de texto', '2025-07-03', 3);
INSERT INTO TASKS (title, description, publication_date, course_id) VALUES ('Tarea 2: Cálculo Diferencial', 'Ejercicios de derivación', '2025-07-08', 1);

-- Insertar Inscripciones de Estudiantes a Cursos (StudentCourse)
INSERT INTO STUDENT_COURSE (student_id, course_id) VALUES (3, 1); -- Carlos López in Matemáticas I
INSERT INTO STUDENT_COURSE (student_id, course_id) VALUES (3, 2); -- Carlos López in Programación Avanzada
INSERT INTO STUDENT_COURSE (student_id, course_id) VALUES (4, 1); -- Ana Martínez in Matemáticas I
INSERT INTO STUDENT_COURSE (student_id, course_id) VALUES (4, 3); -- Ana Martínez in Física General
INSERT INTO STUDENT_COURSE (student_id, course_id) VALUES (5, 2); -- Pedro Sánchez in Programación Avanzada

-- Insertar Calificaciones (Grades)
-- Asumiendo los IDs de STUDENT_COURSE: 1 (Carlos-Matemáticas), 2 (Carlos-Programación), 3 (Ana-Matemáticas), etc.
INSERT INTO GRADES (nota, student_course_id) VALUES ('85', 1); -- Carlos en Matemáticas
INSERT INTO GRADES (nota, student_course_id) VALUES ('A-', 2); -- Carlos en Programación
INSERT INTO GRADES (nota, student_course_id) VALUES ('78', 3); -- Ana en Matemáticas
INSERT INTO GRADES (nota, student_course_id) VALUES ('B+', 4); -- Ana en Física
INSERT INTO GRADES (nota, student_course_id) VALUES ('92', 5); -- Pedro en Programación
-- Make sure table names match exactly (case-sensitive if quoted or specific DB config)
-- For single table inheritance, Teacher and Student data goes into USERS table.
--INSERT INTO USERS (name, email, user_type) VALUES
--('John Doe', 'john.doe@example.com', 'TEACHER'),
--('Jane Smith', 'jane.smith@example.com', 'STUDENT'),
--('Alice Johnson', 'alice.j@example.com', 'STUDENT');

-- If TEACHERS and STUDENTS are not separate tables in H2 (due to single table inheritance),
-- these lines would cause an error. Remove them if that's the case.
-- If they ARE separate tables (e.g., JOINED inheritance), ensure correct IDs.
-- INSERT INTO TEACHERS (id) VALUES (1);
-- INSERT INTO STUDENTS (id) VALUES (2), (3);
--/*
--INSERT INTO COURSES (name, descsriptions, teacher_id) VALUES -- Ensure 'descriptions' is the correct column name
--('Introduction to Programming', 'Learn the basics of coding.', 1),
--('Database Management', 'Understand relational databases.', 1);

--INSERT INTO TASKS (id, title, description, publication_date, course_id) VALUES
--('Assignment 1', 'Complete the fsirst programming assignment.', '2025-07-01', 101),
--('Quiz 1', 'Take the first database quiz.', '2025-07-05', 102);

-- Link students to courses (Many-to-Many)
--INSERT INTO STUDENT_COURSE (student_id, course_id) VALUES
--(2, 101),
--(3, 101),
--(2, 102);