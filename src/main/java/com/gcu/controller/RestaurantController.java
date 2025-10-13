package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gcu.business.ProductServiceInterface;
import com.gcu.business.UserSession;


/**
 * RestaurantController
 * ----------------------------
 * Handles routes related to restaurant administration.
 * 
 * Responsibilities:
 * - Displays the Restaurant Admin page with products.
 * - Dynamically renders the restaurant name for the
 *   currently logged-in restaurant owner.
 */
@Controller
public class RestaurantController {

	
    @Autowired
    private ProductServiceInterface productService;

    @Autowired
    private UserSession userSession;

    /**
     * Displays the Restaurant Admin dashboard.
     * 
     * The restaurant name is currently retrieved from session data
     * or defaults to "Speed-E Eats Deli" for new restaurant owners
     * until a proper RestaurantRepository CRUD form is implemented.
     * 
     * @param model The model used to pass attributes to the Thymeleaf template.
     * @return The restaurantadmin.html view.
     */
    @GetMapping("/restaurantadmin")
    public String adminPage(Model model) {
    	model.addAttribute("restaurantName", "Speed-E-Eats Deli");;
        model.addAttribute("headerTemplate", "layouts/common-user");
        model.addAttribute("products", productService.getAllProducts(userSession.getUsername()));
        
        // 🔹 Touch session bean so GlobalModelAttributes picks it up
        System.out.println("Restaurant Admin accessed by: " + userSession.getUsername());
        
        return "restaurantadmin";
    }

    // Menu page for customers
    @GetMapping("/restaurantmenu")
    public String menuPage(Model model) {
        model.addAttribute("restaurantName", "Speed-E-Eats");
        model.addAttribute("headerTemplate", "layouts/common-user");
        model.addAttribute("products", productService.getAllProducts(userSession.getUsername()));
        
        System.out.println("Restaurant Menu accessed by: " + userSession.getUsername());
        
        return "restaurantmenu";
    }
 
}
