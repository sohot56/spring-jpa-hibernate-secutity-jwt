package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.SinhVienDTO;
import com.example.demo.service.SinhVienService;

@RestController
@RequestMapping("/sinhvien")
public class SinhVienController {
	@Autowired
	private SinhVienService sinhVienService;
	
	@GetMapping
	public List<SinhVienDTO> getAll(){
		return sinhVienService.getAll();
	}
	@PostMapping(value="/add")
	public ResponseEntity<SinhVienDTO> addNew(@RequestBody SinhVienDTO svdto){
		SinhVienDTO addNew = sinhVienService.addNew(svdto);
		if (addNew == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(addNew);
		
	}
	@PutMapping(value="/update/{ma}")
	public ResponseEntity<SinhVienDTO> update(@PathVariable("ma") int ma, @RequestBody SinhVienDTO svdto){
		SinhVienDTO update = sinhVienService.update(ma, svdto);
		if (update == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(update);
	}
}
