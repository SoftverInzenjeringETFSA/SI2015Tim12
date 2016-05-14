package ba.unsa.etf.si.app.iTravel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.BeforeClass;
import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Klijent;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;
import ba.unsa.etf.si.app.iTravel.DBModels.Korisnickiracunxrola;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Racun;
import ba.unsa.etf.si.app.iTravel.DBModels.Rezervacija;
import ba.unsa.etf.si.app.iTravel.DBModels.RezervisaniTerminSoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Rola;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class RezervacijaTest {

	public UnitOfWork uow;
	
	@Test
	public void testInsertDestinacijeHoteliSobe(){
		uow=new UnitOfWork();
		//destinacije
		Destinacija d=new Destinacija("Sarajevo",false,null,null);
		//d.setDestinacijaId(1);
		uow.getDestinacijeService().UbaciDestinacijuUBAzu(d);
		Destinacija d1=new Destinacija("Dubai",true,null,null);
		//d1.setDestinacijaId(2);
		uow.getDestinacijeService().UbaciDestinacijuUBAzu(d);
		//assertEquals(2,uow.getDestinacijeService().DajSveDestinacije().size());
		//hoteli
		Hotel h=new Hotel(d,"Adresa1","BiH","Sarajevo","065498",new Date(2016,9,1),new Date(2016,4,30),new Date(2016,30,8),new Date(2016,5,1),"Europa","DOO",5,null,null);
		//h.setHotelId(1);
		uow.getHoteliService().KreirajHotel(h);
		Hotel h1=new Hotel(d,"Adresa2 i Sa","BiH","Sarajevo","06545648",new Date(2016,9,1),new Date(2016,4,30),new Date(2016,30,8),new Date(2016,5,1),"Holivud","DOO",4,null,null);
		//h1.setHotelId(2);
		uow.getHoteliService().KreirajHotel(h1);
		Hotel h2=new Hotel(d1,"Adresa1 u UAE","UAE","Dubai","+454065498",new Date(2016,9,1),new Date(2016,4,30),new Date(2016,30,8),new Date(2016,5,1),"Harem","DOO2",5,null,null);
		//h2.setHotelId(3);
		uow.getHoteliService().KreirajHotel(h2);
		Hotel h3=new Hotel(d1,"Adresa2 u UAE","UAE","Dubai","+46406545648",new Date(2016,9,1),new Date(2016,4,30),new Date(2016,30,8),new Date(2016,5,1),"Mustuluk","DOO2",5,null,null);
		//h3.setHotelId(4);
		uow.getHoteliService().KreirajHotel(h3);
		//assertEquals(4,uow.getHoteliService().VratiSveHotele().size());
		//sobe
		Soba s=new Soba(h,5,"opis prve sobe",110,100,null,null);
		//s.setSobaId(1);
		uow.getSobeService().AzurirajiliUbaciSobu(s);
		Soba s1=new Soba(h,3,"opis drug sobe",90,800,null,null);
		//s1.setSobaId(2);
		uow.getSobeService().AzurirajiliUbaciSobu(s1);
		Soba s2=new Soba(h,5,"opis trece sobe",130,120,null,null);
		//s2.setSobaId(3);
		uow.getSobeService().AzurirajiliUbaciSobu(s2);
		Soba s3=new Soba(h1,3,"opis cetvrte sobe",110,100,null,null);
		//s3.setSobaId(4);
		uow.getSobeService().AzurirajiliUbaciSobu(s3);
		Soba s4=new Soba(h1,5,"opis pete sobe",110,100,null,null);
		//s4.setSobaId(5);
		uow.getSobeService().AzurirajiliUbaciSobu(s4);
		Soba s5=new Soba(h2,4,"opis seste sobe",170,160,null,null);
		//s5.setSobaId(6);
		uow.getSobeService().AzurirajiliUbaciSobu(s5);
		Soba s6=new Soba(h3,5,"opis sedme sobe",180,165,null,null);
		//s6.setSobaId(7);
		uow.getSobeService().AzurirajiliUbaciSobu(s6);
		Soba s7=new Soba(h3,4,"opis osme sobe",140,110,null,null);
		//s7.setSobaId(8);
		uow.getSobeService().AzurirajiliUbaciSobu(s7);
		Soba s8=new Soba(h3,3,"opis devete sobe",110,100,null,null);
		//s8.setSobaId(9);
		uow.getSobeService().AzurirajiliUbaciSobu(s8);
		Soba s9=new Soba(h3,2,"opis desete sobe",50,40,null,null);
		//s9.setSobaId(10);
		uow.getSobeService().AzurirajiliUbaciSobu(s9);
		//assertEquals(10,uow.getSobeService().VratiSveSobe().size());
	}

	@Test
	public void testInsertOsobaKlijentRacun(){
		//osobe
		Osoba o=new Osoba("Kenan","Prses", new Date(1668,5,5),"adresa usera 1","email@nesto.com","466655465","1234567894562","dsa64","4dsa6545d",null,null,null,null);
		o.setOsobaId(1);
		uow.getOsobaService().KreirajOsobu(o, false);
		Osoba o1=new Osoba("Emina","Prlja", new Date(1668,5,5),"adresa usera 2","emaieet@nesto.com","46546545","1234567894489","dsa6454","4dsasda654d",null,null,null,null);
		o1.setOsobaId(2);
		uow.getOsobaService().KreirajOsobu(o1, false);
		Osoba o2=new Osoba("Adna","Tahic", new Date(1668,5,5),"adresa usera 3","emailsad@nesto.com","46546455","1234567894795","dsa64da","454dsa654d",null,null,null,null);
		o2.setOsobaId(3);
		uow.getOsobaService().KreirajOsobu(o2, false);
		//assertEquals(3,uow.getOsobaService().dajSveOsobe().size());
		//klijenti
		Klijent k=new Klijent(o1);
		k.setKlijentId(1);
		uow.getKlijentiService().KreirajKlijenta(k);
		Klijent k1=new Klijent(o2);
		k1.setKlijentId(2);
		uow.getKlijentiService().KreirajKlijenta(k1);
		//assertEquals(3,uow.getKlijentiService().dajSveKlijente().size());
		//Role
		Rola r=new Rola(1,"Administrator",null,null);
		uow.getRolaService().KreirajRolu(r);
		Rola r1=new Rola(2,"Agent",null,null);
		uow.getRolaService().KreirajRolu(r1);
		Rola r2=new Rola(3,"Supervizor",null,null);
		uow.getRolaService().KreirajRolu(r2);
		//assertEquals(3,uow.getRolaService().dajRolu(3).getRolaId());		
		//korisnicki racuni
		KorisnickiRacun kr=new KorisnickiRacun(o,"kenanprses","Sitim12",null,null,null,null);
		kr.setKorisnickiRacunId(1);
		uow.getKorisnickiRacunService().KreirajKorisnickiRacun(kr, false);
		KorisnickiRacun kr2=new KorisnickiRacun(o,"kagent","Sitim12",null,null,null,null);
		kr2.setKorisnickiRacunId(2);
		uow.getKorisnickiRacunService().KreirajKorisnickiRacun(kr2, false);
		KorisnickiRacun kr3=new KorisnickiRacun(o,"ksupervizor","Sitim12",null,null,null,null);
		kr3.setKorisnickiRacunId(3);
		uow.getKorisnickiRacunService().KreirajKorisnickiRacun(kr3, false);
		//assertEquals(3,uow.getKorisnickiRacunService().dajKorisnika(3).getKorisnickiRacunId().intValue());
		//korisnicki Racun Rola
		Korisnickiracunxrola krr=new Korisnickiracunxrola(r,kr);
		krr.setKorisnickiRacunXrolaId(1);
		uow.getKorisnickiRacunService().KreirajRoluZaKorisnika(krr, false);
		Korisnickiracunxrola krr1=new Korisnickiracunxrola(r,kr2);
		krr1.setKorisnickiRacunXrolaId(2);
		uow.getKorisnickiRacunService().KreirajRoluZaKorisnika(krr1, false);
		Korisnickiracunxrola krr2=new Korisnickiracunxrola(r,kr3);
		krr2.setKorisnickiRacunXrolaId(3);
		uow.getKorisnickiRacunService().KreirajRoluZaKorisnika(krr2, false);
	}
	
	@Test
	public void testInsertRezervacija(){
		KorisnickiRacun kr2=uow.getKorisnickiRacunService().dajKorisnika(2);
		Klijent k1=uow.getKlijentiService().dajSveKlijente().get(0);
		//rezervacija
		Rezervacija rez=new Rezervacija(kr2,k1,new Date(2016,5,14,19,11,00),false,null,null,null,null);
		rez.setRezervacijaId(1);
		uow.getRezervacijaService().kreirajRezervaciju(rez, 2);
		Rezervacija rez1=new Rezervacija(kr2,k1,new Date(2016,5,13,19,22,00),false,null,null,null,null);
		rez1.setRezervacijaId(2);
		uow.getRezervacijaService().kreirajRezervaciju(rez1, 2);
		Soba s1=uow.getSobeService().VratiSobaId(1);
		Soba s2=uow.getSobeService().VratiSobaId(2);
		//rezervisani termin soba
		uow.getRezervacijaService().rezervisiSobu(s1, new Date(2016,7,1), new Date(2016,7,5), rez);
		uow.getRezervacijaService().rezervisiSobu(s2, new Date(2016,7,3), new Date(2016,7,12), rez1);
		
		//racun
		Racun rac=new Racun(rez,null,new Date(2016,5,14,19,11,00),null,500);
		rac.setRacunId(1);
		uow.getRacunService().kreirajRacun(rac);
		Racun rac1=new Racun(rez1,null,new Date(2016,5,13,19,22,00),null,700);
		rac1.setRacunId(2);
		uow.getRacunService().kreirajRacun(rac1);
	}
	
	@Test
	public void testDajRezervaciju() {
		
		uow= new UnitOfWork();
		boolean test=uow.getRezervacijaService().dajRezervaciju(1).getUkljucenPrevoz();
		assertNotEquals(true, test);
	}
	
	@Test
	public void testDajRezervacijuPoDatumuIKlijentu() {
		uow= new UnitOfWork();
		//@SuppressWarnings("deprecation")
		Date d= new Date(2016,5,14,19,11,00);
		Osoba o=uow.getOsobaService().dajOsobuPoJMBG("1234567894795").get(0);
		Klijent k= new Klijent(o);
		
		assertEquals(1, uow.getRezervacijaService().dajRezervacijuPoDatumuIKlijentu(d, k).getRezervacijaId().intValue());		
	}

	@Test
	public void testKreirajRezervacijuSaSobom() {
		
		uow= new UnitOfWork();
		//@SuppressWarnings("deprecation")
		Date d= new Date(2016,4,8);
		Date d1= new Date(2016,4,15);

		Soba s= uow.getSobeService().VratiSobaId(5);
		Osoba o=uow.getOsobaService().dajOsobuPoJMBG("1234567894489").get(0);
		Klijent k=uow.getKlijentiService().dajKlijentaPoOsobi(o).get(0);
		KorisnickiRacun kr=uow.getKorisnickiRacunService().dajKorisnika(2);
		Rezervacija r= new Rezervacija(kr, k, new Date(2016,1,1), true, null, null, null, null);
		assertTrue(uow.getRezervacijaService().kreirajRezervacijuSaSobom(r, s, d, d1, 1, 100));
	}

	@Test(expected= java.lang.NullPointerException.class)
	public void testRezervisiSobu() {
		Soba soba= new Soba();
		soba.setSobaId(16);
		soba.setBrojKreveta(3);
		soba.setCijenaNiska(50);
		soba.setCijenaVisoka(100);
		Rezervacija rezervacija= new Rezervacija();
		rezervacija.setRezervacijaId(3);
		Date d= new Date(2016,4,1);
		Date d1= new Date(2016,4,5);
		assertFalse(uow.getRezervacijaService().rezervisiSobu(soba, d, d1, rezervacija));
	}

	@Test
	public void testKreirajRezervaciju() {
		Rezervacija r= new Rezervacija();
		uow= new UnitOfWork();
		r.setRezervacijaId(16);
		assertFalse(uow.getRezervacijaService().kreirajRezervaciju(r, 2));
	}

	@Test
	public void testDajSveRezervacije() {
		uow= new UnitOfWork();
		assertEquals(0, uow.getRezervacijaService().dajSveRezervacije().size());
	}

	@Test
	public void testDajRezervisaneTermineZaRezervaciju() {
		uow= new UnitOfWork();
		assertEquals(1, uow.getRezervacijaService().dajRezervisaneTermineZaRezervaciju(1).size());
	}

	@Test
	public void testDajSobu() {
		uow= new UnitOfWork();
		assertEquals(5, uow.getRezervacijaService().dajSobu(1).getBrojKreveta().intValue());
	}

	@Test
	public void testPotvrdiRezervaciju() {
		uow= new UnitOfWork();
		assertFalse(uow.getRezervacijaService().potvrdiRezervaciju(1));
	}

}
