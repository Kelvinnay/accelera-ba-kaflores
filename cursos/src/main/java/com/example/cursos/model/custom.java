package com.example.cursos.model;

public class custom {
    //Course
    /*
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "STUDENT_COURSE", // Name of the association table
            joinColumns = @JoinColumn(name = "course_id"), // Column in student_course that refers to this (Course) entity's primary key
            inverseJoinColumns = @JoinColumn(name = "student_id") // Column in student_course that refers to the other (Student) entity's primary key

    )
    private List<Student> students;

     */
   // Student
    //@ManyToMany(mappedBy = "students",fetch = FetchType.LAZY)
    //private List<Course> courses;
    //@ManyToMany(mappedBy = "students")
    //private List<Course> courses = new ArrayList<>();

}
