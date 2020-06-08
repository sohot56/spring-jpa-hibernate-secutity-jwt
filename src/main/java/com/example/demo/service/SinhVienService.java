package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.SinhVienDTO;

public interface SinhVienService {
	public List<SinhVienDTO> getAll();
	public SinhVienDTO addNew(SinhVienDTO svdto);
	public SinhVienDTO update(int ma, SinhVienDTO svdto);
}
