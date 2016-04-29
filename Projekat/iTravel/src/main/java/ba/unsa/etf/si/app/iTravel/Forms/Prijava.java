package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;
import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DAL.SessionFactoryDB;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.HotelRepository;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.hibernate.Session;
import org.hibernate.dialect.function.VarArgsSQLFunction;

import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

import java.awt.event.*;

public class Prijava {
	
	private static DBContext baza;
	
	private JFrame frmPrijava;
	private JTextField textField;
	private JPasswordField passwordField;
	
	class AkcijaIzlaz implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String nesto = textField.getText();
			
			// Primjer ucitavanja iz baze sada ide direktno u repository
			// ali ce se ovo promijeniti tako da cemo pozivati metode
			// iz bll pozivati
			
			int id = 1;
			Hotel noviHotel = baza.getHoteliRepo().ucitajIzBaze(id);
			
			// Primjer upisa u bazu podataka isto vrijedi i za ovaj komad koda
			// on ide u nize slojeve
			Hotel noviHotel1 = new Hotel();
			noviHotel1.setAdresa("TEST");
			noviHotel1.setBrojTelefona("0606060");
			noviHotel1.setGrad("Mostar");
			baza.getHoteliRepo().spasiUBazu(noviHotel1,  SessionFactoryDB.getSession());
			
			JOptionPane.showMessageDialog(null, noviHotel.getGrad().toString(), "Posluka", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		baza = new DBContext();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prijava window = new Prijava();
					window.frmPrijava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Prijava() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrijava = new JFrame();
		frmPrijava.setTitle("Prijava");
		frmPrijava.setBounds(100, 100, 350, 250);
		frmPrijava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrijava.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Unesite svoje korisni\u010Dke podatke");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(50, 29, 223, 21);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmPrijava.getContentPane().add(lblNewLabel);
		
		JLabel lblKorisnikoIme = new JLabel("Korisni\u010Dko ime:");
		lblKorisnikoIme.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKorisnikoIme.setBounds(25, 74, 88, 14);
		frmPrijava.getContentPane().add(lblKorisnikoIme);
		
		JLabel lblifra = new JLabel("\u0160ifra:");
		lblifra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblifra.setBounds(66, 105, 47, 14);
		frmPrijava.getContentPane().add(lblifra);
		
		textField = new JTextField();
		textField.setBounds(123, 71, 150, 20);
		frmPrijava.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setBounds(95, 142, 150, 30);
		frmPrijava.getContentPane().add(btnPotvrdi);
		
		btnPotvrdi.addActionListener(new AkcijaIzlaz());
		
		passwordField = new JPasswordField();
		passwordField.setBounds(123, 102, 150, 20);
		frmPrijava.getContentPane().add(passwordField);
	}
}
