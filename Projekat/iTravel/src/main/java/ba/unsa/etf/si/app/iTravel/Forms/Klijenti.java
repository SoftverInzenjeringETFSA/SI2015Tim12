package ba.unsa.etf.si.app.iTravel.Forms;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.PrikazKlijenata;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
					UnitOfWork.logger.error(e);
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
		frmPrikazKlijenata.setBounds(100, 100, 876, 318);
		frmPrikazKlijenata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrikazKlijenata.getContentPane().setLayout(null);
		frmPrikazKlijenata.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 29, 821, 175);
		frmPrikazKlijenata.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		PrikazKlijenata pk=new PrikazKlijenata();
		Object[][] podaci=pk.PrikaziSveKlijente();
		
		table.setModel(new DefaultTableModel(
			podaci,
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
		btnModifikujKorisnike.setBounds(20, 222, 150, 30);
		frmPrikazKlijenata.getContentPane().add(btnModifikujKorisnike);
		
		JButton btnObriiKorisnika = new JButton("Obri\u0161i klijenta");
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
}
