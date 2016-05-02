package ba.unsa.etf.si.app.iTravel.DAL;

import ba.unsa.etf.si.app.iTravel.DAL.Repositories.HotelRepository;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.KlijentRepository;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.KorisnickiRacunRepository;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.KorisnickiRacunXRolaRepository;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.OsobaRepository;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.RacunRepository;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.RezervacijaRepository;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.RolaRepository;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.SlobodniTerminiRepository;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.SobaRepository;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.TerminRepository;

public class DBContext {
	
	private static DBContext instance;
	
	private HotelRepository hoteliRepo;
	private KorisnickiRacunRepository korisnickiRacunRepository;
	private KlijentRepository klijentRepository;
	private OsobaRepository osobaRepository;
	private SobaRepository sobaRepository;
	private TerminRepository terminRepository;
	private SlobodniTerminiRepository slobodniTerminiRepository;
	private RezervacijaRepository rezervacijaRepository;
	private RacunRepository racunRepository;
	private RolaRepository rolaRepository;
	private KorisnickiRacunXRolaRepository korisnickiRacunXRolaRepository;
	
	// Vidjeti da li treba izbrisati setere
	public DBContext()
	{
		hoteliRepo = new HotelRepository();
		korisnickiRacunRepository = new KorisnickiRacunRepository();
		klijentRepository = new KlijentRepository();
		osobaRepository = new OsobaRepository();
		sobaRepository = new SobaRepository();
		terminRepository = new TerminRepository();
		slobodniTerminiRepository = new SlobodniTerminiRepository();
		rezervacijaRepository = new RezervacijaRepository();
		racunRepository = new RacunRepository();
		rolaRepository = new RolaRepository();
		korisnickiRacunXRolaRepository = new KorisnickiRacunXRolaRepository();
	}

	public static DBContext getInstance() {
		if(instance == null) {
			instance = new DBContext();
		}
		return instance;
	}
	
	public HotelRepository getHoteliRepo() {
		return hoteliRepo;
	}

	public void setHoteliRepo(HotelRepository hoteliRepo) {
		this.hoteliRepo = hoteliRepo;
	}

	public KorisnickiRacunRepository getKorisnickiRacunRepository() {
		return korisnickiRacunRepository;
	}

	public void setKorisnickiRacunRepository(KorisnickiRacunRepository korisnickiRacunRepository) {
		this.korisnickiRacunRepository = korisnickiRacunRepository;
	}

	public KlijentRepository getKlijentRepository() {
		return klijentRepository;
	}

	public void setKlijentRepository(KlijentRepository klijentRepository) {
		this.klijentRepository = klijentRepository;
	}

	public OsobaRepository getOsobaRepository() {
		return osobaRepository;
	}

	public void setOsobaRepository(OsobaRepository osobaRepository) {
		this.osobaRepository = osobaRepository;
	}

	public SobaRepository getSobaRepository() {
		return sobaRepository;
	}

	public void setSobaRepository(SobaRepository sobaRepository) {
		this.sobaRepository = sobaRepository;
	}

	public TerminRepository getTerminRepository() {
		return terminRepository;
	}

	public void setTerminRepository(TerminRepository terminRepository) {
		this.terminRepository = terminRepository;
	}

	public SlobodniTerminiRepository getSlobodniTerminiRepository() {
		return slobodniTerminiRepository;
	}

	public void setSlobodniTerminiRepository(SlobodniTerminiRepository slobodniTerminiRepository) {
		this.slobodniTerminiRepository = slobodniTerminiRepository;
	}

	public RezervacijaRepository getRezervacijaRepository() {
		return rezervacijaRepository;
	}

	public void setRezervacijaRepository(RezervacijaRepository rezervacijaRepository) {
		this.rezervacijaRepository = rezervacijaRepository;
	}

	public RacunRepository getRacunRepository() {
		return racunRepository;
	}

	public void setRacunRepository(RacunRepository racunRepository) {
		this.racunRepository = racunRepository;
	}

	public RolaRepository getRolaRepository() {
		return rolaRepository;
	}

	public void setRolaRepository(RolaRepository rolaRepository) {
		this.rolaRepository = rolaRepository;
	}

	public KorisnickiRacunXRolaRepository getKorisnickiRacunXRolaRepository() {
		return korisnickiRacunXRolaRepository;
	}

	public void setKorisnickiRacunXRolaRepository(KorisnickiRacunXRolaRepository korisnickiRacunXRolaRepository) {
		this.korisnickiRacunXRolaRepository = korisnickiRacunXRolaRepository;
	}
	
}

