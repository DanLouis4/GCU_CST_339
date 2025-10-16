package com.gcu.model;

public class RestaurantModel {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String description;
    private long ownerId;
    private String imageURL;

    // Default constructor
    public RestaurantModel() {}

    // Parameterized constructor
    public RestaurantModel(int id, String name, String address, String phone, String description, long ownerId, String imageURL) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.ownerId = ownerId;
        this.imageURL = imageURL;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getOwnerId() {
        return ownerId;
    }
    
    public void setOwnerId(long ownerId) {
    	this.ownerId = ownerId;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}