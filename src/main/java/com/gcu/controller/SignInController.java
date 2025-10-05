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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.business.SignInServiceInterface;
import com.gcu.model.SignInModel;

@Controller
public class SignInController {

    @Autowired
    private SignInServiceInterface service;

    @GetMapping("/signin")
    public String displayLogin(Model model) {
        model.addAttribute("title", "Login Form");
        model.addAttribute("signinModel", new SignInModel());
        return "signin";
    }

    @PostMapping("/doSignIn")
    public String doLogin(SignInModel model, Model viewModel) {
        boolean authenticated = service.authenticate(model.getUsername(), model.getPassword());
        if(authenticated) {
            System.out.println("User authenticated successfully!");
            return "signinSuccess";
        } else {
            viewModel.addAttribute("error", "Invalid credentials");
            return "signin";
        }
    }
}
