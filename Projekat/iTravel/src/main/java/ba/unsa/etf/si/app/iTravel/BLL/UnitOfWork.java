package ba.unsa.etf.si.app.iTravel.BLL;

import org.apache.log4j.Logger;

public class UnitOfWork {
	
	public final static Logger logger = Logger.getLogger(UnitOfWork.class);
	
	private PrijavaService prijavaService;
	private DohvatiHotel dohvatiHotel;
	private ModifikacijaHotel modifikacijaHotela;
	private UbaciHotel ubaciHotel;
	private ObrisiHotel obrisiHotel;
	private IzvjestajService izvjestajService;
	private HoteliService hoteliService;
	private KorisnickiRacunService korisnickiRacunService;
	private OsobaService osobaService;
	private DestinacijeService destinacijeService;
	private RezervacijeService rezervacijaService;
	private RacunService racunService;
	private RolaService rolaService;
	private PrikazKlijenata prikazKlijenata;
	private PrikazKorisnika prikazKorisnika;
	private OdjavaService odjavaService;
	private PostavkeService postavkeService;
	
	public UnitOfWork() {
		prijavaService = new PrijavaService();
		dohvatiHotel = new DohvatiHotel();
		modifikacijaHotela = new ModifikacijaHotel();
		ubaciHotel = new UbaciHotel();
		obrisiHotel = new ObrisiHotel();
		izvjestajService = new IzvjestajService();
		osobaService = new OsobaService();
		korisnickiRacunService = new KorisnickiRacunService();
		destinacijeService = new DestinacijeService();
		rezervacijaService = new RezervacijeService();
		racunService = new RacunService();
		rolaService = new RolaService();
		prikazKlijenata = new PrikazKlijenata();
		prikazKorisnika = new PrikazKorisnika();
		odjavaService = new OdjavaService();
		postavkeService = new PostavkeService();
		
	}

	public PrijavaService getPrijavaService() {
		if (prijavaService == null)
			prijavaService = new PrijavaService();

		return prijavaService;
	}

	public DohvatiHotel getDohvatiHotel() {
		if (dohvatiHotel == null)
			dohvatiHotel = new DohvatiHotel();
		return dohvatiHotel;
	}

	public ModifikacijaHotel getModifikacijaHotel() {
		if (modifikacijaHotela == null)
			modifikacijaHotela = new ModifikacijaHotel();
		return modifikacijaHotela;
	}

	public ObrisiHotel getObrisiHotel() {
		if (obrisiHotel == null)
			obrisiHotel = new ObrisiHotel();
		return obrisiHotel;
	}

	public UbaciHotel getUbaciHotel() {
		if (ubaciHotel == null)
			ubaciHotel = new UbaciHotel();
		return ubaciHotel;
	}

	public IzvjestajService getIzvjestajService() {
		if(izvjestajService == null)
			izvjestajService = new IzvjestajService();
		
		return izvjestajService;
	}

	public HoteliService getHoteliService()
	{
		if(hoteliService == null)
			hoteliService = new HoteliService();
		
		return hoteliService;
	}
	
	public KorisnickiRacunService getKorisnickiRacunService()
	{
		if(korisnickiRacunService == null)
			korisnickiRacunService = new KorisnickiRacunService();
		
		return korisnickiRacunService;
	}

	public OsobaService getOsobaService()
	{
		if(osobaService == null)
			osobaService = new OsobaService();
		
		return osobaService;
	}

	public DestinacijeService getDestinacijeService(){
		if(destinacijeService==null)
			destinacijeService=new DestinacijeService();
		
		return destinacijeService;
	}
	public RezervacijeService getRezervacijaService(){
		if(rezervacijaService==null)
			rezervacijaService=new RezervacijeService();
		return rezervacijaService;
	}
	
	public RacunService getRacunService(){
		if(racunService==null){
			racunService=new RacunService();
		}
		return racunService;
	}

	public RolaService getRolaService() 
	{
		if(rolaService == null)
			rolaService = new RolaService();
		
		return rolaService;
	}
	
	public PrikazKlijenata getPrikazKlijenata()
	{
		if(prikazKlijenata == null)
			prikazKlijenata = new PrikazKlijenata();
		
		return prikazKlijenata;
	}

	public PrikazKorisnika getPrikazKorisnika()
	{
		if(prikazKorisnika == null)
			prikazKorisnika = new PrikazKorisnika();
		
		return prikazKorisnika;
	}

	public OdjavaService getOdjavaService()
	{
		if(odjavaService == null)
			odjavaService = new OdjavaService();
		
		return odjavaService;
	}

	public PostavkeService getPostavkeService() 
	{
		if(postavkeService == null)
			postavkeService = new PostavkeService();
		
		return postavkeService;
	}
	
	
	
	
	

}
