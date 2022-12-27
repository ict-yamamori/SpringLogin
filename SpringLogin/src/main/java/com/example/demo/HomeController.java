package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Config.CustomerUserDetails;
import com.example.demo.Repository.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;
	
	//userListのアドレスにアクセスした際にGetメソッドを実行。
    @GetMapping("/userList")
    public String getUserList(@AuthenticationPrincipal CustomerUserDetails userDetails,Model model) {
    	List<User> userList = userRepo.findAll();
    	
    	model.addAttribute("userList", userList);
    	
    	String username = userDetails.getUsername_name();
    	
    	int userId = userDetails.getId();

        //template配下のファイル名を指定することでViewを呼び出せる。
    	model.addAttribute("username", username);
    	model.addAttribute("userid",userId);
        return "userList";
    }
    
    @GetMapping("/login")
    public String getSignUp(Model model) {
    	return "login";
    }
    
    @PostMapping("/login")
    public String postSignUp(Model model) {
    	
    	return "redirect:/userList";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
         
        return "signup_form";
    }
    
    @PostMapping("/process_register")
    public String processRegister(@Validated @ModelAttribute("user") User user, BindingResult result) {
    	/* 入力チェック*/
        if (result.hasErrors()) {
            /* NG:ユーザー登録画面に戻る*/
            return "signup_form";
        }
    	
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//         
//        userRepo.save(user);
        
        userService.create(user);
        
        return "register_success";
    }
    
    //テスト用
    @GetMapping("")
    public String viesHomePage(Model model) {
    
        return "index";
    }
}
