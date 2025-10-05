package com.gcu.business;

import org.springframework.stereotype.Service;

@Service
public class SignInService implements SignInServiceInterface {

    @Override
    public boolean authenticate(String username, String password) {
        System.out.println("Hello from SignInService");
        // Hard-coded example credentials
        return username.equals("testuser") && password.equals("pass123");
    }
}
