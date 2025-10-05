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
import org.springframework.stereotype.Service;

@Service
public class UserProfileService implements UserProfileServiceInterface {

    @Autowired
    private UserSession userSession; // Injected per-session Bean.

    /**
     * Returns the username of the current logged-in user.
     */
    @Override
    public String getCurrentUsername() {
        return userSession.getUsername();
    }

    /**
     * Determines if a user is logged in by checking the session.
     */
    @Override
    public boolean isUserLoggedIn() {
        return userSession.isLoggedIn();
    }

    /**
     * Logs out the user and clears session data.
     */
    @Override
    public void logout() {
        userSession.clear();
    }
}
