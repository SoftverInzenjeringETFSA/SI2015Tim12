package ba.unsa.etf.si.app.iTravel;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
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
	public void PrebrojRezervacijeZaDestinacijuTest() {
		uow= new UnitOfWork();
		List<Destinacija> destinacije= new ArrayList<Destinacija>();
		destinacije= uow.getIzvjestajService().VratiListuDestinacija();
		@SuppressWarnings("deprecation")
		Date d= new Date(2016,4,1);
		Date d1= new Date(2016,5,31);
		assertNotEquals(1, uow.getIzvjestajService().PrebrojRezervacijeZaDestinaciju(destinacije.get(0), d, d1));
		
	}

	@Test
	public void UkupanBrojSobaNaRaspolaganjuTest() {
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
		assertEquals(5, uow.getIzvjestajService().ukupanBrojSobaNaRaspolaganju(hotel.get(0)));
		assertEquals(4, uow.getIzvjestajService().ukupanBrojSobaNaRaspolaganju(hotel.get(3)));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void BrojIznajmljenihSobaTest() {
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
		
		Date d= new Date(2016,3,1);
		Date d1= new Date(2016,4,31);
		//Nesto ne fercera
		assertEquals(0, uow.getIzvjestajService().brojIznajmljenihSoba(hotel.get(4), d, d1));
		
	}

	@Test
	public void VratiNazivDestinacijeTest() {
		uow= new UnitOfWork();
		List<Destinacija> destinacije= new ArrayList<Destinacija>();
		destinacije= uow.getIzvjestajService().VratiListuDestinacija();
		assertEquals("Dubai", destinacije.get(0).getNaziv());
		assertEquals(uow.getIzvjestajService().VratiNazivDestinacije(3), destinacije.get(2).getNaziv());
	}

	@Test
	public void VratiNazivHotelaTest() {
		
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
		assertEquals(uow.getIzvjestajService().VratiNazivHotela(1), hotel.get(0).getNaziv());
		assertEquals(uow.getIzvjestajService().VratiNazivHotela(3), hotel.get(2).getNaziv());
	}

	@Test
	public void VratiListuDestinacijaTest() {
		
		uow= new UnitOfWork();
		List<Destinacija> destinacije= new ArrayList<Destinacija>();
		destinacije= uow.getIzvjestajService().VratiListuDestinacija();
		assertEquals("Dubai", destinacije.get(0).getNaziv());
		assertEquals("Pariz", destinacije.get(2).getNaziv());
	}

	@Test
	public void VratiListuHotelaTest() {
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
	
		assertEquals("Holiday", hotel.get(0).getNaziv());
		assertEquals("Twist Tower", hotel.get(2).getNaziv());
		assertEquals("Love", hotel.get(5).getNaziv());
		//Samo zbog commit
	}

	@Test
	public void VratiListuHotelaDestinacijaTest() {
		uow= new UnitOfWork();
		List<Hotel> hotel= new ArrayList<Hotel>();
		List<Destinacija> destinacije= new ArrayList<Destinacija>();
		destinacije= uow.getIzvjestajService().VratiListuDestinacija();
		
		hotel= uow.getIzvjestajService().VratiListuHotela(destinacije.get(0));
		assertEquals("Harem", hotel.get(0).getNaziv());
		assertEquals("HaremVeliki", hotel.get(1).getNaziv());
		
	}

}
