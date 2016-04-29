package ba.unsa.etf.si.app.iTravel.DBModels;
// Generated 28-Apr-2016 23:45:29 by Hibernate Tools 4.0.0.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
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
	private String adresa;
	private String drzava;
	private String grad;
	private String destinacija;
	private String brojTelefona;
	private Date pocetakNiska;
	private Date krajNiska;
	private Date krajVisoka;
	private Date pocetakVisoka;
	private Set<Soba> sobas = new HashSet<Soba>(0);

	public Hotel() {
	}

	public Hotel(String adresa, String drzava, String grad, String destinacija, String brojTelefona, Date pocetakNiska,
			Date krajNiska, Date krajVisoka, Date pocetakVisoka, Set<Soba> sobas) {
		this.adresa = adresa;
		this.drzava = drzava;
		this.grad = grad;
		this.destinacija = destinacija;
		this.brojTelefona = brojTelefona;
		this.pocetakNiska = pocetakNiska;
		this.krajNiska = krajNiska;
		this.krajVisoka = krajVisoka;
		this.pocetakVisoka = pocetakVisoka;
		this.sobas = sobas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Hotel_ID", unique = true, nullable = false)
	public Integer getHotelId() {
		return this.hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
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

	@Column(name = "Destinacija", length = 45)
	public String getDestinacija() {
		return this.destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	@Column(name = "Broj_telefona", length = 45)
	public String getBrojTelefona() {
		return this.brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Pocetak_niska", length = 10)
	public Date getPocetakNiska() {
		return this.pocetakNiska;
	}

	public void setPocetakNiska(Date pocetakNiska) {
		this.pocetakNiska = pocetakNiska;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Kraj_niska", length = 10)
	public Date getKrajNiska() {
		return this.krajNiska;
	}

	public void setKrajNiska(Date krajNiska) {
		this.krajNiska = krajNiska;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Kraj_visoka", length = 10)
	public Date getKrajVisoka() {
		return this.krajVisoka;
	}

	public void setKrajVisoka(Date krajVisoka) {
		this.krajVisoka = krajVisoka;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Pocetak_visoka", length = 10)
	public Date getPocetakVisoka() {
		return this.pocetakVisoka;
	}

	public void setPocetakVisoka(Date pocetakVisoka) {
		this.pocetakVisoka = pocetakVisoka;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
	public Set<Soba> getSobas() {
		return this.sobas;
	}

	public void setSobas(Set<Soba> sobas) {
		this.sobas = sobas;
	}

}
