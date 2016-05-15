package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.RezervisaniTerminSoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class SobeService
{
	DBContext baza = new DBContext();
	
	public SobeService()
	{
		
	}
	
	public void UbaciSobuUBazu(Soba soba) {
		
		baza.getSobaRepository().spasiUBazu(soba);

	}
	
	public void ObrisiJenduSobu(Soba soba) {

		baza.getSobaRepository().obrisiIzBaze(soba);
	}

	public void ObrisiSveSobe()
	{

		baza.getSobaRepository().obrisiSveIzBaze();
	}
	
	public Soba VratiSobaId(int id) {
		Soba sobe= new Soba();

	sobe = baza.getSobaRepository().ucitajIzBaze(id);

		return sobe;

	}

	public ArrayList<Soba> VratiSveSobe() {
		ArrayList<Soba> sobe = new ArrayList<Soba>();

		sobe = (ArrayList<Soba>) baza.getSobaRepository().ucitajSveIzBaze();

		return sobe;

	}

//	public Soba VrtatiSobuPoHoteluIBrojuKreveta(int brojKreveta,int HotelID) {
//		Soba soba = new Soba();
//
//		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
//		listaKriterjona.add(Restrictions.eq("brojKreveta", (int) brojKreveta));
//		listaKriterjona.add(Restrictions.eq("hotel", (int) HotelID));
//
//		soba = baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterjona).get(0);
//
//		return soba;
//	}
	
	public boolean ProvjeriPostojanjeSobaId(int id) {
		Soba soba =new Soba();

		soba = baza.getSobaRepository().ucitajIzBaze(id);

		if (soba == null)
			return false;

		return true;
	}

	public boolean ProvjeriPostojanjeSoba(int brojKreveta) {

		Soba soba =new Soba();
		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("brojKreveta", (int)brojKreveta));
	

		soba = baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterjona).get(0);

		if (soba == null)
			return false;

		return true;
	}
	
	public void AzurirajiliUbaciSobu(Soba soba) {
		baza.getSobaRepository().sacuvajIliAzurirajUBazu(soba);

	}
	//dok cekam SobeService
	public ArrayList<Soba> dajSobeZaHotel(Hotel hotel){
		ArrayList<Soba> sobe=new ArrayList<Soba>();
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("hotel", hotel));
		sobe.addAll(baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterija));
		return sobe;
	}
	
	//ne valja kompleksnost :(
	public ArrayList<RezervisaniTerminSoba> dajTermineZaSobu(ArrayList<Soba> sobe){
		ArrayList<RezervisaniTerminSoba> rts=new ArrayList<RezervisaniTerminSoba>();	
		for(int i=0; i<sobe.size(); i++){
			ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
			listaKriterija.add(Restrictions.eq("soba", sobe.get(i)));
			rts.addAll(baza.getRezervisaniTerminSobaRepository().ucitajIzBazePoKriteriju(listaKriterija));
			for(int j=0; j<rts.size(); j++){
				Soba s=new Soba();
				s=baza.getSobaRepository().ucitajIzBaze(rts.get(j).getSoba().getSobaId());
				rts.get(j).setSoba(s);
			}
		}
		return rts;
	}
	
	//ne valja kompleksnost :(
	public ArrayList<Soba> dajSlobodneSobeZaHotel(Hotel hotel, Date pocetak, Date kraj){
		ArrayList<Soba> sobe=new ArrayList<Soba>();		
		sobe.addAll(dajSobeZaHotel(hotel));
		
		ArrayList<RezervisaniTerminSoba> termini=new ArrayList<RezervisaniTerminSoba>();		
		termini=dajTermineZaSobu(sobe);
		
		for(int i=0; i<termini.size(); i++ ){
			Date rezPoc=termini.get(i).getDatumPocetak();
			Date rezKraj=termini.get(i).getDatumKraj();
			if((pocetak.before(rezPoc) && kraj.after(rezPoc) && kraj.before(rezKraj)) || (pocetak.after(rezPoc) && kraj.before(rezKraj)) || (pocetak.after(rezPoc) && pocetak.before(rezKraj) && kraj.after(rezKraj)) || (pocetak.before(rezPoc) && kraj.after(rezKraj))){
				for(int j=0; j<sobe.size(); j++){
					if(sobe.get(j).getSobaId()==termini.get(i).getSoba().getSobaId())
						sobe.remove(sobe.get(j));
				}
			}
		}
		return sobe;
	}
	

}
