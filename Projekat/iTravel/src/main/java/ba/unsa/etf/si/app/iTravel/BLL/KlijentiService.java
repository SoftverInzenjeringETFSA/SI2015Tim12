package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Klijent;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;

public class KlijentiService {
	DBContext baza = new DBContext();
	
	public void KreirajKlijentaOsoba(Osoba osoba) {
		Klijent k=new Klijent();
		k.setOsoba(osoba);
		baza.getKlijentRepository().spasiUBazu(k);
	}
	
	public void KreirajKlijenta(Klijent klijent){
		baza.getKlijentRepository().spasiUBazu(klijent);
	}
	
	public ArrayList<Klijent> dajSveKlijente(){
		ArrayList<Klijent> k=(ArrayList<Klijent>)baza.getKlijentRepository().ucitajSveIzBaze();
		return  k;
	}
	
	public List<Klijent> dajKlijentaPoOsobi(Osoba osoba){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("osoba", osoba));
		List<Klijent> k=new ArrayList<Klijent>();
		k=baza.getKlijentRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return k;
	}
}
