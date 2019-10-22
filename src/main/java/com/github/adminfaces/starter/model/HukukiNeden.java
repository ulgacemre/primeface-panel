package com.github.adminfaces.starter.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class HukukiNeden extends BaseEntity{

	private String ad;
	
	@ManyToMany(mappedBy="selectedHukukiNeden")
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
