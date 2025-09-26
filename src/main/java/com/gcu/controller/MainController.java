package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model) {
    	
    	model.addAttribute("showNavbar", Boolean.FALSE);
    	model.addAttribute("title", "BUSINESSSNAME");
    	
        return "main";
    }
}


