package com.github.adminfaces.starter.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Veri extends BaseEntity{
	
	private String veriAdi;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veri_kategori_tanim_id")
	private VeriKategori veriKategori;
	
	@ManyToMany(mappedBy="selectedVeriObj")
	private Set<Surec> surec;
	
	public String getVeriAdi() {
		return veriAdi;
	}
	public void setVeriAdi(String veriAdi) {
		this.veriAdi = veriAdi;
	}
	public VeriKategori getVerikategori() {
		return veriKategori;
	}
	public void setVerikategori(VeriKategori verikategori) {
		this.veriKategori = verikategori;
	}
	public VeriKategori getVeriKategori() {
		return veriKategori;
	}
	public void setVeriKategori(VeriKategori veriKategori) {
		this.veriKategori = veriKategori;
	}
	public Set<Surec> getSurec() {
		return surec;
	}
	public void setSurec(Set<Surec> surec) {
		this.surec = surec;
	}
	
	
}
