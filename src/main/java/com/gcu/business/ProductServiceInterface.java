package com.gcu.business;

import java.util.List;
import com.gcu.model.ProductModel;

/**
 * ProductServiceInterface
 * ----------------------------
 * Defines the business operations available for managing products.
 * 
 * Responsibilities:
 * - Provides method definitions for retrieving, creating, updating, 
 *   and deleting product data.
 * - Establishes a contract between the controller and service layers.
 */
public interface ProductServiceInterface
{
    /**
     * Retrieves all product records from the database.
     * 
     * @return List of ProductModel objects representing all products.
     */
    List<ProductModel> findAll();

    /**
     * Retrieves a single product record by its ID.
     * 
     * @param id The product's unique identifier.
     * @return ProductModel object containing product details.
     */
    ProductModel findById(int id);

    /**
     * Retrieves all products linked to a specific restaurant.
     * 
     * @param restaurantId The restaurant ID to filter products by.
     * @return List of ProductModel objects belonging to that restaurant.
     */
    List<ProductModel> findByRestaurantId(int restaurantId);

    /**
     * Creates a new product record in the database.
     * 
     * @param product The ProductModel object containing product data.
     * @return true if creation succeeded; false otherwise.
     */
    boolean create(ProductModel product);

    /**
     * Updates an existing product record in the database.
     * 
     * @param product The ProductModel object with updated data.
     * @return true if update succeeded; false otherwise.
     */
    boolean update(ProductModel product);

    /**
     * Deletes a product record from the database.
     * 
     * @param product The ProductModel object to delete.
     * @return true if deletion succeeded; false otherwise.
     */
    boolean delete(ProductModel product);
}
