package com.tokokakas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokokakas.entity.Barang;
import com.tokokakas.repository.BarangRepository;

@Service
public class BarangService {

	@Autowired
	private BarangRepository barangRepo;
	
	//menampilkan semua data barang
	public List<Barang> getAllBarang(){
		return barangRepo.getAllBarang();
	}
	//menampilkan barang berdasarkan nama
	public Barang getBarangByNama(String nama_barang) {
		return barangRepo.findById(nama_barang).get();
	}
	//update barang
	public Barang updateBarang(Barang barang) {
		return barangRepo.save(barang);
	}
}
