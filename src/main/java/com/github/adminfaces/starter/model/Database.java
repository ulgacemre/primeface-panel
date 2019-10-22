package com.github.adminfaces.starter.model;

import com.github.adminfaces.starter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import java.util.ArrayList;
import java.util.List;

// TODO Veri tabanı implementasyonu yapılana kadar bu obje DB gibi davranacak.
@Component
public class Database {

	@Autowired
	VeriRepository veriRepository;
	@Autowired
	VeriKategoriRepository veriKategoriRepository;
	@Autowired
	ParametreKategoriRepository parametreKategoriRepository;
	@Autowired
	ParametreRepository parametreRepository;
	@Autowired
	VeriSahibiRepository veriSahibiRepository;
	@Autowired
	HukukiNedenRepository hukukiNedenRepository;
	@Autowired
	ImhaMetoduRepository imhaMetoduRepository;
	@Autowired
	AliciRepository aliciRepository;
	@Autowired
	AmacRepository amacRepository;
	@Autowired
	UlkeRepository ulkeRepository;
	@Autowired
	IslemRepository islemRepository;
	
	public List<SelectItem> getVeriList() {

		List<SelectItem> veriList = new ArrayList<>();

		// TODO Kullanıcı kategori ekleyebilir mi? Kullanıcı veri eklerken standart
		// kategori mi seçecek?
		// TODO Veri gelirken sadece system olanlar ve kullanıcının kendi ekledikleri olsun

		List<VeriKategori> kategoriler = veriKategoriRepository.findAll();
		for (VeriKategori kategori : kategoriler) {
			SelectItemGroup sag = new SelectItemGroup(kategori.getAdi());
			List<SelectItem> selectItemList = new ArrayList<>();

			for (Veri veri : kategori.getVeriler()) {
				SelectItem sa = new SelectItem(veri.getVeriAdi());
				selectItemList.add(sa);
			}
			sag.setSelectItems(selectItemList.toArray(new SelectItem[selectItemList.size()]));
			veriList.add(sag);
		}
		return veriList;
	}

	public List<Amac> getAmacList() {
		
		return amacRepository.findAll();
	}

	public List<Alici> getAliciList() {
		return aliciRepository.findAll();
	}

	public List<String> getSaklamaSuresi() {
		List<String> saklamaSuresiList = new ArrayList<>();
		saklamaSuresiList.add("1 hafta");
		saklamaSuresiList.add("1 ay");
		saklamaSuresiList.add("3 ay");
		saklamaSuresiList.add("6 ay");
		saklamaSuresiList.add("1 yıl");
		saklamaSuresiList.add("Kıyamet Gününe Kadar");
		return saklamaSuresiList;
	}

	public List<VeriSahibi> getVeriSahibiList() {
		return veriSahibiRepository.findAll();
	}

	public List<Ulke> getUlkeList() {
	
		return ulkeRepository.findAll();
	}

	public List<String> getGuvenlikTedList() {
		List<String> guvenlikTedList = new ArrayList<>();
		guvenlikTedList.add("Ağ güvenliği ve uygulama güvenliği sağlanmaktadır. ");
		guvenlikTedList.add("Ağ yoluyla kişisel veri aktarımlarında kapalı sistem ağ kullanılmaktadır. ");
		guvenlikTedList.add("Anahtar yönetimi uygulanmaktadır. ");
		guvenlikTedList.add(
				"Bilgi teknolojileri sistemleri tedarik, geliştirme ve bakımı kapsamındaki güvenlik önlemleri alınmaktadır. ");
		guvenlikTedList.add("Bulutta depolanan kişisel verilerin güvenliği sağlanmaktadır. ");
		guvenlikTedList.add("Çalışanlar için veri güvenliği hükümleri içeren disiplin düzenlemeleri mevcuttur. ");
		guvenlikTedList.add(
				"Çalışanlar için veri güvenliği konusunda belli aralıklarla eğitim ve farkındalık çalışmaları yapılmaktadır. ");
		guvenlikTedList.add("Çalışanlar için yetki matrisi oluşturulmuştur. ");
		guvenlikTedList.add("Erişim logları düzenli olarak tutulmaktadır. ");
		guvenlikTedList.add(
				"Erişim, bilgi güvenliği, kullanım, saklama ve imha konularında kurumsal politikalar hazırlanmış ve uygulamaya başlanmıştır. ");
		guvenlikTedList.add("Gerektiğinde veri maskeleme önlemi uygulanmaktadır.        ");
		guvenlikTedList.add("Gizlilik taahhütnameleri yapılmaktadır. ");
		guvenlikTedList.add(
				"Görev değişikliği olan ya da işten ayrılan çalışanların bu alandaki yetkileri kaldırılmaktadır. ");
		guvenlikTedList.add("Güncel anti-virüs sistemleri kullanılmaktadır. ");
		guvenlikTedList.add("Güvenlik duvarları kullanılmaktadır. ");
		guvenlikTedList.add("İmzalanan sözleşmeler veri güvenliği hükümleri içermektedir. ");
		guvenlikTedList.add(
				"Kağıt yoluyla aktarılan kişisel veriler için ekstra güvenlik tedbirleri alınmakta ve ilgili evrak gizlilik dereceli belge formatında gönderilmektedir. ");
		guvenlikTedList.add("Kişisel veri güvenliği politika ve prosedürleri belirlenmiştir. ");
		guvenlikTedList.add("Kişisel veri güvenliği sorunları hızlı bir şekilde raporlanmaktadır. ");
		guvenlikTedList.add("Kişisel veri güvenliğinin takibi yapılmaktadır. ");
		guvenlikTedList.add(
				"Kişisel veri içeren fiziksel ortamlara giriş çıkışlarla ilgili gerekli güvenlik önlemleri alınmaktadır. ");
		guvenlikTedList.add(
				"Kişisel veri içeren fiziksel ortamların dış risklere (yangın, sel vb.) karşı güvenliği sağlanmaktadır. ");
		guvenlikTedList.add("Kişisel veri içeren ortamların güvenliği sağlanmaktadır. ");
		guvenlikTedList.add("Kişisel veriler mümkün olduğunca azaltılmaktadır. ");
		guvenlikTedList
				.add("Kişisel veriler yedeklenmekte ve yedeklenen kişisel verilerin güvenliği de sağlanmaktadır. ");
		guvenlikTedList.add(
				"Kullanıcı hesap yönetimi ve yetki kontrol sistemi uygulanmakta olup bunların takibi de yapılmaktadır. ");
		guvenlikTedList.add("Kurum içi periyodik ve/veya rastgele denetimler yapılmakta ve yaptırılmaktadır. ");
		guvenlikTedList.add("Log kayıtları kullanıcı müdahalesi olmayacak şekilde tutulmaktadır. ");
		guvenlikTedList.add("Mevcut risk ve tehditler belirlenmiştir. ");
		guvenlikTedList.add(
				"Özel nitelikli kişisel veri güvenliğine yönelik protokol ve prosedürler belirlenmiş ve uygulanmaktadır. ");
		guvenlikTedList.add(
				"Özel nitelikli kişisel veriler elektronik posta yoluyla gönderilecekse mutlaka şifreli olarak ve KEP veya kurumsal posta hesabı kullanılarak gönderilmektedir. ");
		guvenlikTedList.add(
				"Özel nitelikli kişisel veriler için güvenli şifreleme / kriptografik anahtarlar kullanılmakta ve farklı birimlerce yönetilmektedir. ");
		guvenlikTedList.add("Saldırı tespit ve önleme sistemleri kullanılmaktadır. ");
		guvenlikTedList.add("Sızma testi uygulanmaktadır. ");
		guvenlikTedList.add("Siber güvenlik önlemleri alınmış olup uygulanması sürekli takip edilmektedir. ");
		guvenlikTedList.add("Şifreleme yapılmaktadır. ");
		guvenlikTedList.add(
				"Taşınabilir bellek, CD, DVD ortamında aktarılan özel nitelikli kişiler veriler şifrelenerek aktarılmaktadır. ");
		guvenlikTedList.add(
				"Veri işleyen hizmet sağlayıcılarının veri güvenliği konusunda belli aralıklarla denetimi sağlanmaktadır. ");
		guvenlikTedList
				.add("Veri işleyen hizmet sağlayıcılarının, veri güvenliği konusunda farkındalığı sağlanmaktadır. ");
		return guvenlikTedList;
	}

	public List<String> getDepartmanList() {
		List<String> departmanList = new ArrayList<>();
		departmanList.add("İnsan Kaynakları");
		departmanList.add("Muhasebe");
		departmanList.add("Finans");
		departmanList.add("İdari İşler");
		departmanList.add("Bilgi İşlem");
		departmanList.add("Satın Alma");
		departmanList.add("İş Sağlığı ve Güvenliği");
		departmanList.add("Sağlık");

		return departmanList;
	}

	// TODO: DB ye taşı
	public List<String> getSurecListByDepartman(String departman) {

		List<String> surecList = new ArrayList<>();

		switch (departman) {
		case "İnsan Kaynakları":
			surecList.add("Personel Adayı Değerlendirme");
			surecList.add("Personel Özlük Dosyası");
			surecList.add("İş Sözleşmesi");
			surecList.add("Kayıt Süreci ");
			surecList.add("SGK e-Bildirge");
			surecList.add("Maaş Ödemeleri");
			surecList.add("Taahhütname");
			surecList.add("AGİ Formu");
			surecList.add("Personel Devam Kontrol Süreci");
			surecList.add("Bordrolama");
			surecList.add("Puantaj ");
			surecList.add("Banka Ödeme Formatlarının Oluşturulması");
			surecList.add("BES Ödemeleri");
			surecList.add("İş Kazaları Ve Meslek Hastalıkları Bildirimi");
			surecList.add("Performans Değerlendirmesi");
			surecList.add("Disiplin İşlemleri");
			surecList.add("Sağlık Süreçlerinin Takibi");
			surecList.add("İcra / Nafaka Ve Vergi Kesintisi Süreçlerinin Yönetimi");
			surecList.add("Raporlama");
			break;

		case "Muhasebe":
			surecList.add("Cari Kayıt");
			surecList.add("Vekaletname, Devir İşlemleri");
			surecList.add("Kurumlar Vergisi Beyannamesi");
			surecList.add("Faturalandırma");
			surecList.add("Vergi Borçsuzluk Belgesi");
			surecList.add("Özel Sağlık Sigortası");
			break;

		case "Finans":
			surecList.add("Çek-Senet Girişi");
			surecList.add("Cari Hesap ve Tahsilat Takibi");
			surecList.add("Faktöring İşlemleri");
			surecList.add("Banka Operasyon İşlemleri");
			surecList.add("POS İşlemleri");
			surecList.add("Sigorta Hasar Tazmin");
			surecList.add("Kıdem Tazminatı Tahakkuku");
			break;

		case "İdari İşler":
			surecList.add("Hizmet Alımı");
			surecList.add("Bina Giriş Çıkışlarının Takibi");
			surecList.add("Araç Takibi");
			surecList.add("Güvenlik Personeli Bildirimi");
			surecList.add("Gelen - Giden Evrak Defteri");
			surecList.add("Acil Durum İrtibat Listesi");
			surecList.add("Misafirhane Hizmetleri");
			surecList.add("Kayıp Buluntu, Müsadere, Muvafakatname");
			surecList.add("Kaza Olay Yönetimi");
			break;

		case "Bilgi İşlem":
			surecList.add("Kullanıcı Hesap Yönetimi");
			surecList.add("Zimmet Formları");
			surecList.add("Erişim Logları");
			break;

		case "Satın Alma":
			surecList.add("Satın Alma");
			surecList.add("İhale Dosyası Oluşturma");
			surecList.add("Sözleşme Yönetimi");
			surecList.add("Piyasa Araştırması");
			break;

		case "İş Sağlığı ve Güvenliği":
			surecList.add("Eğitim Süreçlerinin Yönetimi");
			surecList.add("İSG Kurul Toplantı Tutanakları");
			surecList.add("İş Kazaları Ve Rapor Süreç Yönetimi");
			surecList.add("Risk Dğerlendirmesi");
			surecList.add("Acil Durum İrtibat Listesi");
			break;

		case "Sağlık":
			surecList.add("İşe Giriş Ve Periyodik Sağlık Değerlendirmesi");
			surecList.add("Kişisel Sağlık Dosyası Hazırlama");
			surecList.add("E-Reçete Düzenleme");
			surecList.add("Sağlık Denetimi");
			surecList.add("Sağlık Raporu Hazırlama");
			surecList.add("Psikolojik Danışmanlık Hizmetleri ");
			surecList.add("Meslek Hastalıkları Süreç Yönetimi ");
			break;

		}
		// TODO null
		return surecList;
	}

	public List<String> getSurecListVerilenDepartmanBasta(String departman) {

		List<String> surecList = new ArrayList<>();
		surecList.addAll(getSurecListByDepartman(departman));

		List<String> departmanList = getDepartmanList();

		for (String departmanAdi : departmanList) {
			if (!departmanAdi.equals(departman)) {
				surecList.addAll(getSurecListByDepartman(departmanAdi));
			}
		}

		return surecList;
	}

	public List<String> getSurecList() { // TODO
		List<String> surecList = new ArrayList<>();
		surecList.add("Personel Adayı Değerlendirme");
		surecList.add("Personel Özlük Dosyası");
		surecList.add("İş Sözleşmesi");
		surecList.add("Kayıt Süreci ");
		surecList.add("SGK e-Bildirge");
		surecList.add("Maaş Ödemeleri");
		surecList.add("Taahhütname");
		surecList.add("AGİ Formu");
		surecList.add("Personel Devam Kontrol Süreci");
		surecList.add("Bordrolama");
		surecList.add("Puantaj");
		surecList.add("Banka Ödeme Formatlarının Oluşturulması");
		surecList.add("BES Ödemeleri");
		surecList.add("İş Kazaları Ve Meslek Hastalıkları Bildirimi");
		surecList.add("Performans Değerlendirmesi");
		surecList.add("Disiplin İşlemleri");
		surecList.add("Sağlık Süreçlerinin Takibi");
		surecList.add("İcra / Nafaka Ve Vergi Kesintisi Süreçlerinin Yönetimi");
		surecList.add("Raporlama");
		surecList.add("Cari Kayıt");
		surecList.add("Vekaletname, Devir İşlemleri");
		surecList.add("Kurumlar Vergisi Beyannamesi");
		surecList.add("Faturalandırma");
		surecList.add("Vergi Borçsuzluk Belgesi");
		surecList.add("Özel Sağlık Sigortası");
		surecList.add("Çek-Senet Girişi");
		surecList.add("Cari Hesap ve Tahsilat Takibi");
		surecList.add("Faktöring İşlemleri");
		surecList.add("Banka Operasyon İşlemleri");
		surecList.add("POS İşlemleri");
		surecList.add("Sigorta Hasar Tazmin");
		surecList.add("Kıdem Tazminatı Tahakkuku");
		surecList.add("Hizmet Alımı");
		surecList.add("Bina Giriş Çıkışlarının Takibi");
		surecList.add("Araç Takibi");
		surecList.add("Güvenlik Personeli Bildirimi");
		surecList.add("Gelen - Giden Evrak Defteri");
		surecList.add("Acil Durum İrtibat Listesi");
		surecList.add("Misafirhane Hizmetleri");
		surecList.add("Kayıp Buluntu, Müsadere, Muvafakatname");
		surecList.add("Kaza Olay Yönetimi");
		surecList.add("Kullanıcı Hesap Yönetimi");
		surecList.add("Zimmet Formları");
		surecList.add("Erişim Logları");
		surecList.add("Satın Alma");
		surecList.add("İhale Dosyası Oluşturma");
		surecList.add("Sözleşme Yönetimi");
		surecList.add("Piyasa Araştırması");
		surecList.add("Eğitim Süreçlerinin Yönetimi");
		surecList.add("İSG Kurul Toplantı Tutanakları");
		surecList.add("İş Kazaları Ve Rapor Süreç Yönetimi");
		surecList.add("Risk Dğerlendirmesi");
		surecList.add("Acil Durum İrtibat Listesi");
		surecList.add("İşe Giriş Ve Periyodik Sağlık Değerlendirmesi");
		surecList.add("Kişisel Sağlık Dosyası Hazırlama");
		surecList.add("E-Reçete Düzenleme");
		surecList.add("Sağlık Denetimi");
		surecList.add("Sağlık Raporu Hazırlama");
		surecList.add("Psikolojik Danışmanlık Hizmetleri ");
		surecList.add("Meslek Hastalıkları Süreç Yönetimi ");

		return surecList;
	}

	public List<HukukiNeden> getHukukiNedenList() {
		return hukukiNedenRepository.findAll();
	}

	public List<ImhaMetodu> getImhaMetodu() {
		
		return imhaMetoduRepository.findAll();
	}
	
	public Islem getIslemByKulaniciAndFirma(String kullanici,String firma) {
		return islemRepository.findByKullaniciAndFirma(kullanici, firma);
	}
}
