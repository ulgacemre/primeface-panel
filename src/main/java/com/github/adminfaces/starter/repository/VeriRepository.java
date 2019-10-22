package com.github.adminfaces.starter.repository;

import java.util.List;

import com.github.adminfaces.starter.model.Veri;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.adminfaces.starter.model.Veri;
import com.github.adminfaces.starter.model.VeriKategori;

public interface VeriRepository extends JpaRepository<Veri, Long> {
	
	List<Veri> findByVeriKategori(VeriKategori veriKategori);
	
	Veri findByVeriAdi(String veriAdi);
	

}
