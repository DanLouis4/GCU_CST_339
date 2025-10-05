package com.gcu.business;

import java.util.List;

import com.gcu.model.ProductModel;

public interface ProductServiceInterface {

    //Defines createProduct(ProductModel product)

        void createProduct(ProductModel product);

        List<ProductModel> getAllProducts();
    
    // Defines getAllProducts() returning List<ProductModel>
}
