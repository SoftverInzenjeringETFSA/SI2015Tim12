package ba.unsa.etf.si.app.iTravel.DBModels;
// Generated 13-May-2016 21:00:31 by Hibernate Tools 4.0.0.Final

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

/**
 * KorisnickiRacun generated by hbm2java
 */
@Entity
@Table(name = "korisnicki_racun", catalog = "tim12")
public class KorisnickiRacun implements java.io.Serializable {

	private Integer korisnickiRacunId;
	private Osoba osoba;
	private String username;
	private String password;
	private Set<Korisnickiracunxrola> korisnickiracunxrolas = new HashSet<Korisnickiracunxrola>(0);
	private Set<Rezervacija> rezervacijas = new HashSet<Rezervacija>(0);
	private Set<Korisnickiracunxrola> korisnickiracunxrolas_1 = new HashSet<Korisnickiracunxrola>(0);
	private Set<Rezervacija> rezervacijas_1 = new HashSet<Rezervacija>(0);

	public KorisnickiRacun() {
	}
	


	public KorisnickiRacun(Osoba osoba, String username, String password,
			Set<Korisnickiracunxrola> korisnickiracunxrolas, Set<Rezervacija> rezervacijas,
			Set<Korisnickiracunxrola> korisnickiracunxrolas_1, Set<Rezervacija> rezervacijas_1) {
		this.osoba = osoba;
		this.username = username;
		this.password = password;
		this.korisnickiracunxrolas = korisnickiracunxrolas;
		this.rezervacijas = rezervacijas;
		this.korisnickiracunxrolas_1 = korisnickiracunxrolas_1;
		this.rezervacijas_1 = rezervacijas_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "KorisnickiRacunID", unique = true, nullable = false)
	public Integer getKorisnickiRacunId() {
		return this.korisnickiRacunId;
	}

	public void setKorisnickiRacunId(Integer korisnickiRacunId) {
		this.korisnickiRacunId = korisnickiRacunId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OsobaID")
	public Osoba getOsoba() {
		return this.osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	@Column(name = "Username", length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Password", length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnickiRacun")
	public Set<Korisnickiracunxrola> getKorisnickiracunxrolas() {
		return this.korisnickiracunxrolas;
	}

	public void setKorisnickiracunxrolas(Set<Korisnickiracunxrola> korisnickiracunxrolas) {
		this.korisnickiracunxrolas = korisnickiracunxrolas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnickiRacun")
	public Set<Rezervacija> getRezervacijas() {
		return this.rezervacijas;
	}

	public void setRezervacijas(Set<Rezervacija> rezervacijas) {
		this.rezervacijas = rezervacijas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnickiRacun")
	public Set<Korisnickiracunxrola> getKorisnickiracunxrolas_1() {
		return this.korisnickiracunxrolas_1;
	}

	public void setKorisnickiracunxrolas_1(Set<Korisnickiracunxrola> korisnickiracunxrolas_1) {
		this.korisnickiracunxrolas_1 = korisnickiracunxrolas_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "korisnickiRacun")
	public Set<Rezervacija> getRezervacijas_1() {
		return this.rezervacijas_1;
	}

	public void setRezervacijas_1(Set<Rezervacija> rezervacijas_1) {
		this.rezervacijas_1 = rezervacijas_1;
	}

}
