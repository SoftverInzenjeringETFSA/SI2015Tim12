package ba.unsa.etf.si.app.iTravel.BLL;

import static org.junit.Assert.*;

import org.junit.Test;

public class PromjenaSifreTest {

	@Test
	public void testPromjenePristupnihPodataka() {

		
		PromjenaSifreService servis = new PromjenaSifreService();
		
		UserContext.getInstance().logUserIn(servis.baza.getKorisnickiRacunRepository()
				.ucitajIzBaze(1).getUsername());
		
		 String pass = servis.baza.getKorisnickiRacunRepository()
		.ucitajIzBaze(1).getPassword();
		 
		 assertTrue(servis.PromijeniPristupnePodatke(pass, pass));
		 //assertTrue(!servis.PromijeniPristupnePodatke(pass+"nesto", pass));
	
	}

}
