package ba.unsa.etf.si.app.iTravel.BLL;

import static org.junit.Assert.*;

import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Postavke;

public class PostavkeTest {
	public UnitOfWork uow;
	DBContext baza = new DBContext();
	
	@Test
	public void testModulOmoguce(){
		uow= new UnitOfWork();
		Postavke p=new Postavke(10,"Hoteli", true);
		p= baza.getPostavkeRepository().spasiUBazu(p);
		assertTrue(uow.getPostavkeService().modulOmogucen(1));
		baza.getPostavkeRepository().obrisiIzBaze(p);
	}
	
	@Test
	public void testSpasiPromjenePostavki(){
		uow=new UnitOfWork();
		Postavke p=new Postavke(14, "Hoteli", true);
		p= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p);
	    Postavke p1=new Postavke(15, "Sobe", false);
		p1= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p1);
		Postavke p2=new Postavke(16, "Rezervacije", true);
		p2= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p2);
		
		boolean[] nizPostavki = new boolean[3];
		nizPostavki[0] = false;
		nizPostavki[1] = true;
		nizPostavki[2] = false;
		uow.getPostavkeService().spasiPromjenePostavki(nizPostavki);
		assertFalse(uow.getPostavkeService().dajSvePostavke()[1]);
		baza.getPostavkeRepository().obrisiIzBaze(p);
		baza.getPostavkeRepository().obrisiIzBaze(p1);
		baza.getPostavkeRepository().obrisiIzBaze(p2);
	}
	
	@Test
	public void testDajSvePostavke(){
		uow=new UnitOfWork();
		Postavke p=new Postavke(14, "Hoteli", true);
		p= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p);
	    Postavke p1=new Postavke(15, "Sobe", false);
		p1= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p1);
		Postavke p2=new Postavke(16, "Rezervacije", true);
		p2= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p2);
		
		assertEquals(3,uow.getPostavkeService().dajSvePostavke());
		baza.getPostavkeRepository().obrisiIzBaze(p);
		baza.getPostavkeRepository().obrisiIzBaze(p1);
		baza.getPostavkeRepository().obrisiIzBaze(p2);
	}
	
}
