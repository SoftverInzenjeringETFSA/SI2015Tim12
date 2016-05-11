package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;

public class DestinacijeService {
	DBContext baza;
	
	public DestinacijeService(){
		baza= DBContext.getInstance();
	}
	
	public Destinacija VratiDestinaciju(String nazivDest){
		Destinacija dest=new Destinacija();

		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("naziv", nazivDest));
		
		dest=baza.getDestRepository().ucitajIzBazePoKriteriju(listaKriterjona).get(0);
		return dest;
	} 
	
	public Destinacija VratiDestinaciju(int idDest){
		Destinacija dest=new Destinacija();

		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("destinacijaId", idDest));
		
		dest=baza.getDestRepository().ucitajIzBazePoKriteriju(listaKriterjona).get(0);
		return dest;
	} 
	
	
	public ArrayList<Destinacija> DajSveDestinacije(){
		ArrayList<Destinacija> destinacije=new ArrayList<Destinacija>();
		destinacije.addAll((ArrayList<Destinacija>) baza.getDestRepository().ucitajSveIzBaze());
		return destinacije;
	}
	
}
