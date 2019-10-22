package com.github.adminfaces.starter.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class ParametreKategori extends BaseEntity{

	private String ad;

	@OneToMany(mappedBy="parametreKategori")
	private List<Parametre> parametreler;
	
	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public List<Parametre> getParametreler() {
		return parametreler;
	}

	public void setParametreler(List<Parametre> parametreler) {
		this.parametreler = parametreler;
	}
}
