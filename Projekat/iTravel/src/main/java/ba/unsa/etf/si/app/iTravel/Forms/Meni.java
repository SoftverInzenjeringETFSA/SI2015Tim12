package ba.unsa.etf.si.app.iTravel.Forms;

import javax.swing.JFrame;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;

public class Meni {
	public static void OdjaviSe(){
		UnitOfWork uow = new UnitOfWork();
		uow.getOdjavaService().OdjaviKorisnika();		
		
		java.awt.Window win[] = java.awt.Window.getWindows(); 
		for(int i=0;i<win.length;i++){ 
		win[i].dispose(); 
		} 
		Prijava.PrikaziFormu();
	}
	
	public static void Pocetna(JFrame forma){
		java.awt.Window win[] = java.awt.Window.getWindows(); 
		for(int i=0;i<win.length;i++){ 
		win[i].dispose(); 
		} 				
		if(UserContext.getInstance().getRoleID() == 1){
			PocetnaFormaAdministrator.PrikaziFormu();
			forma.setVisible(false);
		}
		else if(UserContext.getInstance().getRoleID() == 2){
			PocetnaFormaAgent.PrikaziFormu();
			forma.setVisible(false);
		}
		else if(UserContext.getInstance().getRoleID() == 3){
			PocetnaFormaSupervizor.PrikaziFormu();
			forma.setVisible(false);
		}
	}
	public static void Hoteli(JFrame forma){
		java.awt.Window win[] = java.awt.Window.getWindows(); 
		for(int i=0;i<win.length;i++){ 
			win[i].dispose(); 
		} 				
		
		Hoteli.PrikaziFormu();
		forma.setVisible(false);
	}
	public static void Klijenti(JFrame forma){
		java.awt.Window win[] = java.awt.Window.getWindows(); 
		for(int i=0;i<win.length;i++){ 
			win[i].dispose(); 
		} 				
		Klijenti.PrikaziFormu();
		forma.setVisible(false);
	}
	public static void Korisnici(JFrame forma){
		java.awt.Window win[] = java.awt.Window.getWindows(); 
		for(int i=0;i<win.length;i++){ 
			win[i].dispose(); 
		} 				
		Korisnici.PrikaziFormu();
		forma.setVisible(false);			
	}
	
	public static void Izvjestaj(JFrame forma){
		java.awt.Window win[] = java.awt.Window.getWindows(); 
		for(int i=0;i<win.length;i++){ 
			win[i].dispose(); 
		} 
		GenerisanjeIzvjestaja.PrikaziFormu();
		forma.setVisible(false);		
	}
	public static void Rezervacije(JFrame forma){
		java.awt.Window win[] = java.awt.Window.getWindows(); 
		for(int i=0;i<win.length;i++){ 
			win[i].dispose(); 
		} 				
		Rezervacije.PrikaziFormu();
		forma.setVisible(false);
	}
}