package com.example.demo.Repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.Entity.User;

public interface UserDao {
	
	public List<User> selectMany() throws DataAccessException;
}
