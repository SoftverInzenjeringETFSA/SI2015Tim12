package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import ba.unsa.etf.si.app.iTravel.BLL.DestinacijeService;
import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class DodavanjeDestinacije {
	private UnitOfWork uow = new UnitOfWork();
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
	public static void PrikaziFormu() {
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
	private void initialize() {
	
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
	frmDodavanjeDestinacije = new JFrame();
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
				Meni.Pocetna(frmDodavanjeDestinacije);
			
			}
		});
		mnMeni.add(mntmPoetna);
		
		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mntmHoteli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Hoteli(frmDodavanjeDestinacije);
			}
		});
		mnMeni.add(mntmHoteli);
		mntmHoteli.setEnabled(postavke[1]);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Rezervacije(frmDodavanjeDestinacije);
			}
		});
		mnMeni.add(mntmRezervacije);
		mntmRezervacije.setEnabled(postavke[2]);
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
		JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
		mntmKlijenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Klijenti(frmDodavanjeDestinacije);
			}
		});
			mnMeni.add(mntmKlijenti);
			mntmRezervacije.setEnabled(postavke[3]);
		}
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
		JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
		mntmKorisnici.addActionListener(new ActionListener() {
					
			public void actionPerformed(ActionEvent e) {
				Meni.Korisnici(frmDodavanjeDestinacije);		
			}
		});
		mnMeni.add(mntmKorisnici);
		mntmKorisnici.setEnabled(postavke[4]);
		}
		
		JMenu mnRaun = new JMenu("Račun");
		menuBar.add(mnRaun);
		
		JMenuItem mntmPromijeniifru = new JMenuItem("Promijeni šifru");
		mntmPromijeniifru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PromjenaSifre.PrikaziFormu();
			}
		});
		mnRaun.add(mntmPromijeniifru);
		
		JMenuItem mntmOdjaviSe = new JMenuItem("Odjavi se");
		mntmOdjaviSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.OdjaviSe();
			}
		});
		mnRaun.add(mntmOdjaviSe);
		
		JMenu mnPomo = new JMenu("Pomoć");
		menuBar.add(mnPomo);
		
		JMenuItem mntmOFormi = new JMenuItem("O formi...");
		mntmOFormi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Meni.HelpForma("d:\\UnosDestinacijeSlika.jpg");
		}
		});
		mnPomo.add(mntmOFormi);
	}
}

