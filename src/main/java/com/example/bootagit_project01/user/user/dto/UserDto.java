package com.example.bootagit_project01.user.user.dto;


import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDto {
    @Id
    private Long userid;
    private String username;
    private String password;
    private String email;

}
