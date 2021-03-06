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
 * Klijent generated by hbm2java
 */
@Entity
@Table(name = "klijent", catalog = "tim12")
public class Klijent implements java.io.Serializable {

	private Integer klijentId;
	private Osoba osoba;
	private Set<Rezervacija> rezervacijas = new HashSet<Rezervacija>(0);
	private Set<Rezervacija> rezervacijas_1 = new HashSet<Rezervacija>(0);

	public Klijent() {
	}

	public Klijent(Osoba osoba) {
		this.osoba = osoba;
	}

	public Klijent(Osoba osoba, Set<Rezervacija> rezervacijas, Set<Rezervacija> rezervacijas_1) {
		this.osoba = osoba;
		this.rezervacijas = rezervacijas;
		this.rezervacijas_1 = rezervacijas_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "KlijentID", unique = true, nullable = false)
	public Integer getKlijentId() {
		return this.klijentId;
	}

	public void setKlijentId(Integer klijentId) {
		this.klijentId = klijentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OsobaID", nullable = false)
	public Osoba getOsoba() {
		return this.osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "klijent")
	public Set<Rezervacija> getRezervacijas() {
		return this.rezervacijas;
	}

	public void setRezervacijas(Set<Rezervacija> rezervacijas) {
		this.rezervacijas = rezervacijas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "klijent")
	public Set<Rezervacija> getRezervacijas_1() {
		return this.rezervacijas_1;
	}

	public void setRezervacijas_1(Set<Rezervacija> rezervacijas_1) {
		this.rezervacijas_1 = rezervacijas_1;
	}

}
