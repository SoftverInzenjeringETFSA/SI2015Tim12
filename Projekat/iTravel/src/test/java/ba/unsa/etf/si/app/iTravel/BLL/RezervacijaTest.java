package ba.unsa.etf.si.app.iTravel.BLL;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Klijent;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;
import ba.unsa.etf.si.app.iTravel.DBModels.Korisnickiracunxrola;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Rezervacija;
import ba.unsa.etf.si.app.iTravel.DBModels.RezervisaniTerminSoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Rola;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class RezervacijaTest {

	public UnitOfWork uow = new UnitOfWork();
	public Rezervacija rezervacija;
	public Osoba osoba;
	public Rola rola;
	public Klijent klijent;
	public KorisnickiRacun korisnickiracun;
	public Hotel hotel;
	public Soba soba;
	public Destinacija destinacija;
	public Korisnickiracunxrola korisnickaRola;
	public Destinacija d;
	public Hotel h;
	public Soba s;
	
	@SuppressWarnings("deprecation")
	public void PostaviParametre()
	{
		//uow= new UnitOfWork();
		osoba=new Osoba("Kenan","Prses", new Date(1994,5,5),"adresa usera 1","email@nesto.com","466655465","1234567894562","dsa64","4dsa6545d",null,null,null,null);
		osoba = uow.getOsobaService().KreirajOsobu(osoba, false);
		klijent = new Klijent(osoba);
		klijent = uow.getKlijentiService().KreirajKlijenta(klijent);
		korisnickiracun = new KorisnickiRacun(osoba,"kagent","Sitim12",null,null,null,null);
		korisnickiracun = uow.getKorisnickiRacunService().KreirajKorisnickiRacun(korisnickiracun, false);
		Rola rola = uow.getRolaService().dajRolu(1);
		korisnickaRola = new Korisnickiracunxrola(rola,korisnickiracun);
		korisnickaRola = uow.getKorisnickiRacunService().KreirajRoluZaKorisnika(korisnickaRola, false);
		
		rezervacija=new Rezervacija(korisnickiracun,klijent,new Date(2016,5,14),false,null,null,null,null);
		uow.getRezervacijaService().kreirajRezervaciju(rezervacija, rola.getRolaId());
	}
	
	public void PostaviZaSobu()
	{
		d= new Destinacija("mjesto", true, null, null);
		uow.getDestinacijeService().KreirajDestinaciju(d);
		h= new Hotel(d, "adresa", "drzava", "grad", "061111111", new Date(2016,4,1), new Date(2016,7,1), new Date(2016,4,1), new Date(2016,4,1), "naziv",
				"lanac", 5, null, null);
		uow.getHoteliService().KreirajHotel(h);
		s= new Soba(h, 2, "opis", 50, 30, null, null);
		uow.getSobeService().UbaciSobuUBazu(s);
	}
	
	public void ObrisiSve()
	{
		uow.getRezervacijaService().obrisiRezervaciju(rezervacija.getRezervacijaId());
		uow.getKorisnickiRacunService().obrisiKorisnika(korisnickaRola.getKorisnickiRacunXrolaId());
		//uow.getRolaService().ObrisiJednuRolu(rola);
		uow.getKorisnickiRacunService().obrisiKorisnika(korisnickiracun.getKorisnickiRacunId());
		uow.getKlijentiService().ObrisiJednogKlijenta(klijent);
		uow.getOsobaService().ObrisiJednuOsobu(osoba);
	}
	
	public void ObrisiSveSoba()
	{
		uow.getSobeService().ObrisiJenduSobu(s);
		uow.getHoteliService().ObrisiJendaHotel(h);
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
	}
	
	@Test
	public void testDajRezervaciju() {
		//uow= new UnitOfWork();
		PostaviParametre();
		assertEquals(false, uow.getRezervacijaService().dajRezervaciju(rezervacija.getRezervacijaId()).getUkljucenPrevoz());
		ObrisiSve();
	}
	
	

	@Test
	public void testKreirajRezervacijuSaSobom() {
		
		//uow= new UnitOfWork();
		PostaviParametre();
		PostaviZaSobu();
		
		Date d1= new Date(2016,4,8);
		Date d2= new Date(2016,4,15);

		Rezervacija r= new Rezervacija(korisnickiracun, klijent, new Date(2016,1,1), true, null, null, null, null);

		assertTrue(uow.getRezervacijaService().kreirajRezervacijuSaSobom(r, s, d1, d2, 1, 100)>0);
		ObrisiSve();
		ObrisiSveSoba();
		
		
	}

	@Test
	public void testRezervisiSobu() {
		//uow= new UnitOfWork();
		PostaviParametre();
		PostaviZaSobu();
		Date d= new Date(2016,4,1);
		Date d1= new Date(2016,4,5);
		assertFalse(uow.getRezervacijaService().rezervisiSobu(soba, d, d1, rezervacija));
		ObrisiSve();
		ObrisiSveSoba();
	}

//	@Test
//	public void testKreirajRezervaciju() {
//		uow= new UnitOfWork();
//		PostaviParametre();
//		assertFalse(uow.getRezervacijaService().kreirajRezervaciju(rezervacija, 1));
//		ObrisiSve();
//	}
//	
//	@Test
//	public void testdajRezervacijuPoRezervaciji() {
//		uow= new UnitOfWork();
//		PostaviParametre();
//		//assertEquals(uow.getRezervacijaService());
//		ObrisiSve();
//	}


	@Test
	public void testDajSveRezervacije() {
		//uow= new UnitOfWork();
		PostaviParametre();
		assertTrue(uow.getRezervacijaService().dajSveRezervacije().size()>0);
		ObrisiSve();
	}

	@Test
	public void testDajRezervisaneTermineZaRezervaciju() {
		//uow= new UnitOfWork();
		PostaviParametre();
		PostaviZaSobu();
		RezervisaniTerminSoba rts= new RezervisaniTerminSoba(rezervacija, soba, new Date(2016,5,5), new Date(2016,5,15), true);
		uow.getRezervisaniTerminService().DodajRezervisaniTermin(rts);
		assertEquals(1, uow.getRezervacijaService().dajRezervisaneTermineZaRezervaciju(rezervacija.getRezervacijaId()).size());
		uow.getRezervisaniTerminService().ObrisiJenduSobuRezervacija(rts);
		ObrisiSve();
		ObrisiSveSoba();
	}

	@Test
	public void testPotvrdiRezervaciju() {
		//uow= new UnitOfWork();
		assertFalse(uow.getRezervacijaService().potvrdiRezervaciju(1));
	}
	
//	@Test
//	public void testObrisiSveRezervacije(){
//		uow= new UnitOfWork();
//		PostaviParametre();
//		uow.getRezervacijaService().obrisiSveRezervacije();
//		assertTrue(uow.getRezervacijaService().dajSveRezervacije().size()==0);
//		ObrisiSve();
//	}
	
}
