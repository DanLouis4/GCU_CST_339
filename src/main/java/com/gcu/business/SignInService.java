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
import com.gcu.model.UserModel;
import com.gcu.dao.repository.UserRepository;

import com.gcu.dao.repository.UserRepository;
import com.gcu.model.UserModel;

@Service
public class SignInService implements SignInServiceInterface {

    @Autowired
    private UserSession userSession; // Session Bean managed by Spring.
    

    @Autowired
    private UserRepository userRepository;
    /**
     * Authenticates a user's credentials.
     * 
     * @param username The entered username.
     * @param password The entered password.
     * @return true if the credentials match; otherwise false.
     */
    @Override
    public boolean authenticate(String username, String password) {
        try
        {
            // Fetch user record from the database
            UserModel user = userRepository.findByUsername(username);

            // Check for valid credentials (plain-text for now, no BCrypt)
            if (user != null && password.equals(user.getPassword()))
            {
                // Populate session with user info
                userSession.setUsername(user.getUsername());
                userSession.setFirstName(user.getFirstName());
                userSession.setLastName(user.getLastName());
                userSession.setEmail(user.getEmail());
                userSession.setRole(user.getRole());
                userSession.setPasswordToken("********************");

                return true;
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
        }
}
