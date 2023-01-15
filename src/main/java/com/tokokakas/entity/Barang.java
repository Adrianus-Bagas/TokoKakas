package com.tokokakas.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="\"Barang\"")
public class Barang {
	@Id
	@Column(name="\"nama_barang\"")
	private String nama_barang;
	
	@Column(name="\"jumlah_barang\"")
	private int jumlah_barang;
	
	@Column(name="\"harga_barang\"")
	private int harga_barang;
}
