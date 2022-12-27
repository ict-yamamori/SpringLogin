package com.example.demo.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.Service.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * 認証・認可の情報を設定する
     * 画面遷移のURL・パラメータを取得するname属性の値を設定
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
		.authorizeHttpRequests()
			.antMatchers("/userList").authenticated()
	        .anyRequest().permitAll()
		.and().formLogin()
			.usernameParameter("username")
	        .defaultSuccessUrl("/tasks")
	        .permitAll()
		.and().logout().logoutSuccessUrl("/").permitAll();

    	return http.build();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    	authProvider.setUserDetailsService(userDetailsService());
    	authProvider.setPasswordEncoder(passwordEncoder());
    	
    	return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) 
      throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
          .authenticationProvider(authenticationProvider())
          .build();
    }
}
