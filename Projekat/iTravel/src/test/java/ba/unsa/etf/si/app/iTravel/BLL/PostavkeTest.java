package ba.unsa.etf.si.app.iTravel.BLL;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Postavke;

public class PostavkeTest {
	public UnitOfWork uow;
	DBContext baza = new DBContext();
	
	@Test
	public void testModulOmoguce(){
		uow= new UnitOfWork();
		Postavke p=new Postavke(1001,"Hoteli", true);
		p= baza.getPostavkeRepository().spasiUBazu(p);
		boolean provjera=uow.getPostavkeService().modulOmogucen(1001);
		baza.getPostavkeRepository().obrisiIzBaze(p);
		assertTrue(provjera);
	}
	
	@Test
	public void testSpasiPromjenePostavki(){
		uow=new UnitOfWork();
		Postavke p=new Postavke(1401, "Hoteli", true);
		p= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p);
	    Postavke p1=new Postavke(1501, "Sobe", false);
		p1= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p1);
		Postavke p2=new Postavke(1601, "Rezervacije", true);
		p2= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p2);
		
		boolean[] nizPostavki = new boolean[3];
		nizPostavki[0] = false;
		nizPostavki[1] = true;
		nizPostavki[2] = false;
		uow.getPostavkeService().spasiPromjenePostavki(nizPostavki);
		boolean provjera=uow.getPostavkeService().dajSvePostavke()[0];
		
		baza.getPostavkeRepository().obrisiIzBaze(p);
		baza.getPostavkeRepository().obrisiIzBaze(p1);
		baza.getPostavkeRepository().obrisiIzBaze(p2);
		assertFalse(provjera);
		
	}
	
	@Test
	public void testDajSvePostavke(){
		uow=new UnitOfWork();
		Postavke p=new Postavke(1700, "Hoteli", true);
		p= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p);
	    Postavke p1=new Postavke(1800, "Sobe", false);
		p1= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p1);
		Postavke p2=new Postavke(1900, "Rezervacije", true);
		p2= uow.getPostavkeService().baza.getPostavkeRepository().spasiUBazu(p2);
		
		List<Postavke> lista=baza.getPostavkeRepository().ucitajSveIzBaze();
		baza.getPostavkeRepository().obrisiIzBaze(p);
		baza.getPostavkeRepository().obrisiIzBaze(p1);
		baza.getPostavkeRepository().obrisiIzBaze(p2);
		
		assertEquals(3,lista.size());
	}
	
}
