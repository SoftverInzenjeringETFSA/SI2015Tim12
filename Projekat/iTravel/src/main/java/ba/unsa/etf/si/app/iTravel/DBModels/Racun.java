package ba.unsa.etf.si.app.iTravel.DBModels;
// Generated 28-Apr-2016 23:45:29 by Hibernate Tools 4.0.0.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Racun generated by hbm2java
 */
@Entity
@Table(name = "racun", catalog = "tim12")
public class Racun implements java.io.Serializable {

	private Integer racunId;
	private Rezervacija rezervacija;
	private Date datumUplate;
	private Date trenutniDatum;
	private Integer popust;
	private Integer cijena;

	public Racun() {
	}

	public Racun(Rezervacija rezervacija, Date datumUplate, Date trenutniDatum, Integer popust, Integer cijena) {
		this.rezervacija = rezervacija;
		this.datumUplate = datumUplate;
		this.trenutniDatum = trenutniDatum;
		this.popust = popust;
		this.cijena = cijena;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Racun_ID", unique = true, nullable = false)
	public Integer getRacunId() {
		return this.racunId;
	}

	public void setRacunId(Integer racunId) {
		this.racunId = racunId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Rezervacija_ID")
	public Rezervacija getRezervacija() {
		return this.rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Datum_uplate", length = 10)
	public Date getDatumUplate() {
		return this.datumUplate;
	}

	public void setDatumUplate(Date datumUplate) {
		this.datumUplate = datumUplate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Trenutni_datum", length = 10)
	public Date getTrenutniDatum() {
		return this.trenutniDatum;
	}

	public void setTrenutniDatum(Date trenutniDatum) {
		this.trenutniDatum = trenutniDatum;
	}

	@Column(name = "Popust")
	public Integer getPopust() {
		return this.popust;
	}

	public void setPopust(Integer popust) {
		this.popust = popust;
	}

	@Column(name = "Cijena")
	public Integer getCijena() {
		return this.cijena;
	}

	public void setCijena(Integer cijena) {
		this.cijena = cijena;
	}

}
