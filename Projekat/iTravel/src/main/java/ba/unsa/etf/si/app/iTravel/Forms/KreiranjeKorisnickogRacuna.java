package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.SwingConstants;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;
import ba.unsa.etf.si.app.iTravel.DBModels.Korisnickiracunxrola;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Rola;

import javax.rmi.CORBA.Tie;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import java.awt.event.ActionEvent;

public class KreiranjeKorisnickogRacuna {

	private UnitOfWork uow = new UnitOfWork();
	
	private boolean modifikacija = false;
	
	private KorisnickiRacun korisnickiRacun;
	//private Rola rola;
	private Osoba osoba;
	private Korisnickiracunxrola korisnickiracunxrola;
	
	private JFrame frmKreirajiKorisnika;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JComboBox comboBox1;
	private JLabel lblUnesitePodatkeO;
	private JButton btnNewButton;
	
	Korisnici parentFormaKorisnici;

	/**
	 * Launch the application.
	 */
	public void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//KreiranjeKorisnickogRacuna window = new KreiranjeKorisnickogRacuna();
					frmKreirajiKorisnika.setVisible(true);
					//window.frmKreirajiKorisnika.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KreiranjeKorisnickogRacuna()
	{
		initialize();
	}
	
	public KreiranjeKorisnickogRacuna(int idKorisnika)
	{
		modifikacija = true;
		
		this.korisnickiRacun = uow.getKorisnickiRacunService().dajKorisnika(idKorisnika);
		
		//this.korisnickiRacun = korisnickiRacun;
		this.osoba = korisnickiRacun.getOsoba();
		
		Set<Korisnickiracunxrola> skupRola=	korisnickiRacun.getKorisnickiracunxrolas();

		for(Iterator<Korisnickiracunxrola> it=skupRola.iterator();
			it.hasNext();)
		{
			Korisnickiracunxrola r = it.next();
			if(r!=null)
			{
				this.korisnickiracunxrola = r;
				break;
			}
		}
		
		initialize();		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
		frmKreirajiKorisnika = new JFrame();
		
		if(modifikacija == true)
			frmKreirajiKorisnika.setTitle("Promjena korisničkog naloga");
		else
			frmKreirajiKorisnika.setTitle("Kreiranje korisničkog naloga");
		
		frmKreirajiKorisnika.setBounds(100, 100, 353, 496);
		frmKreirajiKorisnika.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKreirajiKorisnika.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Ime:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(25, 60, 99, 14);
		
		JLabel lblNewLabel_1 = new JLabel("Prezime:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(25, 91, 99, 14);
		
		JLabel lblNewLabel_2 = new JLabel("JMBG:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(25, 117, 99, 14);
		
		JLabel lblNewLabel_3 = new JLabel("Broj li\u010Dne karte:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(25, 148, 99, 14);
		
		JLabel lblNewLabel_4 = new JLabel("Adresa:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(25, 179, 99, 14);
		
		JLabel lblNewLabel_5 = new JLabel("Telefon:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(25, 210, 99, 14);
		
		JLabel lblNewLabel_6 = new JLabel("E-mail:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(25, 241, 99, 14);
		
		JLabel lblNewLabel_7 = new JLabel("Korisni\u010Dko ime:");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(25, 272, 99, 14);
		
		JLabel lblNewLabel_8 = new JLabel("Tip korisnika:");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setBounds(25, 339, 99, 14);
		
		if(modifikacija == true)
			btnNewButton = new JButton("Ažuriraj");
		else
			btnNewButton = new JButton("Kreiraj");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Osoba novaOsoba = new Osoba();
				
				String ime = textField.getText();
				String prezime = textField_1.getText();
				String jmb = textField_2.getText();
				String brojLicne = textField_3.getText();
				String adresa = textField_4.getText();
				String brojTelefona = textField_5.getText();
				String email = textField_6.getText();
				String username = textField_7.getText();
				String password = textField_8.getText();
			
				Integer rolaID = comboBox1.getSelectedIndex();
				rolaID = rolaID + 1;
				
				if(modifikacija)
					novaOsoba.setOsobaId(osoba.getOsobaId());
				
				novaOsoba.setIme(ime);
				novaOsoba.setPrezime(prezime);
				novaOsoba.setAdresa(adresa);
				novaOsoba.setBrojLicneKarte(brojLicne);
				novaOsoba.setBrojPasosa("");
				novaOsoba.setBrojTelefona(brojTelefona);
				novaOsoba.setEmail(email);
				novaOsoba.setJmbg(jmb);
				
				String porukaValidacije = uow.getOsobaService().Validiraj(novaOsoba);
				
				if(porukaValidacije == "")
				{
					// Ako se uspije kreirati osoba idi dalje da kreiras korisnicki racun
					if(uow.getOsobaService().KreirajOsobu(novaOsoba, modifikacija).getOsobaId() != null)
					{						
						KorisnickiRacun noviLorisnickiRacun = new KorisnickiRacun();
						
						if(modifikacija)
							noviLorisnickiRacun.setKorisnickiRacunId(korisnickiRacun.getKorisnickiRacunId());
						
						noviLorisnickiRacun.setOsoba(novaOsoba);
						noviLorisnickiRacun.setUsername(username);
						noviLorisnickiRacun.setPassword(password);
						
						String porukaValidacijeRacuna = uow.getKorisnickiRacunService().Validiraj(korisnickiRacun, modifikacija);
						
						if(porukaValidacijeRacuna == "")
						{		
							// Ako se kreira korisnicki racun idi kreiraj rekord za rolu
							if(uow.getKorisnickiRacunService().
									KreirajKorisnickiRacun(korisnickiRacun,  modifikacija).
									getKorisnickiRacunId() != null)
							{
								Korisnickiracunxrola novaKorisnickiracunxrola = new Korisnickiracunxrola();
								
								novaKorisnickiracunxrola.setKorisnickiRacunXrolaId(korisnickiracunxrola.getKorisnickiRacunXrolaId());
								
								// Dobavi rolu
								Rola rola = uow.getRolaService().dajRolu(rolaID);
								
								novaKorisnickiracunxrola.setRola(rola);
								
								// Kreiranje rekorda za rolu
								if(uow.getKorisnickiRacunService()
										.KreirajRoluZaKorisnika(novaKorisnickiracunxrola,  modifikacija)
										.getKorisnickiRacunXrolaId() != null)
								{
									if(modifikacija)
									{
										JOptionPane.showMessageDialog(null,
												"Uspješno ažuriran korisnički račun", "Obavijest",
												JOptionPane.INFORMATION_MESSAGE);
									}
									else
									{
										JOptionPane.showMessageDialog(null,
												"Uspješno kreiran korisnički račun", "Obavijest",
												JOptionPane.INFORMATION_MESSAGE);
									}
									
									
									frmKreirajiKorisnika.dispose();
									
								}
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,
									"<html><font color='red'>"+porukaValidacijeRacuna+"</font></html>",
									"Molimo provjerite username i password!",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"<html><font color='red'>"+porukaValidacije+"</font></html>",
							"Molimo provjerite unos!",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(90, 384, 150, 30);
		
		textField = new JTextField();
		textField.setBounds(134, 60, 139, 20);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(134, 91, 139, 20);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(134, 117, 139, 20);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(134, 148, 139, 20);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(134, 179, 139, 20);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(134, 210, 139, 20);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(134, 241, 139, 20);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(134, 272, 139, 20);
		textField_7.setColumns(10);
		frmKreirajiKorisnika.getContentPane().setLayout(null);
		frmKreirajiKorisnika.getContentPane().add(lblNewLabel);
		frmKreirajiKorisnika.getContentPane().add(lblNewLabel_3);
		frmKreirajiKorisnika.getContentPane().add(lblNewLabel_1);
		frmKreirajiKorisnika.getContentPane().add(lblNewLabel_2);
		frmKreirajiKorisnika.getContentPane().add(lblNewLabel_4);
		frmKreirajiKorisnika.getContentPane().add(lblNewLabel_5);
		frmKreirajiKorisnika.getContentPane().add(lblNewLabel_6);
		frmKreirajiKorisnika.getContentPane().add(lblNewLabel_7);
		frmKreirajiKorisnika.getContentPane().add(lblNewLabel_8);
		frmKreirajiKorisnika.getContentPane().add(textField_7);
		frmKreirajiKorisnika.getContentPane().add(textField_6);
		frmKreirajiKorisnika.getContentPane().add(textField_5);
		frmKreirajiKorisnika.getContentPane().add(textField_4);
		frmKreirajiKorisnika.getContentPane().add(textField_3);
		frmKreirajiKorisnika.getContentPane().add(textField_2);
		frmKreirajiKorisnika.getContentPane().add(textField_1);
		frmKreirajiKorisnika.getContentPane().add(textField);
		frmKreirajiKorisnika.getContentPane().add(btnNewButton);
		
		if(modifikacija == true)
		{
			lblUnesitePodatkeO = new JLabel("Promjena podataka korisnika");
			lblUnesitePodatkeO.setBounds(90, 21, 231, 14);
		}
		else
		{
			lblUnesitePodatkeO = new JLabel("Unesite podatke o novom korisniku");
			lblUnesitePodatkeO.setBounds(53, 21, 231, 14);
		}

		
		
		
		lblUnesitePodatkeO.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		frmKreirajiKorisnika.getContentPane().add(lblUnesitePodatkeO);
		
		JLabel lblifra = new JLabel("\u0160ifra:");
		lblifra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblifra.setBounds(25, 307, 97, 14);
		frmKreirajiKorisnika.getContentPane().add(lblifra);
		
		textField_8 = new JTextField();
		textField_8.setBounds(134, 303, 139, 20);
		frmKreirajiKorisnika.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		comboBox1 = new JComboBox();
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "Putnički agent","Supervizor"}));
		comboBox1.setSelectedIndex(0);
		comboBox1.setBounds(134, 336, 139, 20);
		frmKreirajiKorisnika.getContentPane().add(comboBox1);
		
		if(modifikacija == true)
		{
			textField.setText(osoba.getIme());
			textField_1.setText(osoba.getPrezime());
			textField_2.setText(osoba.getJmbg().toString());
			textField_3.setText(osoba.getBrojLicneKarte());
			textField_4.setText(osoba.getAdresa());
			textField_5.setText(osoba.getBrojTelefona());
			textField_6.setText(osoba.getEmail());
			textField_7.setText(korisnickiRacun.getUsername());
			textField_8.setVisible(false);	
			comboBox1.setSelectedIndex(korisnickiracunxrola.getRola().getRolaId() - 1);
		}
		
		JMenuBar menuBar = new JMenuBar();
		frmKreirajiKorisnika.setJMenuBar(menuBar);
		
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
					frmKreirajiKorisnika.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 2){
					PocetnaFormaAgent forma = new PocetnaFormaAgent();
					frmKreirajiKorisnika.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 3){
					PocetnaFormaSupervizor forma = new PocetnaFormaSupervizor();
					frmKreirajiKorisnika.setVisible(false);
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
				frmKreirajiKorisnika.setVisible(false);
				forma.PrikaziFormu();
			}
		});
		mnMeni.add(mntmHoteli);
		mntmHoteli.setEnabled(postavke[1]);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 				
				Rezervacije forma = new Rezervacije();
				frmKreirajiKorisnika.setVisible(false);
				forma.PrikaziFormu();
			}
		});
		mnMeni.add(mntmRezervacije);
		mntmRezervacije.setEnabled(postavke[2]);
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					java.awt.Window win[] = java.awt.Window.getWindows(); 
					for(int i=0;i<win.length;i++){ 
					win[i].dispose(); 
					} 				
						Klijenti forma = new Klijenti();
						frmKreirajiKorisnika.setVisible(false);
						forma.PrikaziFormu();	
					
				}
			});
				mnMeni.add(mntmKlijenti);
				mntmKlijenti.setEnabled(postavke[3]);
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
					frmKreirajiKorisnika.setVisible(false);
					forma.PrikaziFormu();				
				}
			});
			mnMeni.add(mntmKorisnici);
			mntmKorisnici.setEnabled(postavke[4]);
			}
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
		JMenuItem mntmIzvjestaji = new JMenuItem("Izvještaji");
		mntmIzvjestaji.addActionListener(new ActionListener() {
					
			public void actionPerformed(ActionEvent e) {
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 
				GenerisanjeIzvjestaja forma = new GenerisanjeIzvjestaja();
				frmKreirajiKorisnika.setVisible(false);
				forma.PrikaziFormu();				
			}
		});
		mnMeni.add(mntmIzvjestaji);
		mntmIzvjestaji.setEnabled(postavke[5]);
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
	
}
