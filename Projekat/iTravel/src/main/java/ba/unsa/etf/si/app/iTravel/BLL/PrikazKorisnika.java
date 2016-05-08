package ba.unsa.etf.si.app.iTravel.BLL;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Klijent;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;
import ba.unsa.etf.si.app.iTravel.DBModels.Korisnickiracunxrola;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;

public class PrikazKorisnika {
	DBContext baza;

	public PrikazKorisnika() {
		baza = DBContext.getInstance();
	}
	
	public Object[][] PrikaziSveKorisnike() {
		
		ArrayList<KorisnickiRacun> os = new ArrayList<KorisnickiRacun>();
		os=(ArrayList<KorisnickiRacun>)baza.getKorisnickiRacunRepository().ucitajSveIzBaze();

	
	Object[][] red=new Object[os.size()][9];
	for(int i=0;i<os.size();i++)
	{
		
	red[i][0]=os.get(i).getOsoba().getIme();
	red[i][1]=os.get(i).getOsoba().getPrezime();
	red[i][2]=os.get(i).getOsoba().getJmbg();
	red[i][3]=os.get(i).getOsoba().getAdresa();
	red[i][4]=os.get(i).getOsoba().getAdresa();;
	red[i][5]=os.get(i).getOsoba().getBrojTelefona();
	red[i][6]=os.get(i).getOsoba().getEmail();
	red[i][7]=os.get(i).getUsername();
Set<Korisnickiracunxrola> skupRola=	os.get(i).getKorisnickiracunxrolas();
for(Iterator<Korisnickiracunxrola> it=skupRola.iterator();
	it.hasNext();)
{
	Korisnickiracunxrola r=it.next();
	if(r!=null)
	{
		red[i][8]=r.getRola().getNaziv();
	}
}

	
	}
        
		return red;
	}

}
