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
import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/signin")
public class SignInController {
    
    // Show sign-in form
    @GetMapping("/")
    public String signInForm(Model model, HttpServletRequest request) {
    	
    	boolean loggedIn = request.getSession().getAttribute("username") != null;
    	
    	model.addAttribute("headerTemplate", loggedIn ? "layouts/common-guest" : "layouts/common-user");    	
        model.addAttribute("signInModel", new SignInModel());
        return "signin";
    }

    // Handle form submission
    @PostMapping("/")
    public String processSignIn(
            @Valid SignInModel signInModel, BindingResult bindingResult, Model model, HttpServletRequest request) {

    	if (bindingResult.hasErrors()) {
    	    model.addAttribute("loginError", "Please correct the errors below and try again.");
    	    return "signin";
    	}

        // Temporary hard-coded validation (replace with DB later)
        if ("testuser".equals(signInModel.getUsername()) && "Password123!".equals(signInModel.getPassword())) {
        	
        	// retrieves and set session data        
        	request.getSession().setAttribute("username", signInModel.getUsername());
        	boolean loggedIn = request.getSession().getAttribute("username") != null;
        	
        	// selects the proper navBar
        	model.addAttribute("headerTemplate", loggedIn ? "layouts/common-user" : "layouts/common-guest");    	
        	model.addAttribute("username", signInModel.getUsername());
            model.addAttribute("title", "Speed-E-Eats");
            return "signinSuccess";
        } else {
            model.addAttribute("loginError", "Invalid username or password.");
            return "signin";
        }
    }
}
