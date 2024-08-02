package com.example.bootagit_project01.user.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LoginDto {
    // 로그인 요청 dto

    @NotNull(message = "user 이름 입력은 필수입니다.")
    private String username;

    @NotNull(message = "이메일 입력은 필수입니다.")
    private String password;

}
