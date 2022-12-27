package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskContoroller {
	@GetMapping("/create_task")
	public String getCreateTask(Model model) {
		return "create_task";
	}
	
	
}
