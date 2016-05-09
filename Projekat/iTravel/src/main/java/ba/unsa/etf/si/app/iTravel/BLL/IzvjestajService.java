package ba.unsa.etf.si.app.iTravel.BLL;
import java.util.List;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.RezervisaniTerminSoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class IzvjestajService {
	
	public DBContext baza;
	
	public IzvjestajService() 
	{
		baza = DBContext.getInstance();
	}
	
	public int PrebrojRezervacijeZaDestinaciju(Destinacija destinacija1, Date date, Date date2)
	{	
		ArrayList<Criterion> listaKriterijona = new ArrayList<Criterion>();
		ArrayList<Criterion> listaKriterijona1 = new ArrayList<Criterion>();
		ArrayList<Criterion> listaKriterijona2 = new ArrayList<Criterion>();
		listaKriterijona.add(Restrictions.eq("destinacija", (Destinacija)destinacija1));
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel = baza.getHoteliRepo().ucitajIzBazePoKriteriju(listaKriterijona);
		
		int suma=0;
		for(Hotel h: hotel)
		{
			listaKriterijona1.add(Restrictions.eq("hotel", h));
			List<Soba> sobe= new ArrayList<Soba>();
			sobe = baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterijona1);
			for(Soba s: sobe)
			{
				listaKriterijona2.add(Restrictions.eq("soba", s));
				List<RezervisaniTerminSoba> rez= new ArrayList<RezervisaniTerminSoba>();
				rez = baza.getSobaRezRepozitory().ucitajIzBazePoKriteriju(listaKriterijona2);
				for(RezervisaniTerminSoba r: rez)
				{
					if( date.before(r.getDatumPocetak()) && r.getDatumKraj().before(date2))
					
						suma++;
				}
				listaKriterijona2.clear();
			}
			listaKriterijona1.clear();
		}
		
		
		return suma;	
		
	}
	
	public int ukupanBrojSobaNaRaspolaganju(Hotel i)
	{
		ArrayList<Criterion> listaKriterijona = new ArrayList<Criterion>();
		listaKriterijona.add(Restrictions.eq("hotel", (Hotel)i));
		List<Soba> sobe= new ArrayList<Soba>();
		sobe = baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterijona);
		
		return sobe.size();
	}
	
	public int brojIznajmljenihSoba(Hotel IDhotel,java.util.Date date, java.util.Date date2)
	{
		ArrayList<Criterion> listaKriterijona = new ArrayList<Criterion>();
		listaKriterijona.add(Restrictions.eq("hotel", (Hotel)IDhotel));
		List<Soba> sobe= new ArrayList<Soba>();
		sobe = baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterijona);
		

		int suma=0;
		for(Soba s: sobe)
		{
			ArrayList<Criterion> listaKriterijona1 = new ArrayList<Criterion>();
			listaKriterijona1.add(Restrictions.eq("soba", (Soba)s));
			List<RezervisaniTerminSoba> sobeRez= new ArrayList<RezervisaniTerminSoba>();
			sobeRez = baza.getSobaRezRepozitory().ucitajIzBazePoKriteriju(listaKriterijona1);
			
			for(RezervisaniTerminSoba rs: sobeRez)
			{
				if(rs.getAktivan()==true && date.before(rs.getDatumPocetak()) && rs.getDatumKraj().before(date2))
					suma++;
			}
		}
		
		
		return suma;
	}
	
	public String VratiNazivDestinacije(Integer destID)
	{
		
		Destinacija d= new Destinacija();
		d = baza.getDestRepository().ucitajIzBaze(destID);
		return (String)d.getNaziv();
		
	}
	
	public String VratiNazivHotela(Integer hotelid)
	{
		
		Hotel h= new Hotel();
		h = baza.getHoteliRepo().ucitajIzBaze(hotelid);
		return h.getNaziv();
	}
	
	
	public List<Destinacija> VratiListuDestinacija()
	{
		
		List<Destinacija> dest= new ArrayList<Destinacija>();
		dest= baza.getDestRepository().ucitajSveIzBaze();
		
		return dest;
	}
	

	public List<Hotel> VratiListuHotela()
	{
	
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= baza.getHoteliRepo().ucitajSveIzBaze();
		
		return hotel;
	}
	
	public List<Hotel> VratiListuHotela(Destinacija d)
	{
	
		ArrayList<Criterion> listaKriterijona = new ArrayList<Criterion>();
		listaKriterijona.add(Restrictions.eq("destinacija", (Destinacija)d));
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel = baza.getHoteliRepo().ucitajIzBazePoKriteriju(listaKriterijona);
		
		return hotel;
	}
	
	
	

}