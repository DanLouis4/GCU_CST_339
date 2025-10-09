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
 */

package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.dao.repository.UserRepository;
import com.gcu.model.UserModel;

@Service
public class SignUpService implements SignUpServiceInterface {

	@Autowired
	private UserRepository userRepository; 


    /**
     * Registers a new user.
     * 
     * @param newUser The registration form model.
     * @return true if registration succeeds; false otherwise.
     */
    @Override
    public boolean register(UserModel newUser) {
        return userRepository.create(newUser);
    }
}
