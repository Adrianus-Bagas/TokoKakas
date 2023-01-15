package com.tokokakas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tokokakas.entity.DetailPesanan;
import com.tokokakas.service.BarangService;
import com.tokokakas.service.PesananService;
import com.tokokakas.service.UserService;

@Controller
public class BarangController {

	@Autowired
	private BarangService barangService;
	@Autowired
	private UserService userService;
	@Autowired
	private PesananService pesananService;

	@GetMapping("/belanja/{user_name}")
	public String getAllBarang(@PathVariable("user_name") String user_name, Model model) {
		model.addAttribute("user", userService.getUserByUserName(user_name));
		model.addAttribute("barang", barangService.getAllBarang());
		model.addAttribute("pesanan", pesananService.getAllPesanan(user_name));
		model.addAttribute("detail", new DetailPesanan());
		return "belanja";
	}
}
