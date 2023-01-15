package com.tokokakas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tokokakas.entity.Barang;
import com.tokokakas.entity.DetailPesanan;
import com.tokokakas.entity.Pesanan;
import com.tokokakas.service.BarangService;
import com.tokokakas.service.DetailPesananService;
import com.tokokakas.service.PesananService;
import com.tokokakas.service.UserService;

@Controller
public class DetailPesananController {

	@Autowired
	private DetailPesananService detailService;
	@Autowired
	private UserService userService;
	@Autowired
	private PesananService pesananService;
	@Autowired
	private BarangService barangService;

	@GetMapping("/keranjang/{user_name}/{id_pesanan}")
	public String detailPage(@PathVariable("user_name") String user_name, @PathVariable("id_pesanan") String id_pesanan,
			Model model) {
		model.addAttribute("user", userService.getUserByUserName(user_name));
		model.addAttribute("pesanan", pesananService.getPesananById(id_pesanan));
		model.addAttribute("detail", detailService.getAllDetail(id_pesanan));
		return "detail";
	}

	@GetMapping("/keranjang/tambah-barang/{user_name}/{id_pesanan}")
	public String detailForm(@PathVariable("user_name") String user_name, @PathVariable("id_pesanan") String id_pesanan,
			Model model) {
		model.addAttribute("user", userService.getUserByUserName(user_name));
		model.addAttribute("pesanan", pesananService.getPesananById(id_pesanan));
		model.addAttribute("barang", barangService.getAllBarang());
		model.addAttribute("detail", new DetailPesanan());
		return "belanja";
	}
	
	@DeleteMapping("/keranjang/{user_name}/{id_pesanan}")
	public String deleteDetail(@PathVariable("user_name") String user_name,
			@PathVariable("id_pesanan") String id_pesanan, @ModelAttribute("detail") DetailPesanan detail,Model model) {
		String id_detail = detail.getId_detail();
		Pesanan pesanan = pesananService.getPesananById(id_pesanan);
		Barang barang = barangService.getBarangByNama(detail.getNama_barang());
		int biaya = pesanan.getBiaya() - detail.getJumlah() * barang.getHarga_barang();
		pesanan.setBiaya(biaya);
		detailService.deleteDetail(id_detail);
		pesananService.updatePesanan(pesanan);
		model.addAttribute("user",userService.getUserByUserName(user_name));
		model.addAttribute("pesanan",pesananService.getPesananById(id_pesanan));
		model.addAttribute("detail",detailService.getAllDetail(id_pesanan));
		return "redirect:/keranjang/{user_name}/{id_pesanan}";
	}

	@PostMapping("/keranjang/{user_name}/{id_pesanan}")
	public String detailInsert(@PathVariable("user_name") String user_name,
			@PathVariable("id_pesanan") String id_pesanan, @ModelAttribute("detail") DetailPesanan detail,
			Model model) {
		Pesanan pesanan = pesananService.getPesananById(id_pesanan);
		Barang barang = barangService.getBarangByNama(detail.getNama_barang());
		detail.setId_pesanan(id_pesanan);
		detailService.insertDetail(detail);
		int biaya = pesanan.getBiaya() + detail.getJumlah() * barang.getHarga_barang();
		pesanan.setBiaya(biaya);
		pesananService.updatePesanan(pesanan);
		model.addAttribute("pesanan", pesanan);
		model.addAttribute("user", userService.getUserByUserName(user_name));
		model.addAttribute("detail", detailService.getDetailById(detail.getId_detail()));
		return "redirect:/keranjang/{user_name}/{id_pesanan}";
	}
}
