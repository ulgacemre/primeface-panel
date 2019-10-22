package com.github.adminfaces.starter.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.adminfaces.starter.repository.VeriRepository;

@Entity
public class Surec {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Islem islem;
	private String departman;
	private String surecAdi;

	@ManyToMany
	private Set<VeriSahibi> selectedVeriSahibi;

	@Transient
	private Set<String> selectedVeri; // TODO
	@ManyToMany
	private Set<Veri> selectedVeriObj;

	@ManyToMany
	private Set<Amac> selectedAmac;
	@ManyToMany
	private Set<HukukiNeden> selectedHukukiNeden;
	private String selectedSaklamaSuresi;
	@ManyToOne
	private ImhaMetodu selectedImhaMetoduObj;
	private Long selectedImhaMetodu;
	@ManyToMany
	private Set<Alici> selectedAlicilar;
	private boolean yurtdisiAktarim;
	@ManyToMany
	private Set<Ulke> selectedUlkeler;

	public Surec(String departman, String surecAdi) {
		this.surecAdi = surecAdi;
		this.departman = departman;
	}

	public Surec() {

	}

	public String getSurecAdi() {
		return surecAdi;
	}

	public void setSurecAdi(String surecAdi) {
		this.surecAdi = surecAdi;
	}

	public String getDepartman() {
		return departman;
	}

	public void setDepartman(String departman) {
		this.departman = departman;
	}

	public String getSelectedSaklamaSuresi() {
		return selectedSaklamaSuresi;
	}

	public void setSelectedSaklamaSuresi(String selectedSaklamaSuresi) {
		this.selectedSaklamaSuresi = selectedSaklamaSuresi;
	}

	public boolean isYurtdisiAktarim() {
		return yurtdisiAktarim;
	}

	public void setYurtdisiAktarim(boolean yurtdisiAktarim) {
		this.yurtdisiAktarim = yurtdisiAktarim;
	}

	public Islem getIslem() {
		return islem;
	}

	public void setIslem(Islem islem) {
		this.islem = islem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<VeriSahibi> getSelectedVeriSahibi() {
		return selectedVeriSahibi;
	}

	public void setSelectedVeriSahibi(Set<VeriSahibi> selectedVeriSahibi) {
		this.selectedVeriSahibi = selectedVeriSahibi;
	}

	public Set<Amac> getSelectedAmac() {
		return selectedAmac;
	}

	public void setSelectedAmac(Set<Amac> selectedAmac) {
		this.selectedAmac = selectedAmac;
	}

	public Set<HukukiNeden> getSelectedHukukiNeden() {
		return selectedHukukiNeden;
	}

	public void setSelectedHukukiNeden(Set<HukukiNeden> selectedHukukiNeden) {
		this.selectedHukukiNeden = selectedHukukiNeden;
	}

	public Set<Alici> getSelectedAlicilar() {
		return selectedAlicilar;
	}

	public void setSelectedAlicilar(Set<Alici> selectedAlicilar) {
		this.selectedAlicilar = selectedAlicilar;
	}

	public Set<Ulke> getSelectedUlkeler() {
		return selectedUlkeler;
	}

	public void setSelectedUlkeler(Set<Ulke> selectedUlkeler) {
		this.selectedUlkeler = selectedUlkeler;
	}

	public Set<String> getSelectedVeri() {
//		selectedVeri = new HashSet<>();
//		// TODO Bunun döngüsüz bir yolu yok mu?
//		if (selectedVeriObj != null && selectedVeriObj.size() > 0) {
//			for (Veri obj : selectedVeriObj) {
//				selectedVeri.add(obj.getVeriAdi());
//			}
//		}

		return selectedVeri;
	}

	public void setSelectedVeri(Set<String> selectedVeri) {
		this.selectedVeri = selectedVeri;
	}

	public Set<Veri> getSelectedVeriObj() {
		return selectedVeriObj;
	}

	public void setSelectedVeriObj(Set<Veri> selectedVeriObj) {
		this.selectedVeriObj = selectedVeriObj;
	}

	public ImhaMetodu getSelectedImhaMetoduObj() {
		return selectedImhaMetoduObj;
	}

	public void setSelectedImhaMetoduObj(ImhaMetodu selectedImhaMetoduObj) {
		this.selectedImhaMetoduObj = selectedImhaMetoduObj;
	}

	public Long getSelectedImhaMetodu() {
		return selectedImhaMetodu;
	}

	public void setSelectedImhaMetodu(Long selectedImhaMetodu) {
		this.selectedImhaMetodu = selectedImhaMetodu;
	}

}
