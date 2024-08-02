package com.example.bootagit_project01.user.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {

    @GetMapping("/admin")
    public String adminP(){

        return "admin Controller";
    }


}
