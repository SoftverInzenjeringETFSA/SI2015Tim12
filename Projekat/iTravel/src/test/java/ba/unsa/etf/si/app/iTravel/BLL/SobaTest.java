package ba.unsa.etf.si.app.iTravel.BLL;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class SobaTest {

	public UnitOfWork uow;
	public Soba s;
	public Hotel h;
	public Destinacija d;

	@Test
	public void testUbaciSobuUBazu() {
		uow= new UnitOfWork();
		PocetneVrijednosti();
		int sobe= uow.getIzvjestajService().ukupanBrojSobaNaRaspolaganju(h);
		Izbrisi(h,d,s);
		assertEquals(1, sobe);
		
	}
	
	
//
//	@Test
//	public void testObrisiJenduSobu() {
//		uow= new UnitOfWork();
//		Destinacija d= new Destinacija("mjesto1", true, null, null);
//		uow.getDestinacijeService().KreirajDestinaciju(d);
//		Hotel h= new Hotel(d, "adresa2", "drzava", "grad", "061111111", new Date(2016,4,1), new Date(2016,7,1), new Date(2016,4,1), new Date(2016,4,1), "naziv",
//				"lanac", 5, null, null);
//		uow.getHoteliService().KreirajHotel(h);
//		Soba s= new Soba(h, 1, "opis", 50, 30, null, null);
//		uow.getSobeService().UbaciSobuUBazu(s);
//		Soba s1= new Soba(h, 1, "opis", 50, 30, null, null);
//		uow.getSobeService().UbaciSobuUBazu(s1);
//		int sobe= uow.getIzvjestajService().ukupanBrojSobaNaRaspolaganju(h);
//		uow.getSobeService().ObrisiJenduSobu(s);
//		int sobe2= uow.getIzvjestajService().ukupanBrojSobaNaRaspolaganju(h);
//		uow.getSobeService().ObrisiJenduSobu(s1);
//		uow.getHoteliService().ObrisiJendaHotel(h);
//		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
//		assertEquals(sobe, sobe2);
//		
//	}

	@Test
	public void testObrisiSveSobe() {
		//Ne diram zbog baze
	}

	@Test
	public void testVratiSobaId() {
		uow= new UnitOfWork();
		PocetneVrijednosti();
		List<Soba> sobe= new ArrayList<Soba>();
		sobe=uow.getSobeService().VratiSveSobe();
		int id= s.getSobaId();
		Izbrisi(h,d,s);
		assertEquals(sobe.get(sobe.size()-1).getSobaId().intValue(), id);
	}

	@Test
	public void testVratiSveSobe() {
		uow= new UnitOfWork();
		PocetneVrijednosti();
		List<Soba> sobe= new ArrayList<Soba>();
		sobe=uow.getSobeService().VratiSveSobe();
		
		Izbrisi(h,d,s);
		assertTrue(sobe.size()>0);
	}
	

	@Test
	public void testProvjeriPostojanjeSobaId() {
		uow= new UnitOfWork();
		PocetneVrijednosti();
		assertTrue(uow.getSobeService().ProvjeriPostojanjeSobaId(s.getSobaId()));
		Izbrisi(h,d,s);
	}

	@Test
	public void testProvjeriPostojanjeSoba() {
		uow= new UnitOfWork();
		PocetneVrijednosti();
		assertTrue(uow.getSobeService().ProvjeriPostojanjeSoba(s.getBrojKreveta()));
		Izbrisi(h,d,s);
	}

	@Test
	public void testAzurirajiliUbaciSobu() {
		uow= new UnitOfWork();
		PocetneVrijednosti();
		Soba s1= new Soba();
		s1=s;
		s1.setBrojKreveta(5);
		uow.getSobeService().AzurirajiliUbaciSobu(s1);
		List<Soba> sobe= new ArrayList<Soba>();
		sobe= uow.getSobeService().dajSobeZaHotel(h);
		assertEquals(5, sobe.get(sobe.size()-1).getBrojKreveta().intValue());
		Izbrisi(h,d,s);
	}

	@Test
	public void testDajSobeZaHotel() {
		uow= new UnitOfWork();
		PocetneVrijednosti();
		List<Soba> sobe= new ArrayList<Soba>();
		sobe= uow.getSobeService().dajSobeZaHotel(h);
		Izbrisi(h,d,s);
		assertTrue(sobe.size()>0);
	}

//	@Test
//	public void testDajTermineZaSobu() {
//		
//	}

//	@Test
//	public void testDajSlobodneSobeZaHotel() {
//		
//		
//	}
	

	public void Izbrisi(Hotel h, Destinacija d, Soba s)
	{
		uow= new UnitOfWork();
		uow.getSobeService().ObrisiJenduSobu(s);
		uow.getHoteliService().ObrisiJendaHotel(h);
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
	}
	
	public void PocetneVrijednosti()
	{
		d= new Destinacija("mjesto", true, null, null);
		uow.getDestinacijeService().KreirajDestinaciju(d);
		h= new Hotel(d, "adresa", "drzava", "grad", "061111111", new Date(2016,4,1), new Date(2016,7,1), new Date(2016,4,1), new Date(2016,4,1), "naziv",
				"lanac", 5, null, null);
		uow.getHoteliService().KreirajHotel(h);
		s= new Soba(h, 2, "opis", 50, 30, null, null);
		uow.getSobeService().UbaciSobuUBazu(s);
	}
}
