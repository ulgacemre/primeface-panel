package com.github.adminfaces.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.adminfaces.starter.model.Islem;

public interface IslemRepository extends JpaRepository<Islem, Long> {
	
	public Islem findByKullaniciAndFirma(String kullanici, String firma);
	

}
