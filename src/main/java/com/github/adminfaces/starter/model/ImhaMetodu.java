package com.github.adminfaces.starter.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class ImhaMetodu extends BaseEntity{

	private String ad;
	
	@OneToMany(mappedBy="selectedImhaMetoduObj")
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
