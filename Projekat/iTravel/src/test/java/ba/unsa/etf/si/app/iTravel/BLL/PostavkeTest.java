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
		Postavke p=new Postavke(1,"Hoteli", true);
		p= baza.getPostavkeRepository().spasiUBazu(p);
		assertTrue(uow.getPostavkeService().modulOmogucen(1));
		baza.getPostavkeRepository().obrisiIzBaze(p);
	}
	
	@Test
	public void testSpasiPromjenePostavki(){
		uow=new UnitOfWork();
		Postavke p=new Postavke(1, "Hoteli", true);
		p= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p);
		p=new Postavke(2, "Sobe", false);
		p= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p);
		p=new Postavke(3, "Rezervacije", true);
		p= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p);
		
		boolean[] nizPostavki = new boolean[3];
		nizPostavki[0] = false;
		nizPostavki[1] = true;
		nizPostavki[2] = false;
		uow.getPostavkeService().spasiPromjenePostavki(nizPostavki);
		assertFalse(uow.getPostavkeService().dajSvePostavke()[1]);
		uow.getPostavkeService().baza.getPostavkeRepository().obrisiSveIzBaze();
	}
	
	@Test
	public void testDajSvePostavke(){
		uow=new UnitOfWork();
		Postavke p=new Postavke(1, "Hoteli", true);
		p= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p);
		p=new Postavke(2, "Sobe", false);
		p= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p);
		p=new Postavke(3, "Rezervacije", true);
		p= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p);
		
		assertEquals(3,uow.getPostavkeService().dajSvePostavke());
		uow.getPostavkeService().baza.getPostavkeRepository().obrisiSveIzBaze();
	}
	
}
