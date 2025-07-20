package com.example.cursos.service;

import com.example.cursos.dto.task.GradeResponseDto;
import com.example.cursos.model.Grade;
import com.example.cursos.repository.GradeRepository;
import com.example.cursos.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeService {

    GradeRepository gradeRepository;

    @Autowired
    GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }
    public GradeResponseDto getAllGradesForStudent(Integer studentId,Integer courseId){
        List<Grade> lstGrade = gradeRepository.findGradesByStudentIdAndCourseId(studentId,courseId);
        GradeResponseDto gradeRsp = new GradeResponseDto();
        for (Grade grade:lstGrade){
            gradeRsp.setNota(grade.getNota());
            gradeRsp.setEmail(grade.getStudentCourse().getStudent().getEmail());
            gradeRsp.setName(grade.getStudentCourse().getStudent().getName());
            gradeRsp.setCurso(grade.getStudentCourse().getCourse().getName());
            gradeRsp.setCodigo("idEstudiante = "+grade.getStudentCourse().getStudent().getId()+
                    " ,idCurso = "+grade.getStudentCourse().getCourse().getId()+" ,idNota = "+grade.getId());
        }
        System.out.println("GRADES"+gradeRsp);
        return gradeRsp;

    }
}
