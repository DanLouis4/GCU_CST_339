package com.gcu.business;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.gcu.dao.repository.UserRepository;
import com.gcu.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationSuccessListener extends SavedRequestAwareAuthenticationSuccessHandler {


    @Autowired
    private UserSession userSession;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        UserModel user = userRepository.findByUsername(username);

        if (user != null) {
            userSession.setId(user.getId());
            userSession.setUsername(user.getUsername());
            userSession.setFirstName(user.getFirstName());
            userSession.setLastName(user.getLastName());
            userSession.setEmail(user.getEmail());
            userSession.setRole(user.getRole());
            System.out.println("[LOGIN SUCCESS] User session initialized: " + user.getUsername() + " (ID: " + user.getId() + ")");
        } else {
            System.out.println("[LOGIN ERROR] User not found in DB: " + username);
        }

        // Continue with default redirect (e.g., /home)
        response.sendRedirect("/home");
    }

}