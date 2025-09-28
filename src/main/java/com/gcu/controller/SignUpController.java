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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.UserModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    // Show the sign-up form
    @GetMapping("/")
    public String signUpForm(Model model, HttpServletRequest request) {
    	
    	boolean loggedIn = request.getSession().getAttribute("username") != null;
    	
    	model.addAttribute("headerTemplate", loggedIn ? "layouts/common-guest" : "layouts/common-user");
    	model.addAttribute("userModel", new UserModel());
        return "signup";
    }

    // Handle form submission
    @PostMapping("/")
    public String processSignUp(@Valid UserModel userModel, BindingResult bindingResult, Model model, HttpServletRequest request) {

        // If validation fails, reload the sign-up form with correct navbar
        if (bindingResult.hasErrors()) {
            boolean loggedIn = request.getSession().getAttribute("username") != null;
            model.addAttribute("headerTemplate", loggedIn ? "layouts/common-guest" : "layouts/common-guest");
            return "signup";
        }
        
        // retreats and sets session data
    	request.getSession().setAttribute("username", userModel.getUsername());
    	boolean loggedIn = request.getSession().getAttribute("username") == null;
    	
    	// sets the proper navBar
    	model.addAttribute("headerTemplate", loggedIn ? "layouts/common-guest" : "layouts/common-user");    	
    	model.addAttribute("username", userModel.getUsername());
        
        // TODO: Save user to the database in a later milestone

        // Add user info to show on the success page
        model.addAttribute("userModel", userModel);
        return "signupSuccess";
    }
}
