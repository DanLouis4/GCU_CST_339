package com.gcu.business;

import java.util.List;
import com.gcu.model.RestaurantModel;

/**
 * RestaurantServiceInterface
 * ----------------------------
 * Defines the business operations available for managing restaurants.
 * 
 * Responsibilities:
 * - Provides method definitions for retrieving, creating, updating, 
 *   and deleting restaurant data.
 * - Establishes a contract between the controller and repository layers.
 */
public interface RestaurantServiceInterface
{
    /**
     * Retrieves all restaurants from the database.
     * 
     * @return List of RestaurantModel objects representing all restaurants.
     */
    List<RestaurantModel> getAllRestaurants();

    /**
     * Retrieves all restaurants belonging to a specific owner.
     * 
     * @param ownerId The ID of the restaurant owner.
     * @return List of RestaurantModel objects belonging to that owner.
     */
    List<RestaurantModel> getRestaurantsByOwner(long ownerId);

    /**
     * Retrieves a single restaurant by its ID.
     * 
     * @param id The unique restaurant ID.
     * @return RestaurantModel containing the restaurant details, or null if not found.
     */
    RestaurantModel findById(int id);
    
    /**
     * Creates a new restaurant record.
     * 
     * @param restaurant The RestaurantModel containing restaurant details.
     * @return true if successful; false otherwise.
     */
    boolean create(RestaurantModel restaurant);

    /**
     * Updates an existing restaurant record.
     * 
     * @param restaurant The RestaurantModel with updated data.
     * @return true if successful; false otherwise.
     */
    boolean update(RestaurantModel restaurant);

    /**
     * Deletes a restaurant record by ID.
     * 
     * @param restaurant The restaurant ID to delete.
     * @return true if successful; false otherwise.
     */
    boolean delete(RestaurantModel restaurant);
}
