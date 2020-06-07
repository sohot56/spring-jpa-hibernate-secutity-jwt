package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "sinhvien")
public class SinhVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ma;
	
	@Column(name = "ten")
	private String ten;
	
	@Column(name = "ngaysinh")
	private Date ngaysinh;
}
