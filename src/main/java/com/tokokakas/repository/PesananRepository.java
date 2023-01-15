package com.tokokakas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tokokakas.entity.Pesanan;

public interface PesananRepository extends JpaRepository<Pesanan, String> {
	@Query("select d from Pesanan d where d.deleted=0 and d.status='Belum Dibeli' and d.user_name=:user_name")
	List<Pesanan> getAllPesanan(@Param("user_name") String user_name);
	@Query("select d from Pesanan d where d.status='Sudah Dibeli' and d.user_name=:user_name")
	List<Pesanan> getPesananSudahDibeli(@Param("user_name") String user_name);
}
