package ba.unsa.etf.si.app.iTravel.BLL;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.RezervisaniTerminSoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class SobaTest {

	public UnitOfWork uow;
	public Soba s;
	public Hotel h;
	public Destinacija d;

	@Test
	public void testUbaciSobuUBazu() {
		uow= new UnitOfWork();
		Destinacija d= new Destinacija("mjesto", true, null, null);
		uow.getDestinacijeService().KreirajDestinaciju(d);
		Hotel h= new Hotel(d, "adresa", "drzava", "grad", "061111111", new Date(2016,4,1), new Date(2016,7,1), new Date(2016,4,1), new Date(2016,4,1), "naziv",
				"lanac", 5, null, null);
		uow.getHoteliService().KreirajHotel(h);
		Soba s= new Soba(h, 2, "opis", 50, 30, null, null);
		uow.getSobeService().UbaciSobuUBazu(s);
		int sobe= uow.getIzvjestajService().ukupanBrojSobaNaRaspolaganju(h);
		uow= new UnitOfWork();
		uow.getSobeService().ObrisiJenduSobu(s);
		uow.getHoteliService().ObrisiJendaHotel(h);
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
		assertEquals(1, sobe);
		
	}
	
	
	@Test
	public void testObrisiSveSobe() {
		uow= new UnitOfWork();
		//uow.getSobeService().ObrisiSveSobe();
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

	@SuppressWarnings("deprecation")
	@Test
	public void testDajTermineZaSobu() {
		uow= new UnitOfWork();
		PocetneVrijednosti();
		Soba s2= new Soba(h, 5, "opis", 50, 30, null, null);
		uow.getSobeService().UbaciSobuUBazu(s2);
		ArrayList<Soba> sobe= new ArrayList<Soba>();
		sobe.add(s);
		sobe.add(s2);
		RezervisaniTerminSoba rt= new RezervisaniTerminSoba(null, s, new Date(2016,5,1), new Date(2016,5,10), true);
		uow.getRezervisaniTerminService().DodajRezervisaniTermin(rt);
		List<RezervisaniTerminSoba> rts= new ArrayList<RezervisaniTerminSoba>();
		rts= uow.getSobeService().dajTermineZaSobu(sobe);
		uow.getSobeService().ObrisiJenduSobu(s2);
		uow.getRezervisaniTerminService().ObrisiJenduSobuRezervacija(rt);
		Izbrisi(h,d,s);
		assertEquals(1, rts.size());	
	}


	public void Izbrisi(Hotel h, Destinacija d, Soba s)
	{
		uow= new UnitOfWork();
		uow.getSobeService().ObrisiJenduSobu(s);
		uow.getHoteliService().ObrisiJendaHotel(h);
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
	}
	
	@SuppressWarnings("deprecation")
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
