/**
 * GlobalModelAttributes
 * -----------------------------------------
 * Injects common model attributes into all controllers.
 * Ensures that the logged-in user's name is available
 * across all views that use the common-user layout.
 */

package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gcu.business.UserSession;


@ControllerAdvice // Allows global configuration and behavior to be applied across all controllers in the application
public class GlobalModelAttributes {

	@Autowired
	private UserSession userSession;

    /**
     * Adds the username and role to the model for all templates.
     * If no user is logged in, this remains null.
     */
    @ModelAttribute("username")
    public String username() {
    	return userSession.getUsername();
    }
    
    @ModelAttribute("role")
    public String role() {
    	return userSession.getRole();
    }
}
