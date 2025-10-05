package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.model.ProductModel;

@Service
public class ProductService implements ProductServiceInterface {

    // Maintains in-memory list of products (until Database is used)

    private List<ProductModel> products = new ArrayList<>();
    private long nextId = 1;

    // Implements createProduct(ProductModel product)

    @Override
    public void createProduct(ProductModel product) 
    {
        
        if (product.getId() == null) {
            product.setId(nextId++);
        }
        products.add(product);
    }

    // Implements getAllProducts()

    @Override
    public List<ProductModel> getAllProducts() {
        return new ArrayList<>(products); 
    }
}
