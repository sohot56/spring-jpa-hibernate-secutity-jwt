package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SinhVien;
import com.example.demo.repository.SinhVienDTO;
import com.example.demo.repository.SinhVienRepository;
import com.example.demo.service.exception.SinhVienException;
import com.example.demo.service.mapping.SinhVienMapper;

@Service
@Transactional
//@ComponentScan(value = "com.example.demo.repository")
public class SinhVienServiceImpl implements SinhVienService {
	@Autowired
	private SinhVienRepository sinhVienRepository;
	@Autowired
	private SinhVienMapper sinhVienMapper;

	@Override
	public List<SinhVienDTO> getAll() {
		return sinhVienRepository.findAll().stream().map(sinhVienMapper::toDTO).collect(Collectors.toList());

	}

	@Override
	public SinhVienDTO addNew(SinhVienDTO svdto) {
		SinhVien sv = sinhVienMapper.toEntity(svdto);
		if (sv == null) {
			throw new SinhVienException("sinh vien không đúng");
		}
		return sinhVienMapper.toDTO(sinhVienRepository.saveAndFlush(sv));

	}

	// mandatory 1 tran nằm trong 1trans khác. khi nó rollback thì thằng bên ngoài
	// cũng rollback
	// nested 1 tran nằm trong 1 tran khác nhưng khi nó rollback không ảnh hưởng đến
	// bên ngoài
	// required 1 trans chứa 1 tran khác
	//
	@Override
	@Transactional(value = TxType.REQUIRED, rollbackOn = SinhVienException.class)
	public SinhVienDTO update(int ma, SinhVienDTO svdto) {
		Optional<SinhVien> findById = sinhVienRepository.findById(ma);
		SinhVien svById = findById.orElseThrow(new Supplier<SinhVienException>() {
			@Override
			public SinhVienException get() {
				throw new SinhVienException("khong tim thay sinh vien " + ma);
			}
		});
		if (svById == null) {
			return null;
		}
		SinhVien sv = sinhVienMapper.toEntity(svdto);
		if (sv == null) {
			throw new SinhVienException("khong convert duoc sinh vien can update");
		}
		svById.setTen(sv.getTen());
		svById.setNgaysinh(sv.getNgaysinh());
		SinhVien saveAndFluh = sinhVienRepository.saveAndFlush(svById);

		return sinhVienMapper.toDTO(saveAndFluh);
	}

}
