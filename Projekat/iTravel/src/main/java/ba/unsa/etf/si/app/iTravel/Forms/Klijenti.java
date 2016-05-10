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
import javax.swing.JOptionPane;

public class Klijenti {

	private JFrame frmPrikazKlijenata;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Klijenti window = new Klijenti();
					window.frmPrikazKlijenata.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Klijenti() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrikazKlijenata = new JFrame();
		frmPrikazKlijenata.setTitle("Prikaz klijenata");
		frmPrikazKlijenata.setBounds(100, 100, 876, 329);
		frmPrikazKlijenata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrikazKlijenata.getContentPane().setLayout(null);
		frmPrikazKlijenata.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 29, 821, 175);
		frmPrikazKlijenata.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Predrag", "Simani\u0107", "1203993896541", "123456", "061789654", "psimanic1@etf.unsa.ba", "Neka Adresa 5", "12.03.1993."},
				{"Emina", "Prlja", "1405993174515", "124512", "062845854", "eprlja1@etf.unsa.ba", "Adresa 6", "14.05.1993."},
				{"Adna", "Tahi\u0107", "1001993123323", "321321", "062365456", null, "Neko Mjesto 12", null},
			},
			new String[] {
				"Ime", "Prezime", "JMBG", "Broj paso\u0161a", "Broj telefona", "E-mail", "Adresa", "Datum ro\u0111enja"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(88);
		table.getColumnModel().getColumn(6).setPreferredWidth(95);
		scrollPane.setViewportView(table);
		
		JButton btnModifikujKorisnike = new JButton("Modifikuj klijenta");
		btnModifikujKorisnike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnModifikujKorisnike.setBounds(20, 222, 150, 30);
		frmPrikazKlijenata.getContentPane().add(btnModifikujKorisnike);
		
		JButton btnObriiKorisnika = new JButton("Obri\u0161i klijenta");
		btnObriiKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Jeste li sigurni da želite obrisati odabranog klijnta?", "Brisanje klijenta", JOptionPane.OK_CANCEL_OPTION);
			}
		});
		btnObriiKorisnika.setBounds(180, 222, 150, 30);
		frmPrikazKlijenata.getContentPane().add(btnObriiKorisnika);
		
		JButton btnIzlaz = new JButton("Izlaz");
		btnIzlaz.setBounds(691, 222, 150, 30);
		frmPrikazKlijenata.getContentPane().add(btnIzlaz);
		
		JMenuBar menuBar = new JMenuBar();
		frmPrikazKlijenata.setJMenuBar(menuBar);
		
		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		
		JMenuItem mntmPoetna = new JMenuItem("Početna");
		mnMeni.add(mntmPoetna);
		
		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mnMeni.add(mntmHoteli);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mnMeni.add(mntmRezervacije);
		
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
					Klijenti window = new Klijenti();
					window.frmPrikazKlijenata.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
