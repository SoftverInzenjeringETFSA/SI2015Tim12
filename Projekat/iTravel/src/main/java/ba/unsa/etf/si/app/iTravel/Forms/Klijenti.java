package ba.unsa.etf.si.app.iTravel.Forms;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ba.unsa.etf.si.app.iTravel.BLL.NasModel;
import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Klijenti {

	private UnitOfWork uow = new UnitOfWork();
	
	private JFrame frmPrikazKlijenata;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu() {
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
		
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
		frmPrikazKlijenata = new JFrame();
		frmPrikazKlijenata.setTitle("Prikaz klijenata");
		frmPrikazKlijenata.setBounds(100, 100, 876, 407);
		frmPrikazKlijenata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrikazKlijenata.getContentPane().setLayout(null);
		frmPrikazKlijenata.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 840, 325);
		frmPrikazKlijenata.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		Object[][] podaci=uow.getKlijentiService().PrikaziSveKlijente();
		
		table.setModel(new NasModel(
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
		
		JButton btnIzlaz = new JButton("Izlaz");
		btnIzlaz.setBounds(691, 222, 150, 30);
		frmPrikazKlijenata.getContentPane().add(btnIzlaz);
		
		JMenuBar menuBar = new JMenuBar();
		frmPrikazKlijenata.setJMenuBar(menuBar);
		
		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		
		JMenuItem mntmPoetna = new JMenuItem("Početna");
		mntmPoetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Pocetna(frmPrikazKlijenata);
			}
		});
		mnMeni.add(mntmPoetna);
		
		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mntmHoteli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Hoteli(frmPrikazKlijenata);
			}
		});
		mnMeni.add(mntmHoteli);
		mntmHoteli.setEnabled(postavke[1]);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Rezervacije(frmPrikazKlijenata);
			}
		});
		mnMeni.add(mntmRezervacije);
		mntmRezervacije.setEnabled(postavke[2]);
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
			mntmKorisnici.addActionListener(new ActionListener() {
						
				public void actionPerformed(ActionEvent e) {
					Meni.Korisnici(frmPrikazKlijenata);
				}
			});
			mnMeni.add(mntmKorisnici);
			mntmKorisnici.setEnabled(postavke[4]);
			}
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
		JMenuItem mntmIzvjestaji = new JMenuItem("Izvještaji");
		mntmIzvjestaji.addActionListener(new ActionListener() {
					
			public void actionPerformed(ActionEvent e) {
				Meni.Izvjestaj(frmPrikazKlijenata);
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
				Meni.HelpForma("/HelpImages/KlijentiSlika.jpg");
			}
		});
		mnPomo.add(mntmOFormi);
	}
}
