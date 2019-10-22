package com.github.adminfaces.starter.model;

import com.github.adminfaces.starter.repository.ImhaMetoduRepository;
import com.github.adminfaces.starter.repository.IslemRepository;
import com.github.adminfaces.starter.repository.SurecRepository;
import com.github.adminfaces.starter.repository.VeriRepository;
//import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Named
@SessionScoped
// @ManagedBean
public class EnvanterBean {

	@Autowired
	Database database;

	@Autowired
	IslemRepository islemRepository;

	@Autowired
	SurecRepository surecRepository;

	@Autowired
	VeriRepository veriRepository;

	@Autowired
	ImhaMetoduRepository imhaMetoduRepository;

	private Islem islem;
	private Integer progressPercent = 0;

	private List<SelectItem> veriList;
	private List<Amac> amacList;
	private List<Alici> aliciList;
	private List<String> saklamaSuresiList;
	private List<ImhaMetodu> imhaMetoduList;
	private List<VeriSahibi> veriSahibiList;
	private List<HukukiNeden> hukukiNedenList;
	private List<Ulke> ulkeList;
	private List<String> departmanList;
	private List<String> surecList;

	@PostConstruct
	public void init() {

		// TODO İşlem ve checked olan elemanlar, login olunan üye bilgisine göre
		// gelecek.

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			System.out.println(">> init meodu içinde: İsim:" + authentication.getName());
		}

		islem = database.getIslemByKulaniciAndFirma(authentication.getName(), authentication.getName());
		if (islem == null) {
			islem = new Islem(authentication.getName(), authentication.getName());
		} else {
			// TODO islem için selectedxx ler doldurulacak.
			islem.setSelectedSurecList(new ArrayList<String>());
			for (Surec surec : islem.getSelectedSurecListObj()) {
				islem.getSelectedSurecList().add(surec.getSurecAdi());
				
				surec.setSelectedVeri(new HashSet<>());
				for (Veri veri : surec.getSelectedVeriObj()) {
					surec.getSelectedVeri().add(veri.getVeriAdi());
				}
				
				
				if(surec.getSelectedImhaMetoduObj() != null) {
					surec.setSelectedImhaMetodu(surec.getSelectedImhaMetoduObj().getId());
				}
				
			}
		}

		veriList = database.getVeriList();
		amacList = database.getAmacList();
		aliciList = database.getAliciList();
		saklamaSuresiList = database.getSaklamaSuresi();
		veriSahibiList = database.getVeriSahibiList();
		hukukiNedenList = database.getHukukiNedenList();
		ulkeList = database.getUlkeList();
		departmanList = database.getDepartmanList();
		imhaMetoduList = database.getImhaMetodu();
		// Süreç Departmana göre gelecek
		// surecList = database.getSurecList();

	}

	public void exceleAktar() {
	} // TODO Tekrar yazılacak. Lazım olduğunda geçmişten alınabilir

	public String onFlowProcess(FlowEvent event) {

		if (getStepOrder(event.getNewStep()) > getStepOrder(event.getOldStep()))
			progressPercent = progressPercent + 20;
		else
			progressPercent = progressPercent - 20;
		//RequestContext.getCurrentInstance().update("tst:pb1"); // TODO rename

		// TODO Bu taba gelmeden önce de tersi işlemi yapıp obje olmayan listeyi
		// doldurmak gerekli
		if (event.getOldStep().equals("veri")) {
			veriTabIslem();
		}
		if (event.getOldStep().equals("saklamaSuresi")) {
			saklamaIslem();
		}

		// TODO geri deyince davranışı sapıtabilir.
		// islem =
		islemRepository.save(islem);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		System.out.println("İsim:" + authentication.getName());

		return event.getNewStep();
	}

	private void veriTabIslem() {
		for (Surec surec : islem.getSelectedSurecListObj()) {
			if (surec.getSelectedVeriObj() != null && surec.getSelectedVeriObj().size() > 0) {
				for (Veri veri : surec.getSelectedVeriObj()) {
					if (!islem.getSelectedSurecList().contains(veri.getVeriAdi())) {
						surec.getSelectedVeriObj().remove(veri);
					}
				}
			} else {
				surec.setSelectedVeriObj(new HashSet<>());
			}

			for (String veriStr : surec.getSelectedVeri()) {
				boolean mevcut = false;
				for (Veri veri : surec.getSelectedVeriObj()) {
					if (veri.getVeriAdi().equals(veriStr)) {
						mevcut = true;
						break;
					}
				}
				if (!mevcut) {
					Veri veri = veriRepository.findByVeriAdi(veriStr);
					surec.getSelectedVeriObj().add(veri);
				}
				mevcut = false;
			}
		}
	}

	private void saklamaIslem() {
		for (Surec surec : islem.getSelectedSurecListObj()) {
			Optional<ImhaMetodu> imhaMetoduOpt = imhaMetoduRepository.findById(surec.getSelectedImhaMetodu());
			ImhaMetodu imhaMetodu = imhaMetoduOpt.get();
			// if (surec.getSelectedImhaMetoduObj().get)

			if (surec.getSelectedImhaMetoduObj() == null) {
				surec.setSelectedImhaMetoduObj(imhaMetodu);
			} else {
				if (surec.getSelectedImhaMetoduObj().getId() != imhaMetodu.getId()) {
					surec.setSelectedImhaMetoduObj(imhaMetodu);
				}
			}
		}

	}

	// TODO Yeniden düzenle
	private int getStepOrder(String stepName) {
		switch (stepName) {
		case "surec":
			return 1;
		case "veri":
			return 2;
		case "amac":
			return 3;
		case "saklamaSuresi":
			return 4;
		case "aktarma":
			return 5;

		}

		return -1;
	}

	public void onDepartmanChange() {
		//TODO user setleme kısmı bir yeri bozar mı ?
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Islem islemTmp = database.getIslemByKulaniciAndFirma(authentication.getName(), authentication.getName());
		if (islemTmp == null) {
			islem = new Islem(authentication.getName(), authentication.getName(),islem.getDepartman());
		} else {
			// TODO islem için selectedxx ler doldurulacak.
			islem = islemTmp;
			islem.setSelectedSurecList(new ArrayList<String>());
			List<Surec> surecList = islem.getSelectedSurecListObj();
			for (Surec surec : surecList) {
				islem.getSelectedSurecList().add(surec.getSurecAdi());
				
				surec.setSelectedVeri(new HashSet<>());
				for (Veri veri : surec.getSelectedVeriObj()) {
					surec.getSelectedVeri().add(veri.getVeriAdi());
				}
				
				
				if(surec.getSelectedImhaMetoduObj() != null) {
					surec.setSelectedImhaMetodu(surec.getSelectedImhaMetoduObj().getId());
				}
				
			}
		}

		surecList = database.getSurecListVerilenDepartmanBasta(islem.getDepartman());
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<SelectItem> getVeriList() {
		return veriList;
	}

	public void setVeriList(List<SelectItem> veriList) {
		this.veriList = veriList;
	}

	public List<String> getSaklamaSuresiList() {
		return saklamaSuresiList;
	}

	public void setSaklamaSuresiList(List<String> saklamaSuresiList) {
		this.saklamaSuresiList = saklamaSuresiList;
	}

	public Integer getProgressPercent() {
		return progressPercent;
	}

	public void setProgressPercent(Integer progressPercent) {
		this.progressPercent = progressPercent;
	}

	public List<String> getDepartmanList() {
		return departmanList;
	}

	public void setDepartmanList(List<String> departmanList) {
		this.departmanList = departmanList;
	}

	public List<String> getSurecList() {
		return surecList;
	}

	public void setSurecList(List<String> surecList) {
		this.surecList = surecList;
	}

	public Islem getIslem() {
		return islem;
	}

	public void setIslem(Islem islem) {
		this.islem = islem;
	}

	public List<Amac> getAmacList() {
		return amacList;
	}

	public void setAmacList(List<Amac> amacList) {
		this.amacList = amacList;
	}

	public List<VeriSahibi> getVeriSahibiList() {
		return veriSahibiList;
	}

	public void setVeriSahibiList(List<VeriSahibi> veriSahibiList) {
		this.veriSahibiList = veriSahibiList;
	}

	public List<HukukiNeden> getHukukiNedenList() {
		return hukukiNedenList;
	}

	public void setHukukiNedenList(List<HukukiNeden> hukukiNedenList) {
		this.hukukiNedenList = hukukiNedenList;
	}

	public List<Alici> getAliciList() {
		return aliciList;
	}

	public void setAliciList(List<Alici> aliciList) {
		this.aliciList = aliciList;
	}

	public List<ImhaMetodu> getImhaMetoduList() {
		return imhaMetoduList;
	}

	public void setImhaMetoduList(List<ImhaMetodu> imhaMetoduList) {
		this.imhaMetoduList = imhaMetoduList;
	}

	public List<Ulke> getUlkeList() {
		return ulkeList;
	}

	public void setUlkeList(List<Ulke> ulkeList) {
		this.ulkeList = ulkeList;
	}

}
