package ba.unsa.etf.si.app.iTravel.DBModels;
// Generated 13-May-2016 21:00:31 by Hibernate Tools 4.0.0.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Rola generated by hbm2java
 */
@Entity
@Table(name = "rola", catalog = "tim12")
public class Rola implements java.io.Serializable {

	private int rolaId;
	private String naziv;
	private Set<Korisnickiracunxrola> korisnickiracunxrolas = new HashSet<Korisnickiracunxrola>(0);
	private Set<Korisnickiracunxrola> korisnickiracunxrolas_1 = new HashSet<Korisnickiracunxrola>(0);

	public Rola() {
	}

	public Rola(int rolaId) {
		this.rolaId = rolaId;
	}

	public Rola(int rolaId, String naziv, Set<Korisnickiracunxrola> korisnickiracunxrolas,
			Set<Korisnickiracunxrola> korisnickiracunxrolas_1) {
		this.rolaId = rolaId;
		this.naziv = naziv;
		this.korisnickiracunxrolas = korisnickiracunxrolas;
		this.korisnickiracunxrolas_1 = korisnickiracunxrolas_1;
	}

	@Id

	@Column(name = "RolaID", unique = true, nullable = false)
	public int getRolaId() {
		return this.rolaId;
	}

	public void setRolaId(int rolaId) {
		this.rolaId = rolaId;
	}

	@Column(name = "Naziv", length = 40)
	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rola")
	public Set<Korisnickiracunxrola> getKorisnickiracunxrolas() {
		return this.korisnickiracunxrolas;
	}

	public void setKorisnickiracunxrolas(Set<Korisnickiracunxrola> korisnickiracunxrolas) {
		this.korisnickiracunxrolas = korisnickiracunxrolas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rola")
	public Set<Korisnickiracunxrola> getKorisnickiracunxrolas_1() {
		return this.korisnickiracunxrolas_1;
	}

	public void setKorisnickiracunxrolas_1(Set<Korisnickiracunxrola> korisnickiracunxrolas_1) {
		this.korisnickiracunxrolas_1 = korisnickiracunxrolas_1;
	}

}
