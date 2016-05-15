package ba.unsa.etf.si.app.iTravel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;

public class HoteliTest {
	
	public UnitOfWork uow;
	DBContext baza = new DBContext();
	@Test
	public void testKreirajHotel() {
		uow= new UnitOfWork();
		ArrayList<Hotel> hoteli1= new ArrayList<Hotel>();
		hoteli1 = uow.getHoteliService().VratiSveHotele();
		Hotel h= kreirajH(1);
		uow.getHoteliService().KreirajHotel(h);
		ArrayList<Hotel> hoteli2= new ArrayList<Hotel>();
		hoteli2 =  uow.getHoteliService().VratiSveHotele();
		baza.getHoteliRepo().obrisiIzBaze(h);
		assertEquals(hoteli1.size()+1,hoteli2.size());
		
	}

	@Test
	public void testVratiHotelId() {
		uow= new UnitOfWork();
		Hotel h= kreirajH(1);
		
		uow.getHoteliService().KreirajHotel(h);
		Hotel h1= uow.getHoteliService().VratiHotelId(h.getHotelId());
		baza.getHoteliRepo().obrisiIzBaze(h);
		assertEquals(h1.getAdresa(), "adresa");
		
	}

	@Test
	public void testVratiSveHotele() {
		uow= new UnitOfWork();
		Hotel h= kreirajH(1);
		uow.getHoteliService().KreirajHotel(h);
		ArrayList<Hotel> hoteli1= new ArrayList<Hotel>();
		hoteli1 = uow.getHoteliService().VratiSveHotele();
		int index= hoteli1.size();
		baza.getHoteliRepo().obrisiIzBaze(h);
		assertEquals("adresa", hoteli1.get(index-1).getAdresa());
		
	}

	@Test
	public void testVrtatiHotelPoNazivu() {
		uow= new UnitOfWork();
		Hotel h= kreirajH(1);
		uow.getHoteliService().KreirajHotel(h);
		Hotel h1= uow.getHoteliService().VrtatiHotelPoNazivu("naziv", "adresa");
		baza.getHoteliRepo().obrisiIzBaze(h);
		assertEquals("grad", h1.getGrad());
	}

	@Test
	public void testAzurirajHotel() {
		uow= new UnitOfWork();
		Hotel h= kreirajH(1);
		uow.getHoteliService().KreirajHotel(h);
		Hotel h1= kreirajH(2);
		uow.getHoteliService().AzurirajHotel(h1);
		Hotel h3= uow.getHoteliService().VrtatiHotelPoNazivu("naziv2", "adresa1");
		baza.getHoteliRepo().obrisiIzBaze(h);
		baza.getHoteliRepo().obrisiIzBaze(h1);
		assertEquals("061111222", h3.getBrojTelefona());
		
	}
	
	@Test
	public void testObrisiJendaHotel() {
		uow= new UnitOfWork();
		
		Hotel h= kreirajH(1);
		Hotel h1= kreirajH(2);
		uow.getHoteliService().KreirajHotel(h);
		uow.getHoteliService().KreirajHotel(h1);
		ArrayList<Hotel> hoteli1= new ArrayList<Hotel>();
		hoteli1 = uow.getHoteliService().VratiSveHotele();
		baza.getHoteliRepo().obrisiIzBaze(h);
		ArrayList<Hotel> hoteli2= new ArrayList<Hotel>();
		hoteli2 = uow.getHoteliService().VratiSveHotele();
		baza.getHoteliRepo().obrisiIzBaze(h1);
		
		assertEquals(hoteli1.size(), hoteli2.size()+1);
	}

	@Test
	public void testObrisiSveHotele() {
		//Provjeriti za jenkins
	}

	@Test
	public void testProvjeriPostojanjeHotelaId() {
		uow= new UnitOfWork();
		Hotel h= kreirajH(1);
		uow.getHoteliService().KreirajHotel(h);
		boolean postoji= uow.getHoteliService().ProvjeriPostojanjeHotelaId(h.getHotelId());
		baza.getHoteliRepo().obrisiIzBaze(h);
		assertTrue(postoji);
	}

	@Test
	public void testProvjeriPostojanjeHotela() {
		uow= new UnitOfWork();
		Hotel h= kreirajH(1);
		uow.getHoteliService().KreirajHotel(h);
		boolean postoji= uow.getHoteliService().ProvjeriPostojanjeHotela(h.getNaziv(), h.getAdresa());
		baza.getHoteliRepo().obrisiIzBaze(h);
		assertTrue(postoji);
	}

	@Test
	public void testVratiHotelZaDestinaciju() {
		uow= new UnitOfWork();
		Hotel h= kreirajH(1);
		uow.getHoteliService().KreirajHotel(h);
		ArrayList<Hotel> hoteli= new ArrayList<Hotel>();
		hoteli= uow.getHoteliService().VratiHotelZaDestinaciju(h.getDestinacija().getNaziv());
		baza.getHoteliRepo().obrisiIzBaze(h);
		assertEquals("adresa", hoteli.get(hoteli.size()-1).getAdresa());
	}
	
	public Hotel kreirajH(int id)
	{
		Destinacija d= uow.getIzvjestajService().VratiListuDestinacija().get(0);
		if(id==1) return new Hotel(d, "adresa", "drzava", "grad", "061111111", new Date(2016,4,1), new Date(2016,7,1), new Date(2016,4,1), new Date(2016,4,1), "naziv",
				"lanac", 5, null, null);
		
		return new Hotel(d, "adresa1", "drzava", "grad", "061111222", new Date(2016,4,1), new Date(2016,7,1), new Date(2016,4,1), new Date(2016,4,1), "naziv2",
				"lanac", 5, null, null);
	}

}
