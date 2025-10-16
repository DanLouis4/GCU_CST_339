package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.business.ProductServiceInterface;
import com.gcu.business.RestaurantServiceInterface;
import com.gcu.business.UserSession;
import com.gcu.model.ProductModel;
import com.gcu.model.RestaurantModel;

/**
 * ProductController
 * ----------------------------
 * Handles all product-related page requests.
 * 
 * Responsibilities:
 * - Displays all products (menu items) for a selected restaurant.
 * - Provides routes for adding, updating, and deleting products.
 * - Ensures that all product operations are scoped to the logged-in user's restaurants.
 */
@Controller
public class ProductController
{
    @Autowired
    private ProductServiceInterface productService;
    
    @Autowired
    private RestaurantServiceInterface restaurantService;

    @Autowired
    private UserSession userSession;

    /**
     * Default constructor.
     */
    public ProductController()
    {
        // Default constructor
    }

    // -------------------------------------
    // VIEWING PRODUCTS
    // -------------------------------------

    /**
     * Displays the Admin Menu page for a specific restaurant.
     * 
     * @param restaurantId The restaurant ID whose menu will be managed.
     * @param model Spring Model to pass restaurant and product data.
     * @return restaurantmenuadmin.html view.
     */
    @GetMapping("/restaurants/menu/{restaurantId}")
    public String showRestaurantMenuAdmin(@PathVariable int restaurantId, Model model)
    {
        try
        {
            // Validate session
            String loggedInUser = userSession.getUsername();
            if (loggedInUser == null)
            {
                return "redirect:/signin";
            }

            RestaurantModel restaurant = restaurantService.findById(restaurantId);
            
            // Retrieve all products for the given restaurant
            model.addAttribute("products", productService.findByRestaurantId(restaurantId));

            
             
            model.addAttribute("products", productService.findByRestaurantId(restaurantId));
            model.addAttribute("restaurantId", restaurantId);
            model.addAttribute("restaurantName", restaurant.getName());
            model.addAttribute("headerTemplate", "layouts/common-user");
            return "restaurantmenuadmin";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }

    // -------------------------------------
    // ADDING NEW PRODUCTS
    // -------------------------------------

    /**
     * Displays the Add New Product form for a specific restaurant.
     * 
     * @param restaurantId The restaurant ID that this new product will be linked to.
     * @param model Spring Model to pass data to the view.
     * @return productnew.html Thymeleaf form view for creating a new product.
     */
    @GetMapping("/products/new/{restaurantId}")
    public String showAddProductForm(@PathVariable int restaurantId, Model model)
    {
        try
        {
            // Validate user session
            if (userSession.getUsername() == null)
            {
                return "redirect:/signin";
            }

            // Prepare new product model for binding
            ProductModel product = new ProductModel();
            product.setRestaurantId(restaurantId);
            model.addAttribute("productModel", product);

            model.addAttribute("headerTemplate", "layouts/common-user");
            return "productnew";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * Handles POST submission for creating a new product.
     * 
     * @param product The ProductModel object bound from the form.
     * @return Redirects back to the restaurant's product list after successful creation.
     */
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("productModel") ProductModel product)
    {
        try
        {
            // Persist the product
            boolean success = productService.create(product);

            if (success)
            {
                System.out.println("Product successfully created: " + product.getName());
            }
            else
            {
                System.err.println("Failed to create product: " + product.getName());
            }

            // Redirect back to the restaurant's menu
            return "redirect:/products/" + product.getRestaurantId();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }

    // -------------------------------------
    // DELETING PRODUCTS
    // -------------------------------------

    /**
     * Handles product deletion.
     * 
     * @param product The ProductModel containing the product to delete.
     * @return Redirects back to the restaurant's menu after deletion.
     */
    @PostMapping("/products/delete")
    public String deleteProduct(@ModelAttribute("productModel") ProductModel product)
    {
        try
        {
            boolean success = productService.delete(product);

            if (success)
            {
                System.out.println("Product deleted successfully: ID " + product.getId());
            }
            else
            {
                System.err.println("Failed to delete product: ID " + product.getId());
            }

            return "redirect:/products/" + product.getRestaurantId();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }
}
