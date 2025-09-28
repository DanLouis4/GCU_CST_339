/**
 * MainController
 * ----------------------------
 * Handles the root ("/") request.
 * Displays the landing page (main.html) with BUSINESSNAME, logo, 
 * and Sign In / Sign Up buttons.
 * 
 * No business logic yet — primarily for showing the homepage.
 */


package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model) {
    	
    	model.addAttribute("showNavbar", Boolean.FALSE); // Attempts to hide the navbar from homepage
    	model.addAttribute("title", "Speed-E-Eats");
    	
        return "main";
    }
}


