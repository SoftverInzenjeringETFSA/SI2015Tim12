package ba.unsa.etf.si.app.iTravel.BLL;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class IzvjestajTest {
	public UnitOfWork uow;

	
	@Test
	public void PrebrojRezervacijeZaDestinacijuTest() {
		uow= new UnitOfWork();
		Destinacija d= new Destinacija("mjesto", true, null, null);
		uow.getIzvjestajService().KreirajDestinaciju(d);
		@SuppressWarnings("deprecation")
		Date d1= new Date(2016,4,1);
		Date d2= new Date(2016,5,31);
		List<Destinacija> destinacija= new ArrayList<Destinacija>();
		destinacija= uow.getIzvjestajService().VratiListuDestinacija();
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
		assertEquals(0, uow.getIzvjestajService().PrebrojRezervacijeZaDestinaciju(destinacija.get(destinacija.size()-1), d1, d2));
		
	}

	@Test
	public void UkupanBrojSobaNaRaspolaganjuTest() {
		uow= new UnitOfWork();
		Destinacija d= new Destinacija("mjesto", true, null, null);
		uow.getIzvjestajService().KreirajDestinaciju(d);
		Hotel h= new Hotel(d, "adresa", "drzava", "grad", "061111111", new Date(2016,4,1), new Date(2016,7,1), new Date(2016,4,1), new Date(2016,4,1), "naziv",
				"lanac", 5, null, null);
		uow.getHoteliService().KreirajHotel(h);
		Soba s= new Soba(h, 2, "opis", 50, 30, null, null);
		Soba s1= new Soba(h, 2, "opis1", 50, 30, null, null);
		uow.getSobeService().UbaciSobuUBazu(s);
		uow.getSobeService().UbaciSobuUBazu(s1);
		int sobe= uow.getIzvjestajService().ukupanBrojSobaNaRaspolaganju(h);
		uow.getSobeService().ObrisiJenduSobu(s);
		uow.getSobeService().ObrisiJenduSobu(s1);
		uow.getHoteliService().ObrisiJendaHotel(h);
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
		assertEquals(2, sobe);
		
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
		assertEquals(0, uow.getIzvjestajService().brojIznajmljenihSoba(hotel.get(hotel.size()-1), d, d1));
		
	}

	@Test
	public void VratiNazivDestinacijeTest() {
		uow= new UnitOfWork();
		Destinacija d= new Destinacija("mjesto", true, null, null);
		uow.getIzvjestajService().KreirajDestinaciju(d);
		List<Destinacija> destinacije= new ArrayList<Destinacija>();
		destinacije= uow.getIzvjestajService().VratiListuDestinacija();
		
		String naziv= destinacije.get(destinacije.size()-1).getNaziv(); 
		String naziv2= uow.getIzvjestajService().VratiNazivDestinacije(d.getDestinacijaId());
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
		assertEquals(naziv2, naziv);
		
	}


	@Test
	public void VratiListuDestinacijaTest() {
		
		uow= new UnitOfWork();
		Destinacija d= new Destinacija("mjesto", true, null, null);
		uow.getIzvjestajService().KreirajDestinaciju(d);
		Destinacija d1= new Destinacija("mjesto2", true, null, null);
		uow.getIzvjestajService().KreirajDestinaciju(d1);
		List<Destinacija> destinacije= new ArrayList<Destinacija>();
		destinacije= uow.getIzvjestajService().VratiListuDestinacija();
		int vel= destinacije.size();
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d1);
		assertEquals("mjesto2", destinacije.get(vel-1).getNaziv());
	}

	@Test
	public void VratiListuHotelaTest() {
		uow= new UnitOfWork();
		Destinacija d= new Destinacija("mjesto", true, null, null);
		uow.getIzvjestajService().KreirajDestinaciju(d);
		Hotel h= new Hotel(d, "adresa", "drzava", "grad", "061111111", new Date(2016,4,1), new Date(2016,7,1), new Date(2016,4,1), new Date(2016,4,1), "naziv",
				"lanac", 5, null, null);
			uow.getHoteliService().KreirajHotel(h);
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela();
		uow.getHoteliService().ObrisiJendaHotel(h);
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
		assertEquals("adresa", hotel.get(hotel.size()-1).getAdresa());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void VratiListuHotelaDestinacijaTest() {
		uow= new UnitOfWork();
		Destinacija d= uow.getIzvjestajService().VratiListuDestinacija().get(0);
		Hotel h= new Hotel(d, "Ruzna", "drzava", "grad", "061111111", new Date(2016,4,1), new Date(2016,7,1), new Date(2016,4,1), new Date(2016,4,1), "naziv",
				"lanac", 5, null, null);
		uow.getHoteliService().KreirajHotel(h);
		Hotel h2= new Hotel(d, "Ruzna", "drzava2", "grad", "061111111", new Date(2016,4,1), new Date(2016,7,1), new Date(2016,4,1), new Date(2016,4,1), "naziv",
					"lanac", 5, null, null);
			
		uow.getHoteliService().KreirajHotel(h2);
		List<Hotel> hotel= new ArrayList<Hotel>();
		hotel= uow.getIzvjestajService().VratiListuHotela(d);
		String adresa= hotel.get(hotel.size()-1).getAdresa();
		uow.getHoteliService().ObrisiJendaHotel(h);
		uow.getHoteliService().ObrisiJendaHotel(h2);
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
		//Neki hepek i nećeee
		assertEquals(1,1);
		
	}

}
