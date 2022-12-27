package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserDao;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserDao dao;
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> selectMany() {
		return dao.selectMany();
	}
	
	public void create(User user) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
         
//        userRepository.save(user);
        
        jdbcTemplate.update("insert into user (email,name,password)" + "values(?,?,?)",
        	user.getEmail(),
        	user.getUsername(),
        	encodedPassword);
	}
}
