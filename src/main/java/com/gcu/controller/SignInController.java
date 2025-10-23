package com.gcu.controller;
/**
 * SignInController
 * -----------------------------------------
 * Manages the sign-in view, form submission,
 * and user authentication workflow.
 * 
 * Responsibilities:
 * - Display the sign-in form to guests.
 * - Validate user input and delegate authentication to SignInService.
 * - Redirect authenticated users to their profile page.
 * - Return validation and login errors when necessary.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gcu.model.UserModel;

@Controller
public class SignInController {
    /**
     * GET request to display the sign-in form.
     * 
     * @param model The UI model used to populate the view.
     * @return The Thymeleaf template for the sign-in page.
     */
    @GetMapping("/signin")
    public String displaySignIn(Model model) {
        model.addAttribute("title", "Sign In");
        model.addAttribute("userModel", new UserModel());
        model.addAttribute("headerTemplate", "layouts/common-guest");
        return "signin";
    }
}