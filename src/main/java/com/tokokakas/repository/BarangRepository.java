package com.tokokakas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tokokakas.entity.Barang;

public interface BarangRepository extends JpaRepository<Barang, String> {
	@Query("select b from Barang b")
	List<Barang> getAllBarang();
}
