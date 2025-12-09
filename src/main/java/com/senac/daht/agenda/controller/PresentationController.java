package com.senac.daht.agenda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin("*")
public class PresentationController {

    @GetMapping({"/home", "/", ""})
    public String home() {
        return "forward:/index.html";
    }

}
