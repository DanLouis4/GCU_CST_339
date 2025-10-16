package com.gcu.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.dao.repository.ProductRepository;
import com.gcu.model.ProductModel;

/**
 * ProductService
 * ----------------------------
 * Implements ProductServiceInterface to manage product business logic.
 * Acts as the intermediary between the controller and repository layers.
 * 
 * Responsibilities:
 * - Handles CRUD operations for products.
 * - Delegates database interaction to ProductRepository.
 * - Provides filtering for products by restaurant.
 */
@Service
public class ProductService implements ProductServiceInterface
{
    @Autowired
    private ProductRepository productRepository;

    /**
     * Default constructor.
     */
    public ProductService()
    {
        // Default constructor
    }

    // -------------------------------------
    // DataAccessInterface Methods
    // -------------------------------------

    /**
     * Retrieves all product records from the database.
     * 
     * @return List of ProductModel objects representing all products.
     */
    @Override
    public List<ProductModel> findAll()
    {
        try
        {
            return productRepository.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves a single product record by its ID.
     * 
     * @param id The product's unique identifier.
     * @return The ProductModel object containing the product details.
     */
    @Override
    public ProductModel findById(int id)
    {
        try
        {
            return productRepository.findById(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all products linked to a specific restaurant.
     * 
     * @param restaurantId The restaurant ID to filter products by.
     * @return List of ProductModel objects for that restaurant.
     */
    @Override
    public List<ProductModel> findByRestaurantId(int restaurantId)
    {
        try
        {
            return productRepository.findByRestaurantId(restaurantId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a new product record in the database.
     * 
     * @param product The ProductModel object containing product data.
     * @return true if the product was successfully created; false otherwise.
     */
    @Override
    public boolean create(ProductModel product)
    {
        try
        {
            return productRepository.create(product);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing product record in the database.
     * 
     * @param product The ProductModel object with updated information.
     * @return true if the product was successfully updated; false otherwise.
     */
    @Override
    public boolean update(ProductModel product)
    {
        try
        {
            return productRepository.update(product);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a product record from the database.
     * 
     * @param product The ProductModel object to delete.
     * @return true if deletion succeeded; false otherwise.
     */
    @Override
    public boolean delete(ProductModel product)
    {
        try
        {
            return productRepository.delete(product);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
