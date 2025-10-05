/**
 * SignUpController
 * -----------------------------------------
 * Handles the user registration form flow.
 * 
 * Responsibilities:
 * - Display the sign-up form for new users.
 * - Validate submitted data using Jakarta validation.
 * - Delegate registration logic to SignUpService.
 * - If registration succeeds, store the user in session
 *   and redirect to the user profile page.
 * - If registration fails, return to the sign-up view
 *   with appropriate error messaging.
 */

package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.business.SignUpServiceInterface;
import com.gcu.model.UserModel;

import jakarta.validation.Valid;

@Controller
public class SignUpController {

    // Injects the service layer (IoC)
    @Autowired
    private SignUpServiceInterface service;

    /**
     * GET request handler for the registration page.
     * Initializes a blank SignUpModel for form binding.
     * 
     * @param model The UI model for view rendering.
     * @return The Thymeleaf template for the sign-up form.
     */
    @GetMapping("/signup")
    public String displayRegistration(Model model) {
        model.addAttribute("title", "Register Account");
        model.addAttribute("signUpModel", new UserModel());
        model.addAttribute("headerTemplate", "layouts/common-guest");
        return "signup";
    }

    /**
     * POST request handler for form submission.
     * Validates the registration form and delegates registration
     * to the SignUpService. If successful, redirects to the
     * user profile page and loads the user layout.
     * 
     * @param signUpModel The form data bound to the SignUpModel class.
     * @param bindingResult Captures validation errors.
     * @param model The UI model for passing data to the view.
     * @return Redirects to /userprofile on success or redisplays form on failure.
     */
    @PostMapping("/signup")
    public String processRegistration(
            @Valid @ModelAttribute UserModel signUpModel,
            BindingResult bindingResult,
            Model model) {

        // Return to form if validation fails
        if (bindingResult.hasErrors()) {
            model.addAttribute("headerTemplate", "layouts/common-guest");
            return "signup";
        }

        // Attempt registration through service layer
        boolean registered = service.register(signUpModel);

        // On success, load the user profile and set user layout
        if (registered) {
            model.addAttribute("headerTemplate", "layouts/common-user");
            return "redirect:/userprofile";
        }

        // On failure, redisplay the form with an error message
        model.addAttribute("error", "Registration failed. Please try again.");
        model.addAttribute("headerTemplate", "layouts/common-guest");
        return "signup";
    }
}
