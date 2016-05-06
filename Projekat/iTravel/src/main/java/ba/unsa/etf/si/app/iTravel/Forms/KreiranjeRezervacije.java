package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;

import com.toedter.calendar.JCalendar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KreiranjeRezervacije {

	private JFrame frmKreiranjeRezervacije;
	private JTable table;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KreiranjeRezervacije window = new KreiranjeRezervacije();
					window.frmKreiranjeRezervacije.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KreiranjeRezervacije() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKreiranjeRezervacije = new JFrame();
		frmKreiranjeRezervacije.setTitle("Kreiranje rezervacije");
		frmKreiranjeRezervacije.setBounds(100, 100, 836, 553);
		frmKreiranjeRezervacije.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKreiranjeRezervacije.getContentPane().setLayout(null);
		frmKreiranjeRezervacije.setLocationRelativeTo(null);
		
		JLabel lblDestinacija = new JLabel("Destinacija:");
		lblDestinacija.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestinacija.setBounds(35, 53, 66, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblDestinacija);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(111, 50, 153, 20);
		frmKreiranjeRezervacije.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Unesite podatke o novoj rezervaciji");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(20, 11, 287, 20);
		frmKreiranjeRezervacije.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dostupni hoteli na odabranoj destinaciji:");
		lblNewLabel_1.setBounds(30, 92, 234, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 118, 764, 128);
		frmKreiranjeRezervacije.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Naziv hotela", "Broj zvjezdica", "Broj slobodnih soba", "Cijena u VS", "Cijena u NS", "Prijevoz", "Opis"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(112);
		table.getColumnModel().getColumn(1).setPreferredWidth(76);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(68);
		table.getColumnModel().getColumn(5).setPreferredWidth(58);
		table.getColumnModel().getColumn(6).setPreferredWidth(194);
		scrollPane.setViewportView(table);
		
		JLabel lblOd = new JLabel("Od:");
		lblOd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOd.setBounds(77, 271, 25, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblOd);
		
		JLabel lblDo = new JLabel("Do:");
		lblDo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDo.setBounds(359, 271, 25, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblDo);
		
		JLabel lblNewLabel_2 = new JLabel("Podaci o klijentu:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(30, 317, 145, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblNewLabel_2);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIme.setBounds(30, 354, 72, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrezime.setBounds(30, 385, 72, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblPrezime);
		
		JLabel lblJmbg = new JLabel("JMBG:");
		lblJmbg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJmbg.setBounds(30, 416, 72, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblJmbg);
		
		JLabel lblBrojPasoa = new JLabel("Broj paso\u0161a:");
		lblBrojPasoa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrojPasoa.setBounds(30, 451, 72, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblBrojPasoa);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(394, 448, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(textField_4);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefon.setBounds(311, 451, 73, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblTelefon);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(294, 354, 90, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblEmail);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(394, 354, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(textField_5);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresa.setBounds(294, 388, 90, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblAdresa);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(394, 385, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(textField_6);
		
		JLabel lblDatumRoenja = new JLabel("Datum ro\u0111enja:");
		lblDatumRoenja.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatumRoenja.setBounds(294, 419, 90, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblDatumRoenja);
		
		JLabel lblCijena = new JLabel("Cijena:");
		lblCijena.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCijena.setBounds(601, 353, 61, 21);
		frmKreiranjeRezervacije.getContentPane().add(lblCijena);
		
		JLabel label = new JLabel("954,23");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(663, 353, 61, 21);
		frmKreiranjeRezervacije.getContentPane().add(label);
		
		JLabel lblNewLabel_3 = new JLabel("KM");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(721, 354, 46, 20);
		frmKreiranjeRezervacije.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Potvrdi");
		btnNewButton.setBounds(598, 399, 150, 30);
		frmKreiranjeRezervacije.getContentPane().add(btnNewButton);
		
		JButton btnPotvrdi = new JButton("Izlaz");
		btnPotvrdi.setBounds(598, 438, 150, 30);
		frmKreiranjeRezervacije.getContentPane().add(btnPotvrdi);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(112, 382, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(112, 351, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(112, 413, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(112, 445, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(textField_3);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(110, 271, 153, 20);
		frmKreiranjeRezervacije.getContentPane().add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(393, 271, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(dateChooser_1);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(394, 417, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(dateChooser_2);
		
		JCheckBox chckbxPrijevoz = new JCheckBox("Prijevoz");
		chckbxPrijevoz.setBounds(600, 267, 97, 23);
		frmKreiranjeRezervacije.getContentPane().add(chckbxPrijevoz);
		
		JMenuBar menuBar = new JMenuBar();
		frmKreiranjeRezervacije.setJMenuBar(menuBar);
		
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
}
