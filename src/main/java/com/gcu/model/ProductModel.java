package com.gcu.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * ProductModel
 * ----------------------------
 * Represents a single product entry in the system.
 * 
 * Responsibilities:
 * - Holds the product’s name, description, price, and the restaurant it belongs to.
 * - Includes validation annotations to ensure valid input when creating or updating products.
 */

public class ProductModel
{
    // ----------------------------
    // Fields
    // ----------------------------

    private int id;

    @NotNull(message = "Product name is required.")
    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters.")
    private String name;

    @NotBlank(message = "Product description is required.")
    @Size(min = 5, max = 255, message = "Description must be between 5 and 255 characters.")
    private String description;

    @NotNull(message = "Price is required.")
    @Min(value = 1, message = "Price must be greater than 0.")
    private Double price;

    private int restaurantId;

    // ----------------------------
    // Constructors
    // ----------------------------

    /**
     * Default constructor.
     */
    public ProductModel() {}

    /**
     * Parameterized constructor.
     * 
     * @param id The product ID.
     * @param name The product name.
     * @param description The product description.
     * @param price The product price.
     * @param restaurantId The ID of the restaurant that owns the product.
     */
    public ProductModel(int id, String name, String description, Double price, int restaurantId)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    // ----------------------------
    // Getters and Setters
    // ----------------------------

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public int getRestaurantId()
    {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId)
    {
        this.restaurantId = restaurantId;
    }
}
