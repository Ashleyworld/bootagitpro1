package com.example.bootagit_project01.user.user.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class UserDto {
    private Long userid;
    private String username;
    private String password;
    private String email;


}
