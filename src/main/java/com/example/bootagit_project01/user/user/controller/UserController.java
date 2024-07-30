package com.example.bootagit_project01.user.user.controller;
/*
* Spring Framework에서 제공하는 어노테이션으로, 주로 RESTful 웹 서비스를 구현할 때 사용됩니다.
*  RESTful 웹 서비스는 HTTP 프로토콜을 사용하여 클라이언트와 서버 간의 상호 작용을 정의
*
*
*  @Controller와 @ResponseBody를 합친 기능을 합니다.
* 이는 RESTful 웹 서비스에서 JSON 또는 XML 형식의 응답을 쉽게 반환
* */

import com.example.bootagit_project01.user.user.dto.UserDto;
import com.example.bootagit_project01.user.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired //의존관계를 주입:외부에서 주입
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "view/home";
    }


    @GetMapping("/userlist")
    public ResponseEntity<List<UserDto>> getUserList() {
        //  모든 유저 조회

        List<UserDto> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/new")
    public String createUser()
    {
        return "user";
    }

    @PostMapping("/new")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){

        UserDto createdUser = userService.joinUser(userDto);

        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("delete/{userid}")

    public ResponseEntity<Void> deleteUser(@PathVariable Long userid){

        userService.deleteUser(userid);

        return ResponseEntity.noContent().build();
        // 빈 ResponseEntity와 함께 http 상태코드 204 (no content) 반환
    }

    @PostMapping("update/{userid}")
    public  ResponseEntity<UserDto> updateUser(@PathVariable Long userid, @RequestBody UserDto userDto){

        UserDto updatedUser = userService.updateUser(userid, userDto);

        return ResponseEntity.ok(updatedUser);

    }


}
