package ba.unsa.etf.si.app.iTravel.BLL;

import static org.junit.Assert.*;

import org.junit.Test;

import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;

public class DestinacijaTest {

	public UnitOfWork uow;
	@Test
	public void testVratiDestinaciju() {
		uow= new UnitOfWork();
		Destinacija d= new Destinacija("mjesto", true, null, null);
		uow.getDestinacijeService().KreirajDestinaciju(d);
		assertEquals(d.getOmogucenPrevoz(), uow.getDestinacijeService().VratiDestinaciju(d.getNaziv()).getOmogucenPrevoz());
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
	}

	@Test
	public void testVratiDestinacijuPoId() {
		uow= new UnitOfWork();
		Destinacija d= new Destinacija("mjesto", true, null, null);
		uow.getDestinacijeService().KreirajDestinaciju(d);
		assertTrue(uow.getDestinacijeService().VratiDestinacijuPoId(d.getDestinacijaId()).getOmogucenPrevoz());
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
	}

	

	@Test
	public void testDajSveDestinacije() {
		uow= new UnitOfWork();
		Destinacija d= new Destinacija("mjesto", true, null, null);
		uow.getDestinacijeService().KreirajDestinaciju(d);
		assertTrue(uow.getDestinacijeService().DajSveDestinacije().size()>0);
		uow.getDestinacijeService().ObrisiJednuDestinaciju(d);
	}

	


}
