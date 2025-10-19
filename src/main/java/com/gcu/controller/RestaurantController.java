package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.business.ProductServiceInterface;
import com.gcu.business.RestaurantServiceInterface;
import com.gcu.business.UserSession;
import com.gcu.model.RestaurantModel;

/**
 * RestaurantController
 * ----------------------------
 * Handles all restaurant-related page requests.
 * 
 * Responsibilities:
 * - Displays the Restaurant Manager page for the logged-in owner.
 * - Provides routes for adding, updating, and deleting restaurants.
 * - Ensures all operations are restricted to the current user session.
 */
@Controller
public class RestaurantController
{
    @Autowired
    private RestaurantServiceInterface restaurantService;
    
    @Autowired
    private ProductServiceInterface productService;

    @Autowired
    private UserSession userSession;

    /**
     * Default constructor.
     */
    public RestaurantController()
    {
        // Default constructor
    }

    // -------------------------------------
    // VIEWING RESTAURANTS
    // -------------------------------------

    /**
     * Displays all restaurants belonging to the currently logged-in user.
     * 
     * @param model Spring Model used to pass data to the view.
     * @return restaurantmanager.html Thymeleaf view.
     */
    @GetMapping("/restaurantmanager")
    public String showRestaurantManager(Model model)
    {
        try
        {
            // Validate session
            String loggedInUser = userSession.getUsername();
            if (loggedInUser == null)
            {
                return "redirect:/signin";
            }

            // Retrieve restaurants for this owner
            long ownerId = userSession.getId();
            model.addAttribute("restaurants", restaurantService.getRestaurantsByOwner(ownerId));

            // Add user info for personalization
            model.addAttribute("username", userSession.getUsername());

            model.addAttribute("headerTemplate", "layouts/common-user");
            return "restaurantmanager";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }

	 // -------------------------------------
	 // VIEWING RESTAURANT MENU (PUBLIC VIEW)
	 // -------------------------------------
	
	 /**
	  * Displays a specific restaurant’s menu page for customers.
	  *
	  * @param id    The ID of the restaurant to view.
	  * @param model Spring Model to pass restaurant and menu data to the view.
	  * @return restaurantmenu.html Thymeleaf view.
	  */
	 @GetMapping("/restaurantmenu/{id}")
	 public String showRestaurantMenu(@PathVariable("id") int id, Model model)
	 {
	     try
	     {
	         // Retrieve restaurant details
	         RestaurantModel restaurant = restaurantService.findById(id);
	         if (restaurant == null)
	         {
	             System.err.println("Restaurant not found for ID: " + id);
	             return "redirect:/home";
	         }
	
	         // Retrieve products associated with the restaurant
	         model.addAttribute("restaurant", restaurant);
	         model.addAttribute("products", productService.findByRestaurantId(id));
	
	         // Sets the correct navigation bar and layout
	         model.addAttribute("headerTemplate", "layouts/common-user");
	
	         // Render restaurantmenu.html
	         return "restaurantmenu";
	     }
	     catch (Exception e)
	     {
	         e.printStackTrace();
	         System.err.println("Error loading restaurant menu for ID: " + id);
	         return "error";
	     }
	 }    

    // -------------------------------------
    // ADDING NEW RESTAURANTS
    // -------------------------------------

    /**
     * Displays the Add New Restaurant form.
     * 
     * @param model Spring Model used to pass data to the view.
     * @return restaurantnew.html Thymeleaf form.
     */
    @GetMapping("/restaurants/new")
    public String showAddRestaurantForm(Model model)
    {
        try
        {
            // Verify active session
            if (userSession.getUsername() == null)
            {
                return "redirect:/signin";
            }

            // Create blank model for form binding
            model.addAttribute("restaurantModel", new RestaurantModel());
            model.addAttribute("headerTemplate", "layouts/common-user");
            return "restaurantnew";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * Handles form submission for creating a new restaurant.
     * 
     * @param restaurant The RestaurantModel populated from the form.
     * @return Redirects to the Restaurant Manager page on success.
     */
    @PostMapping("/restaurants/save")
    public String saveRestaurant(@ModelAttribute("restaurantModel") RestaurantModel restaurant)
    {
        try
        {
            // Ensure valid user session
            String loggedInUser = userSession.getUsername();
            if (loggedInUser == null)
            {
                return "redirect:/signin";
            }

            // Assign ownership to logged-in user
            restaurant.setOwnerId(userSession.getId());

            // Persist restaurant
            boolean success = restaurantService.create(restaurant);

            if (success)
            {
                System.out.println("Restaurant created successfully: " + restaurant.getName());
                return "redirect:/restaurantmanager";
            }
            else
            {
                System.err.println("Failed to create restaurant: " + restaurant.getName());
                return "redirect:/restaurants/new";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }
    
	 // -------------------------------------
	 // UPDATING RESTAURANTS
	 // -------------------------------------
	
	 /**
	  * Displays the Edit Restaurant form for a specific restaurant.
	  * 
	  * @param id    The ID of the restaurant to edit.
	  * @param model Spring model used to pass data to the view.
	  * @return restaurantedit.html view populated with the restaurant data.
	  */
	 @GetMapping("/restaurants/edit/{id}")
	 public String showEditRestaurantForm(@PathVariable("id") int id, Model model)
	 {
	     try
	     {
	         // Retrieve restaurant from DB
	         RestaurantModel restaurant = restaurantService.findById(id);
	
	         // Redirect if restaurant doesn't exist
	         if (restaurant == null)
	         {
	             System.err.println("Restaurant not found for ID: " + id);
	             return "redirect:/restaurantmanager";
	         }
	
	         // Bind restaurant data to the model for pre-filling form fields
	         model.addAttribute("restaurant", restaurant);
	         model.addAttribute("headerTemplate", "layouts/common-user");
	
	         return "restaurantedit"; // View: restaurantedit.html
	     }
	     catch (Exception e)
	     {
	         e.printStackTrace();
	         return "error";
	     }
	 }
	
	 /**
	  * Handles form submission for updating a restaurant record.
	  * 
	  * @param restaurant The RestaurantModel containing updated data.
	  * @return Redirects back to the Restaurant Manager page after successful update.
	  */
	 @PostMapping("/restaurants/update")
	 public String updateRestaurant(@ModelAttribute("restaurant") RestaurantModel restaurant)
	 {
	     try
	     {
	         boolean success = restaurantService.update(restaurant);
	
	         if (success)
	         {
	             System.out.println("Restaurant updated successfully: " + restaurant.getName());
	         }
	         else
	         {
	             System.err.println("Failed to update restaurant with ID: " + restaurant.getId());
	         }
	
	         return "redirect:/restaurantmanager";
	     }
	     catch (Exception e)
	     {
	         e.printStackTrace();
	         return "error";
	     }
	 }



    // -------------------------------------
    // DELETING RESTAURANTS
    // -------------------------------------

    /**
     * Deletes a restaurant record by its ID.
     * 
     * @param restaurant The RestaurantModel containing the ID of the restaurant to delete.
     * @return Redirects back to the restaurant manager after deletion.
     */
    @PostMapping("/restaurants/delete/{id}")
    public String deleteRestaurant(@PathVariable("id") int id, Model model)
    {
    	
        try
        {
	         // Retrieve restaurant from DB
	        RestaurantModel restaurant = restaurantService.findById(id);
	        
            boolean success = restaurantService.delete(restaurant);

            if (success)
            {
                System.out.println("Restaurant deleted successfully: ID " + restaurant.getId());
            }
            else
            {
                System.err.println("Failed to delete restaurant: ID " + restaurant.getId());
            }
            
            // display restaurants by onwer
             long ownerId = userSession.getId();         
             model.addAttribute("restaurants", restaurantService.getRestaurantsByOwner(ownerId));
            
            
	         model.addAttribute("restaurant", restaurant);
	         model.addAttribute("headerTemplate", "layouts/common-user");
            return "restaurantmanager";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }
}
