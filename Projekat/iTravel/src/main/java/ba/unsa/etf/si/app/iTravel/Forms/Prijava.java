package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;

import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class Prijava {
	
	private static UnitOfWork uow = new UnitOfWork();
	
	private JFrame frmPrijava;
	private JTextField textField;
	private JPasswordField passwordField;
	
	class AkcijaIzlaz implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			
					
			
			String usernameValue = textField.getText();
			char[] passwordValue = passwordField.getPassword();
			
			if(uow.getPrijavaService().ProvjeriPristupnePodatke(usernameValue, passwordValue))
			{		
				
				JOptionPane.showMessageDialog(null, "Dobrodošli u iTravel", "Poruka o prijavi", JOptionPane.INFORMATION_MESSAGE);
				
				if(UserContext.getInstance().getRoleID() == 1)
				{
					PocetnaFormaAdministrator.PrikaziFormu();
					frmPrijava.setVisible(false);
				}
				else if (UserContext.getInstance().getRoleID() == 2)
				{
					PocetnaFormaAgent.PrikaziFormu();
					frmPrijava.setVisible(false);
				}
				else if(UserContext.getInstance().getRoleID() == 3)
				{
					PocetnaFormaSupervizor.PrikaziFormu();
					frmPrijava.setVisible(false);
				}
				else
				{
					PocetnaFormaAgent.PrikaziFormu();
					frmPrijava.setVisible(false);
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Pogrešni pristupni podaci", "Poruka o prijavi", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}	
	
	public static void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prijava window = new Prijava();
					window.frmPrijava.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prijava window = new Prijava();
					window.frmPrijava.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
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
		frmPrijava.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrijava.getContentPane().setLayout(null);
		frmPrijava.setLocationRelativeTo(null);
		
		
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
		frmPrijava.getRootPane().setDefaultButton(btnPotvrdi);
		
		btnPotvrdi.addActionListener(new AkcijaIzlaz());
		
		passwordField = new JPasswordField();
		passwordField.setBounds(123, 102, 150, 20);
		frmPrijava.getContentPane().add(passwordField);
	}
}
