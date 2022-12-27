package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("UserDaoImpl")
public class UserDaoImpl implements UserDao {
	@Autowired
	JdbcTemplate jdbc;

	@Override
	public List<User> selectMany() throws DataAccessException {
		String sql = "select * from user";
		List<Map<String, Object>> list = jdbc.queryForList(sql);
		
		//結果返却用の変数
		List<User> userList = new ArrayList<>();
		
		for (Map<String,Object> map : list) {
			User user = new User();
			
			user.setId((int) map.get("id"));
			user.setEmail((String) map.get("email"));
			user.setUsername((String) map.get("name"));
			user.setPassword((String) map.get("password"));
			
			userList.add(user);
		}
		
		return userList;
	}
}
