package com.example.demo.App;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Config.CustomerUserDetails;
import com.example.demo.Entity.Task;
import com.example.demo.Entity.User;
import com.example.demo.Repository.TaskRepository;

@Controller
public class TaskContoroller {
	@Autowired
	TaskRepository taskRepository;
	
	@GetMapping("/tasks")
	public String getTaskAll(Model model,@AuthenticationPrincipal CustomerUserDetails userDetails) {
		int userId = userDetails.getId();
		List<Task> task = taskRepository.findByUserId(userId);
				
		model.addAttribute("taskList",task);
		
		return "taskList";
	}
	
	@GetMapping("/create_task")
	public String getCreateTask(Task task, Model model,@AuthenticationPrincipal CustomerUserDetails userDetails) {
		int userId = userDetails.getId();
		model.addAttribute("userid",userId);
		
		return "create_task";
	}
	
	@PostMapping("/create_task")
	public String postCreateTask(@Validated Task task, BindingResult bindingResult,@AuthenticationPrincipal CustomerUserDetails userDetails,User user, Model model) {
		if (bindingResult.hasErrors()) {
			return "create_task";
        }
		
		int userId = userDetails.getId();
		
		task.setTitle(task.getTitle());
		task.setDetail(task.getDetail());
		task.setEnd_time(task.getEnd_time());
		task.setUserId(userId);
		
		taskRepository.save(task);
		
		return "redirect:/userList";
	}
}
