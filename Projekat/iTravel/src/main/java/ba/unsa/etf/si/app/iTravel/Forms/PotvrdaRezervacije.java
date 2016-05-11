package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PotvrdaRezervacije {

	private JFrame frmPotvrdaRezervacije;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PotvrdaRezervacije window = new PotvrdaRezervacije();
					window.frmPotvrdaRezervacije.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PotvrdaRezervacije() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPotvrdaRezervacije = new JFrame();
		frmPotvrdaRezervacije.setTitle("Potvrda rezervacije");
		frmPotvrdaRezervacije.setBounds(100, 100, 317, 195);
		frmPotvrdaRezervacije.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPotvrdaRezervacije.getContentPane().setLayout(null);
		frmPotvrdaRezervacije.setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Izdaj potvrdu");
		btnNewButton.setBounds(35, 40, 230, 30);
		frmPotvrdaRezervacije.getContentPane().add(btnNewButton);
		
		JButton btnIzdajFakturu = new JButton("Izdaj fakturu");
		btnIzdajFakturu.setBounds(35, 81, 230, 30);
		frmPotvrdaRezervacije.getContentPane().add(btnIzdajFakturu);
		
		JMenuBar menuBar = new JMenuBar();
		frmPotvrdaRezervacije.setJMenuBar(menuBar);
		
		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		
		JMenuItem mntmPoetna = new JMenuItem("Početna");
		mntmPoetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 				
				if(UserContext.getInstance().getRoleID() == 1){
					PocetnaFormaAdministrator forma = new PocetnaFormaAdministrator();
					frmPotvrdaRezervacije.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 2){
					PocetnaFormaAgent forma = new PocetnaFormaAgent();
					frmPotvrdaRezervacije.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 3){
					PocetnaFormaSupervizor forma = new PocetnaFormaSupervizor();
					frmPotvrdaRezervacije.setVisible(false);
					forma.PrikaziFormu();
				}
			}
		});
		mnMeni.add(mntmPoetna);
		
		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mntmHoteli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 				
				Hoteli forma = new Hoteli();
				frmPotvrdaRezervacije.setVisible(false);
				forma.PrikaziFormu();
			}
		});
		mnMeni.add(mntmHoteli);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 				
				Rezervacije forma = new Rezervacije();
				frmPotvrdaRezervacije.setVisible(false);
				forma.PrikaziFormu();
			}
		});
		mnMeni.add(mntmRezervacije);
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					java.awt.Window win[] = java.awt.Window.getWindows(); 
					for(int i=0;i<win.length;i++){ 
					win[i].dispose(); 
					} 				
						Klijenti forma = new Klijenti();
						frmPotvrdaRezervacije.setVisible(false);
						forma.PrikaziFormu();	
					
				}
			});
				mnMeni.add(mntmKlijenti);
			}
			
			if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
			mntmKorisnici.addActionListener(new ActionListener() {
						
				public void actionPerformed(ActionEvent e) {
					java.awt.Window win[] = java.awt.Window.getWindows(); 
					for(int i=0;i<win.length;i++){ 
					win[i].dispose(); 
					} 				
					Korisnici forma = new Korisnici();
					frmPotvrdaRezervacije.setVisible(false);
					forma.PrikaziFormu();				
				}
			});
			mnMeni.add(mntmKorisnici);
			}
		
		JMenu mnRaun = new JMenu("Račun");
		menuBar.add(mnRaun);
		
		JMenuItem mntmPromijeniifru = new JMenuItem("Promijeni šifru");
		mntmPromijeniifru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PromjenaSifre novaForma = new PromjenaSifre();
				novaForma.PrikaziFormu();
			}
		});
		mnRaun.add(mntmPromijeniifru);
		
		JMenuItem mntmOdjaviSe = new JMenuItem("Odjavi se");
		mntmOdjaviSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OdjavaService odjava = new OdjavaService();
				odjava.OdjaviKorisnika();
							
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 
				Prijava prijava = new Prijava();
				prijava.PrikaziFormu();
			}
		});
		mnRaun.add(mntmOdjaviSe);
	}
	public void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PotvrdaRezervacije window = new PotvrdaRezervacije();
					window.frmPotvrdaRezervacije.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
