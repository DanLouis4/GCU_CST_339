/**
 * SignUpService
 * -----------------------------------------
 * Implements the SignUpServiceInterface to handle
 * new user registration requests.
 * 
 * Responsibilities:
 * - Simulate a successful registration process.
 * - Store the registered user's username in the session
 *   (via the session-scoped UserSession Bean).
 * 
 * Note:
 * This implementation does not persist data to a database.
 * Database persistence will be implemented in Milestone 4.
 */

package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcu.model.UserModel;

@Service
public class SignUpService implements SignUpServiceInterface {

    // Injects the session-scoped Bean to track the logged-in user.
    @Autowired
    private UserSession userSession;

    /**
     * Registers a new user.
     * For Milestone 3, this simulates successful registration
     * without persisting data.
     * 
     * @param newUser The registration form model.
     * @return true if registration succeeds; false otherwise.
     */
    @Override
    public boolean register(UserModel newUser) {
        // Basic validation (to be replaced with DB logic later)
        if (newUser.getUsername() != null && newUser.getPassword() != null) {
            // Store username in session to simulate an active login
            userSession.setUsername(newUser.getUsername());
            return true;
        }
        return false;
    }
}
