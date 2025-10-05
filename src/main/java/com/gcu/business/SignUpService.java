package com.gcu.business;

import org.springframework.stereotype.Service;
import com.gcu.model.UserModel;

@Service
public class SignUpService implements SignUpServiceInterface {

    @Override
    public boolean register(UserModel newUser) {
        System.out.println("Hello from SignUpService");
        // Emulate registration success without persistence
        return newUser.getUsername() != null && newUser.getPassword() != null;
    }
}
