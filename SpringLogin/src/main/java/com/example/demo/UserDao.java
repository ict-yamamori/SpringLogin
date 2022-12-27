package com.example.demo;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface UserDao {
	
	public List<User> selectMany() throws DataAccessException;
}
