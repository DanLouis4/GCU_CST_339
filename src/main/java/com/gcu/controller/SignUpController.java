package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.UserModel;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    // Show the sign-up form
    @GetMapping("/")
    public String signUpForm(Model model) {
        // Provide a new UserModel for form binding
        model.addAttribute("userModel", new UserModel());
        return "signup";
    }

    // Handle form submission
    @PostMapping("/")
    public String processSignUp(
            @Valid UserModel userModel,
            BindingResult bindingResult,
            Model model) {

        // If validation fails, reload the sign up form
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        // TODO: Save user to the database in a later milestone

        // Add user info to show on the success page
        model.addAttribute("userModel", userModel);
        return "signupSuccess";
    }
}
