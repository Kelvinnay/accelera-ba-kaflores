package com.example.cursos.dto.user;

import java.util.List;

public class ListUserResponseDto {
    List<ListUserDetailsResponseDto> users;

    public ListUserResponseDto(List<ListUserDetailsResponseDto> users) {
        this.users = users;
    }

    public List<ListUserDetailsResponseDto> getUsers() {
        return users;
    }

    public void setUsers(List<ListUserDetailsResponseDto> users) {
        this.users = users;
    }
}
