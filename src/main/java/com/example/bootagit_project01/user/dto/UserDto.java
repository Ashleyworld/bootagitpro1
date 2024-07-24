package com.example.bootagit_project01.user.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @Setter(AccessLevel.NONE)
    private Long userid;
    private String username;
    private String password;
    private String email;

}
