package com.gcu.business;

import com.gcu.dao.repository.UserRepository;
import com.gcu.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SignUpService
 * ----------------------------
 * Handles registration logic for new users.
 * 
 * Responsibilities:
 * - Saves new user information to the database.
 * - Automatically assigns a default restaurant entry
 *   to any new user with the "RESTAURANT" role.
 */

@Service
public class SignUpService implements SignUpServiceInterface
{
    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user.
     * 
     * Handles the creation of a new user record in the database.
     * This version no longer auto-generates a default restaurant
     * for users with the "RESTAURANT" role. Restaurant creation
     * will now be managed manually by the user through the
     * application interface.
     * 
     * @param newUser The registration form model.
     * @return true if registration succeeds; false otherwise.
     */
    @Override
    public boolean register(UserModel newUser)
    {
        try
        {
            // Attempt to create the new user
            boolean success = userRepository.create(newUser);

            if (!success)
            {
                System.err.println("User registration failed for: " + newUser.getUsername());
                return false;
            }

            System.out.println("User registered successfully: " + newUser.getUsername());
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("An error occurred during registration for user: " + newUser.getUsername());
            return false;
        }
    }
}
