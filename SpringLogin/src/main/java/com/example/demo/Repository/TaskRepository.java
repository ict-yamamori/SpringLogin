package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Query("SELECT a FROM Task a WHERE a.userId = ?1")
	public List<Task> findByUserId(int user_id);
}
