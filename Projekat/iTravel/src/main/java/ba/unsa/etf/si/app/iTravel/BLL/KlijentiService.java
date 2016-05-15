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
	
	public Object[][] PrikaziSveKlijente() {
		ArrayList<Klijent> klijenti = new ArrayList<Klijent>();
		klijenti=(ArrayList<Klijent>)baza.getKlijentRepository().ucitajSveIzBaze();
		ArrayList<Osoba> os = new ArrayList<Osoba>();
		for(int i=0;i<klijenti.size();i++)
		{	os.add((klijenti.get(i).getOsoba()));}
		Object[][] red=new Object[os.size()][8];
		for(int i=0;i<os.size();i++)
		{
			
		red[i][0]=os.get(i).getIme();
		red[i][1]=os.get(i).getPrezime();
		red[i][2]=os.get(i).getJmbg();
		red[i][3]=os.get(i).getBrojPasosa();
		red[i][4]=os.get(i).getBrojTelefona();
		red[i][5]=os.get(i).getEmail();
		red[i][6]=os.get(i).getAdresa();
		red[i][7]=os.get(i).getDatumRodjenja();
		}
		return red;
	}
	
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
