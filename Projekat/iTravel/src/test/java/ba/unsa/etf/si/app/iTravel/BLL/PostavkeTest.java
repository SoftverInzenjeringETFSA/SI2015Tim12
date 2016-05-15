package ba.unsa.etf.si.app.iTravel.BLL;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Postavke;

public class PostavkeTest {
	public UnitOfWork uow=new UnitOfWork();
	DBContext baza = new DBContext();
	
	@Test
	public void testModulOmoguce(){
		
		Postavke p=new Postavke(1001,"Hoteli", true);
		p= baza.getPostavkeRepository().spasiUBazu(p);
		boolean provjera=uow.getPostavkeService().modulOmogucen(1001);
		baza.getPostavkeRepository().obrisiIzBaze(p);
		assertTrue(provjera);
	}
	
//	@Test
//	public void testSpasiPromjenePostavki(){
//		Random r= new Random();
//		Integer i= r.nextInt(100);
//		Postavke p=new Postavke(i+123, "Hoteli", true);
//		baza.getPostavkeRepository().spasiUBazu(p);
//	    Postavke p1=new Postavke(i+223, "Sobe", false);
//		baza.getPostavkeRepository().spasiUBazu(p1);
//		Postavke p2=new Postavke(i+323, "Rezervacije", true);
//		baza.getPostavkeRepository().spasiUBazu(p2);
//		
//		boolean[] nizPostavki = new boolean[3];
//		nizPostavki[0] = false;
//		nizPostavki[1] = true;
//		nizPostavki[2] = false;
//		boolean hocel= uow.getPostavkeService().spasiPromjenePostavki(nizPostavki);
//		boolean provjera=uow.getPostavkeService().dajSvePostavke()[0];
//		
//		baza.getPostavkeRepository().obrisiIzBaze(p2);
//		baza.getPostavkeRepository().obrisiIzBaze(p1);
//		baza.getPostavkeRepository().obrisiIzBaze(p);
//		assertFalse(provjera);
//		
//	}
	
	@Test
	public void testDajSvePostavke(){
		
		Postavke p=new Postavke(1700, "Hoteli", true);
		baza.getPostavkeRepository().spasiUBazu(p);
	    Postavke p1=new Postavke(1800, "Sobe", false);
		baza.getPostavkeRepository().spasiUBazu(p1);
		Postavke p2=new Postavke(1900, "Rezervacije", true);
		baza.getPostavkeRepository().spasiUBazu(p2);
		
		List<Postavke> lista=baza.getPostavkeRepository().ucitajSveIzBaze();
		baza.getPostavkeRepository().obrisiIzBaze(p);
		baza.getPostavkeRepository().obrisiIzBaze(p1);
		baza.getPostavkeRepository().obrisiIzBaze(p2);
		
		assertEquals("Rezervacije",lista.get(lista.size()-1).getNazivPostavke());
	}
	
}
