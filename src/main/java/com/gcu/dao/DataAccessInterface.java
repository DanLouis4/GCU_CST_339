package com.gcu.dao;

import java.util.List;

public interface DataAccessInterface<T> {

	List<T> findAll();
	T findByID(int id);
	boolean create(T t);
	boolean update(T t);
	boolean delete(T t);
	
}
