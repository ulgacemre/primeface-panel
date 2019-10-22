package com.github.adminfaces.starter.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Ulke extends BaseEntity{

	private String ad;
	
	@ManyToMany(mappedBy="selectedUlkeler")
	private Set<Surec> surec;

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public Set<Surec> getSurec() {
		return surec;
	}

	public void setSurec(Set<Surec> surec) {
		this.surec = surec;
	}
}
