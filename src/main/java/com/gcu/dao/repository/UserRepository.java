package com.gcu.dao.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.gcu.dao.DataAccessInterface;
import com.gcu.model.UserModel;

@Repository
public class UserRepository implements DataAccessInterface<UserModel> {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserRepository(DataSource dataSource) {
		
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<UserModel> findAll() {
        List<UserModel> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users";
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
            while (srs.next()) {
                users.add(new UserModel(
                    srs.getString("first_name"),
                    srs.getString("last_name"),
                    srs.getString("username"),
                    srs.getString("email"),
                    srs.getString("password"),
                    srs.getString("role")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
	}

	@Override
	public UserModel findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(UserModel user) {

        try {
        	
            String sql = "INSERT INTO users (first_name, last_name, username, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";
            int rows = jdbcTemplate.update(sql, user.getFirstName(),
            									user.getLastName(),
            									user.getUsername(),
            									user.getEmail(),
            									user.getPassword(),
            									user.getRole());
            return rows == 1;
            
        }       
        catch (Exception e) {
        	
            e.printStackTrace();
            return false;
            
        }

		
	}

	@Override
	public boolean update(UserModel t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(UserModel t) {
		// TODO Auto-generated method stub
		return false;
	}

}
