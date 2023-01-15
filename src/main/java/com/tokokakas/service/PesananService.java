package com.tokokakas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokokakas.entity.Pesanan;
import com.tokokakas.repository.PesananRepository;

@Service
public class PesananService {
	
	@Autowired
	private PesananRepository pesananRepo;
	
	//menampilkan semua pesanan yang belum dibeli
	public List<Pesanan> getAllPesanan(String user_name){
		return pesananRepo.getAllPesanan(user_name);
	}
	
	//menampilkan semua pesanan yang sudah dibeli
		public List<Pesanan> getPesananSudahDibeli(String user_name){
			return pesananRepo.getPesananSudahDibeli(user_name);
		}
	
	//menambah pesanan
	public void insertPesanan(Pesanan pesanan) {
		pesananRepo.save(pesanan);
	}
	
	//menampilkan pesanan sesuai id
	public Pesanan getPesananById(String id_pesanan) {
		return pesananRepo.findById(id_pesanan).get();
	}
	
	//update pesanan
	public Pesanan updatePesanan(Pesanan pesanan) {
		return pesananRepo.save(pesanan);
	}
	
	//menghapus pesanan
	public void deletePesanan(String id_pesanan) {
		pesananRepo.deleteById(id_pesanan);;
	}
}
