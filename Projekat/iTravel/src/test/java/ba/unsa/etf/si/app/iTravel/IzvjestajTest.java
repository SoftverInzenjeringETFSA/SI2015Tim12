package ba.unsa.etf.si.app.iTravel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;

public class IzvjestajTest {
	public static UnitOfWork uow;

	
	@Test
	public void testPrebrojRezervacijeZaDestinacijuTest() {
		uow= new UnitOfWork();
		List<Destinacija> destinacije= new ArrayList<Destinacija>();
		destinacije= uow.getIzvjestajService().VratiListuDestinacija();
		@SuppressWarnings("deprecation")
		Date d= new Date(2016,5,1);
		Date d1= new Date(2016,5,31);
		assertNotEquals(1, uow.getIzvjestajService().PrebrojRezervacijeZaDestinaciju(destinacije.get(0), d, d1));
		
	}

	@Test
	public void testUkupanBrojSobaNaRaspolaganju() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testBrojIznajmljenihSoba() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVratiNazivDestinacije() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testVratiNazivHotela() {
		
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
		assertEquals(uow.getIzvjestajService().VratiNazivHotela(0), hotel.get(0).getNaziv());
		assertEquals(uow.getIzvjestajService().VratiNazivHotela(2), hotel.get(2).getNaziv());
	}

	@Test
	public void testVratiListuDestinacija() {
		
		uow= new UnitOfWork();
		List<Destinacija> destinacije= new ArrayList<Destinacija>();
		destinacije= uow.getIzvjestajService().VratiListuDestinacija();
		assertEquals("Dubai", destinacije.get(0).getNaziv());
		assertEquals("Pariz", destinacije.get(2).getNaziv());
	}

	@Test
	public void testVratiListuHotela() {
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
	
		assertEquals("Holiday", hotel.get(0).getNaziv());
		assertEquals("Twist Tower", hotel.get(2).getNaziv());
		assertEquals("Love", hotel.get(5).getNaziv());
		
	}

	@Test
	public void testVratiListuHotelaDestinacija() {
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		List<Destinacija> destinacije= new ArrayList<Destinacija>();
		destinacije= uow.getIzvjestajService().VratiListuDestinacija();
		
		hotel= uow.getIzvjestajService().VratiListuHotela(destinacije.get(0));
		assertEquals("Harem", hotel.get(0).getNaziv());
		assertEquals("HaremVeliki", hotel.get(1).getNaziv());
		
	}

}
