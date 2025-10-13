package com.gcu.business;

/**
 * SignInServiceInterface
 * ----------------------------
 * Defines the method for user authentication.
 */
public interface SignInServiceInterface {
	
    /**
     * Authenticates a user by username and password.
     * 
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return true if authentication succeeds; false otherwise.
     */
    boolean authenticate(String username, String password);
}
