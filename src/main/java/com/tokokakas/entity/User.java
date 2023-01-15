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
@Table(name="\"User\"")
public class User {
	@Id
	@Column(name="\"user_name\"")
	private String user_name;
	
	@Column(name="\"password\"")
	private String password;
	
	@Column(name="\"first_name\"")
	private String first_name;
	
	@Column(name="\"last_name\"")
	private String last_name;
	
	@Column(name="\"saldo\"")
	private int saldo = 0;
	
	@Column(name="\"top_up\"")
	private int top_up = 0;
	
	@Column(name="\"kakas_pay\"")
	private int kakas_pay = 0;
}
