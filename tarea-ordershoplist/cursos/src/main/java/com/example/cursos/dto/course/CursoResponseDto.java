package com.example.cursos.dto.course;


import java.util.List;

public class CursoResponseDto {
    List<CursoDetailsResponseDto> cursos;

    public CursoResponseDto(List<CursoDetailsResponseDto> cursos) {
        this.cursos = cursos;
    }

    public List<CursoDetailsResponseDto> getCursos() {
        return cursos;
    }

    public void setCursos(List<CursoDetailsResponseDto> cursos) {
        this.cursos = cursos;
    }
}
