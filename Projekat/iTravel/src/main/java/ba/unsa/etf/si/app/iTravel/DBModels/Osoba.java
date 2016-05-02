package ba.unsa.etf.si.app.iTravel.DBModels;
// Generated 02-May-2016 22:04:00 by Hibernate Tools 4.0.0.Final

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
 * Osoba generated by hbm2java
 */
@Entity
@Table(name = "osoba", catalog = "tim12")
public class Osoba implements java.io.Serializable {

	private Integer osobaId;
	private String ime;
	private String prezime;
	private Date datumRodjenja;
	private String adresa;
	private String email;
	private String brojTel;
	private Integer jmbg;
	private String brPasosa;
	private Set<Klijent> klijents = new HashSet<Klijent>(0);
	private Set<KorisnickiRacun> korisnickiRacuns = new HashSet<KorisnickiRacun>(0);

	public Osoba() {
	}

	public Osoba(String ime, String prezime, Date datumRodjenja, String adresa, String email, String brojTel,
			Integer jmbg, String brPasosa, Set<Klijent> klijents, Set<KorisnickiRacun> korisnickiRacuns) {
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.adresa = adresa;
		this.email = email;
		this.brojTel = brojTel;
		this.jmbg = jmbg;
		this.brPasosa = brPasosa;
		this.klijents = klijents;
		this.korisnickiRacuns = korisnickiRacuns;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Osoba_ID", unique = true, nullable = false)
	public Integer getOsobaId() {
		return this.osobaId;
	}

	public void setOsobaId(Integer osobaId) {
		this.osobaId = osobaId;
	}

	@Column(name = "Ime", length = 45)
	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	@Column(name = "Prezime", length = 45)
	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Datum_rodjenja", length = 10)
	public Date getDatumRodjenja() {
		return this.datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	@Column(name = "Adresa", length = 45)
	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	@Column(name = "Email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Broj_tel", length = 45)
	public String getBrojTel() {
		return this.brojTel;
	}

	public void setBrojTel(String brojTel) {
		this.brojTel = brojTel;
	}

	@Column(name = "JMBG")
	public Integer getJmbg() {
		return this.jmbg;
	}

	public void setJmbg(Integer jmbg) {
		this.jmbg = jmbg;
	}

	@Column(name = "Br_pasosa", length = 45)
	public String getBrPasosa() {
		return this.brPasosa;
	}

	public void setBrPasosa(String brPasosa) {
		this.brPasosa = brPasosa;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osoba")
	public Set<Klijent> getKlijents() {
		return this.klijents;
	}

	public void setKlijents(Set<Klijent> klijents) {
		this.klijents = klijents;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osoba")
	public Set<KorisnickiRacun> getKorisnickiRacuns() {
		return this.korisnickiRacuns;
	}

	public void setKorisnickiRacuns(Set<KorisnickiRacun> korisnickiRacuns) {
		this.korisnickiRacuns = korisnickiRacuns;
	}

}