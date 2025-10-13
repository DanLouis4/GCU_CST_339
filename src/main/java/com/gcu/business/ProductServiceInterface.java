package com.gcu.business;

import java.util.List;

import com.gcu.model.ProductModel;

public interface ProductServiceInterface {

    //Defines createProduct(ProductModel product)

        boolean createProduct(ProductModel product);
                
		int getRestaurantIdByUsername(String username);

		/**
		 * Retrieves all products from the database.
		 * 
		 * @return List of ProductModel objects based on user.
		 */
		List<ProductModel> getAllProducts(String username);
    }
