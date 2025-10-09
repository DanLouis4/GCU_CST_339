package com.gcu.dao.repository;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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
    // ----------------------------
    // Variables
    // ----------------------------
    private JdbcTemplate jdbcTemplate;

    // ----------------------------
    // Constructor
    // ----------------------------
    @Autowired
    public ProductRepository(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ----------------------------
    // DataAccessInterface Methods
    // ----------------------------

    /**
     * Retrieves all product records across all restaurants.
     * 
     * @return A List of ProductModel objects.
     */
    @Override
    public List<ProductModel> findAll()
    {
        List<ProductModel> products = new ArrayList<>();

        try
        {
            String sql = "SELECT id, name, description, price, restaurant_id FROM products";
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);

            while (srs.next())
            {
                products.add(new ProductModel(
                        srs.getInt("id"),
                        srs.getString("name"),
                        srs.getString("description"),
                        srs.getDouble("price"),
                        srs.getInt("restaurant_id")
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return products;
    }

    /**
     * Creates a new product record linked to a restaurant.
     * 
     * @param product The ProductModel containing data to insert.
     * @return true if successful; false otherwise.
     */
    @Override
    public boolean create(ProductModel product)
    {
        try
        {
            String sql = """
                INSERT INTO products (name, description, price, restaurant_id)
                VALUES (?, ?, ?, ?)
            """;

            int rows = jdbcTemplate.update(sql,
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getRestaurantId());

            return rows == 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // ----------------------------
    // Custom Methods
    // ----------------------------

    /**
     * Retrieves all products belonging to a specific restaurant owner.
     * 
     * @param username The restaurant owner’s username.
     * @return A List of ProductModel objects.
     */
    public List<ProductModel> findByOwner(String username)
    {
        List<ProductModel> products = new ArrayList<>();

        try
        {
            String sql = """
                SELECT p.id, p.name, p.description, p.price, p.restaurant_id
                FROM products p
                JOIN restaurants r ON p.restaurant_id = r.id
                JOIN users u ON r.owner_id = u.id
                WHERE u.username = ?
            """;

            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, username);

            while (srs.next())
            {
                products.add(new ProductModel(
                        srs.getInt("id"),
                        srs.getString("name"),
                        srs.getString("description"),
                        srs.getDouble("price"),
                        srs.getInt("restaurant_id")
                ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return products;
    }

	@Override
	public ProductModel findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ProductModel t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ProductModel t) {
		// TODO Auto-generated method stub
		return false;
	}
}
