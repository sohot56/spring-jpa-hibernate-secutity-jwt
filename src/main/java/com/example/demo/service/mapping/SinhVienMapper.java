package com.example.demo.service.mapping;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.demo.model.SinhVien;
import com.example.demo.repository.SinhVienDTO;

@Service
public class SinhVienMapper implements IMapping<SinhVien, SinhVienDTO> {
	
	@Override
	public SinhVienDTO toDTO(SinhVien t) {
		if (t == null) {
			return null;
		}
		SinhVienDTO svdto = new SinhVienDTO();
		svdto.setMa(t.getMa());
		svdto.setTen(t.getTen());
		svdto.setNgaysinh(new DateTimeConverter().dateToString(t.getNgaysinh()));
		return svdto;
	}

	@Override
	public SinhVien toEntity(SinhVienDTO k) {
		if (k == null) {
			return null;
		}
		Date stringToDate = new DateTimeConverter().stringToDate(k.getNgaysinh());
		if(stringToDate == null) {
			return null;
		}
		SinhVien sv = new SinhVien();
		sv.setMa(k.getMa());
		sv.setTen(k.getTen());
		sv.setNgaysinh(stringToDate);
		return sv;
	}

}
