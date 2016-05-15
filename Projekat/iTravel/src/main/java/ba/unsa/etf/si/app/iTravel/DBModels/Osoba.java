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
	private String brojTelefona;
	private String jmbg;
	private String brojPasosa;
	private String brojLicneKarte;
	private Set<KorisnickiRacun> korisnickiRacuns = new HashSet<KorisnickiRacun>(0);
	private Set<Klijent> klijents = new HashSet<Klijent>(0);
	private Set<Klijent> klijents_1 = new HashSet<Klijent>(0);
	private Set<KorisnickiRacun> korisnickiRacuns_1 = new HashSet<KorisnickiRacun>(0);

	public Osoba() {
	}
	

	public Osoba(String ime, String prezime, Date datumRodjenja, String adresa, String email, String brojTelefona,
			String jmbg, String brojPasosa, String brojLicneKarte, Set<KorisnickiRacun> korisnickiRacuns,
			Set<Klijent> klijents, Set<Klijent> klijents_1, Set<KorisnickiRacun> korisnickiRacuns_1) {
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.adresa = adresa;
		this.email = email;
		this.brojTelefona = brojTelefona;
		this.jmbg = jmbg;
		this.brojPasosa = brojPasosa;
		this.brojLicneKarte = brojLicneKarte;
		this.korisnickiRacuns = korisnickiRacuns;
		this.klijents = klijents;
		this.klijents_1 = klijents_1;
		this.korisnickiRacuns_1 = korisnickiRacuns_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "OsobaID", unique = true, nullable = false)
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
	@Column(name = "DatumRodjenja", length = 10)
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

	@Column(name = "BrojTelefona", length = 45)
	public String getBrojTelefona() {
		return this.brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	@Column(name = "JMBG", length = 13)
	public String getJmbg() {
		return this.jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	@Column(name = "BrojPasosa", length = 45)
	public String getBrojPasosa() {
		return this.brojPasosa;
	}

	public void setBrojPasosa(String brojPasosa) {
		this.brojPasosa = brojPasosa;
	}

	@Column(name = "BrojLicneKarte", length = 45)
	public String getBrojLicneKarte() {
		return this.brojLicneKarte;
	}

	public void setBrojLicneKarte(String brojLicneKarte) {
		this.brojLicneKarte = brojLicneKarte;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osoba")
	public Set<KorisnickiRacun> getKorisnickiRacuns() {
		return this.korisnickiRacuns;
	}

	public void setKorisnickiRacuns(Set<KorisnickiRacun> korisnickiRacuns) {
		this.korisnickiRacuns = korisnickiRacuns;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osoba")
	public Set<Klijent> getKlijents() {
		return this.klijents;
	}

	public void setKlijents(Set<Klijent> klijents) {
		this.klijents = klijents;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osoba")
	public Set<Klijent> getKlijents_1() {
		return this.klijents_1;
	}

	public void setKlijents_1(Set<Klijent> klijents_1) {
		this.klijents_1 = klijents_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "osoba")
	public Set<KorisnickiRacun> getKorisnickiRacuns_1() {
		return this.korisnickiRacuns_1;
	}

	public void setKorisnickiRacuns_1(Set<KorisnickiRacun> korisnickiRacuns_1) {
		this.korisnickiRacuns_1 = korisnickiRacuns_1;
	}

}
