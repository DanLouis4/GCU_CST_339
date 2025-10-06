package com.gcu.controller;

import com.gcu.business.ProductServiceInterface;
import com.gcu.model.ProductModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductServiceInterface productService;

    // Show form
    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("headerTemplate", "layouts/common-user");
        return "productnew";
    }

    // Save product
    @PostMapping("/save")
    public String saveProduct(
            @Valid @ModelAttribute("productModel") ProductModel product,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("headerTemplate", "layouts/common-user");
            return "productnew";
        }

        productService.createProduct(product);
        return "redirect:/restaurantadmin?success=true";
    }

    // Show all products
    @GetMapping("/productnew")
    public String showProducts(
            @RequestParam(value = "success", required = false) String success,
            Model model) {

        List<ProductModel> productList = productService.getAllProducts();
        model.addAttribute("productList", productList);

        if (success != null) {
            model.addAttribute("message", "Product added successfully!");
        }

        return "restaurantadmin";
    }
    @GetMapping("/products/menu") 
    public String viewRestaurantMenu(Model model) {
        List<ProductModel> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("restaurantName", "Speed-E-Eats");
        model.addAttribute("headerTemplate", "layouts/common-user");
        return "restaurantmenu"; 
    }

}
