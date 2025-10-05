/**
 * MainController
 * ----------------------------
 * Handles the root ("/") request.
 * Displays the landing page (main.html) with Speed-E-Eats and its logo, 
 * and Sign In / Sign Up buttons.
 * 
 * No business logic yet — primarily for showing the homepage.
 */


package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model) {
    		
    	model.addAttribute("headerTemplate", "layouts/common-guest");    	
    	model.addAttribute("title", "Speed-E-Eats");
    	
        return "main";
    }
}


