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
 * Rezervacija generated by hbm2java
 */
@Entity
@Table(name = "rezervacija", catalog = "tim12")
public class Rezervacija implements java.io.Serializable {

	private Integer rezervacijaId;
	private KorisnickiRacun korisnickiRacun;
	private Klijent klijent;
	private Date datumRezervacije;
	private Boolean ukljucenPrevoz;
	private Set<Racun> racuns = new HashSet<Racun>(0);
	private Set<RezervisaniTerminSoba> rezervisaniTerminSobas = new HashSet<RezervisaniTerminSoba>(0);
	private Set<RezervisaniTerminSoba> rezervisaniTerminSobas_1 = new HashSet<RezervisaniTerminSoba>(0);
	private Set<Racun> racuns_1 = new HashSet<Racun>(0);

	public Rezervacija() {
	}

	public Rezervacija(KorisnickiRacun korisnickiRacun, Klijent klijent, Date datumRezervacije, Boolean ukljucenPrevoz,
			Set<Racun> racuns, Set<RezervisaniTerminSoba> rezervisaniTerminSobas,
			Set<RezervisaniTerminSoba> rezervisaniTerminSobas_1, Set<Racun> racuns_1) {
		this.korisnickiRacun = korisnickiRacun;
		this.klijent = klijent;
		this.datumRezervacije = datumRezervacije;
		this.ukljucenPrevoz = ukljucenPrevoz;
		this.racuns = racuns;
		this.rezervisaniTerminSobas = rezervisaniTerminSobas;
		this.rezervisaniTerminSobas_1 = rezervisaniTerminSobas_1;
		this.racuns_1 = racuns_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "RezervacijaID", unique = true, nullable = false)
	public Integer getRezervacijaId() {
		return this.rezervacijaId;
	}

	public void setRezervacijaId(Integer rezervacijaId) {
		this.rezervacijaId = rezervacijaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AgentKreiraoID")
	public KorisnickiRacun getKorisnickiRacun() {
		return this.korisnickiRacun;
	}

	public void setKorisnickiRacun(KorisnickiRacun korisnickiRacun) {
		this.korisnickiRacun = korisnickiRacun;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "KlijentID")
	public Klijent getKlijent() {
		return this.klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DatumRezervacije", length = 19)
	public Date getDatumRezervacije() {
		return this.datumRezervacije;
	}

	public void setDatumRezervacije(Date datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}

	@Column(name = "UkljucenPrevoz")
	public Boolean getUkljucenPrevoz() {
		return this.ukljucenPrevoz;
	}

	public void setUkljucenPrevoz(Boolean ukljucenPrevoz) {
		this.ukljucenPrevoz = ukljucenPrevoz;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rezervacija")
	public Set<Racun> getRacuns() {
		return this.racuns;
	}

	public void setRacuns(Set<Racun> racuns) {
		this.racuns = racuns;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rezervacija")
	public Set<RezervisaniTerminSoba> getRezervisaniTerminSobas() {
		return this.rezervisaniTerminSobas;
	}

	public void setRezervisaniTerminSobas(Set<RezervisaniTerminSoba> rezervisaniTerminSobas) {
		this.rezervisaniTerminSobas = rezervisaniTerminSobas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rezervacija")
	public Set<RezervisaniTerminSoba> getRezervisaniTerminSobas_1() {
		return this.rezervisaniTerminSobas_1;
	}

	public void setRezervisaniTerminSobas_1(Set<RezervisaniTerminSoba> rezervisaniTerminSobas_1) {
		this.rezervisaniTerminSobas_1 = rezervisaniTerminSobas_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rezervacija")
	public Set<Racun> getRacuns_1() {
		return this.racuns_1;
	}

	public void setRacuns_1(Set<Racun> racuns_1) {
		this.racuns_1 = racuns_1;
	}

}
