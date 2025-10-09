package com.gcu.business;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * UserSession
 * ----------------------------
 * Maintains the active user's session data across requests.
 * This version includes a tokenized password representation
 * for display purposes only (never the real password).
 */

@Component
@SessionScope
public class UserSession
{
    // ----------------------------
    // Fields
    // ----------------------------
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    // Tokenized password representation (never the raw password)
    private String passwordToken = "********************";

    // ----------------------------
    // Getters and Setters
    // ----------------------------

    /**
     * Gets the username of the current user.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the current user.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the first name of the current user.
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the current user.
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the current user.
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the current user.
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the current user.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the current user.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Gets the masked (tokenized) password value.
     * This is not the user's real password.
     * @return password token (e.g., "********************")
     */
    public String getPasswordToken() {
        return passwordToken;
    }
    
    /**
     * Sets the password token (for display only).
     * Typically called after user registration or login.
     * @param token masked token (default: "********************")
     */
    public void setPasswordToken(String token) {
        this.passwordToken = token;
    }

    /**
     * Gets the role of the current user.
     * @return role (e.g., "CUSTOMER" or "RESTAURANT")
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the current user.
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    // ----------------------------
    // Utility Methods
    // ----------------------------

    /**
     * Clears all session data. Called when user logs out or session ends.
     */
    public void clear() {
        this.username = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.role = null;
        this.passwordToken = "********************";
    }
}
