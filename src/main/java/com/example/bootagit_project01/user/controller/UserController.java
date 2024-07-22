package com.example.bootagit_project01.user.controller;
/*
* Spring Framework에서 제공하는 어노테이션으로, 주로 RESTful 웹 서비스를 구현할 때 사용됩니다.
*  RESTful 웹 서비스는 HTTP 프로토콜을 사용하여 클라이언트와 서버 간의 상호 작용을 정의
*
*
*  @Controller와 @ResponseBody를 합친 기능을 합니다.
* 이는 RESTful 웹 서비스에서 JSON 또는 XML 형식의 응답을 쉽게 반환
* */

import com.example.bootagit_project01.user.dto.UserDto;
import com.example.bootagit_project01.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "view/home";
    }

    @GetMapping("/login")
    public String login() {
        return "";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.ok("User registered successfully");

    }

}
