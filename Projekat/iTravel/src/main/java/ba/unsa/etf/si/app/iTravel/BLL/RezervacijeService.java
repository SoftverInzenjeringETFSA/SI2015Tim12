package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Klijent;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Racun;
import ba.unsa.etf.si.app.iTravel.DBModels.Rezervacija;
import ba.unsa.etf.si.app.iTravel.DBModels.RezervisaniTerminSoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class RezervacijeService {
	
	DBContext baza;
	
	public RezervacijeService(){		
		baza = DBContext.getInstance();
	}
	
	//dok cekam SobeService
	public ArrayList<Soba> dajSobeZaHotel(Hotel hotel){
		ArrayList<Soba> sobe=new ArrayList<Soba>();
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("hotel", hotel));
		sobe.addAll(baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterija));
		return sobe;
	}
	
	//dok cekam SobeService
	//ne valja kompleksnost :(
	public ArrayList<RezervisaniTerminSoba> dajTermineZaSobu(ArrayList<Soba> sobe){
		ArrayList<RezervisaniTerminSoba> rts=new ArrayList<RezervisaniTerminSoba>();	
		for(int i=0; i<sobe.size(); i++){
			ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
			listaKriterija.add(Restrictions.eq("soba", sobe.get(i)));
			rts.addAll(baza.getRezervisaniTerminSobaRepository().ucitajIzBazePoKriteriju(listaKriterija));
			for(int j=0; j<rts.size(); j++){
				Soba s=new Soba();
				s=baza.getSobaRepository().ucitajIzBaze(rts.get(j).getSoba().getSobaId());
				rts.get(j).setSoba(s);
			}
		}
		return rts;
	}
	
	//dok cekam SobeService
	//ne valja kompleksnost :(
	public ArrayList<Soba> dajSlobodneSobeZaHotel(Hotel hotel, Date pocetak, Date kraj){
		ArrayList<Soba> sobe=new ArrayList<Soba>();		
		sobe.addAll(dajSobeZaHotel(hotel));
		
		ArrayList<RezervisaniTerminSoba> termini=new ArrayList<RezervisaniTerminSoba>();		
		termini=dajTermineZaSobu(sobe);
		
		for(int i=0; i<termini.size(); i++ ){
			Date rezPoc=termini.get(i).getDatumPocetak();
			Date rezKraj=termini.get(i).getDatumKraj();
			if((pocetak.before(rezPoc) && kraj.after(rezPoc) && kraj.before(rezKraj)) || (pocetak.after(rezPoc) && kraj.before(rezKraj)) || (pocetak.after(rezPoc) && pocetak.before(rezKraj) && kraj.after(rezKraj)) || (pocetak.before(rezPoc) && kraj.after(rezKraj))){
				for(int j=0; j<sobe.size(); j++){
					if(sobe.get(j).getSobaId()==termini.get(i).getSoba().getSobaId())
						sobe.remove(sobe.get(j));
				}
			}
		}
		return sobe;
	}
	
	//dok cekam OsobaService
	private List<Osoba> dajOsobuPoJMBG(int jmbg){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("jmbg", (Integer)jmbg));
		List<Osoba> s=new ArrayList<Osoba>();
		s=baza.getOsobaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return s;
	}
	
	//dok cekam KorisnickiRacunService
	private List<KorisnickiRacun> dajKorisnickiRacunPoKorisnickiRacunID(int id){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("korisnickiRacunId", (Integer)id));
		List<KorisnickiRacun> tmp=new ArrayList<KorisnickiRacun>();
		tmp=baza.getKorisnickiRacunRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return tmp;
	}
	
	//dok cekam KlijentService
	private List<Klijent> dajKlijentaPoOsobi(Osoba osoba){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("osoba", osoba));
		List<Klijent> k=new ArrayList<Klijent>();
		k=baza.getKlijentRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return k;
	}
	
	public Rezervacija dajRezervaciju(int idRezervacije){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("rezervacijaId", idRezervacije));
		List<Rezervacija> r=new ArrayList<Rezervacija>();
		r=baza.getRezervacijaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return r.get(0);
	}
	
	public Rezervacija dajRezervacijuPoDatumuIKlijentu(Date datum, Klijent klijent){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("datumRezervacije", datum));
		listaKriterija.add(Restrictions.eq("klijent", klijent));
		List<Rezervacija> r=new ArrayList<Rezervacija>();
		r=baza.getRezervacijaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return r.get(0);
	}
	
    public boolean	kreirajRezervacijuSaSobom(Rezervacija rez, Soba soba, Date odD, Date doD, int idAgent, int cijena){
			if(kreirajRezervaciju(rez, idAgent)){
				if(rezervisiSobu(soba,odD,doD, dajRezervacijuPoRezervaciji(rez).get(0))){
					Racun racun=new Racun();
					Rezervacija tmpRez=new Rezervacija();
					tmpRez=dajRezervacijuPoDatumuIKlijentu(rez.getDatumRezervacije(),rez.getKlijent());
					
					racun.setCijena(cijena);
					racun.setTrenutniDatum(new Date());
					racun.setRezervacija(tmpRez);
					RacunService r=new RacunService();
					r.kreirajRacun(racun);
			
					return true;
				}
			}
			return false;
	}
	
    public boolean rezervisiSobu(Soba soba, Date odD, Date doD, Rezervacija rez){
		try{
	    	Soba s=new Soba();
	    	s= baza.getSobaRepository().ucitajIzBaze(soba.getSobaId());
	    	RezervisaniTerminSoba rezervTermin=new RezervisaniTerminSoba();
	    	rezervTermin.setDatumKraj(doD);
	    	rezervTermin.setDatumPocetak(odD);
	    	rezervTermin.setRezervacija(rez);
	    	rezervTermin.setSoba(s);
	    	rezervTermin.setAktivan(true);
	    	baza.getRezervisaniTerminSobaRepository().spasiUBazu(rezervTermin);
	    	return true;			
		}catch(Exception e){
			UnitOfWork.logger.error(e);
			return false;
		}
	}
	
    
	public boolean kreirajRezervaciju(Rezervacija rez, int idAgenta) {
		try{
			rez.setKorisnickiRacun(dajKorisnickiRacunPoKorisnickiRacunID(idAgenta).get(0));			
		
			Klijent k=new Klijent();
			k=rez.getKlijent();
			baza.getOsobaRepository().spasiUBazu(k.getOsoba());
			
			Osoba s=dajOsobuPoJMBG(rez.getKlijent().getOsoba().getJmbg()).get(0);
			k.setOsoba(s);

			baza.getKlijentRepository().spasiUBazu(k);
		
			rez.setKlijent(dajKlijentaPoOsobi(s).get(0));
			baza.getRezervacijaRepository().spasiUBazu(rez);
		}catch(Exception e){
			UnitOfWork.logger.error(e);
			return false;
		}		
		return true;
	}
	
	private List<Rezervacija> dajRezervacijuPoRezervaciji(Rezervacija rez){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("klijent", rez.getKlijent()));
		listaKriterija.add(Restrictions.eq("datumRezervacije", rez.getDatumRezervacije()));
		listaKriterija.add(Restrictions.eq("korisnickiRacun", rez.getKorisnickiRacun()));
		listaKriterija.add(Restrictions.eq("ukljucenPrevoz", rez.getUkljucenPrevoz()));
		List<Rezervacija> r=new ArrayList<Rezervacija>();
		r=baza.getRezervacijaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return r;
	}
}
