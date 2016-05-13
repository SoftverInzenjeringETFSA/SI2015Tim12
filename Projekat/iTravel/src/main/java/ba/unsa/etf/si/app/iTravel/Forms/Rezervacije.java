package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Klijent;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Racun;
import ba.unsa.etf.si.app.iTravel.DBModels.Rezervacija;
import ba.unsa.etf.si.app.iTravel.DBModels.RezervisaniTerminSoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Rezervacije {

	private JFrame frmPrikazRezervacija;
	private JTable table;
	private UnitOfWork uow=new UnitOfWork();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rezervacije window = new Rezervacije();
					window.frmPrikazRezervacija.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
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
		
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
		frmPrikazRezervacija = new JFrame();
		frmPrikazRezervacija.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				UcitajSveRezervacije();
			}
		});
		frmPrikazRezervacija.setTitle("Prikaz rezervacija");
		frmPrikazRezervacija.setBounds(100, 100, 876, 332);
		frmPrikazRezervacija.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrikazRezervacija.getContentPane().setLayout(null);
		frmPrikazRezervacija.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 29, 821, 175);
		frmPrikazRezervacija.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Destinacija", "Hotel", "Ime klijenta", "Prezime klijenta", "Cijena", "Od (datum)", "Do (datum)", "Prijevoz", "Status","id"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, Object.class, Object.class,String.class
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
				frmPrikazRezervacija.dispose();
			}
		});
		button_izlaz.setBounds(691, 226, 150, 30);
		frmPrikazRezervacija.getContentPane().add(button_izlaz);
		
		JButton btnModifikujKorisnike = new JButton("Potvrdi rezervaciju");
		btnModifikujKorisnike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PotvrdiRezervaciju();
			}
		});
		btnModifikujKorisnike.setBounds(180, 226, 150, 30);
		frmPrikazRezervacija.getContentPane().add(btnModifikujKorisnike);
		
		JButton btnObriiKorisnika = new JButton("Otka\u017Ei rezervaciju");
		btnObriiKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OtkaziRezervaciju();
			}
		});
		btnObriiKorisnika.setBounds(340, 226, 150, 30);
		frmPrikazRezervacija.getContentPane().add(btnObriiKorisnika);
		
		JButton btnDodajKorisnika = new JButton("Kreiraj rezervaciju");
		btnDodajKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KreiranjeRezervacije forma = new KreiranjeRezervacije();
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
		mntmPoetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 				
				if(UserContext.getInstance().getRoleID() == 1){
					PocetnaFormaAdministrator forma = new PocetnaFormaAdministrator();
					frmPrikazRezervacija.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 2){
					PocetnaFormaAgent forma = new PocetnaFormaAgent();
					frmPrikazRezervacija.setVisible(false);
					forma.PrikaziFormu();
				}
				else if(UserContext.getInstance().getRoleID() == 3){
					PocetnaFormaSupervizor forma = new PocetnaFormaSupervizor();
					frmPrikazRezervacija.setVisible(false);
					forma.PrikaziFormu();
				}
			}
		});
		mnMeni.add(mntmPoetna);
		
		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mntmHoteli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 				
				
				Hoteli forma = new Hoteli();
				frmPrikazRezervacija.setVisible(false);
				forma.PrikaziFormu();
			}
		});
		mnMeni.add(mntmHoteli);
		mntmHoteli.setEnabled(postavke[1]);
		
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					java.awt.Window win[] = java.awt.Window.getWindows(); 
					for(int i=0;i<win.length;i++){ 
					win[i].dispose(); 
					} 				
						Klijenti forma = new Klijenti();
						frmPrikazRezervacija.setVisible(false);
						forma.PrikaziFormu();	
					
				}
			});
				mnMeni.add(mntmKlijenti);
				mntmKlijenti.setEnabled(postavke[3]);
			}
			
			if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
			mntmKorisnici.addActionListener(new ActionListener() {
						
				public void actionPerformed(ActionEvent e) {
					java.awt.Window win[] = java.awt.Window.getWindows(); 
					for(int i=0;i<win.length;i++){ 
					win[i].dispose(); 
					} 				
					Korisnici forma = new Korisnici();
					frmPrikazRezervacija.setVisible(false);
					forma.PrikaziFormu();				
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
					Rezervacije window = new Rezervacije();
					window.frmPrikazRezervacija.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
		
	}
	
	
	private void UcitajSveRezervacije(){
		ArrayList<Rezervacija> rezervacije=uow.getRezervacijaService().dajSveRezervacije();

		DefaultTableModel model=(DefaultTableModel) table.getModel();
		model.setRowCount(0);
		table.setModel(model);
		if(rezervacije!=null) {
			for(int i=0; i<rezervacije.size(); i++){
				Klijent k= rezervacije.get(i).getKlijent();
				Osoba osoba=uow.getRezervacijaService().dajOsobuPoId(k.getOsoba().getOsobaId());
				
				Racun r=null;
				//if(rezervacije.get(i).getRacuns().iterator().hasNext())
				Object[] racuniList=rezervacije.get(i).getRacuns().toArray();
				r=(Racun) racuniList[0];
				RezervisaniTerminSoba termin=uow.getRezervacijaService().dajRezervisaneTermineZaRezervaciju(rezervacije.get(i).getRezervacijaId()).get(0);
				Soba s= uow.getRezervacijaService().dajSobu(termin.getSoba().getSobaId());
				Hotel h=s.getHotel();
				Destinacija d=uow.getDestinacijeService().VratiDestinacijuPoId(h.getDestinacija().getDestinacijaId());
				String status="Nije potvdjeno";
				if(r.getDatumUplate()!=null) status="Potvrdjeno";
				Object[] row={d.getNaziv(), h.getNaziv(),osoba.getIme(),osoba.getPrezime(),r.getCijena(),termin.getDatumPocetak(),termin.getDatumKraj(),rezervacije.get(i).getUkljucenPrevoz(),status,rezervacije.get(i).getRezervacijaId()};
				model.addRow(row);					
			}
		}
		table.setModel(model);
		
		TableColumnModel tcm = table.getColumnModel();
		if(tcm.getColumnCount()==10)
			tcm.removeColumn( tcm.getColumn(9) );
		
	}
	
	private void PotvrdiRezervaciju(){
		if(table.getSelectedRow()!=-1){
			int row=table.getSelectedRow();
			int idRezervacije=Integer.parseInt(table.getModel().getValueAt(row, 9).toString());
			boolean provjera= uow.getRezervacijaService().potvrdiRezervaciju(idRezervacije);
			if(provjera){
				JOptionPane.showMessageDialog(null, "Rezervacija je potvdjena", "Info", JOptionPane.INFORMATION_MESSAGE);
				UcitajSveRezervacije();
			}else
				JOptionPane.showMessageDialog(null, "Niste uspjeli potvrditi rezervaciju", "Info", JOptionPane.INFORMATION_MESSAGE);	
		}
	}
	
	private void OtkaziRezervaciju(){
		if(table.getSelectedRow()!=-1){
			int row=table.getSelectedRow();
			int idRezervacije=Integer.parseInt(table.getModel().getValueAt(row, 9).toString());
			
			boolean provjera=false;
			int dialogResult = JOptionPane.showConfirmDialog(null,"Da li zaista zelite otkazati rezervaciju?");
			if(dialogResult==JOptionPane.YES_OPTION)
				provjera=uow.getRezervacijaService().obrisiRezervaciju(idRezervacije);
			if(provjera){
				JOptionPane.showMessageDialog(null, "Rezervacija je otkazana", "Info", JOptionPane.INFORMATION_MESSAGE);
				UcitajSveRezervacije();
			}else
				JOptionPane.showMessageDialog(null, "Niste uspjeli otkazati rezervaciju", "Info", JOptionPane.INFORMATION_MESSAGE);	
		}
	}
}
