package com.tokokakas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokokakas.entity.DetailPesanan;
import com.tokokakas.repository.DetailPesananRepository;

@Service
public class DetailPesananService {
	
	@Autowired
	private DetailPesananRepository detailpesananRepo;
	
	//menampilkan semua detail
	public List<DetailPesanan> getAllDetail(String id_pesanan){
		return detailpesananRepo.getAllDetail(id_pesanan);
	}
	
	//menampilkan detail berdasarkan id
	public DetailPesanan getDetailById(String id_detail) {
		return detailpesananRepo.findById(id_detail).get();
	}
	
	//menambahkan detail
	public void insertDetail(DetailPesanan detail_pesanan) {
		detailpesananRepo.save(detail_pesanan);
	}
	
	//edit detail
	public DetailPesanan updateDetail(DetailPesanan detail_pesanan) {
		return detailpesananRepo.save(detail_pesanan);
	}
	
	//hapus detail
	public void deleteDetail(String id_detail) {
		detailpesananRepo.deleteById(id_detail);
	}
}
