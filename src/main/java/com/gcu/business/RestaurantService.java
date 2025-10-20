package com.gcu.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcu.dao.repository.RestaurantRepository;
import com.gcu.model.RestaurantModel;

/**
 * RestaurantService
 * ----------------------------
 * Implements RestaurantServiceInterface to manage restaurant business logic.
 * Acts as the intermediary between the controller and the repository layers.
 * 
 * Responsibilities:
 * - Handles CRUD operations for restaurant data.
 * - Delegates database operations to RestaurantRepository.
 */
@Service
public class RestaurantService implements RestaurantServiceInterface
{
    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * Default constructor.
     */
    public RestaurantService()
    {
        // Default constructor
    }

    /**
     * Retrieves all restaurants from the database.
     * 
     * @return List of RestaurantModel objects representing all restaurants.
     */
    @Override
    public List<RestaurantModel> getAllRestaurants()
    {
        return restaurantRepository.findAll();
    }

    /**
     * Retrieves all restaurants belonging to a specific owner.
     * 
     * @param ownerId The ID of the restaurant owner.
     * @return List of RestaurantModel objects belonging to that owner.
     */
    @Override
    public List<RestaurantModel> getRestaurantsByOwner(long ownerId)
    {
        return restaurantRepository.findByOwnerId(ownerId);
    }

    /**
     * Retrieves a single restaurant by its ID.
     * 
     * @param id The unique ID of the restaurant.
     * @return RestaurantModel containing the restaurant details, or null if not found.
     */
    @Override
    public RestaurantModel findById(int id)
    {
        try
        {
            return restaurantRepository.findByID(id);
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
     * @param restaurant The RestaurantModel containing restaurant details.
     * @return true if successful; false otherwise.
     */
    @Override
    public boolean create(RestaurantModel restaurant)
    {
        return restaurantRepository.create(restaurant);
    }

    /**
     * Updates an existing restaurant record.
     * 
     * @param restaurant The RestaurantModel with updated data.
     * @return true if successful; false otherwise.
     */
    @Override
    public boolean update(RestaurantModel restaurant)
    {
        return restaurantRepository.update(restaurant);
    }

    /**
     * Deletes a restaurant record from the database.
     * 
     * @param id The restaurant ID to delete.
     * @return true if successful; false otherwise.
     */
    public boolean delete(RestaurantModel id) {
    	
        return restaurantRepository.delete(id);
    }

}
