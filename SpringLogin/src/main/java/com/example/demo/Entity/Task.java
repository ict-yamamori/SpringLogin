package com.example.demo.Entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "title", nullable = false, length = 20)
	private String title;
	
	@NotNull
	@Column(name = "detail", nullable = false, length = 128)
	private String detail;
	
	@NotNull
	@Column(name = "end_time",nullable = false)
	private Date end_time;
	
	@Column(name = "user_id",nullable = false)
    private int userId;
}
