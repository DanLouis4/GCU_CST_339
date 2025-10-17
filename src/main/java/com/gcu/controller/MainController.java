package com.gcu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.gcu.business.RestaurantServiceInterface;
import com.gcu.business.UserSession;
import com.gcu.model.RestaurantModel;

/**
 * Displays the user's home page after login.
 * 
 * Responsibilities:
 * - Retrieves the logged-in user's information from the active session.
 * - Displays personalized content such as recent orders (future implementation).
 * - Loads the home.html view.
 */
@Controller
public class MainController
{
    @Autowired
    private UserSession userSession;
    
    @Autowired
    private RestaurantServiceInterface restaurantService;

    @GetMapping("/")
    public String mainPage(Model model) {
    		
    	model.addAttribute("headerTemplate", "layouts/common-guest");    	
    	model.addAttribute("title", "Speed-E-Eats");
    	
        return "main";
    }
    
    /**
     * Displays the Home page for the logged-in user.
     * 
     * @param model Spring Model used to pass attributes to the Thymeleaf view.
     * @return home.html Thymeleaf view.
     */
    @GetMapping("/home")
    public String showHomePage(Model model)
    {
        try
        {
            // Retrieve the logged-in user from the active session
            String loggedInUser = userSession.getUsername();

            // Verify that a user session exists before displaying the page
            if (loggedInUser == null)
            {
                // If session expired or invalid, redirect to login page
                return "redirect:/signin";
            }

            // Add user data to the view
            model.addAttribute("username", userSession.getUsername());
            model.addAttribute("firstName", userSession.getFirstName());
            model.addAttribute("lastName", userSession.getLastName());
            model.addAttribute("email", userSession.getEmail());
            model.addAttribute("loginSuccess", "Welcome back, " + userSession.getFirstName() + "!");

            // Add restaurants for the homepage carousel
            model.addAttribute("restaurants", restaurantService.getAllRestaurants());
            
            // Common layout and metadata
        	model.addAttribute("headerTemplate", "layouts/common-user");    	
        	model.addAttribute("title", "Speed-E-Eats");
            
            // Placeholder for future order integration
            // Example:
            // List<OrderModel> recentOrders = orderService.getRecentOrdersByUser(loggedInUser.getId());
            // model.addAttribute("recentOrders", recentOrders);

            // Render the home page view
            return "home";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error"; // fallback error page (optional)
        }
    }
    
	/**
	 * Displays the Home page for the logged-in user.
	 * 
	 * @param model Spring Model used to pass attributes to the Thymeleaf view.
	 * @return home.html Thymeleaf view.
	 */
	@GetMapping("/restaurants")
	public String showRestaurantPage(Model model)
	{
		try
		{
			// Retrieve the logged-in user from the active session
			String loggedInUser = userSession.getUsername();
			
			// Verify that a user session exists before displaying the page
			if (loggedInUser == null)
			{
				// If session expired or invalid, redirect to login page
				return "redirect:/signin";
			}
			
	        // Retrieve all restaurants from the service
	        List<RestaurantModel> allRestaurants = restaurantService.getAllRestaurants();

	        // Pass the list to the view
	        model.addAttribute("restaurants", allRestaurants);			
			
			// Add user data to the view
			model.addAttribute("username", userSession.getUsername());
			model.addAttribute("firstName", userSession.getFirstName());
			model.addAttribute("lastName", userSession.getLastName());
			model.addAttribute("email", userSession.getEmail());
			model.addAttribute("loginSuccess", "Welcome back, " + userSession.getFirstName() + "!");
			
			// Add
			model.addAttribute("headerTemplate", "layouts/common-user");    	
			model.addAttribute("title", "Speed-E-Eats Restaurants");
			
			// Placeholder for future order integration
			// Example:
			// List<OrderModel> recentOrders = orderService.getRecentOrdersByUser(loggedInUser.getId());
			// model.addAttribute("recentOrders", recentOrders);
			
			// Render the home page view
			return "restaurants";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "error"; // fallback error page (optional)
		}
	}
    
}


