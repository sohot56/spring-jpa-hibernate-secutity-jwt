package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SinhVien;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Integer> {
//	SinhVien findByMa(int ma);
//	
//	@Query("UPDATE Customer c SET c.name = :name WHERE c.id = :id")
//	int update(@Param("id") int id, @Param("name") String name);
}
