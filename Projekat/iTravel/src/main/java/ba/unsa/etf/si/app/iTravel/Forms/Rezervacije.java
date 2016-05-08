package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Rezervacije {

	private JFrame frmPrikazRezervacija;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rezervacije window = new Rezervacije();
					window.frmPrikazRezervacija.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Rezervacije() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrikazRezervacija = new JFrame();
		frmPrikazRezervacija.setTitle("Prikaz rezervacija");
		frmPrikazRezervacija.setBounds(100, 100, 876, 318);
		frmPrikazRezervacija.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrikazRezervacija.getContentPane().setLayout(null);
		frmPrikazRezervacija.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 29, 821, 175);
		frmPrikazRezervacija.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Bec", "Piramida", "Kenan", "Pr\u0161e\u0161", "856,26", "12.06.2016.", "21.06.2016", "Da", "Potvr\u0111eno"},
				{"Zagreb", "Dobar Hotel", "Emina", "Prlja", "546,43", "20.05.2016.", "25.05.2016.", "Ne", "Nije potvr\u0111eno"},
			},
			new String[] {
				"Destinacija", "Hotel", "Ime klijenta", "Prezime klijenta", "Cijena", "Od (datum)", "Do (datum)", "Prijevoz", "Status"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(89);
		table.getColumnModel().getColumn(3).setPreferredWidth(102);
		table.getColumnModel().getColumn(4).setPreferredWidth(59);
		table.getColumnModel().getColumn(5).setPreferredWidth(79);
		table.getColumnModel().getColumn(6).setPreferredWidth(83);
		table.getColumnModel().getColumn(7).setPreferredWidth(73);
		table.getColumnModel().getColumn(8).setPreferredWidth(87);
		scrollPane.setViewportView(table);
		
		JButton button_izlaz = new JButton("Izlaz");
		button_izlaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		button_izlaz.setBounds(691, 226, 150, 30);
		frmPrikazRezervacija.getContentPane().add(button_izlaz);
		
		JButton btnModifikujKorisnike = new JButton("Potvrdi rezervaciju");
		btnModifikujKorisnike.setBounds(180, 226, 150, 30);
		frmPrikazRezervacija.getContentPane().add(btnModifikujKorisnike);
		
		JButton btnObriiKorisnika = new JButton("Otka\u017Ei rezervaciju");
		btnObriiKorisnika.setBounds(340, 226, 150, 30);
		frmPrikazRezervacija.getContentPane().add(btnObriiKorisnika);
		
		JButton btnDodajKorisnika = new JButton("Kreiraj rezervaciju");
		btnDodajKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KreiranjeRezervacije forma=new KreiranjeRezervacije();
				//frmPrikazRezervacija.setVisible(false);
				forma.PrikaziFormu();
			}
		});
		btnDodajKorisnika.setBounds(20, 226, 150, 30);
		frmPrikazRezervacija.getContentPane().add(btnDodajKorisnika);
		
		JMenuBar menuBar = new JMenuBar();
		frmPrikazRezervacija.setJMenuBar(menuBar);
		
		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		
		JMenuItem mntmPoetna = new JMenuItem("Početna");
		mnMeni.add(mntmPoetna);
		
		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mnMeni.add(mntmHoteli);
		
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
