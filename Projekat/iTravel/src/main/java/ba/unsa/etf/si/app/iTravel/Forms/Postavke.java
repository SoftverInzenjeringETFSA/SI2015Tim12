package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.hibernate.PropertyValueException;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Postavke {

	private UnitOfWork uow = new UnitOfWork();
	
	private JFrame frmPostavke;

	private JCheckBox chckbxHoteli;
	private	JCheckBox chckbxRezervacije;
	private JCheckBox chckbxKlijenti;
	private	JCheckBox chckbxKorisnici;
	private JCheckBox chckbxIzvjestaji;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {		
					//Postavke window = new Postavke();
					//window.frmPostavke.setVisible(true);		
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Postavke(PocetnaFormaAdministrator parentForma) {
		initialize(parentForma);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final PocetnaFormaAdministrator parentForma) {
		
		frmPostavke = new JFrame();
		frmPostavke.setTitle("Postavke");
		frmPostavke.setBounds(100, 100, 228, 286);
		frmPostavke.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPostavke.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmPostavke.setJMenuBar(menuBar);
		
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
					frmPostavke.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 2){
					PocetnaFormaAgent forma = new PocetnaFormaAgent();
					frmPostavke.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 3){
					PocetnaFormaSupervizor forma = new PocetnaFormaSupervizor();
					frmPostavke.setVisible(false);
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
				frmPostavke.setVisible(false);
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
				frmPostavke.setVisible(false);
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
						frmPostavke.setVisible(false);
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
					frmPostavke.setVisible(false);
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
		frmPostavke.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Uključi/isključi module:");
		lblNewLabel.setBounds(28, 21, 131, 14);
		frmPostavke.getContentPane().add(lblNewLabel);
		
		chckbxHoteli = new JCheckBox("Hoteli");
		chckbxHoteli.setBounds(38, 42, 97, 23);
		frmPostavke.getContentPane().add(chckbxHoteli);
		chckbxHoteli.setSelected(uow.getPostavkeService().modulOmogucen(1));
		
		chckbxRezervacije = new JCheckBox("Rezervacije");
		chckbxRezervacije.setBounds(38, 67, 97, 23);
		frmPostavke.getContentPane().add(chckbxRezervacije);
		chckbxRezervacije.setSelected(uow.getPostavkeService().modulOmogucen(2));
		
		chckbxKlijenti = new JCheckBox("Klijenti");
		chckbxKlijenti.setBounds(38, 93, 97, 23);
		frmPostavke.getContentPane().add(chckbxKlijenti);
		chckbxKlijenti.setSelected(uow.getPostavkeService().modulOmogucen(3));
		
		chckbxKorisnici = new JCheckBox("Korisnici");
		chckbxKorisnici.setBounds(38, 119, 97, 23);
		frmPostavke.getContentPane().add(chckbxKorisnici);
		chckbxKorisnici.setSelected(uow.getPostavkeService().modulOmogucen(4));
		
		chckbxIzvjestaji = new JCheckBox("Izvještaji");
		chckbxIzvjestaji.setBounds(38, 145, 97, 23);
		frmPostavke.getContentPane().add(chckbxIzvjestaji);
		chckbxIzvjestaji.setSelected(uow.getPostavkeService().modulOmogucen(5));
		
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setBounds(28, 179, 150, 30);
		frmPostavke.getContentPane().add(btnPotvrdi);
		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean[] nizPostavki = new boolean[5];
				nizPostavki[0] = chckbxHoteli.isSelected();
				nizPostavki[1] = chckbxRezervacije.isSelected();
				nizPostavki[2] = chckbxKlijenti.isSelected();
				nizPostavki[3] = chckbxKorisnici.isSelected();
				nizPostavki[4] = chckbxIzvjestaji.isSelected();
				
				boolean uspjesno = uow.getPostavkeService().spasiPromjenePostavki(nizPostavki);
				
				if(uspjesno)
				{
					JOptionPane.showMessageDialog(null, 
							"Uspješno spašene promjene.",							
							"Obavještenje",
							JOptionPane.INFORMATION_MESSAGE);
					
					frmPostavke.dispose();
					parentForma.OsvjeziPostavkeFormu();
				}
				else
				{
					JOptionPane.showMessageDialog(null, 
							"Dogodila se greška, molimo kontaktirajte administratora ili pokušajte kasnije.",
							"Obavještenje",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
	}
	
	public void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPostavke.setVisible(true);
					//Postavke window = new Postavke();
					//window.frmPostavke.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}
}
