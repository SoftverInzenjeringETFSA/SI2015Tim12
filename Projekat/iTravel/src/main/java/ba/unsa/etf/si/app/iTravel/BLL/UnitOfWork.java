package ba.unsa.etf.si.app.iTravel.BLL;

public class UnitOfWork {
	public static class UserContext {
		public static String Username;
		public static int Identitfication;
		public static int RolaID;

		public UserContext(String username, int ID, int idRole) {
			Username = username;
			Identitfication = ID;
			RolaID = idRole;
		}
	}

	private PrijavaService prijavaService;
	private DohvatiHotel dohvatiHotel;
	private ModifikacijaHotel modifikacijaHotela;
	private UbaciHotel ubaciHotel;
	private ObrisiHotel obrisiHotel;
	private IzvjestajService izvjestajService;
	private HoteliService hoteliService;
	private DestinacijeService destinacijeService;
	private RezervacijeService kreiranjeRezervacijaService;
	private RacunService racunService;

	public UnitOfWork() {
		prijavaService = new PrijavaService();
		dohvatiHotel = new DohvatiHotel();
		modifikacijaHotela = new ModifikacijaHotel();
		ubaciHotel = new UbaciHotel();
		obrisiHotel = new ObrisiHotel();
		izvjestajService= new IzvjestajService();
		destinacijeService=new DestinacijeService();
		kreiranjeRezervacijaService=new RezervacijeService();
		racunService=new RacunService();
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
	
	public DestinacijeService getDestinacijeService(){
		if(destinacijeService==null)
			destinacijeService=new DestinacijeService();
		
		return destinacijeService;
	}
	public RezervacijeService getKreiranjeRezervacijaService(){
		if(kreiranjeRezervacijaService==null)
			kreiranjeRezervacijaService=new RezervacijeService();
		return kreiranjeRezervacijaService;
	}
	
	public RacunService getRacunService(){
		if(racunService==null){
			racunService=new RacunService();
		}
		return racunService;
	}

}
