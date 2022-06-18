package com.study.loginableweb.controller;


import com.study.loginableweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RestAPIController {

    @GetMapping
    @ResponseBody
    public String home(){
        return "home";
    }
}
