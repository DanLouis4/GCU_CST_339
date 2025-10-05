package com.gcu.business;

public interface SignInServiceInterface {
	
    boolean authenticate(String username, String password);
}
