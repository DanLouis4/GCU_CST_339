package com.gcu.dao.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.gcu.dao.DataAccessInterface;
import com.gcu.model.ProductModel;

/**
 * ProductRepository
 * ----------------------------
 * Implements DataAccessInterface for ProductModel.
 * Handles product creation and retrieval operations.
 * 
 * Responsibilities:
 * - Inserts new product records linked to a restaurant.
 * - Retrieves product lists for all or specific restaurant owners.
 */
@Repository
public class ProductRepository implements DataAccessInterface<ProductModel>
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Default constructor.
     */
    public ProductRepository()
    {
        // Default constructor
    }

    // -------------------------------------
    // DataAccessInterface Methods
    // -------------------------------------

    /**
     * Retrieves all products from the database.
     * 
     * @return List of ProductModel objects containing all product records.
     */
    @Override
    public List<ProductModel> findAll()
    {
        List<ProductModel> products = new ArrayList<>();

        try
        {
            String sql = "SELECT id, name, description, price, restaurant_id, imageURL FROM products";
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);

            while (srs.next())
            {
                products.add(new ProductModel(
                    srs.getInt("id"),
                    srs.getString("name"),
                    srs.getString("description"),
                    srs.getDouble("price"),
                    srs.getInt("restaurant_id"),
                    srs.getString("imageURL")
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return products;
    }

    /**
     * Retrieves all products linked to a specific restaurant.
     * 
     * @param restaurantId The restaurant ID to filter products by.
     * @return List of ProductModel objects belonging to the specified restaurant.
     */
    public List<ProductModel> findByRestaurantId(long restaurantId)
    {
        List<ProductModel> products = new ArrayList<>();

        try
        {
            String sql = "SELECT id, name, description, price, restaurant_id, imageURL FROM products WHERE restaurant_id = ?";
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, restaurantId);

            while (srs.next())
            {
                products.add(new ProductModel(
                    srs.getInt("id"),
                    srs.getString("name"),
                    srs.getString("description"),
                    srs.getDouble("price"),
                    srs.getInt("restaurant_id"),
                    srs.getString("imageURL")
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return products;
    }

    /**
     * Retrieves a single product record by its ID.
     * 
     * @param id The product ID.
     * @return ProductModel containing the product details.
     */
    @Override
    public ProductModel findByID(int id)
    {
        ProductModel product = null;

        try
        {
            String sql = "SELECT id, name, description, price, restaurant_id, imageURL FROM products WHERE id = ?";
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, id);

            if (srs.next())
            {
                product = new ProductModel(
                    srs.getInt("id"),
                    srs.getString("name"),
                    srs.getString("description"),
                    srs.getDouble("price"),
                    srs.getInt("restaurant_id"),
                    srs.getString("imageURL")
                );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return product;
    }

    /**
     * Creates a new product record in the database.
     * 
     * @param product The ProductModel object containing the product data.
     * @return true if creation succeeded; false otherwise.
     */
    @Override
    public boolean create(ProductModel product)
    {
        String sql = "INSERT INTO products (name, description, price, restaurant_id, imageURL) VALUES (?, ?, ?, ?, ?)";

        try
        {
            int rows = jdbcTemplate.update(sql,
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getRestaurantId(),
                    product.getImageURL());

            return rows > 0;
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
     * @return true if update succeeded; false otherwise.
     */
    @Override
    public boolean update(ProductModel product)
    {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, restaurant_id = ?, imageURL = ? WHERE id = ?";

        try
        {
            int rows = jdbcTemplate.update(sql,
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getRestaurantId(),
                    product.getId(),
                    product.getImageURL());

            return rows > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a product record from the database by its ID.
     * 
     * @param id The ID of the product to delete.
     * @return true if deletion succeeded; false otherwise.
     */
    @Override
    public boolean delete(ProductModel product)
    {
        String sql = "DELETE FROM products WHERE id = ?";

        try
        {
            int rows = jdbcTemplate.update(sql, product.getId());
            return rows > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
