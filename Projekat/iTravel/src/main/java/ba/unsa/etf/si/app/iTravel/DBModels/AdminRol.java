package ba.unsa.etf.si.app.iTravel.DBModels;
// Generated 28-Apr-2016 23:45:29 by Hibernate Tools 4.0.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AdminRol generated by hbm2java
 */
@Entity
@Table(name = "admin_rol", catalog = "tim12")
public class AdminRol implements java.io.Serializable {

	private Integer adminId;
	private KorisnickiRacun korisnickiRacun;

	public AdminRol() {
	}

	public AdminRol(KorisnickiRacun korisnickiRacun) {
		this.korisnickiRacun = korisnickiRacun;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Admin_ID", unique = true, nullable = false)
	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "KR_ID")
	public KorisnickiRacun getKorisnickiRacun() {
		return this.korisnickiRacun;
	}

	public void setKorisnickiRacun(KorisnickiRacun korisnickiRacun) {
		this.korisnickiRacun = korisnickiRacun;
	}

}
