/**
 * SignInController
 * ----------------------------
 * Handles Sign In functionality.
 * 
 * - GET "/signin" → Displays the login form.
 * - POST "/signin" → Processes login submission.
 *   * Uses Jakarta validation for form inputs.
 *   * Checks against hardcoded test credentials:
 *       username: testuser / password: Password123!
 *   * On success → redirects to signinSuccess.html with welcome message.
 *   * On failure → redisplays form with error message.
 * 
 * TODO: Replace hardcoded check with DB-driven authentication in future.
 */

package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.SignInModel;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/signin")
public class SignInController {
    
    // Show sign-in form
    @GetMapping("/")
    public String signInForm(Model model) {
    	model.addAttribute("showNavbar", Boolean.FALSE);
        model.addAttribute("signInModel", new SignInModel());
        return "signin";
    }

    // Handle form submission
    @PostMapping("/")
    public String processSignIn(
            @Valid SignInModel signInModel,
            BindingResult bindingResult,
            Model model) {

    	if (bindingResult.hasErrors()) {
    	    model.addAttribute("loginError", "Please correct the errors below and try again.");
    	    return "signin";
    	}


        // Temporary hard-coded validation (replace with DB later)
        if ("testuser".equals(signInModel.getUsername()) && "Password123!".equals(signInModel.getPassword())) {
            model.addAttribute("username", signInModel.getUsername());
            return "signinSuccess";
        } else {
            model.addAttribute("loginError", "Invalid username or password.");
            return "signin";
        }
    }
}
