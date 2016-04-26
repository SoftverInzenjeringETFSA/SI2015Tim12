package ba.unsa.etf.si.app.iTravel.DBModels;
// Generated 27-Apr-2016 01:13:41 by Hibernate Tools 4.0.0.Final

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
 * Termin generated by hbm2java
 */
@Entity
@Table(name = "termin", catalog = "tim12")
public class Termin implements java.io.Serializable {

	private Integer terminId;
	private Date pocetak;
	private Date kraj;
	private Set<SlobodniTermini> slobodniTerminis = new HashSet<SlobodniTermini>(0);

	public Termin() {
	}

	public Termin(Date pocetak, Date kraj) {
		this.pocetak = pocetak;
		this.kraj = kraj;
	}

	public Termin(Date pocetak, Date kraj, Set<SlobodniTermini> slobodniTerminis) {
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.slobodniTerminis = slobodniTerminis;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Termin_ID", unique = true, nullable = false)
	public Integer getTerminId() {
		return this.terminId;
	}

	public void setTerminId(Integer terminId) {
		this.terminId = terminId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Pocetak", nullable = false, length = 10)
	public Date getPocetak() {
		return this.pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Kraj", nullable = false, length = 10)
	public Date getKraj() {
		return this.kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "termin")
	public Set<SlobodniTermini> getSlobodniTerminis() {
		return this.slobodniTerminis;
	}

	public void setSlobodniTerminis(Set<SlobodniTermini> slobodniTerminis) {
		this.slobodniTerminis = slobodniTerminis;
	}

}
