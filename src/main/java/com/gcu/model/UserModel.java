/**
 * UserModel
 * ----------------------------------------
 * Serves as the unified data model for both
 * user registration (Sign Up) and authentication (Sign In).
 *
 * Fields map directly to the "Users" table in the ER diagram.
 * This class uses Jakarta Validation for form input verification
 * and will later integrate with the database layer for persistence.
 */

package com.gcu.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserModel {
    
	/** Primary key for each user record (auto-generated in DB). */
    private Long id;

    /** User's first name (required during registration). */
    @NotNull(message = "First name is required")
    private String firstName;

    @NotNull(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Username is required")
    @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
    private String username;

    @NotNull(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
    @Pattern(regexp = ".*[!@#$%^&*(),.?\":{}|<>].*", 
             message = "Password must contain at least one special character")
    private String password;
    
    
    @NotNull(message = "Role is required")
    private String role; // CUSTOMER or OWNER

    
    /** Default constructor required by Spring MVC and frameworks. */
    public UserModel() {}

    /** Full constructor used during sign-up or testing. */
    public UserModel(String firstName, String lastName, String username, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and setters

	public Long getId() { return id; }
	public void setId(Long id) {this.id = id; }
	
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "UserModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
