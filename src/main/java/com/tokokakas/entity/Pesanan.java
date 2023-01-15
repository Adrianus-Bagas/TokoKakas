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
@Table(name="\"Pesanan\"")
public class Pesanan {
	@Id
	@Column(name="\"id_pesanan\"")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id_pesanan;
	
	@Column(name="\"tanggal_pesanan\"")
	private String tanggal_pesanan=null;
	
	@Column(name="\"biaya\"")
	private int biaya=0;
	
	@Column(name="\"tanggal_dibuat\"")
	private String tanggal_dibuat;
	
	@Column(name="\"status\"")
	private String status="Belum Dibeli";
	
	@Column(name="\"user_name\"")
	private String user_name;
	
	@Column(name="\"deleted\"")
	private int deleted=0;
	
	@Column(name="\"nama_pesanan\"")
	private String nama_pesanan;
}
