package com.govnorganization.personalblog.personalblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonalBlogController {

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @PostMapping("/new_article")
    public String new_article(){

        return "new";
    }


}
