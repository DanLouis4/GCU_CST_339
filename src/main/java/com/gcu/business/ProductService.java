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
    public List<ProductModel> getAllProducts()
    {
        return productRepository.findAll();
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
