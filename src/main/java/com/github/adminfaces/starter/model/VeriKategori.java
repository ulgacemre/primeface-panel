package com.github.adminfaces.starter.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class VeriKategori extends BaseEntity {

	private String adi;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "veriKategori")
	private List<Veri> veriler;
	
	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public List<Veri> getVeriler() {
		return veriler;
	}

	public void setVeriler(List<Veri> veriler) {
		this.veriler = veriler;
	}

}
