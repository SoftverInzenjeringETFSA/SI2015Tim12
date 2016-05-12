package ba.unsa.etf.si.app.iTravel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Klijent;

public class RezervacijeTest {

	public static UnitOfWork uow;
	
	@Test
	public void testDajSobeZaHotel() {
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
		assertEquals(5, uow.getRezervacijaService().dajSobeZaHotel(hotel.get(0)).size());
	}

	@Test
	public void testDajTermineZaSobu() {
		
		
		
	}

	@Test
	public void testDajSlobodneSobeZaHotel() {
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
		Date d= new Date(2016,3,1);
		Date d1= new Date(2016,4,31);
		//Nesto ne fercera
		//assertEquals(4, uow.getRezervacijaService().dajSlobodneSobeZaHotel(hotel.get(0), d, d1).size());
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
		
		//Klijent k= new Klijent(1,2);
	}

	@Test
	public void testKreirajRezervacijuSaSobom() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRezervisiSobu() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public void testKreirajRezervaciju() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDajSveRezervacije() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDajRezervisaneTermineZaRezervaciju() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDajSobu() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public void testPotvrdiRezervaciju() {
		//fail("Not yet implemented"); // TODO
	}

}
