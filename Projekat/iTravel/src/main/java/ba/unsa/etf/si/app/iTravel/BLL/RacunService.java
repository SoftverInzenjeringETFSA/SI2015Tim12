package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Racun;
import ba.unsa.etf.si.app.iTravel.DBModels.Rezervacija;


public class RacunService {
	DBContext baza;
	
	public RacunService(){
		baza= DBContext.getInstance();
	}
	
	public void kreirajRacun(Racun racun){
		baza.getRacunRepository().spasiUBazu(racun);
	}
	
	public ArrayList<Racun> dajSveRacune(){
		ArrayList<Racun> racuni=new ArrayList<Racun>();

		racuni=(ArrayList<Racun>) baza.getRacunRepository().ucitajSveIzBaze();		
		
		return racuni;
	}
	
	public Racun dajRacun(int idRacuna) {
		Racun racun = new Racun();

		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("racunId", (Integer) idRacuna));

		racun = baza.getRacunRepository().ucitajIzBazePoKriteriju(listaKriterjona).get(0);

		return racun;
	}
	
	public Racun dajRacunPoIdRezervacije(int idRezervacije) {
		Racun racun = new Racun();

		RezervacijeService kr=new RezervacijeService();
		Rezervacija r=new Rezervacija();
		r=kr.dajRezervaciju(idRezervacije);
		
		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("rezervacija", r));

		racun = baza.getRacunRepository().ucitajIzBazePoKriteriju(listaKriterjona).get(0);

		return racun;
	}
	
	public void updateRacun(Racun racun){
		baza.getRacunRepository().sacuvajIliAzurirajUBazu(racun);
	}
}
