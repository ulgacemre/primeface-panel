package com.github.adminfaces.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.adminfaces.starter.model.ParametreKategori;

public interface ParametreKategoriRepository extends JpaRepository<ParametreKategori, Long> {

	ParametreKategori findByAd(String ad);
}
