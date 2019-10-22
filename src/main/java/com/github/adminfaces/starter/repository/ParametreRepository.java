package com.github.adminfaces.starter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.adminfaces.starter.model.Parametre;
import com.github.adminfaces.starter.model.ParametreKategori;

public interface ParametreRepository extends JpaRepository<Parametre, Long> {
	
	List<Parametre> findByParametreKategori(ParametreKategori parametreKategori);

}
