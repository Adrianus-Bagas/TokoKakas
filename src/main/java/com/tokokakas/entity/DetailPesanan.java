package com.tokokakas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="\"DetailPesanan\"")
public class DetailPesanan {

	@Id
	@Column(name="\"id_detail\"")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id_detail;
	
	@Column(name="\"jumlah\"")
	private int jumlah;
	
	@Column(name="\"id_pesanan\"")
	private String id_pesanan;
	
	@Column(name="\"nama_barang\"")
	private String nama_barang;
}
