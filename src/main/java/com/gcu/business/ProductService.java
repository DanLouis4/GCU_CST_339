package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.business.ProductServiceInterface;
import com.gcu.dao.repository.ProductRepository;
import com.gcu.model.ProductModel;

/**
 * ProductService
 * ----------------------------
 * Implements ProductServiceInterface to provide business logic
 * for managing products.
 * 
 * Responsibilities:
 * - Retrieves and creates products using ProductRepository.
 * - Acts as an intermediary between controllers and the data access layer.
 */

@Service
public class ProductService implements ProductServiceInterface
{
    // ----------------------------
    // Variables
    // ----------------------------
    @Autowired
    private ProductRepository productRepository;
    
    

    // ----------------------------
    // Methods
    // ----------------------------

    /**
     * Retrieves all products from the database.
     * 
     * @return List of ProductModel objects.
     */
    @Override
    public List<ProductModel> getAllProducts(String username)
    {
        return productRepository.findByOwner(username);
    }

    /**
     * Retrieves the restaurant ID associated with the logged-in user.
     * 
     * @param username The username of the restaurant owner.
     * @return The restaurant ID if found; 0 otherwise.
     */
    public int getRestaurantIdByUsername(String username)
    {
        try
        {
            String sql = """
                SELECT r.id
                FROM restaurants r
                JOIN users u ON r.owner_id = u.id
                WHERE u.username = ?
            """;

            // Use ProductRepository's JdbcTemplate to run this query
            return productRepository.getJdbcTemplate().queryForObject(sql, Integer.class, username);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    
    
    /**
     * Creates a new product record.
     * 
     * @param product The product model containing the new product details.
     * @return true if the product was successfully created; false otherwise.
     */
    @Override
    public boolean createProduct(ProductModel product)
    {
        return productRepository.create(product);
    }

}
