package com.tokokakas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tokokakas.entity.DetailPesanan;

public interface DetailPesananRepository extends JpaRepository<DetailPesanan, String> {
	@Query("select d from DetailPesanan d where d.id_pesanan=:id_pesanan")
	List<DetailPesanan> getAllDetail(@Param("id_pesanan") String id_pesanan);
}
