

package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import ba.unsa.etf.si.app.iTravel.BLL.DestinacijeService;
import ba.unsa.etf.si.app.iTravel.BLL.HoteliService;
import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.SobeService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;
import ba.unsa.etf.si.app.iTravel.Forms.DodavanjeHotela;

import javassist.bytecode.ConstantAttribute;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Ref;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class DodavanjeDestinacije {

	private JFrame frmDodavanjeDestinacije;
	private JTextField textField;
	private JCheckBox chckbxNewCheckBox;
   
	
	
	
	class AkcijaDodavanja implements ActionListener 
	{  
		   
		public void actionPerformed(ActionEvent event) {
			if (ValidacijaPoljaZaDodavanjeDestinacije())
			{
			
			DestinacijeService destinacijeService = new DestinacijeService();
			
			
			
			Destinacija destinacija =new Destinacija();
			destinacija.setNaziv(textField.getText());
			destinacija.setOmogucenPrevoz(chckbxNewCheckBox.isSelected());
			
			
			destinacijeService.UbaciDestinacijuUBAzu(destinacija);
			
			
		
			
			
			JOptionPane.showMessageDialog(null, "Uspjesno ste kreirali destinaciju", "Info", JOptionPane.INFORMATION_MESSAGE);	
			}
			else {
				JOptionPane.showMessageDialog(null, ":(", "Info", JOptionPane.INFORMATION_MESSAGE);

			}
}

		
	}
	
	private boolean ValidacijaPoljaZaDodavanjeDestinacije(){
	 if(textField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli destinaciju", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeDestinacije window = new DodavanjeDestinacije();
					window.frmDodavanjeDestinacije.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodavanjeDestinacije() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {frmDodavanjeDestinacije = new JFrame();
	frmDodavanjeDestinacije.setTitle("Unos destinacije");
	frmDodavanjeDestinacije.setBounds(100, 100, 400, 265);
	frmDodavanjeDestinacije.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frmDodavanjeDestinacije.getContentPane().setLayout(null);
	frmDodavanjeDestinacije.setLocationRelativeTo(null);

	
	JLabel lblNazivDestinacije = new JLabel("Naziv destinacije:");
	lblNazivDestinacije.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNazivDestinacije.setBounds(10, 68, 171, 20);
	frmDodavanjeDestinacije.getContentPane().add(lblNazivDestinacije);
	
	JLabel lblPut = new JLabel("Prijevoz:");
	lblPut.setHorizontalAlignment(SwingConstants.RIGHT);
	lblPut.setBounds(20, 99, 161, 20);
	frmDodavanjeDestinacije.getContentPane().add(lblPut);
	
	
	
	
	JButton btnNewButton = new JButton("Dodaj");
	btnNewButton.setBounds(115, 158, 150, 30);
	frmDodavanjeDestinacije.getContentPane().add(btnNewButton);
	
	btnNewButton.addActionListener(new AkcijaDodavanja());
	
	
	
	textField = new JTextField();
	textField.setColumns(10);
	textField.setBounds(191, 68, 138, 20);
	frmDodavanjeDestinacije.getContentPane().add(textField);
	
	chckbxNewCheckBox = new JCheckBox("Omogucen");
	chckbxNewCheckBox.setBounds(187, 98, 97, 23);
	frmDodavanjeDestinacije.getContentPane().add(chckbxNewCheckBox);
	
	
		
		JMenuBar menuBar = new JMenuBar();
		frmDodavanjeDestinacije.setJMenuBar(menuBar);
		
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
					frmDodavanjeDestinacije.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 2){
					PocetnaFormaAgent forma = new PocetnaFormaAgent();
					frmDodavanjeDestinacije.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 3){
					PocetnaFormaSupervizor forma = new PocetnaFormaSupervizor();
					frmDodavanjeDestinacije.setVisible(false);
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
				frmDodavanjeDestinacije.setVisible(false);
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
				frmDodavanjeDestinacije.setVisible(false);
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
					frmDodavanjeDestinacije.setVisible(false);
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
				frmDodavanjeDestinacije.setVisible(false);
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
					DodavanjeDestinacije window = new DodavanjeDestinacije();
					window.frmDodavanjeDestinacije.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
		
	}
}

