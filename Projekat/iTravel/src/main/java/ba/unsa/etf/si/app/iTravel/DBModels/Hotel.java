package ba.unsa.etf.si.app.iTravel.DBModels;
// Generated 13-May-2016 21:00:31 by Hibernate Tools 4.0.0.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Hotel generated by hbm2java
 */
@Entity
@Table(name = "hotel", catalog = "tim12")
public class Hotel implements java.io.Serializable {

	private Integer hotelId;
	private Destinacija destinacija;
	private String adresa;
	private String drzava;
	private String grad;
	private String brojTelefona;
	private Date pocetakNiska;
	private Date krajNiska;
	private Date krajVisoka;
	private Date pocetakVisoka;
	private String naziv;
	private String nazivLanca;
	private Integer brojZvjezdica;
	private Set<Soba> sobas = new HashSet<Soba>(0);
	private Set<Soba> sobas_1 = new HashSet<Soba>(0);

	public Hotel() {
	}
	
	

	public Hotel(Destinacija destinacija, String adresa, String drzava, String grad, String brojTelefona,
			Date pocetakNiska, Date krajNiska, Date krajVisoka, Date pocetakVisoka, String naziv, String nazivLanca,
			Integer brojZvjezdica, Set<Soba> sobas, Set<Soba> sobas_1) {
		this.destinacija = destinacija;
		this.adresa = adresa;
		this.drzava = drzava;
		this.grad = grad;
		this.brojTelefona = brojTelefona;
		this.pocetakNiska = pocetakNiska;
		this.krajNiska = krajNiska;
		this.krajVisoka = krajVisoka;
		this.pocetakVisoka = pocetakVisoka;
		this.naziv = naziv;
		this.nazivLanca = nazivLanca;
		this.brojZvjezdica = brojZvjezdica;
		this.sobas = sobas;
		this.sobas_1 = sobas_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "HotelID", unique = true, nullable = false)
	public Integer getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DestinacijaID")
	public Destinacija getDestinacija() {
		return this.destinacija;
	}

	public void setDestinacija(Destinacija destinacija) {
		this.destinacija = destinacija;
	}

	@Column(name = "Adresa", length = 70)
	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	@Column(name = "Drzava", length = 45)
	public String getDrzava() {
		return this.drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	@Column(name = "Grad", length = 45)
	public String getGrad() {
		return this.grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	@Column(name = "BrojTelefona", length = 45)
	public String getBrojTelefona() {
		return this.brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PocetakNiska", length = 10)
	public Date getPocetakNiska() {
		return this.pocetakNiska;
	}

	public void setPocetakNiska(Date pocetakNiska) {
		this.pocetakNiska = pocetakNiska;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "KrajNiska", length = 10)
	public Date getKrajNiska() {
		return this.krajNiska;
	}

	public void setKrajNiska(Date krajNiska) {
		this.krajNiska = krajNiska;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "KrajVisoka", length = 10)
	public Date getKrajVisoka() {
		return this.krajVisoka;
	}

	public void setKrajVisoka(Date krajVisoka) {
		this.krajVisoka = krajVisoka;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PocetakVisoka", length = 10)
	public Date getPocetakVisoka() {
		return this.pocetakVisoka;
	}

	public void setPocetakVisoka(Date pocetakVisoka) {
		this.pocetakVisoka = pocetakVisoka;
	}

	@Column(name = "Naziv", length = 50)
	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Column(name = "NazivLanca", length = 50)
	public String getNazivLanca() {
		return this.nazivLanca;
	}

	public void setNazivLanca(String nazivLanca) {
		this.nazivLanca = nazivLanca;
	}

	@Column(name = "BrojZvjezdica")
	public Integer getBrojZvjezdica() {
		return this.brojZvjezdica;
	}

	public void setBrojZvjezdica(Integer brojZvjezdica) {
		this.brojZvjezdica = brojZvjezdica;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
	public Set<Soba> getSobas() {
		return this.sobas;
	}

	public void setSobas(Set<Soba> sobas) {
		this.sobas = sobas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
	public Set<Soba> getSobas_1() {
		return this.sobas_1;
	}

	public void setSobas_1(Set<Soba> sobas_1) {
		this.sobas_1 = sobas_1;
	}

}
