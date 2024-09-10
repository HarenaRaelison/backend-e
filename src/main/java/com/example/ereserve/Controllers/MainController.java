package com.example.ereserve.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {
    @RequestMapping("/")
    public String main() {
        return "welcome, let's get started !  ";
    }
    @RequestMapping("/user")
            public Principal user(Principal user){
     return user;
    }
}
