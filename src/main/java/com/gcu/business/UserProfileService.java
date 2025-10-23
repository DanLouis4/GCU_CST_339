/**
 * UserProfileService
 * -----------------------------------------
 * Implements UserProfileServiceInterface and provides
 * the concrete business logic for user profile operations.
 * 
 * Responsibilities:
 * - Retrieve user data from the session.
 * - Check login state using the session-scoped UserSession Bean.
 * - Clear session data when logging out.
 */

package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
public class UserProfileService implements UserProfileServiceInterface {

    @Autowired
    private UserSession userSession; // Injected per-session Bean.

    /**
     * Returns the username of the current logged-in user.
     */
    @Override
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName()))
        {
            return auth.getName();
        }
        return null;
    }

    /**
     * Checks if a user is logged in based on whether session data exists.
     * @return true if session username exists, false otherwise
     */
    @Override
    public boolean isUserLoggedIn() {
        return userSession.getUsername() != null;
    }

    /**
     * Logs out the user and clears session data.
     */
    @Override
    public void logout() {
        userSession.clear();
    }
}
