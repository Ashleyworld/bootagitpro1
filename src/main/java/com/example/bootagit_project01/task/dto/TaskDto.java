package com.example.bootagit_project01.task.dto;

import com.example.bootagit_project01.user.dto.UserDto;
import com.example.bootagit_project01.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private UserDto user;


    public void setUserId(Long userId) {
    }
}

