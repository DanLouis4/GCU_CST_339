/**
 * UserSession
 * -----------------------------------------
 * Maintains the currently authenticated user's session data.
 * This class is managed by Spring with a @SessionScope annotation,
 * ensuring that a unique instance exists for each browser session.
 * 
 * Responsibilities:
 * - Store the username of the logged-in user.
 * - Track login state for the active session.
 * - Clear session data on logout or session expiration.
 */

package com.gcu.business;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {

    // Tracks the currently logged-in user's username.
    private String username;

    /**
     * Returns the username of the currently authenticated user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the current session user.
     * @param username The authenticated user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Checks if a user is currently logged in for this session.
     * @return true if a username is stored; otherwise false.
     */
    public boolean isLoggedIn() {
        return username != null;
    }

    /**
     * Clears session data, effectively logging the user out.
     */
    public void clear() {
        this.username = null;
    }
}
