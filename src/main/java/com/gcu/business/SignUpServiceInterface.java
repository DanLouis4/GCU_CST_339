/**
 * SignUpServiceInterface
 * -----------------------------------------
 * Defines the contract for user registration functionality.
 * 
 * Responsibilities:
 * - Accept new user registration data from the controller.
 * - Validate and process registration requests.
 * - Return a boolean result to indicate success or failure.
 */

package com.gcu.business;

import com.gcu.model.UserModel;

public interface SignUpServiceInterface {

    /**
     * Registers a new user.
     * @param newUser The registration form data.
     * @return true if registration succeeds; false otherwise.
     */
    boolean register(UserModel newUser);
}
