/**
 * SignInService
 * -----------------------------------------
 * Handles authentication logic for user sign-in.
 * Uses the injected UserSession Bean to persist
 * session data for the currently logged-in user.
 * 
 * Responsibilities:
 * - Validate user credentials (hardcoded for Milestone 3).
 * - Initialize session state for authenticated users.
 */

package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService implements SignInServiceInterface {

    @Autowired
    private UserSession userSession; // Session Bean managed by Spring.

    /**
     * Authenticates a user's credentials.
     * 
     * @param username The entered username.
     * @param password The entered password.
     * @return true if the credentials match; otherwise false.
     */
    @Override
    public boolean authenticate(String username, String password) {
        // Hardcoded test credentials
        if ("testuser".equals(username) && "Pass12345!".equals(password)) {
            userSession.setUsername(username); // Store username in session.
            return true;
        }
        return false;
    }
}
