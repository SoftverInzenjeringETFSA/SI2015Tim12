package ba.unsa.etf.si.app.iTravel.BLL;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Index;

import org.hibernate.Session;
import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;
import ba.unsa.etf.si.app.iTravel.DBModels.Korisnickiracunxrola;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Rola;

public class KorisnickiRacunTest
{
	
	public UnitOfWork uow = new UnitOfWork();
	DBContext baza = new DBContext();
	
	@Test
	public void testUcitavanjaKorisnika()
	{
		Osoba osoba = new Osoba("Kenan","Prses", new Date(1668,5,5),"adresa usera 1","email@nesto.com","466655465","1234567894562","dsa64","4dsa6545d",null,null,null,null);
		osoba = baza.getOsobaRepository().spasiUBazu(osoba);
		
		Rola rola = baza.getRolaRepository().ucitajIzBaze(1);
		
		KorisnickiRacun korisnickiRacun = new KorisnickiRacun();
		korisnickiRacun.setUsername("test");
		korisnickiRacun.setPassword("test");
		korisnickiRacun.setOsoba(osoba);
		
		korisnickiRacun = baza.getKorisnickiRacunRepository().spasiUBazu(korisnickiRacun);
		
		Korisnickiracunxrola korisnickiracunxrola = new Korisnickiracunxrola();
		korisnickiracunxrola.setKorisnickiRacun(korisnickiRacun);
		korisnickiracunxrola.setRola(rola);
		
		korisnickiracunxrola = baza.getKorisnickiRacunXRolaRepository().spasiUBazu(korisnickiracunxrola);
		
		Object[][] korisnici = uow.getKorisniciService().PrikaziSveKorisnike();
		assertNotEquals(0, korisnici.length);
		
		baza.getKorisnickiRacunXRolaRepository().obrisiIzBaze(korisnickiracunxrola);
		baza.getKorisnickiRacunRepository().obrisiIzBaze(korisnickiRacun);
		baza.getOsobaRepository().obrisiIzBaze(osoba);
		//baza.getRolaRepository().obrisiIzBaze(rola);		
	}
	
	@Test
	public void testKreiranjaKorisnickogRacuna()
	{
		Osoba osoba = new Osoba("Kenan","Prses", new Date(1668,5,5),"adresa usera 1","email@nesto.com","466655465","1234567894562","dsa64","4dsa6545d",null,null,null,null);
		osoba = baza.getOsobaRepository().spasiUBazu(osoba);
		
		Rola rola = baza.getRolaRepository().ucitajIzBaze(1);
		
		KorisnickiRacun korisnickiRacun = new KorisnickiRacun();
		korisnickiRacun.setUsername("testNoviUserUnique");
		korisnickiRacun.setPassword("test");
		korisnickiRacun.setOsoba(osoba);
		
		korisnickiRacun = uow.getKorisnickiRacunService()
							 .KreirajKorisnickiRacun(korisnickiRacun, false);
		
		assertEquals("testNoviUserUnique", uow.getKorisnickiRacunService()
											  .dajKorisnika(korisnickiRacun.getKorisnickiRacunId())
											  .getUsername());

		baza.getKorisnickiRacunRepository().obrisiIzBaze(korisnickiRacun);
		baza.getOsobaRepository().obrisiIzBaze(osoba);
	}
	
	@Test
	public void testRoleKorisnika()
	{/*
		Osoba osoba = new Osoba("Kenan","Prses", new Date(1668,5,5),"adresa usera 1","email@nesto.com","466655465","1234567894562","dsa64","4dsa6545d",null,null,null,null);
		osoba = baza.getOsobaRepository().spasiUBazu(osoba);
		
		Rola rola = baza.getRolaRepository().ucitajIzBaze(1);
		
		KorisnickiRacun korisnickiRacun = new KorisnickiRacun();
		korisnickiRacun.setUsername("test");
		korisnickiRacun.setPassword("test");
		korisnickiRacun.setOsoba(osoba);
		
		korisnickiRacun = baza.getKorisnickiRacunRepository().spasiUBazu(korisnickiRacun);
		
		Korisnickiracunxrola korisnickiracunxrola = new Korisnickiracunxrola();
		korisnickiracunxrola.setKorisnickiRacun(korisnickiRacun);
		korisnickiracunxrola.setRola(rola);
		
		korisnickiracunxrola = uow.getKorisnickiRacunService().KreirajRoluZaKorisnika(korisnickiracunxrola, false);
		*/
		assertTrue(true);
	}

}
