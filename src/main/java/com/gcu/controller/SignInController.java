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

package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.business.SignInServiceInterface;
import com.gcu.model.UserModel;

import jakarta.validation.Valid;

@Controller
public class SignInController {

    @Autowired
    private SignInServiceInterface service; // Business layer (IoC)

    /**
     * GET request to display the sign-in form.
     * 
     * @param model The UI model used to populate the view.
     * @return The Thymeleaf template for the sign-in page.
     */
    @GetMapping("/signin")
    public String displaySignIn(Model model) {
        model.addAttribute("title", "Sign In");
        model.addAttribute("signInModel", new UserModel());
        model.addAttribute("headerTemplate", "layouts/common-guest");
        return "signin";
    }

    /**
     * POST request to process the sign-in form submission.
     * Validates user input and delegates authentication
     * to the SignInService.
     * 
     * @param signInModel The form data bound to the SignInModel.
     * @param bindingResult Validation results from @Valid annotations.
     * @param model The model used to pass data back to the view.
     * @return Redirects to the user profile on success, or redisplays form on failure.
     */
    @PostMapping("/signin")
    public String processSignIn(
            @Valid @ModelAttribute UserModel signInModel,
            BindingResult bindingResult,
            Model model) {

        // If validation fails, return to sign-in page.
        if (bindingResult.hasErrors()) {
            model.addAttribute("headerTemplate", "layouts/common-guest");
            return "signin";
        }

        // Delegate authentication to service layer.
        boolean authenticated = service.authenticate(
                signInModel.getUsername(), signInModel.getPassword());

        // If successful, redirect to the user profile.
        if (authenticated) {
            model.addAttribute("headerTemplate", "layouts/common-user");
            return "redirect:/userprofile";
        }

        // On failure, redisplay form with error message.
        model.addAttribute("loginError", "Invalid username or password.");
        model.addAttribute("headerTemplate", "layouts/common-guest");
        return "signin";
    }
}
