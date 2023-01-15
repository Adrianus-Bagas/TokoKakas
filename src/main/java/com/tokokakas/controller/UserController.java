package com.tokokakas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.tokokakas.entity.User;
import com.tokokakas.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/user")
	public String checkLogin(@ModelAttribute("user") User user, Model model) {
		boolean result = userService.checkUser(user.getUser_name(), user.getPassword());
		User dataUser = userService.getUserByUserName(user.getUser_name());
		model.addAttribute("user", dataUser);
		if (result == true) {
			return "home";
		} else {
			return "error";
		}
	}

	@GetMapping("/user/{user_name}")
	public String home(@PathVariable String user_name, @ModelAttribute("user") User user, Model model) {
		model.addAttribute("user", userService.getUserByUserName(user_name));
		return "home";
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/login")
	public String insertUser(@ModelAttribute("user") User user) {
		userService.insertUser(user);
		return "redirect:/login";
	}

	@GetMapping("/user/edit/{user_name}")
	public String editPage(@PathVariable String user_name, Model model) {
		model.addAttribute("user", userService.getUserByUserName(user_name));
		return "edit";
	}

	@PutMapping("/user/{user_name}")
	public String updateUser(@PathVariable String user_name, @ModelAttribute("user") User user, Model model) {
		User updated_user = userService.getUserByUserName(user_name);
		updated_user.setUser_name(user_name);
		updated_user.setPassword(user.getPassword());
		updated_user.setFirst_name(user.getFirst_name());
		updated_user.setLast_name(user.getLast_name());
		updated_user.setKakas_pay(user.getSaldo()+user.getTop_up());
		updated_user.setSaldo(user.getSaldo()+user.getTop_up());
		userService.updateUser(updated_user);
		model.addAttribute("user", updated_user);
		return "redirect:/user/{user_name}";
	}

	@GetMapping("/user/kakas-pay/{user_name}")
	public String kakasPayPage(@PathVariable String user_name, Model model) {
		model.addAttribute("user", userService.getUserByUserName(user_name));
		return "buy-kakas-pay";
	}

}
