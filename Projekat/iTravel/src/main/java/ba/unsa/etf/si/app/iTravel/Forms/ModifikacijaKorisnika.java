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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifikacijaKorisnika {

	private JFrame frmModifikacijaKorisnika;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifikacijaKorisnika window = new ModifikacijaKorisnika ();
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
		
		textField = new JTextField();
		textField.setBounds(134, 60, 139, 20);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(134, 91, 139, 20);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(134, 122, 139, 20);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(134, 153, 139, 20);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(134, 184, 139, 20);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(134, 215, 139, 20);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(134, 246, 139, 20);
		textField_7.setColumns(10);
		frmModifikacijaKorisnika.getContentPane().setLayout(null);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_3);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_4);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_5);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_6);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_7);
		frmModifikacijaKorisnika.getContentPane().add(lblNewLabel_8);
		frmModifikacijaKorisnika.getContentPane().add(comboBox);
		frmModifikacijaKorisnika.getContentPane().add(textField_7);
		frmModifikacijaKorisnika.getContentPane().add(textField_6);
		frmModifikacijaKorisnika.getContentPane().add(textField_5);
		frmModifikacijaKorisnika.getContentPane().add(textField_4);
		frmModifikacijaKorisnika.getContentPane().add(textField_3);
		frmModifikacijaKorisnika.getContentPane().add(textField_1);
		frmModifikacijaKorisnika.getContentPane().add(textField);
		frmModifikacijaKorisnika.getContentPane().add(btnNewButton);
		
		JLabel lblUnesitePodatkeO = new JLabel("Unesite nove podatke o korisniku");
		lblUnesitePodatkeO.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUnesitePodatkeO.setBounds(60, 24, 222, 14);
		frmModifikacijaKorisnika.getContentPane().add(lblUnesitePodatkeO);
		
		JLabel label = new JLabel("\u0160ifra:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(25, 281, 97, 14);
		frmModifikacijaKorisnika.getContentPane().add(label);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(134, 277, 139, 20);
		frmModifikacijaKorisnika.getContentPane().add(textField_2);
		
		JMenuItem menuItem = new JMenuItem("New menu item");
		menuItem.setBounds(-5, 85, 129, 22);
		frmModifikacijaKorisnika.getContentPane().add(menuItem);
		
		JMenuBar menuBar = new JMenuBar();
		frmModifikacijaKorisnika.setJMenuBar(menuBar);
		
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
