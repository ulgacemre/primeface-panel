package com.github.adminfaces.starter.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Parametre extends BaseEntity{

	private String ad;
	
	@ManyToOne
	@JoinColumn(name="parametre_kategori_id")
	private ParametreKategori parametreKategori;

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public ParametreKategori getParametreKategori() {
		return parametreKategori;
	}

	public void setParametreKategori(ParametreKategori parametreKategori) {
		this.parametreKategori = parametreKategori;
	}
}
