package com.tokokakas.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.tokokakas.entity.Barang;
import com.tokokakas.entity.DetailPesanan;
import com.tokokakas.entity.Pesanan;
import com.tokokakas.entity.User;
import com.tokokakas.service.BarangService;
import com.tokokakas.service.DetailPesananService;
import com.tokokakas.service.PesananService;
import com.tokokakas.service.UserService;

@Controller
public class PesananController {

	@Autowired
	private PesananService pesananService;
	@Autowired
	private UserService userService;
	@Autowired
	private DetailPesananService detailService;
	@Autowired
	private BarangService barangService;

	@GetMapping("/keranjang/{user_name}")
	public String pesananPage(@PathVariable String user_name, Model model) {
		model.addAttribute("user", userService.getUserByUserName(user_name));
		model.addAttribute("pesanan", pesananService.getAllPesanan(user_name));
		return "keranjang";
	}
	
	@GetMapping("/riwayat/{user_name}")
	public String riwayatPage(@PathVariable String user_name, Model model) {
		model.addAttribute("user", userService.getUserByUserName(user_name));
		model.addAttribute("pesanan", pesananService.getPesananSudahDibeli(user_name));
		return "riwayat";
	}
	
	@DeleteMapping("/riwayat/{user_name}")
	public String hapusRiwayat(@PathVariable String user_name, @ModelAttribute("pesanan") Pesanan pesanan) {
		List<DetailPesanan> detail = detailService.getAllDetail(pesanan.getId_pesanan());
		for(int i=0;i<detail.size();i++) {
			String id_detail = detail.get(i).getId_detail();
			detailService.deleteDetail(id_detail);
		}
		pesananService.deletePesanan(pesanan.getId_pesanan());
		return "redirect:/riwayat/{user_name}";
	}
	
	@PostMapping("/keranjang/{user_name}")
	public String insertKeranjang(@PathVariable String user_name, Model model,
			@ModelAttribute("pesanan") Pesanan pesanan) {
		model.addAttribute("user", userService.getUserByUserName(user_name));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		pesanan.setTanggal_dibuat(dtf.format(now));
		pesananService.insertPesanan(pesanan);
		return "redirect:/keranjang/{user_name}";
	}
	
	@DeleteMapping("/keranjang/{user_name}")
	public String deleteKeranjang(@PathVariable String user_name, @ModelAttribute("pesanan") Pesanan pesanan) {
		List<DetailPesanan> detail = detailService.getAllDetail(pesanan.getId_pesanan());
		for(int i=0;i<detail.size();i++) {
			String id_detail = detail.get(i).getId_detail();
			detailService.deleteDetail(id_detail);
		}
		pesananService.deletePesanan(pesanan.getId_pesanan());
		return "redirect:/keranjang/{user_name}";
	}
	
	@PutMapping("/keranjang/{user_name}")
	public String beliKeranjang(@PathVariable String user_name, Model model,
			@ModelAttribute("pesanan") Pesanan pesanan) {
		Pesanan psn = pesananService.getPesananById(pesanan.getId_pesanan());
		User user = userService.getUserByUserName(user_name);
		List<DetailPesanan> detail = detailService.getAllDetail(pesanan.getId_pesanan());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		for(int i=0;i<detail.size();i++) {
			String nama_barang = detail.get(i).getNama_barang();
			Barang barang = barangService.getBarangByNama(nama_barang);
			int jumlahBaru = barang.getJumlah_barang()-detail.get(i).getJumlah();
			barang.setJumlah_barang(jumlahBaru);
			barangService.updateBarang(barang);
		}
		psn.setTanggal_pesanan(dtf.format(now));
		psn.setStatus("Sudah Dibeli");
		psn.setDeleted(1);
		psn.setUser_name(user_name);
		pesananService.updatePesanan(psn);
		int kakas_pay = user.getKakas_pay()-psn.getBiaya();
		user.setKakas_pay(kakas_pay);
		user.setSaldo(kakas_pay);
		userService.updateUser(user);
		return "redirect:/keranjang/{user_name}";
	}
}
