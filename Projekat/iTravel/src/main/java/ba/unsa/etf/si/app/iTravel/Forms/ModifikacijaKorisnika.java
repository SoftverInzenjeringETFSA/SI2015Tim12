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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifikacijaKorisnika {
	private UnitOfWork uow = new UnitOfWork();
	private JFrame frmModifikacijaKorisnika;
	private JTextField textFieldModifikacija;
	private JTextField textField_1Modifikacija;
	private JTextField textField_3Modifikacija;
	private JTextField textField_4Modifikacija;
	private JTextField textField_5Modifikacija;
	private JTextField textField_6Modifikacija;
	private JTextField textField_7Modifikacija;
	private JTextField textField_2Modifikacija;

	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifikacijaKorisnika window = new ModifikacijaKorisnika();
					window.frmModifikacijaKorisnika.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public ModifikacijaKorisnika() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
		frmModifikacijaKorisnika = new JFrame();
		frmModifikacijaKorisnika.setTitle("Modifikacija korisnika");
		frmModifikacijaKorisnika.setBounds(100, 100, 362, 454);
		frmModifikacijaKorisnika.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModifikacijaKorisnika.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Ime:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(25, 60, 99, 14);
		
		JLabel lblNewLabel_3 = new JLabel("Broj li\u010Dne karte:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(25, 122, 99, 14);
		
		JLabel lblNewLabel_4 = new JLabel("Adresa:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(25, 153, 99, 14);
		
		JLabel lblNewLabel_5 = new JLabel("Telefon:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(25, 184, 99, 14);
		
		JLabel lblNewLabel_6 = new JLabel("E-mail:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(25, 215, 99, 14);
		
		JLabel lblNewLabel_7 = new JLabel("Korisni\u010Dko ime:");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(25, 246, 99, 14);
		
		JLabel lblNewLabel_8 = new JLabel("Tip korisnika:");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setBounds(25, 308, 99, 14);
		
		JButton btnNewButton = new JButton("Modifikuj");
		btnNewButton.setBounds(90, 354, 150, 30);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "Supervizor", "Putni\u010Dki agent"}));
		comboBox.setBounds(134, 306, 139, 24);
		
		textFieldModifikacija = new JTextField();
		textFieldModifikacija.setBounds(134, 60, 139, 20);
		textFieldModifikacija.setColumns(10);
		
		textField_1Modifikacija = new JTextField();
		textField_1Modifikacija.setBounds(134, 91, 139, 20);
		textField_1Modifikacija.setColumns(10);
		
		textField_3Modifikacija = new JTextField();
		textField_3Modifikacija.setBounds(134, 122, 139, 20);
		textField_3Modifikacija.setColumns(10);
		
		textField_4Modifikacija = new JTextField();
		textField_4Modifikacija.setBounds(134, 153, 139, 20);
		textField_4Modifikacija.setColumns(10);
		
		textField_5Modifikacija = new JTextField();
		textField_5Modifikacija.setBounds(134, 184, 139, 20);
		textField_5Modifikacija.setColumns(10);
		
		textField_6Modifikacija = new JTextField();
		textField_6Modifikacija.setBounds(134, 215, 139, 20);
		textField_6Modifikacija.setColumns(10);
		
		textField_7Modifikacija = new JTextField();
		textField_7Modifikacija.setBounds(134, 246, 139, 20);
		textField_7Modifikacija.setColumns(10);
		frmModifikacijaKorisnika.getContentPane().setLayout(null);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_3);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_4);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_5);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_6);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_7);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_8);
		frmModifikacijaKorisnika.getContentPane().add(comboBox);
		frmModifikacijaKorisnika.getContentPane().add(textField_7Modifikacija);
		frmModifikacijaKorisnika.getContentPane().add(textField_6Modifikacija);
		frmModifikacijaKorisnika.getContentPane().add(textField_5Modifikacija);
		frmModifikacijaKorisnika.getContentPane().add(textField_4Modifikacija);
		frmModifikacijaKorisnika.getContentPane().add(textField_3Modifikacija);
		frmModifikacijaKorisnika.getContentPane().add(textField_1Modifikacija);
		frmModifikacijaKorisnika.getContentPane().add(textFieldModifikacija);
		frmModifikacijaKorisnika.getContentPane().add(btnNewButton);
		
		JLabel lblUnesitePodatkeO = new JLabel("Unesite nove podatke o korisniku");
		lblUnesitePodatkeO.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnesitePodatkeO.setBounds(60, 24, 222, 14);
		frmModifikacijaKorisnika.getContentPane().add(lblUnesitePodatkeO);
		
		JLabel label = new JLabel("\u0160ifra:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(25, 281, 97, 14);
		frmModifikacijaKorisnika.getContentPane().add(label);
		
		textField_2Modifikacija = new JTextField();
		textField_2Modifikacija.setColumns(10);
		textField_2Modifikacija.setBounds(134, 277, 139, 20);
		frmModifikacijaKorisnika.getContentPane().add(textField_2Modifikacija);
		
		JMenuItem menuItem = new JMenuItem("New menu item");
		menuItem.setBounds(-5, 85, 129, 22);
		frmModifikacijaKorisnika.getContentPane().add(menuItem);
		
		JMenuBar menuBar = new JMenuBar();
		frmModifikacijaKorisnika.setJMenuBar(menuBar);
		
		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		
		JMenuItem mntmPoetna = new JMenuItem("Početna");
		mntmPoetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Pocetna(frmModifikacijaKorisnika);
			}
		});
		mnMeni.add(mntmPoetna);
		
		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mntmHoteli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Hoteli(frmModifikacijaKorisnika);
			}
		});
		mnMeni.add(mntmHoteli);
		mntmHoteli.setEnabled(postavke[1]);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Rezervacije(frmModifikacijaKorisnika);
			}
		});
		mnMeni.add(mntmRezervacije);
		mntmRezervacije.setEnabled(postavke[2]);
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Meni.Klijenti(frmModifikacijaKorisnika);
				}
			});
				mnMeni.add(mntmKlijenti);
				mntmRezervacije.setEnabled(postavke[3]);
			}
			
			if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
			mntmKorisnici.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Meni.Korisnici(frmModifikacijaKorisnika);
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
				Meni.HelpForma("/HelpImages/KreiranjeKorisnika.jpg");
			}
		});
		mnPomo.add(mntmOFormi);
	}
}
