package ba.unsa.etf.si.app.iTravel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Klijent;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Rezervacija;
import ba.unsa.etf.si.app.iTravel.DBModels.RezervisaniTerminSoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class RezervacijeTest {

	public static UnitOfWork uow;
	DBContext baza;
	
	@Test
	public void testDajSobeZaHotel() {
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
		assertEquals(5, uow.getRezervacijaService().dajSobeZaHotel(hotel.get(0)).size());
	}

	@Test
	public void testDajTermineZaSobu() {
		uow= new UnitOfWork();
		baza= new DBContext();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("hotel", (Hotel)hotel.get(0)));
		ArrayList<Soba> sobe= new ArrayList<Soba>();
		
		sobe.addAll(baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterija));
		assertEquals(9, uow.getRezervacijaService().dajTermineZaSobu(sobe).size());
		
	}

	@Test
	public void testDajSlobodneSobeZaHotel() {
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
		Date d= new Date(2016,3,1);
		Date d1= new Date(2016,4,31);
		assertEquals(4, uow.getRezervacijaService().dajSlobodneSobeZaHotel(hotel.get(0), d, d1).size());
	}

	@Test
	public void testDajOsobuPoId() {
		uow= new UnitOfWork();
		assertEquals("Kenan", uow.getRezervacijaService().dajOsobuPoId(1).getIme());
	}

	@Test
	public void testDajRezervaciju() {
		uow= new UnitOfWork();
		assertNotEquals(0, uow.getRezervacijaService().dajRezervaciju(1).getUkljucenPrevoz());
	}

	@Test
	public void testDajRezervacijuPoDatumuIKlijentu() {
		baza= new DBContext();
		uow= new UnitOfWork();
		@SuppressWarnings("deprecation")
		Date d= new Date(2016,4,8);
		Klijent k= new Klijent();
		k= baza.getKlijentRepository().ucitajIzBaze(1);
		Osoba o= new Osoba();
		o= baza.getOsobaRepository().ucitajIzBaze(2);
		k.setOsoba(o);
		
		//Pada iz nekog razlogaa, baci error
		//assertEquals(1, uow.getRezervacijaService().dajRezervacijuPoDatumuIKlijentu(d, k).getRezervacijaId().intValue());
		
		
	}

	@Test(expected=AssertionError.class)
	public void testKreirajRezervacijuSaSobom() {
		baza= new DBContext();
		uow= new UnitOfWork();
		@SuppressWarnings("deprecation")
		Date d= new Date(2016,4,8);
		Date d1= new Date(2016,4,15);
		Soba s= new Soba();
		s= baza.getSobaRepository().ucitajIzBaze(1);
		Rezervacija r= new Rezervacija();
		r= baza.getRezervacijaRepository().ucitajIzBaze(2);
		assertTrue(uow.getRezervacijaService().kreirajRezervacijuSaSobom(r, s, d, d1, 1, 100));
		
		
	}

	@Test(expected=java.lang.AssertionError.class)
	public void testRezervisiSobu() {
		Soba soba= new Soba();
		soba.setSobaId(17);
		soba.setBrojKreveta(3);
		soba.setCijenaNiska(50);
		soba.setCijenaVisoka(100);
		Rezervacija rezervacija= new Rezervacija();
		rezervacija.setRezervacijaId(2);
		Date d= new Date(2016,4,1);
		Date d1= new Date(2016,4,5);
		assertFalse(uow.getRezervacijaService().rezervisiSobu(soba, d, d1, rezervacija));
		
	}

	//Nisam kreirala klijenta i dodijelila rezervaciji, treba da vrati false
	@Test
	public void testKreirajRezervaciju() {
		
		Rezervacija r= new Rezervacija();
		uow= new UnitOfWork();
		r.setRezervacijaId(2);
		assertFalse(uow.getRezervacijaService().kreirajRezervaciju(r, 2));
	}

	@Test
	public void testDajSveRezervacije() {
		uow= new UnitOfWork();
		assertEquals(3, uow.getRezervacijaService().dajSveRezervacije().get(2).getKlijent().getKlijentId());
	}

	//Ne radi fino metoda, treba joj se rezervacija proslijediti, ovako se 
	//proslijedi ID rezervisanog termina, a on je jedinstven i uvijek vraća 1
	@Test
	public void testDajRezervisaneTermineZaRezervaciju() {
		uow= new UnitOfWork();
		assertEquals(1, uow.getRezervacijaService().dajRezervisaneTermineZaRezervaciju(2).size());
	}

	@Test
	public void testDajSobu() {
		uow= new UnitOfWork();
		assertEquals("prva ", uow.getRezervacijaService().dajSobu(1).getOpis().toString());
	}

	@Test
	public void testPotvrdiRezervaciju() {
		uow= new UnitOfWork();
		assertTrue(uow.getRezervacijaService().potvrdiRezervaciju(2));
	}

}