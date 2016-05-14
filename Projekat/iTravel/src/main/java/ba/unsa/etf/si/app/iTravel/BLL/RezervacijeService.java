package ba.unsa.etf.si.app.iTravel.BLL;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.print.attribute.standard.DateTimeAtCreation;

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
import ba.unsa.etf.si.app.iTravel.Forms.Rezervacije;

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
	private List<Osoba> dajOsobuPoJMBG(String jmbg){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("jmbg", (String)jmbg));
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
	
	//dok cekam OsobaService
	public Osoba dajOsobuPoId(int idOsoba){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("osobaId", idOsoba));
		List<Osoba> k=baza.getOsobaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return k.get(0);
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
				if(rezervisiSobu(soba,odD,doD, dajRezervacijuPoRezervaciji(rez))){
					Racun racun=new Racun();
					Rezervacija tmpRez=new Rezervacija();
					//tmpRez=dajRezervacijuPoDatumuIKlijentu(rez.getDatumRezervacije(),rez.getKlijent());
					tmpRez=dajRezervacijuPoRezervaciji(rez);
					racun.setCijena(cijena);
					
					racun.setTrenutniDatum(new Timestamp(new Date().getTime()));
					racun.setRezervacija(tmpRez);
					
					RacunService r=new RacunService();
					
					ArrayList<Racun> racuni=r.dajSveRacune();
					if(racuni.size()!=0)
						racun.setRacunId(racuni.get(racuni.size()-1).getRacunId()+1);
					else
						racun.setRacunId(1);
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
			
	    	
			ArrayList<RezervisaniTerminSoba> rezTer=(ArrayList<RezervisaniTerminSoba>)baza.getRezervisaniTerminSobaRepository().ucitajSveIzBaze();
			if(rezTer.size()!=0)
				rezervTermin.setRezervisaniTerminId(rezTer.get(rezTer.size()-1).getRezervisaniTerminId()+1);
			else
				rezervTermin.setRezervisaniTerminId(1);
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
			
			Osoba s;
			ArrayList<Osoba> osobe=(ArrayList<Osoba>)dajOsobuPoJMBG(k.getOsoba().getJmbg());
			if(osobe.size()!=0){
				s=osobe.get(0);
			}else{
				baza.getOsobaRepository().spasiUBazu(k.getOsoba());
				s=dajOsobuPoJMBG(rez.getKlijent().getOsoba().getJmbg()).get(0);
			}
			
			k.setOsoba(s);
	
			ArrayList<Klijent> klijenati=(ArrayList<Klijent>)baza.getKlijentRepository().ucitajSveIzBaze();
			if(klijenati.size()!=0){
				int test=klijenati.get(klijenati.size()-1).getKlijentId();
				k.setKlijentId(test+1);
			}else
				k.setKlijentId(1);
			baza.getKlijentRepository().spasiUBazu(k);
		
			rez.setKlijent(dajKlijentaPoOsobi(s).get(0));
			
			ArrayList<Rezervacija> rezervacijeSve= (ArrayList<Rezervacija>)baza.getRezervacijaRepository().ucitajSveIzBaze();
			
			rez.setRezervacijaId(rezervacijeSve.get(rezervacijeSve.size()-1).getRezervacijaId()+1);
			
			baza.getRezervacijaRepository().spasiUBazu(rez);
		}catch(Exception e){
			UnitOfWork.logger.error(e);
			return false;
		}		
		return true;
	}
	
	private Rezervacija dajRezervacijuPoRezervaciji(Rezervacija rez){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("klijent", rez.getKlijent()));
		//listaKriterija.add(Restrictions.eq("datumRezervacije", rez.getDatumRezervacije()));
		listaKriterija.add(Restrictions.eq("korisnickiRacun", rez.getKorisnickiRacun()));
		listaKriterija.add(Restrictions.eq("ukljucenPrevoz", rez.getUkljucenPrevoz()));
		List<Rezervacija> r=new ArrayList<Rezervacija>();
		r=baza.getRezervacijaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		for(int i=0; i<r.size(); i++){
			if(r.get(i).equals(rez)) return r.get(i);
		}
		return r.get(0);
	}
	
	public ArrayList<Rezervacija> dajSveRezervacije(){
		ArrayList<Rezervacija> r=new ArrayList<Rezervacija>();
		r=(ArrayList<Rezervacija>) baza.getRezervacijaRepository().ucitajSveIzBaze();
		return r;
	}
	
	//dok cekam rezervisaniTerminSobaService
	public ArrayList<RezervisaniTerminSoba> dajRezervisaneTermineZaRezervaciju(int idRezervacije){
		Rezervacija rez=dajRezervaciju(idRezervacije);
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("rezervacija", rez));
		ArrayList<RezervisaniTerminSoba> r=new ArrayList<RezervisaniTerminSoba>();
		r=(ArrayList<RezervisaniTerminSoba>)baza.getRezervisaniTerminSobaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return r;
	}
	
	//dok cekam SobaService
	public Soba dajSobu(int idSobe){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("sobaId", idSobe));
		Soba r=new Soba();
		r=baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterija).get(0);
		HoteliService hs=new HoteliService();		
		Hotel h=hs.VratiHotelId(r.getHotel().getHotelId());
		r.setHotel(h);
		return r;
	}
	
	public boolean potvrdiRezervaciju(int idRezervacije){
		try{
			Rezervacija rez= dajRezervaciju(idRezervacije);
			Racun racun= rez.getRacuns().iterator().next();
			racun.setDatumUplate(new Timestamp(new Date().getTime()));
			baza.getRacunRepository().sacuvajIliAzurirajUBazu(racun);
			return true;
		}catch(Exception e){
			UnitOfWork.logger.error(e);
			return false;
		}
	}
	
	public boolean obrisiRezervaciju(int idRezervacije){
		try{
			Rezervacija rez= dajRezervaciju(idRezervacije);
			Racun racun= rez.getRacuns().iterator().next();
			baza.getRacunRepository().obrisiIzBaze(racun);
			RezervisaniTerminSoba r=null;
			if(rez.getRezervisaniTerminSobas().iterator().hasNext())
				r=rez.getRezervisaniTerminSobas().iterator().next();
			baza.getRezervisaniTerminSobaRepository().obrisiIzBaze(r);
			
			baza.getRezervacijaRepository().obrisiIzBaze(rez);
			
			return true;
		}catch(Exception e){
			UnitOfWork.logger.error(e);
			return false;
		}
	}
		
}
