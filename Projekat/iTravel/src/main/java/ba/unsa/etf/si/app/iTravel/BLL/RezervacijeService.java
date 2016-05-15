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
	
	public Rezervacija dajRezervaciju(int idRezervacije){
		return baza.getRezervacijaRepository().ucitajIzBaze(idRezervacije);
	}
	
	public Rezervacija dajRezervacijuPoDatumuIKlijentu(Date datum, Klijent klijent){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("datumRezervacije", datum));
		listaKriterija.add(Restrictions.eq("klijent", klijent));
		List<Rezervacija> r=new ArrayList<Rezervacija>();
		r=baza.getRezervacijaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return r.get(0);
	}
	
    public int kreirajRezervacijuSaSobom(Rezervacija rez, Soba soba, Date odD, Date doD, int idAgent, int cijena){
			if(kreirajRezervaciju(rez, idAgent)){
				if(rezervisiSobu(soba,odD,doD, dajRezervacijuPoRezervaciji(rez))){
					Racun racun=new Racun();
					Rezervacija tmpRez=new Rezervacija();
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
					
					return tmpRez.getRezervacijaId();
				}
			}
			return 0;
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
			KorisnickiRacunService kss=new KorisnickiRacunService();
			rez.setKorisnickiRacun(kss.dajKorisnickiRacunPoKorisnickiRacunID(idAgenta));			
		
			Klijent k=new Klijent();
			k=rez.getKlijent();
			
			Osoba s;
			OsobaService os=new OsobaService();
			ArrayList<Osoba> osobe=(ArrayList<Osoba>)os.dajOsobuPoJMBG(k.getOsoba().getJmbg());
			if(osobe.size()!=0){
				s=osobe.get(0);
			}else{
				s= baza.getOsobaRepository().spasiUBazu(k.getOsoba());
			}
		
			k.setOsoba(s);
	
			ArrayList<Klijent> klijenati=(ArrayList<Klijent>)baza.getKlijentRepository().ucitajSveIzBaze();
			if(klijenati.size()!=0){
				int test=klijenati.get(klijenati.size()-1).getKlijentId();
				k.setKlijentId(test+1);
			}else
				k.setKlijentId(1);
			Klijent kSaId= baza.getKlijentRepository().spasiUBazu(k);
			
			rez.setKlijent(kSaId);
			
			ArrayList<Rezervacija> rezervacijeSve= (ArrayList<Rezervacija>)baza.getRezervacijaRepository().ucitajSveIzBaze();
			if(rezervacijeSve.size()!=0)
				rez.setRezervacijaId(rezervacijeSve.get(rezervacijeSve.size()-1).getRezervacijaId()+1);
			else 
				rez.setRezervacijaId(1);
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
		return (ArrayList<Rezervacija>) baza.getRezervacijaRepository().ucitajSveIzBaze();
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
			
			baza.getRezervacijaRepository().obrisiIzBaze(rez);
			
			return true;
		}catch(Exception e){
			UnitOfWork.logger.error(e);
			return false;
		}
	}
	
	public void obrisiSveRezervacije(){
		baza.getRezervacijaRepository().obrisiSveIzBaze();
	}
		
}
