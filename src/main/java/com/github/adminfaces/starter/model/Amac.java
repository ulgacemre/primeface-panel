package com.github.adminfaces.starter.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Amac extends BaseEntity{

	private String ad;
	
	@ManyToMany(mappedBy="selectedAmac")
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
