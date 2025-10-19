package com.gcu.dao.repository;

import com.gcu.data.DataAccessInterface;
import com.gcu.model.RestaurantModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * RestaurantRepository
 * ----------------------------
 * Implements DataAccessInterface for RestaurantModel.
 * Handles all restaurant creation, retrieval, update, and delete operations.
 * 
 * Responsibilities:
 * - Inserts new restaurant records linked to a specific owner.
 * - Retrieves restaurant lists for all or specific owners.
 * - Updates existing restaurant information.
 * - Deletes restaurant records from the database.
 */

@Repository
public class RestaurantRepository implements DataAccessInterface<RestaurantModel>
{
    // ----------------------------
    // Variables
    // ----------------------------
    private JdbcTemplate jdbcTemplate;

    // ----------------------------
    // Constructor
    // ----------------------------
    public RestaurantRepository(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    // ----------------------------
    // DataAccessInterface Methods
    // ----------------------------    

    /**
     * Retrieves all restaurant records from the database.
     * 
     * @return List of RestaurantModel objects representing all restaurants.
     */
    @Override
    public List<RestaurantModel> findAll()
    {
    	List<RestaurantModel> restaurants = new ArrayList<>();
    	
        try
        {
        	String sql = "SELECT id, name, address, phone, description, owner_id, imageURL FROM restaurants";
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);         
            while (rs.next())
            {
            	restaurants.add(new RestaurantModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("description"),
                    rs.getLong("owner_id"),
                    rs.getString("imageURL")
            ));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
		return restaurants;
    }

    /**
     * Retrieves a restaurant record by its ID.
     * 
     * @param id The restaurant's unique identifier.
     * @return RestaurantModel object if found; otherwise null.
     */
    @Override
    public RestaurantModel findById(int id)
    {
        String sql = "SELECT * FROM restaurants WHERE id = ?";
        try
        {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new RestaurantModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("description"),
                    rs.getLong("owner_id"),
                    rs.getString("imageURL")
            ), id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all restaurants owned by a specific user.
     * 
     * @param ownerId The ID of the restaurant owner.
     * @return List of RestaurantModel objects owned by the specified user.
     */
    public List<RestaurantModel> findByOwnerId(long ownerId)
    {
        String sql = "SELECT * FROM restaurants WHERE owner_id = ?";
        try
        {
            return jdbcTemplate.query(sql, (rs, rowNum) -> new RestaurantModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("description"),
                    rs.getLong("owner_id"),
                    rs.getString("imageURL")
            ), ownerId); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a new restaurant record in the database.
     * 
     * @param restaurant The RestaurantModel object containing restaurant details.
     * @return true if creation was successful; false otherwise.
     */
    @Override
    public boolean create(RestaurantModel restaurant)
    {
        String sql = "INSERT INTO restaurants (name, address, phone, description, owner_id, imageURL) VALUES (?, ?, ?, ?, ?, ?)";
        try
        {
            int rows = jdbcTemplate.update(sql,
                    restaurant.getName(),
                    restaurant.getAddress(),
                    restaurant.getPhone(),
                    restaurant.getDescription(),
                    restaurant.getOwnerId(),
                    restaurant.getImageURL());
            return rows > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing restaurant record in the database.
     * 
     * @param restaurant The RestaurantModel object containing updated information.
     * @return true if update was successful; false otherwise.
     */
    @Override
    public boolean update(RestaurantModel restaurant)
    {
        String sql = "UPDATE restaurants SET name = ?, address = ?, phone = ?, description = ?, imageURL = ? WHERE id = ?";
        try
        {
            int rows = jdbcTemplate.update(sql,
                    restaurant.getName(),
                    restaurant.getAddress(),
                    restaurant.getPhone(),
                    restaurant.getDescription(),
                    restaurant.getImageURL(),
                    restaurant.getId());
            return rows > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a restaurant record from the database by its ID.
     * 
     * @param id The ID of the restaurant to delete.
     * @return true if deletion was successful; false otherwise.
     */
    @Override
    public boolean delete(RestaurantModel restaurant)
    {
        String sql = "DELETE FROM restaurants WHERE id = ?";
        try
        {
            int rows = jdbcTemplate.update(sql, restaurant.getId());
            return rows > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
