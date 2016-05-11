package ba.unsa.etf.si.app.iTravel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;

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
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
	}

	@Test
	public void testDajSlobodneSobeZaHotel() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDajOsobuPoId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDajRezervaciju() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDajRezervacijuPoDatumuIKlijentu() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testKreirajRezervacijuSaSobom() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRezervisiSobu() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testKreirajRezervaciju() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDajSveRezervacije() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDajRezervisaneTermineZaRezervaciju() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDajSobu() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testPotvrdiRezervaciju() {
		fail("Not yet implemented"); // TODO
	}

}
