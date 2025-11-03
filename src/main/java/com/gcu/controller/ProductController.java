package com.gcu.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    RestaurantModel restaurant;

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
    @GetMapping("/restaurants/menu/admin/{restaurantId}")
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
                      
            // Retrieve all products for the given restaurant
            model.addAttribute("products", productService.findByRestaurantId(restaurantId));

            restaurant = restaurantService.findById(restaurantId);
             
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
    	RestaurantModel restaurant = restaurantService.findById(restaurantId);
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
            model.addAttribute("restaurant", restaurant);
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
    @PostMapping("/products/save/{restaurantId}")
    public String saveProduct(@ModelAttribute("productModel") ProductModel product, Model model)
    {
    	
    	restaurant = restaurantService.findById(product.getRestaurantId());
    	
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
            
            // Retrieve all products for the given restaurant
            model.addAttribute("products", productService.findByRestaurantId(restaurant.getId()));

            restaurant = restaurantService.findById(restaurant.getId());
             
            model.addAttribute("products", productService.findByRestaurantId(restaurant.getId()));
            model.addAttribute("restaurantId", restaurant.getId());
            model.addAttribute("restaurantName", restaurant.getName());
            model.addAttribute("headerTemplate", "layouts/common-user");
            
            // Redirect back to the restaurant's menu
            return "restaurantmenuadmin";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "error";
        }
    }
    
    // ---------------------------
    // EDIT PRODUCT
    // ---------------------------
    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable int id, Model model) {
        ProductModel product = productService.findById(id);
        if (product == null) {
            return "redirect:/error";
        }
        model.addAttribute("productModel", product);
        model.addAttribute("headerTemplate", "layouts/common-user");
        return "productedit"; // must match the template file name
    }


    @PostMapping("/products/update")
    public String updateProduct(@ModelAttribute("productModel") ProductModel product,
                                @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                                RedirectAttributes redirectAttributes) {
        try {
            // Fetch existing product from DB
            ProductModel existingProduct = productService.findById(product.getId());
            if (existingProduct == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Product not found!");
                return "redirect:/error";
            }

            // Only update image if a new file is uploaded
            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/images"); // your existing images folder

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                product.setImageURL("/images/" + fileName); // Update with new image
            } else {
                product.setImageURL(existingProduct.getImageURL()); // Keep old image
            }

            // Update product in DB
            productService.update(product);
            redirectAttributes.addFlashAttribute("updateMessage", "Product updated successfully!");

            return "redirect:/restaurants/menu/admin/" + product.getRestaurantId();

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating product!");
            return "redirect:/error";
        }
    }
    

	 // -------------------------------------
	 // DELETING PRODUCTS
	 // -------------------------------------
	
	 /**
	  * Handles product deletion.
	  * 
	  * @param id The ID of the product to delete.
	  * @return Redirects back to the restaurant's menu after deletion.
	  */
	 @PostMapping("/product/delete/{id}")
	 public String deleteProduct(@PathVariable("id") int id, Model model)
	 {
	     try
	     {
	         // Retrieve the product to access its restaurant ID
	         ProductModel product = productService.findById(id);
	         restaurant = restaurantService.findById(product.getRestaurantId());
	
	         // Perform deletion
	         boolean success = productService.delete(product);
	
	         if (success)
	         {
	             System.out.println("Product deleted successfully: ID " + id);
	         }
	         else
	         {
	             System.err.println("Failed to delete product: ID " + id);
	         }
	
	         // Retrieve all products for the given restaurant
	         model.addAttribute("products", productService.findByRestaurantId(restaurant.getId()));
	
	         restaurant = restaurantService.findById(restaurant.getId());
	          
	         model.addAttribute("products", productService.findByRestaurantId(restaurant.getId()));
	         model.addAttribute("restaurantId", restaurant.getId());
	         model.addAttribute("restaurantName", restaurant.getName());
	         model.addAttribute("headerTemplate", "layouts/common-user");
	         
	         // Redirect back to the restaurant's menu
	         return "restaurantmenuadmin";
	     }
	     catch (Exception e)
	     {
	         e.printStackTrace();
	         return "error";
	     }
	 }

}
