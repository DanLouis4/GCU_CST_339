/**
 * UserProfileServiceInterface
 * -----------------------------------------
 * Defines the contract for managing user profile
 * and session-related business logic.
 * 
 * Responsibilities:
 * - Retrieve the currently logged-in user.
 * - Verify login status.
 * - Handle logout and session clearing.
 */

package com.gcu.business;

public interface UserProfileServiceInterface {

    /**
     * Retrieves the username of the current user.
     * @return The username string.
     */
    String getCurrentUsername();

    /**
     * Determines whether a user is currently logged in.
     * @return true if a valid session exists; otherwise false.
     */
    boolean isUserLoggedIn();

    /**
     * Logs out the current user and clears the session data.
     */
    void logout();
}
