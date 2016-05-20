package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.hibernate.engine.internal.StatisticalLoggingSessionEventListener;

import ba.unsa.etf.si.app.iTravel.BLL.NasModel;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Korisnici {
	
	private UnitOfWork uow = new UnitOfWork();


	private JFrame frmPrikazKorisnika;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Korisnici window = new Korisnici();
					window.frmPrikazKorisnika.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}
	
	public void OsvjeziFormu()
	{
		Object[][] podaci= uow.getKorisniciService().PrikaziSveKorisnike();
		
		table.setModel(new NasModel(
				podaci,
				new String[] {
					"Ime", "Prezime", "JMBG", "Broj li\u010Dne karte", "Adresa", "Telefon", "E-mail", "Username", "Tip korisnika", "Id"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		
		table.getColumnModel().getColumn(2).setPreferredWidth(89);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(99);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		TableColumnModel tcm = table.getColumnModel();
		if(tcm.getColumnCount()==10)
			tcm.removeColumn( tcm.getColumn(9) );
	}
	
	private void OtvoriKreirajKorisnikaFormu(boolean modif, int idSelektovanogKorisnika)
	{
		if(modif)
	 	{
 			KreiranjeKorisnickogRacuna forma = new KreiranjeKorisnickogRacuna(this, idSelektovanogKorisnika);
 			forma.PrikaziFormu();
 		}
 		else		
 		{
 			KreiranjeKorisnickogRacuna forma = new KreiranjeKorisnickogRacuna(this);
 			forma.PrikaziFormu();
 		}

	}

	/**
	 * Create the application.
	 */
	public Korisnici() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
		frmPrikazKorisnika = new JFrame();
		frmPrikazKorisnika.setTitle("Prikaz korisnika");
		frmPrikazKorisnika.setBounds(100, 100, 876, 336);
		frmPrikazKorisnika.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrikazKorisnika.getContentPane().setLayout(null);
		frmPrikazKorisnika.setLocationRelativeTo(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 29, 821, 175);
		frmPrikazKorisnika.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(false);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		Object[][] podaci= uow.getKorisniciService().PrikaziSveKorisnike();
		
		table.setModel(new NasModel(
			podaci,
			new String[] {
				"Ime", "Prezime", "JMBG", "Broj li\u010Dne karte", "Adresa", "Telefon", "E-mail", "Username", "Tip korisnika", "Id"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		table.getColumnModel().getColumn(2).setPreferredWidth(89);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(99);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		TableColumnModel tcm = table.getColumnModel();
		if(tcm.getColumnCount()==10)
			tcm.removeColumn( tcm.getColumn(9) );
		
		JButton button_izlaz = new JButton("Izlaz");
		button_izlaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				frmPrikazKorisnika.dispose();
			}
		});
		button_izlaz.setBounds(676, 226, 150, 30);
		frmPrikazKorisnika.getContentPane().add(button_izlaz);
		
		JButton btnModifikujKorisnike = new JButton("Modifikuj korisnika");
		btnModifikujKorisnike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	        	int row=table.getSelectedRow();
	        	if(row<0) 
	        	{
	        		JOptionPane.showMessageDialog(null, "Nije odabran korisnik.");
	        	}
	        	else
	        	{
	        		int idSelektovanogKorisnika = Integer.parseInt(table.getModel().getValueAt(row, 9).toString());			
					//KorisnickiRacun korisnickiRacun = uow.getKorisnickiRacunService().dajKorisnika(idSelektovanogKorisnika);
					OtvoriKreirajKorisnikaFormu(true, idSelektovanogKorisnika);
	        	}
			}
		});
		btnModifikujKorisnike.setBounds(180, 226, 150, 30);
		frmPrikazKorisnika.getContentPane().add(btnModifikujKorisnike);
		
		JButton btnObriiKorisnika = new JButton("Obri\u0161i korisnika");
		btnObriiKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row=table.getSelectedRow();
				if(row<0) 
				{
					JOptionPane.showMessageDialog(null, "Nije odabran korisnik.");
				}
				else
				{
					int reply = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite izbrisati korisnika",
							"Potvrda", JOptionPane.YES_NO_OPTION);
					
			        if (reply == JOptionPane.YES_OPTION) {
			        	
			        	
						int idSelektovanogKorisnika = Integer.parseInt(table.getModel().getValueAt(row, 9).toString());
						
			        	boolean uspjesno = uow.getKorisnickiRacunService().obrisiKorisnika(idSelektovanogKorisnika);
			        	
			        	if(uspjesno)
			        	{
			        		JOptionPane.showMessageDialog(null, "Uspješno obrisan korisnik.");
			        		OsvjeziFormu();
			        	}	
			        	else
			        		JOptionPane.showMessageDialog(null, "Dogodila se greška pri brisanju korisnika.");
			        }
				}
				
		       
			}
		});
		
		btnObriiKorisnika.setBounds(340, 226, 150, 30);
		frmPrikazKorisnika.getContentPane().add(btnObriiKorisnika);
		
		JButton btnDodajKorisnika = new JButton("Dodaj korisnika");
		btnDodajKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OtvoriKreirajKorisnikaFormu(false, -1);
			}
		});
		btnDodajKorisnika.setBounds(20, 226, 150, 30);
		frmPrikazKorisnika.getContentPane().add(btnDodajKorisnika);
		
		JMenuBar menuBar = new JMenuBar();
		frmPrikazKorisnika.setJMenuBar(menuBar);
		
		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		
		JMenuItem mntmPoetna = new JMenuItem("Početna");
		mntmPoetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Pocetna(frmPrikazKorisnika);
			}
		});
		mnMeni.add(mntmPoetna);
		
		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mntmHoteli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Hoteli(frmPrikazKorisnika);
			}
		});
		mnMeni.add(mntmHoteli);
		mntmHoteli.setEnabled(postavke[1]);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Rezervacije(frmPrikazKorisnika);
			}
		});
		mnMeni.add(mntmRezervacije);
		mntmRezervacije.setEnabled(postavke[2]);
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Meni.Klijenti(frmPrikazKorisnika);
				}
			});
				mnMeni.add(mntmKlijenti);
				mntmKlijenti.setEnabled(postavke[3]);
			}
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
		JMenuItem mntmIzvjestaji = new JMenuItem("Izvještaji");
		mntmIzvjestaji.addActionListener(new ActionListener() {
					
			public void actionPerformed(ActionEvent e) {
				Meni.Izvjestaj(frmPrikazKorisnika);
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
	}
	
	
}
