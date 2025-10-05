package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gcu.business.ProductServiceInterface;

@Controller
public class RestaurantController {

    @Autowired
    private ProductServiceInterface productService;

    // Admin page for restaurant owners
    @GetMapping("/restaurantadmin")
    public String adminPage(Model model) {
        model.addAttribute("restaurantName", "Sample Restaurant");
        model.addAttribute("products", productService.getAllProducts());
        return "restaurantadmin";
    }

    // Menu page for customers
    @GetMapping("/restaurantmenu")
    public String menuPage(Model model) {
        model.addAttribute("restaurantName", "My Restaurant");
        model.addAttribute("products", productService.getAllProducts());
        return "restaurantmenu";
    }
}
