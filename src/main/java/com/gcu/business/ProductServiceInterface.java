package com.gcu.business;

import java.util.List;

import com.gcu.model.ProductModel;

public interface ProductServiceInterface {

    //Defines createProduct(ProductModel product)

        boolean createProduct(ProductModel product);

        List<ProductModel> getAllProducts();

		int getRestaurantIdByUsername(String username);
    
    // Defines getAllProducts() returning List<ProductModel>
}
