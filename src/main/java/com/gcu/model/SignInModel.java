package com.gcu.model;

import jakarta.validation.constraints.NotNull;

public class SignInModel {

    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    private String password;

    public SignInModel() {}

    public SignInModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignInModel{" +
                "username='" + username + '\'' +
                '}';
        // Excluding password
    }
}
