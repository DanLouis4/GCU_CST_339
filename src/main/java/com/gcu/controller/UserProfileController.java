/**
 * UserProfileController
 * -----------------------------------------
 * Handles all profile-related navigation and
 * session management actions for authenticated users.
 * 
 * Responsibilities:
 * - Display the user profile view for authenticated users.
 * - Redirect unauthenticated users back to the sign-in page.
 * - Delegate session clearing and logout to the UserProfileService.
 */

package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.gcu.business.UserProfileServiceInterface;
import com.gcu.business.UserSession;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserProfileController {

	@Autowired
	private UserSession userSession;

    @Autowired
    private UserProfileServiceInterface profileService;

    /**
     * Displays the user profile page.
     * Redirects to sign-in if no active session exists.
     */
    @GetMapping("/userprofile")
    public String displayUserProfile(Model model) {
    	
    	System.out.println("The last name is " + userSession.getLastName());
    	System.out.println("The email is " + userSession.getEmail());
    	
        model.addAttribute("username", userSession.getUsername());
        model.addAttribute("firstName", userSession.getFirstName());
        model.addAttribute("lastName", userSession.getLastName());
        model.addAttribute("email", userSession.getEmail());
        model.addAttribute("title", "User Profile");
        model.addAttribute("headerTemplate", "layouts/common-user");
        return "userprofile";
    }

    /**
     * Logs out the current user and clears session data.
     * Redirects to the sign-in page.
     */
    @GetMapping("/signout")
    public String signout(HttpServletRequest request) {
        userSession.clear(); // same method as above
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return "redirect:/signin?logout=true";
    }
}
