package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.gcu.dao.repository.UserRepository;
import com.gcu.model.UserModel;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserSession userSession;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        UserModel user = userRepository.findByUsername(username);

        if (user != null) {
            userSession.setUsername(user.getUsername());
            userSession.setFirstName(user.getFirstName());
            userSession.setLastName(user.getLastName());
            userSession.setEmail(user.getEmail());
            userSession.setRole(user.getRole());
        }
    }
}