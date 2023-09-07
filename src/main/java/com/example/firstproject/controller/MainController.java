package com.example.firstproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    @GetMapping("/js")
    public String main(){
        return "main";
    }
    @GetMapping("/ls")
    public String single(){
        return "single";
    }
}
