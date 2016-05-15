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
 * Soba generated by hbm2java
 */
@Entity
@Table(name = "soba", catalog = "tim12")
public class Soba implements java.io.Serializable {

	private Integer sobaId;
	private Hotel hotel;
	private Integer brojKreveta;
	private String opis;
	private int cijenaVisoka;
	private int cijenaNiska;
	private Set<RezervisaniTerminSoba> rezervisaniTerminSobas = new HashSet<RezervisaniTerminSoba>(0);
	private Set<RezervisaniTerminSoba> rezervisaniTerminSobas_1 = new HashSet<RezervisaniTerminSoba>(0);

	public Soba() {
	}

	public Soba(Hotel hotel, int cijenaVisoka, int cijenaNiska) {
		this.hotel = hotel;
		this.cijenaVisoka = cijenaVisoka;
		this.cijenaNiska = cijenaNiska;
	}

	
	public Soba(Hotel hotel, Integer brojKreveta, String opis, int cijenaVisoka, int cijenaNiska,
			Set<RezervisaniTerminSoba> rezervisaniTerminSobas, Set<RezervisaniTerminSoba> rezervisaniTerminSobas_1) {
		this.hotel = hotel;
		this.brojKreveta = brojKreveta;
		this.opis = opis;
		this.cijenaVisoka = cijenaVisoka;
		this.cijenaNiska = cijenaNiska;
		this.rezervisaniTerminSobas = rezervisaniTerminSobas;
		this.rezervisaniTerminSobas_1 = rezervisaniTerminSobas_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "SobaID", unique = true, nullable = false)
	public Integer getSobaId() {
		return this.sobaId;
	}

	public void setSobaId(Integer sobaId) {
		this.sobaId = sobaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HotelID", nullable = false)
	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@Column(name = "BrojKreveta")
	public Integer getBrojKreveta() {
		return this.brojKreveta;
	}

	public void setBrojKreveta(Integer brojKreveta) {
		this.brojKreveta = brojKreveta;
	}

	@Column(name = "Opis", length = 200)
	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Column(name = "CijenaVisoka", nullable = false)
	public int getCijenaVisoka() {
		return this.cijenaVisoka;
	}

	public void setCijenaVisoka(int cijenaVisoka) {
		this.cijenaVisoka = cijenaVisoka;
	}

	@Column(name = "CijenaNiska", nullable = false)
	public int getCijenaNiska() {
		return this.cijenaNiska;
	}

	public void setCijenaNiska(int cijenaNiska) {
		this.cijenaNiska = cijenaNiska;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "soba")
	public Set<RezervisaniTerminSoba> getRezervisaniTerminSobas() {
		return this.rezervisaniTerminSobas;
	}

	public void setRezervisaniTerminSobas(Set<RezervisaniTerminSoba> rezervisaniTerminSobas) {
		this.rezervisaniTerminSobas = rezervisaniTerminSobas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "soba")
	public Set<RezervisaniTerminSoba> getRezervisaniTerminSobas_1() {
		return this.rezervisaniTerminSobas_1;
	}

	public void setRezervisaniTerminSobas_1(Set<RezervisaniTerminSoba> rezervisaniTerminSobas_1) {
		this.rezervisaniTerminSobas_1 = rezervisaniTerminSobas_1;
	}

}
