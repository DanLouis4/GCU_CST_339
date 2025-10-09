package com.gcu.business;

import com.gcu.dao.repository.UserRepository;
import com.gcu.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Registers a new user.
     * 
     * After successful registration, if the user’s role
     * is "RESTAURANT", a default restaurant record is automatically
     * created and linked to the user. This allows restaurant
     * owners to immediately manage products without having to
     * create a restaurant entry manually.
     * 
     * @param newUser The registration form model.
     * @return true if registration succeeds; false otherwise.
     */
    @Override
    public boolean register(UserModel newUser)
    {
        boolean success = userRepository.create(newUser);

        // Auto-insert a default restaurant for RESTAURANT users
        if (success && "RESTAURANT".equalsIgnoreCase(newUser.getRole()))
        {
            try
            {
                String restaurantSql = """
                    INSERT INTO restaurants (name, owner_id, address, phone)
                    VALUES ('Speed-E-Eats Deli',
                            (SELECT id FROM users WHERE username = ? LIMIT 1),
                            '123 Main St, Phoenix, AZ',
                            '555-123-4567');
                """;

                jdbcTemplate.update(restaurantSql, newUser.getUsername());
                System.out.println("Default restaurant created for: " + newUser.getUsername());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.err.println("Failed to auto-create restaurant for user: " + newUser.getUsername());
            }
        }

        return success;
    }
}
