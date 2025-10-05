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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gcu.business.UserProfileServiceInterface;

@Controller
public class UserProfileController {

    @Autowired
    private UserProfileServiceInterface profileService;

    /**
     * Displays the user profile page.
     * Redirects to sign-in if no active session exists.
     */
    @GetMapping("/userprofile")
    public String displayUserProfile(Model model) {
        if (!profileService.isUserLoggedIn()) {
            return "redirect:/signin";
        }

        model.addAttribute("title", "User Profile");
        model.addAttribute("username", profileService.getCurrentUsername());
        model.addAttribute("headerTemplate", "layouts/common-user");
        return "userprofile";
    }

    /**
     * Logs out the current user and clears session data.
     * Redirects to the sign-in page.
     */
    @GetMapping("/signout")
    public String signOut() {
        profileService.logout();
        return "redirect:/signin";
    }
}
