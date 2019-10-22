package com.github.adminfaces.starter.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Islem {

	// TODO Departmanın burada olmasına gerek var mı?
	private String departman;
	// TODO Burayı transient yapmak ilke çekildiği yerde problem çıkarabilir.
	@Transient
	private List<String> selectedSurecList;
	@Id
	@GeneratedValue
	private Long id;
	
	private String kullanici; // TODO Objeye dönüştürülecek
	
	private String firma; // TODO Objeye dönüştürülecek
	
	@OneToMany(mappedBy="islem",cascade = CascadeType.ALL)
	private List<Surec> selectedSurecListObj;

	public Islem() {
		selectedSurecList = new ArrayList<>();
		// selectedSurecListObj = new ArrayList<>(); setlerken oluştur.
	}

	public Islem(String departman) {
		selectedSurecList = new ArrayList<>();
		this.departman = departman;
	}

	public Islem(String kullanici, String firma,String departman) {
		this.kullanici = kullanici;
		this.firma = firma;
		this.departman = departman;
	}
	
	public Islem(String kullanici, String firma) {
		this.kullanici = kullanici;
		this.firma = firma;
	}

	public String getDepartman() {
		return departman;
	}

	public void setDepartman(String departman) {
		this.departman = departman;
	}

	public List<Surec> getSelectedSurecListObj() {
		return selectedSurecListObj;
	}

	public void setSelectedSurecListObj(List<Surec> selectedSurecListObj) {
		this.selectedSurecListObj = selectedSurecListObj;
	}

	public void setSelectedSurecList(List<String> selectedSurecList) {

		if (selectedSurecListObj == null) {
			selectedSurecListObj = new ArrayList<>();
		}

		// Listeye yeni eklenen varsa obje olarak da ekle
		for (String selectedSurec : selectedSurecList) {

			if (selectedSurecListObj.stream().filter(yeniSurec -> selectedSurec.equals(yeniSurec.getSurecAdi()))
					.findFirst().orElse(null) == null) {

				Surec surecObj = new Surec(departman, selectedSurec);
				surecObj.setIslem(this);
				selectedSurecListObj.add(surecObj);
			}
		}
		
		// Listeden çıkarılan varsa obje listesinden de çıkar
		List<Surec> tmp = new ArrayList<Surec>(selectedSurecListObj);
		for (Surec surecObj:tmp) {
			if (!selectedSurecList.contains(surecObj.getSurecAdi())) {
				selectedSurecListObj.removeIf(removedSurec -> removedSurec.getSurecAdi().equals(surecObj.getSurecAdi()));
				//selectedSurecListObj.remove(1);
			}
		}

		this.selectedSurecList = selectedSurecList;
	}

	public List<String> getSelectedSurecList() {
		return selectedSurecList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKullanici() {
		return kullanici;
	}

	public void setKullanici(String kullanici) {
		this.kullanici = kullanici;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

}
