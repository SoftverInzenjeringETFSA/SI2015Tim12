package ba.unsa.etf.si.app.iTravel.BLL;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;

public class PromjenaSifreService {
	
	DBContext baza;
	
	public PromjenaSifreService() {
		
		baza = DBContext.getInstance();
	}
	

	public boolean PromijeniPristupnePodatke(String passStari, String passNovi){
		
		KorisnickiRacun racun = baza.getKorisnickiRacunRepository()
									.ucitajIzBaze(UserContext.getInstance().getIdentification());
		
		if(racun.getPassword().equals(passStari)){
			racun.setPassword(passNovi);
			baza.getKorisnickiRacunRepository().sacuvajIliAzurirajUBazu(racun);
			return true;
		}
		else{
			return false;

		}	
	}

}
