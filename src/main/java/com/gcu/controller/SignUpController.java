/**
 * SignUpController
 * ----------------------------
 * Handles user registration (Sign Up).
 * 
 * - GET "/signup" → Displays the registration form.
 * - POST "/signup" → Validates and processes submitted form.
 *   * Uses Jakarta validation annotations for required fields.
 *   * Enforces rules: 
 *       - username with no special characters
 *       - email format must be valid
 *       - password length 8–32 with at least one special character
 *   * On success → shows signupSuccess.html confirmation.
 *   * On validation errors → redisplays form with feedback messages.
 * 
 * TODO: Save new users to database once persistence layer is added.
 */
package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.business.SignUpServiceInterface;
import com.gcu.model.UserModel;

@Controller
public class SignUpController {

    @Autowired
    private SignUpServiceInterface service;

    @GetMapping("/signup")
    public String displayRegistration(Model model) {
        model.addAttribute("title", "Registration Form");
        model.addAttribute("userModel", new UserModel());
        return "signup";
    }

    @PostMapping("/doSignUp")
    public String doRegistration(UserModel model, Model viewModel) {
        boolean registered = service.register(model);
        if(registered) {
            System.out.println("User registered successfully!");
            return "signupSuccess";
        } else {
            viewModel.addAttribute("error", "Registration failed");
            return "signup";
        }
    }
}
