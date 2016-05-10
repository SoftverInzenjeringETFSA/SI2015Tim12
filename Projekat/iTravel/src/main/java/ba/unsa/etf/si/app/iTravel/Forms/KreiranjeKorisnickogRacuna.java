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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KreiranjeKorisnickogRacuna {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KreiranjeKorisnickogRacuna window = new KreiranjeKorisnickogRacuna();
					window.frmKreirajiKorisnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KreiranjeKorisnickogRacuna() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKreirajiKorisnika = new JFrame();
		frmKreirajiKorisnika.setTitle("Kreiranje korisni\u010Dkog naloga");
		frmKreirajiKorisnika.setBounds(100, 100, 353, 497);
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
		
		JButton btnNewButton = new JButton("Kreiraj");
		btnNewButton.setBounds(90, 384, 150, 30);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "Supervizor", "Putni\u010Dki agent"}));
		comboBox.setBounds(134, 334, 139, 24);
		
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
		frmKreirajiKorisnika.getContentPane().add(comboBox);
		frmKreirajiKorisnika.getContentPane().add(textField_7);
		frmKreirajiKorisnika.getContentPane().add(textField_6);
		frmKreirajiKorisnika.getContentPane().add(textField_5);
		frmKreirajiKorisnika.getContentPane().add(textField_4);
		frmKreirajiKorisnika.getContentPane().add(textField_3);
		frmKreirajiKorisnika.getContentPane().add(textField_2);
		frmKreirajiKorisnika.getContentPane().add(textField_1);
		frmKreirajiKorisnika.getContentPane().add(textField);
		frmKreirajiKorisnika.getContentPane().add(btnNewButton);
		
		JLabel lblUnesitePodatkeO = new JLabel("Unesite podatke o novom korisniku");
		lblUnesitePodatkeO.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnesitePodatkeO.setBounds(53, 21, 231, 14);
		frmKreirajiKorisnika.getContentPane().add(lblUnesitePodatkeO);
		
		JLabel lblifra = new JLabel("\u0160ifra:");
		lblifra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblifra.setBounds(25, 307, 97, 14);
		frmKreirajiKorisnika.getContentPane().add(lblifra);
		
		textField_8 = new JTextField();
		textField_8.setBounds(134, 303, 139, 20);
		frmKreirajiKorisnika.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		frmKreirajiKorisnika.setJMenuBar(menuBar);
		
		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		
		JMenuItem mntmPoetna = new JMenuItem("Početna");
		mnMeni.add(mntmPoetna);
		
		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mnMeni.add(mntmHoteli);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mnMeni.add(mntmRezervacije);
		
		JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
		mnMeni.add(mntmKlijenti);
		
		JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
		mnMeni.add(mntmKorisnici);
		
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
					KreiranjeKorisnickogRacuna window = new KreiranjeKorisnickogRacuna();
					window.frmKreirajiKorisnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
