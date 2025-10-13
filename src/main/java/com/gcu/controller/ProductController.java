//package com.gcu.controller;
//
//import com.gcu.business.ProductServiceInterface;
//import com.gcu.model.ProductModel;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class ProductController {
//
//    @Autowired
//    private ProductServiceInterface productService;
//
//    // Show form
//    @GetMapping("/new")
//    public String showNewProductForm(Model model) {
//        model.addAttribute("productModel", new ProductModel());
//        model.addAttribute("headerTemplate", "layouts/common-user");
//        return "productnew";
//    }
//
//    // Save product
//    @PostMapping("/save")
//    public String saveProduct(
//            @Valid @ModelAttribute("productModel") ProductModel product,
//            BindingResult result,
//            Model model) {
//
//        if (result.hasErrors()) {
//            model.addAttribute("headerTemplate", "layouts/common-user");
//            return "productnew";
//        }
//
//        productService.createProduct(product);
//        model.addAttribute("headerTemplate", "layouts/common-user");
//        return "redirect:/restaurantadmin?successMessage=true";
//    }
//
//    // Show all products
//    @GetMapping("/productnew")
//    public String showProducts(
//            @RequestParam(value = "success", required = false) String success,
//            Model model) {
//
//        List<ProductModel> productList = productService.getAllProducts();
//        model.addAttribute("productList", productList);
//
//        if (success != null) {
//            model.addAttribute("message", "Product added successfully!");
//        }
//
//        return "restaurantadmin";
//    }
//    @GetMapping("/products/menu") 
//    public String viewRestaurantMenu(Model model) {
//        List<ProductModel> products = productService.getAllProducts();
//        model.addAttribute("products", products);
//        model.addAttribute("restaurantName", "Speed-E-Eats");
//        model.addAttribute("headerTemplate", "layouts/common-user");
//        return "restaurantmenu"; 
//    }
//
//}
package com.gcu.controller;

import com.gcu.business.ProductServiceInterface;
import com.gcu.business.UserSession;
import com.gcu.model.ProductModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//@Controller
//@RequestMapping("/products")
//public class ProductController {
//
//    @Autowired
//    private ProductServiceInterface productService;
//
//    // Show form
//    @GetMapping("/new")
//    public String showNewProductForm(Model model) {
//        model.addAttribute("productModel", new ProductModel());
//        model.addAttribute("headerTemplate", "layouts/common-user");
//        return "productnew";
//    }
//
//    // Save product
//    @PostMapping("/save")
//    public String saveProduct(
//            @Valid @ModelAttribute("productModel") ProductModel product,
//            BindingResult result,
//            Model model) {
//
//        if (result.hasErrors()) {
//            model.addAttribute("headerTemplate", "layouts/common-user");
//            return "productnew";
//        }
//
//        productService.createProduct(product);
//        model.addAttribute("headerTemplate", "layouts/common-user");
//        return "redirect:/restaurantadmin?successMessage=true";
//    }
//
//    // Show all products
//    @GetMapping("/productnew")
//    public String showProducts(
//            @RequestParam(value = "success", required = false) String success,
//            Model model) {
//
//        List<ProductModel> productList = productService.getAllProducts();
//        model.addAttribute("productList", productList);
//
//        if (success != null) {
//            model.addAttribute("message", "Product added successfully!");
//        }
//
//        return "restaurantadmin";
//    }
//    @GetMapping("/products/menu") 
//    public String viewRestaurantMenu(Model model) {
//        List<ProductModel> products = productService.getAllProducts();
//        model.addAttribute("products", products);
//        model.addAttribute("restaurantName", "Speed-E-Eats");
//        model.addAttribute("headerTemplate", "layouts/common-user");
//        return "restaurantmenu"; 
//    }
//
//}
@Controller
public class ProductController {

    @Autowired
    private ProductServiceInterface productService;
    
    @Autowired
    private UserSession userSession;

    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("headerTemplate", "layouts/common-user");
        return "productnew";
    }

    @PostMapping("/save") // ✅ This now matches @{/save}
    public String saveProduct(
            @Valid @ModelAttribute("productModel") ProductModel product,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("headerTemplate", "layouts/common-user");
            return "productnew";
        }
        
        // Retrieve the restaurant ID of the logged-in owner
        int restaurantId = productService.getRestaurantIdByUsername(userSession.getUsername());

        // Assign the restaurant ID to the product before saving
        product.setRestaurantId(restaurantId);

        // Existing logic — no need to change the call here
        boolean isSaved = productService.createProduct(product);
        
        if (isSaved) {
            model.addAttribute("successMessage", "Product added successfully!");
        } else {
            model.addAttribute("errorMessage", "Failed to add product. Please try again.");
        }
        
        model.addAttribute("headerTemplate", "layouts/common-user");
        return "redirect:/restaurantadmin?successMessage=true";
    }

    @GetMapping("/productnew")
    public String showProducts(@RequestParam(value = "success", required = false) String success, Model model) {
        List<ProductModel> productList = productService.getAllProducts(userSession.getUsername());
        model.addAttribute("productList", productList);
        
        
        
        if (success != null) {
            model.addAttribute("message", "Product added successfully!");
        }
        return "restaurantadmin";
    }

    @GetMapping("/products/menu")
    public String viewRestaurantMenu(Model model) {
        List<ProductModel> products = productService.getAllProducts(userSession.getUsername());
        model.addAttribute("products", products);
        model.addAttribute("restaurantName", "Speed-E-Eats");
        model.addAttribute("headerTemplate", "layouts/common-user");
        return "restaurantmenu";
    }
  

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        
    	// TODO in later milestones.
		
    	return null;
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    
    	// TODO in later milestones.
		
    	return null;
		
    }

}
