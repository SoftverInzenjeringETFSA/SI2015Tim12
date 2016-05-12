package ba.unsa.etf.si.app.iTravel.BLL;

import org.apache.log4j.Logger;

public class UnitOfWork {
	
	public final static Logger logger = Logger.getLogger(UnitOfWork.class);
	
	private PrijavaService prijavaService;
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
	private PromjenaSifreService promjenaSifreService;
	
	public UnitOfWork() {
		prijavaService = new PrijavaService();
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
		promjenaSifreService = new PromjenaSifreService();
		
	}

	public PrijavaService getPrijavaService() {
		if (prijavaService == null)
			prijavaService = new PrijavaService();

		return prijavaService;
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

	public PromjenaSifreService getPromjenaSifreService() 
	{
		if(promjenaSifreService == null)
			promjenaSifreService = new PromjenaSifreService();
		
		return promjenaSifreService;
	}
	
	
	
	
	

}
