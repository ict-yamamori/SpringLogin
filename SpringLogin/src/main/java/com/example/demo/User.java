package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable = false, length = 20)
	private String username;
	
	@Column(nullable = false, unique = true, length = 45)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
	@AssertTrue(message = "パスワードが一致しません。")
    public boolean isPasswordValid() {
        if (password == null || password.isEmpty()) {
            return true;
        }

        return password.equals(passwordConfirmation);
    } 
}
