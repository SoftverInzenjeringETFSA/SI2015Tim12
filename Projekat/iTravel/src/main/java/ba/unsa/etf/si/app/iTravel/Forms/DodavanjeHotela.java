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

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodavanjeHotela {

	private UnitOfWork uow = new UnitOfWork();
	
	private JFrame frmUnosHotela;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTextField textField_7;
	private JSpinner spinner ;
	private JDateChooser dateChooser_1;
	private JDateChooser dateChooser;
	
	public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date){
	    if(date != null) {
	        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	        return sqlDate;
	    }
	    return null;
	}
	
	class AkcijaDodavanja implements ActionListener 
	{  
		   
		public void actionPerformed(ActionEvent event) {
			if (ValidacijaPoljaZaDodavanjeHotela())
			{
			HoteliService hoteliService = new HoteliService();
			SobeService sobeService = new SobeService();
			DestinacijeService destinacijeService = new DestinacijeService();
			
			Hotel hotel = new Hotel();
			hotel.setNaziv(textField.getText());
			hotel.setAdresa(textField_1.getText()); 
			
			hotel.setBrojZvjezdica( (Integer) spinner.getValue());
			hotel.setPocetakVisoka(convertUtilDateToSqlDate ( dateChooser.getDate()));
			hotel.setKrajVisoka(convertUtilDateToSqlDate ( dateChooser_1.getDate()));
			
			
			Soba soba = new Soba();
			soba.setCijenaNiska(Integer.parseInt(textField_3.getText()));
			soba.setHotel(hotel);
			soba.setCijenaVisoka(Integer.parseInt(textField_6.getText()));
			
			Destinacija destinacija =new Destinacija();
			destinacija.setNaziv(textField_7.getText());
			
			
			destinacijeService.UbaciDestinacijuUBAzu(destinacija);
			hotel.setDestinacija(destinacija);
			hoteliService.KreirajHotel(hotel);
			
		
			
			int BrojSoba = Integer.parseInt(textField_2.getText());
			for (int i = 0; i < BrojSoba; i++) {
				
				sobeService.UbaciSobuUBazu(soba);
			}
			JOptionPane.showMessageDialog(null, "Uspjesno ste kreirali hotel", "Info", JOptionPane.INFORMATION_MESSAGE);	
			}
			else {
				JOptionPane.showMessageDialog(null, ":(", "Info", JOptionPane.INFORMATION_MESSAGE);

			}
}

		
	}
	
	private boolean ValidacijaPoljaZaDodavanjeHotela(){
		if(dateChooser.getCalendar()==null) {
			JOptionPane.showMessageDialog(null, "Niste unijeli datum 'Od'", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(dateChooser_1.getCalendar()==null){
			JOptionPane.showMessageDialog(null, "Niste unijeli datum 'Do'", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_1.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli lokaciju", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli naziv", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_2.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli broj soba", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_3.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli cijenu", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_6.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli cijenu", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_7.getText().equals("")){
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
					DodavanjeHotela window = new DodavanjeHotela();
					window.frmUnosHotela.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodavanjeHotela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
	frmUnosHotela = new JFrame();
	frmUnosHotela.setTitle("Unos hotela");
	frmUnosHotela.setBounds(100, 100, 400, 471);
	frmUnosHotela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frmUnosHotela.getContentPane().setLayout(null);
	frmUnosHotela.setLocationRelativeTo(null);

	
	JLabel lblNazivHotela = new JLabel("Naziv hotela:");
	lblNazivHotela.setHorizontalAlignment(SwingConstants.RIGHT);
	lblNazivHotela.setBounds(10, 68, 161, 20);
	frmUnosHotela.getContentPane().add(lblNazivHotela);
	
	JLabel lblBrojZvjezdica = new JLabel("Lokacija hotela:");
	lblBrojZvjezdica.setHorizontalAlignment(SwingConstants.RIGHT);
	lblBrojZvjezdica.setBounds(10, 99, 160, 20);
	frmUnosHotela.getContentPane().add(lblBrojZvjezdica);
	
	JLabel label = new JLabel("Broj zvjezdica:");
	label.setHorizontalAlignment(SwingConstants.RIGHT);
	label.setBounds(10, 161, 161, 20);
	frmUnosHotela.getContentPane().add(label);
	
	JLabel lblBroj = new JLabel("Broj unajmljenih soba:");
	lblBroj.setHorizontalAlignment(SwingConstants.RIGHT);
	lblBroj.setBounds(10, 130, 161, 20);
	frmUnosHotela.getContentPane().add(lblBroj);
	
	JLabel lblPoetniDatum = new JLabel("Početak visoke sezone:");
	lblPoetniDatum.setHorizontalAlignment(SwingConstants.RIGHT);
	lblPoetniDatum.setBounds(10, 192, 161, 20);
	frmUnosHotela.getContentPane().add(lblPoetniDatum);
	
	JLabel lblKrajnjiDatum = new JLabel("Kraj visoke sezone:");
	lblKrajnjiDatum.setHorizontalAlignment(SwingConstants.RIGHT);
	lblKrajnjiDatum.setBounds(10, 223, 161, 20);
	frmUnosHotela.getContentPane().add(lblKrajnjiDatum);
	
	textField = new JTextField();
	textField.setBounds(191, 68, 138, 20);
	frmUnosHotela.getContentPane().add(textField);
	textField.setColumns(10);
	
	textField_1 = new JTextField();
	textField_1.setColumns(10);
	textField_1.setBounds(191, 99, 138, 20);
	frmUnosHotela.getContentPane().add(textField_1);
	
	textField_2 = new JTextField();
	textField_2.setColumns(10);
	textField_2.setBounds(191, 130, 138, 20);
	frmUnosHotela.getContentPane().add(textField_2);
	
	spinner = new JSpinner();
	spinner.setModel(new SpinnerNumberModel(1, 1, 6, 1));
	spinner.setBounds(191, 161, 42, 20);
	frmUnosHotela.getContentPane().add(spinner);
	
	JButton btnNewButton = new JButton("Dodaj");
	btnNewButton.setBounds(120, 370, 150, 30);
	frmUnosHotela.getContentPane().add(btnNewButton);
	
	btnNewButton.addActionListener(new AkcijaDodavanja());
	
	JLabel lblCijena = new JLabel("Cijena (visoka sezona):");
	lblCijena.setHorizontalAlignment(SwingConstants.RIGHT);
	lblCijena.setBounds(10, 254, 161, 20);
	frmUnosHotela.getContentPane().add(lblCijena);
	
	textField_3 = new JTextField();
	textField_3.setColumns(10);
	textField_3.setBounds(191, 254, 138, 20);
	frmUnosHotela.getContentPane().add(textField_3);
	
	JLabel lblCijenaUNiskoj = new JLabel("Cijena (niska sezona):");
	lblCijenaUNiskoj.setHorizontalAlignment(SwingConstants.RIGHT);
	lblCijenaUNiskoj.setBounds(10, 286, 161, 20);
	frmUnosHotela.getContentPane().add(lblCijenaUNiskoj);
	
	JLabel lbDestinacija = new JLabel("Destinacija:");
	lbDestinacija.setHorizontalAlignment(SwingConstants.RIGHT);
	lbDestinacija.setBounds(10, 316, 161, 20);
	frmUnosHotela.getContentPane().add(lbDestinacija);
	
	textField_6 = new JTextField();
	textField_6.setColumns(10);
	textField_6.setBounds(191, 286, 138, 20);
	frmUnosHotela.getContentPane().add(textField_6);
	
	JLabel lblNewLabel = new JLabel("Unesite podatke o novom hotelu");
	lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblNewLabel.setBounds(88, 24, 213, 14);
	frmUnosHotela.getContentPane().add(lblNewLabel);
	
    dateChooser = new JDateChooser();
	dateChooser.setBounds(189, 192, 140, 20);
	frmUnosHotela.getContentPane().add(dateChooser);
	
    dateChooser_1 = new JDateChooser();
	dateChooser_1.setBounds(189, 223, 140, 20);
	frmUnosHotela.getContentPane().add(dateChooser_1);
	
	
	
	textField_7 = new JTextField();
	textField_7.setColumns(10);
	textField_7.setBounds(191, 316, 138, 20);
	frmUnosHotela.getContentPane().add(textField_7);
		
		JMenuBar menuBar = new JMenuBar();
		frmUnosHotela.setJMenuBar(menuBar);
		
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
					frmUnosHotela.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 2){
					PocetnaFormaAgent forma = new PocetnaFormaAgent();
					frmUnosHotela.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 3){
					PocetnaFormaSupervizor forma = new PocetnaFormaSupervizor();
					frmUnosHotela.setVisible(false);
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
				frmUnosHotela.setVisible(false);
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
				frmUnosHotela.setVisible(false);
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
					frmUnosHotela.setVisible(false);
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
				frmUnosHotela.setVisible(false);
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
				frmUnosHotela.setVisible(false);
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
	public void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeHotela window = new DodavanjeHotela();
					window.frmUnosHotela.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
		
	}
}

