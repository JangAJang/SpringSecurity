package com.SpringSecurity.basic.controller;


import com.SpringSecurity.basic.model.User;
import com.SpringSecurity.basic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping({"", "/"})
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public String manager(){
        return "manager";
    }

    @GetMapping("/login")
    public String login(){
        return "loginForm";
    }

    @GetMapping("/joinProc")
    @ResponseBody
    public String joinProc(User user){

        return "login";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }


}
