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
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		ArrayList<Criterion> listaKriterija1 = new ArrayList<Criterion>();
		ArrayList<Criterion> listaKriterija2 = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("destinacija", (Destinacija)destinacija1));
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel = baza.getHoteliRepo().ucitajIzBazePoKriteriju(listaKriterija);
		
		int suma=0;
		for(Hotel h: hotel)
		{
			listaKriterija1.add(Restrictions.eq("hotel", (Hotel)h));
			List<Soba> sobe= new ArrayList<Soba>();
			sobe = baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterija1);
			if(sobe.size()==0) return -1; 
			for(Soba s: sobe)
			{
				listaKriterija2.add(Restrictions.eq("soba", (Soba)s));
				List<RezervisaniTerminSoba> rez= new ArrayList<RezervisaniTerminSoba>();
				rez = baza.getSobaRezRepozitory().ucitajIzBazePoKriteriju(listaKriterija2);
				for(RezervisaniTerminSoba r: rez)
				{
					if( (date.getTime()< r.getDatumPocetak().getTime() && r.getDatumKraj().getTime()<date2.getTime()) 
							|| ( r.getDatumPocetak().getTime()<date.getTime() && date.getTime() < r.getDatumKraj().getTime() && r.getDatumKraj().getTime()<date2.getTime()) 
							|| (date.getTime() < r.getDatumPocetak().getTime() && r.getDatumPocetak().getTime()<date2.getTime() && date2.getTime()<r.getDatumKraj().getTime())
							|| (r.getDatumPocetak().getTime()==date.getTime()) || (r.getDatumKraj().getTime()==date2.getTime()))
					
						suma++;
				}
				listaKriterija2.clear();
			}
			listaKriterija1.clear();
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
	
	public int brojIznajmljenihSoba(Hotel IDhotel,Date date, Date date2)
	{
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("hotel", (Hotel)IDhotel));
		List<Soba> sobe= new ArrayList<Soba>();
		sobe = baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		

		int suma=0;
		for(Soba s: sobe)
		{
			ArrayList<Criterion> listaKriterija1 = new ArrayList<Criterion>();
			listaKriterija1.add(Restrictions.eq("soba", (Soba)s));
			List<RezervisaniTerminSoba> sobeRez= new ArrayList<RezervisaniTerminSoba>();
			sobeRez = baza.getSobaRezRepozitory().ucitajIzBazePoKriteriju(listaKriterija1);
			
			for(RezervisaniTerminSoba r: sobeRez)
			{
				if(r.getAktivan()==true)
					if( (date.getTime()< r.getDatumPocetak().getTime() && r.getDatumKraj().getTime()<date2.getTime()) 
							|| ( r.getDatumPocetak().getTime()<date.getTime() && date.getTime() < r.getDatumKraj().getTime() && r.getDatumKraj().getTime()<date2.getTime()) 
							|| (date.getTime() < r.getDatumPocetak().getTime() && r.getDatumPocetak().getTime()<date2.getTime() && date2.getTime()<r.getDatumKraj().getTime())
							|| (r.getDatumPocetak().getTime()==date.getTime()) || (r.getDatumKraj().getTime()==date2.getTime()))
					
					suma++;
			}
			listaKriterija1.clear();
		}
		
		return suma;
	}
	
	public String VratiNazivDestinacije(Integer destID)
	{
		
		Destinacija d= new Destinacija();
		d = baza.getDestRepository().ucitajIzBaze(destID);
		return (String)d.getNaziv();
		
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
	
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("destinacija", (Destinacija)d));
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel = baza.getHoteliRepo().ucitajIzBazePoKriteriju(listaKriterija);
		
		return hotel;
	}
	
	
	
	
	

}