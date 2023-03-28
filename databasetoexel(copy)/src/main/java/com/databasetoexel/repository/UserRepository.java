package com.databasetoexel.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.databasetoexel.entity.UserEntity;
@Repository
public class UserRepository {
	@Autowired
	JdbcTemplate jdbctemplete;
	
	public List<UserEntity> getall(){
		String sql= "select * from mytable";
		return jdbctemplete.query(sql, new BeanPropertyRowMapper<>(UserEntity.class));
	}
}
