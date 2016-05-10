package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Klijent;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;

public class PrikazKlijenata {
	DBContext baza;

	public PrikazKlijenata() {
		baza = DBContext.getInstance();
	}
	
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

}
