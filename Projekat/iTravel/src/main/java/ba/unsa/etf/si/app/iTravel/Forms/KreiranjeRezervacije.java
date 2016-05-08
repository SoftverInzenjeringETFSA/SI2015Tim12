package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.persistence.Convert;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import com.toedter.calendar.JDateChooser;


import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork.UserContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Klijent;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Rezervacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

import com.toedter.calendar.JCalendar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.time.temporal.ChronoUnit;
import java.beans.PropertyChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionAdapter;

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
	private JTable table_Sobe;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JDateChooser dateChooser_2;
	private JCheckBox chckbxPrijevoz;
	private JComboBox<String> comboBox;
	private JLabel cijena;
	private UnitOfWork uow=new UnitOfWork();
	
	/**
	 * Launch the application.
	 */
	public void PrikaziFormu() {
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
		frmKreiranjeRezervacije.setBounds(100, 100, 901, 553);
		frmKreiranjeRezervacije.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKreiranjeRezervacije.getContentPane().setLayout(null);
		frmKreiranjeRezervacije.setLocationRelativeTo(null);
		
		JLabel lblDestinacija = new JLabel("Destinacija:");
		lblDestinacija.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestinacija.setBounds(35, 53, 66, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblDestinacija);

		comboBox = new JComboBox<String>();
		//ucitavanje hotela na osnovu izabrane destinacije
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				model.setRowCount(0);
				table.setModel(model);
				if(comboBox.getSelectedItem().toString()!="Izaberite destinaciju"){
					ArrayList<Hotel> h=uow.getHoteliService().VratiHotelZaDestinaciju(comboBox.getSelectedItem().toString());				
					if(h!=null) {
						for(int i=0; i<h.size(); i++){
							Object[] row={h.get(i).getNaziv(), h.get(i).getBrojZvjezdica(),h.get(i).getSobas().size(),h.get(i).getDestinacija().getOmogucenPrevoz(),h.get(i).getHotelId()};
							model.addRow(row);					
						}
					}
					table.setModel(model);
					
				}
				table_Sobe.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Broj kreveta", "Cijena u VS", "Cijena u NS", "Opis"
						}
					));
				
				TableColumnModel tcm = table.getColumnModel();
				if(tcm.getColumnCount()==5)
					tcm.removeColumn( tcm.getColumn(4) );
			}
		});
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
		scrollPane.setBounds(30, 118, 410, 128);
		frmKreiranjeRezervacije.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Naziv hotela", "Broj zvjezdica", "Broj dostupnih soba", "Prijevoz","Id"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(112);
		table.getColumnModel().getColumn(1).setPreferredWidth(76);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
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
		
		cijena= new JLabel("0");
		cijena.setFont(new Font("Tahoma", Font.BOLD, 15));
		cijena.setBounds(663, 353, 61, 21);
		frmKreiranjeRezervacije.getContentPane().add(cijena);
		
		JLabel lblNewLabel_3 = new JLabel("KM");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(721, 354, 46, 20);
		frmKreiranjeRezervacije.getContentPane().add(lblNewLabel_3);
				
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
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dateChooser.getJCalendar().setMinSelectableDate(new Date());
				if(dateChooser_1.getCalendar()!=null)
					dateChooser.getJCalendar().setMaxSelectableDate(dateChooser_1.getDate());
			}
		});
		//ucitavanje soba na promjenu datuma "od" ukoliko je postavljen datum "do" i ukoliko je odabran neki red u tabeli
		dateChooser.getDateEditor().addPropertyChangeListener(
	    new PropertyChangeListener() {
	        public void propertyChange(PropertyChangeEvent e) {
	        	if ("date".equals(e.getPropertyName())) {
		        	if(table.getSelectedRow()!=-1 && dateChooser_1.getCalendar()!=null){
		        		table_Sobe.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
										"Broj kreveta", "Cijena u VS", "Cijena u NS", "Opis","Id"
								}
							));
			        	int row=table.getSelectedRow();
						int id=Integer.parseInt(table.getModel().getValueAt(row, 4).toString());			
						UcitavanjeSoba(id);
		        	}
	        	}
	        }
	    });
		dateChooser.setBounds(110, 271, 153, 20);
		frmKreiranjeRezervacije.getContentPane().add(dateChooser);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.getCalendarButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(dateChooser.getCalendar()!=null)
					dateChooser_1.getJCalendar().setMinSelectableDate(dateChooser.getDate());
			}
		});
		//ucitavanje soba na promjenu datuma "do" ukoliko je postavljen datum "od" i ukoliko je odabran neki red u tabeli
		dateChooser_1.getDateEditor().addPropertyChangeListener(
	    new PropertyChangeListener() {
	        public void propertyChange(PropertyChangeEvent e) {
	        	if ("date".equals(e.getPropertyName())) {
		        	if(table.getSelectedRow()!=-1 && dateChooser.getCalendar()!=null){
		        		table_Sobe.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
										"Broj kreveta", "Cijena u VS", "Cijena u NS", "Opis","Id"
								}
							));
			        	int row=table.getSelectedRow();
						int id=Integer.parseInt(table.getModel().getValueAt(row, 4).toString());			
						UcitavanjeSoba(id);
		        	}
	        	}
	        }
	    });
		dateChooser_1.setBounds(393, 271, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(dateChooser_1);
		
		dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(394, 417, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(dateChooser_2);
		
		JButton btnNewButton = new JButton("Potvrdi");
		//button potvrdi tj kreiranje reezervacije
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kreiranje();
			}
		});
		btnNewButton.setBounds(597, 377, 150, 30);
		frmKreiranjeRezervacije.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(463, 118, 412, 128);
		frmKreiranjeRezervacije.getContentPane().add(scrollPane_1);
		
		
		table_Sobe = new JTable();
		table_Sobe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IzracunajCijenu();
			}
		});
		table_Sobe.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Broj kreveta", "Cijena u VS", "Cijena u NS", "Opis"
			}
		));
		table_Sobe.getColumnModel().getColumn(0).setPreferredWidth(69);
		table_Sobe.getColumnModel().getColumn(1).setPreferredWidth(65);
		table_Sobe.getColumnModel().getColumn(2).setPreferredWidth(65);
		table_Sobe.getColumnModel().getColumn(3).setPreferredWidth(130);
		scrollPane_1.setViewportView(table_Sobe);
		
		JLabel lblSlobodneSobeU = new JLabel("Sobe u odabranom hotelu i vremenskom intervalu:");
		lblSlobodneSobeU.setBounds(463, 92, 240, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblSlobodneSobeU);
		
		//ucitavanje soba na klik misa na red u tabeli ukoliko su izabrani datumi
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(dateChooser.getCalendar()!=null && dateChooser_1.getCalendar()!=null){
					table_Sobe.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
									"Broj kreveta", "Cijena u VS", "Cijena u NS", "Opis","Id"
							}
						));
					int row=table.rowAtPoint(e.getPoint());
					int id=Integer.parseInt(table.getModel().getValueAt(row, 4).toString());
					UcitavanjeSoba(id);
				}
				PrijevozChecking(table.rowAtPoint(e.getPoint()));
			}
		});
		
		
		
		chckbxPrijevoz = new JCheckBox("Prijevoz");
		chckbxPrijevoz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IzracunajCijenu();
			}
		});
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
		
		//ucitavanje destinacija u combobox
		frmKreiranjeRezervacije.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				UcitajDestinacije();
			}
		});
		
	}
	
	private void UcitajDestinacije(){
		ArrayList<Destinacija> destinacije=uow.getDestinacijeService().DajSveDestinacije();
		TableColumnModel tcm = table.getColumnModel();
		if(tcm.getColumnCount()==5)
			tcm.removeColumn( tcm.getColumn(4) );
		
		ArrayList<String> list=new ArrayList<String>();
	    list.add("Izaberite destinaciju");
	    for(int i=0; i<destinacije.size(); i++){
			list.add(destinacije.get(i).getNaziv());
		}
	    comboBox.setModel(new DefaultComboBoxModel(list.toArray()));
	}
	
	private void PrijevozChecking(int row){
		ArrayList<Hotel> hoteli=uow.getHoteliService().VratiHotelZaDestinaciju(comboBox.getSelectedItem().toString());				
		if(hoteli.get(row).getDestinacija().getOmogucenPrevoz()){
			chckbxPrijevoz.setEnabled(true);
		}else{
			chckbxPrijevoz.setEnabled(false);
		}
		
	}
	
	//prikaz slobodnih soba na osnovu odobranog datum i odabranog hotela
	private void UcitavanjeSoba(int idHotel){
		Date odDatuma=(Date) dateChooser.getDate();
		Date doDatuma=(Date) dateChooser_1.getDate();
		
		if(odDatuma!=null && doDatuma!=null){
			Hotel h=new Hotel();
			h=uow.getHoteliService().VratiHotelId(idHotel);
			
	     	ArrayList<Soba>	sobe=uow.getKreiranjeRezervacijaService().dajSlobodneSobeZaHotel(h,odDatuma,doDatuma);
	    	
			DefaultTableModel model=(DefaultTableModel) table_Sobe.getModel();
			model.setRowCount(0);
			if(sobe!=null) {
				for(int i=0; i<sobe.size(); i++) {
					Object[] rowTmp={sobe.get(i).getBrojKreveta(), sobe.get(i).getCijenaVisoka(),sobe.get(i).getCijenaNiska(), sobe.get(i).getOpis(),sobe.get(i).getSobaId()};
					model.addRow(rowTmp);					
				}
			}
			TableColumnModel tcm = table_Sobe.getColumnModel();
			if(tcm.getColumnCount()==5)
				tcm.removeColumn( tcm.getColumn(4) );
			table_Sobe.setModel(model);
		}
	}
	
	private static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	private void IzracunajCijenu(){
		if(table.getSelectedRow()!=-1 && table_Sobe.getSelectedRow()!=-1){
			double CijenaPrevoza=70;
			if(chckbxPrijevoz.isSelected()) CijenaPrevoza=70;
			else CijenaPrevoza=0;
			int rowHotela=table.getSelectedRow();
			int idHotela=Integer.parseInt(table.getModel().getValueAt(rowHotela, 4).toString());
			Hotel h=new Hotel();
			h=uow.getHoteliService().VratiHotelId(idHotela);
			
			int rowSobe=table_Sobe.getSelectedRow();
			int cijenaSobe=0;
		
			if(h.getPocetakNiska().after(new Date()) && h.getKrajNiska().before(new Date()))
				cijenaSobe=Integer.parseInt(table_Sobe.getModel().getValueAt(rowSobe, 2).toString());
			else 
				cijenaSobe=Integer.parseInt(table_Sobe.getModel().getValueAt(rowSobe, 1).toString());
			int brojDana=(int)getDifferenceDays(dateChooser.getDate(),dateChooser_1.getDate());
			double cijenaTmp=(cijenaSobe* brojDana)+ CijenaPrevoza;
			cijena.setText(Double.toString(cijenaTmp));				
		}

	}
	private void Kreiranje(){
		if(ValidacijaPoljaZaPotvrduRezervacije() && table_Sobe.getSelectedRow()!=-1 && table.getSelectedRow()!=-1 && dateChooser.getCalendar()!=null && dateChooser_1.getCalendar()!=null){
			Date odDatuma=(Date) dateChooser.getCalendar().getTime();
			Date doDatuma=(Date) dateChooser_1.getCalendar().getTime();					
				Osoba osoba=new Osoba();
				osoba.setIme(textField_1.getText());
				osoba.setPrezime(textField.getText());
				osoba.setJmbg(Integer.parseInt(textField_2.getText()));
				osoba.setBrojPasosa(textField_3.getText());
				osoba.setEmail(textField_5.getText());
				osoba.setAdresa(textField_6.getText());
				osoba.setDatumRodjenja((Date) dateChooser_2.getCalendar().getTime());
				osoba.setBrojTelefona(textField_4.getText());
			Klijent klijent=new Klijent();
		    klijent.setOsoba(osoba);
		    
		    int idAgenta= UserContext.Identitfication;
		    
		    Rezervacija rezerv=new Rezervacija();
		    rezerv.setKlijent(klijent);
		    rezerv.setDatumRezervacije(new Date());
		    rezerv.setUkljucenPrevoz(chckbxPrijevoz.isSelected());
		    
			Soba soba=new Soba();
			int row=table_Sobe.getSelectedRow();
			int id=Integer.parseInt(table_Sobe.getModel().getValueAt(row, 4).toString());
			soba.setSobaId(id);

			boolean provjera=uow.getKreiranjeRezervacijaService().kreirajRezervacijuSaSobom(rezerv,soba, odDatuma, doDatuma, idAgenta, (int)Double.parseDouble(cijena.getText()));
			if(provjera)
				JOptionPane.showMessageDialog(null, "Uspjesno ste kreirali rezervaciju", "Info", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, ":(", "Info", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	private boolean ValidacijaPoljaZaPotvrduRezervacije(){
		if(dateChooser.getCalendar()==null) {
			JOptionPane.showMessageDialog(null, "Niste unijeli datum 'Od'", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(dateChooser_1.getCalendar()==null){
			JOptionPane.showMessageDialog(null, "Niste unijeli datum 'Do'", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_1.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli ime", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli prezime", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_2.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli JMBG", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_3.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli broj pasoša", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_5.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli email", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_6.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli adresu", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(dateChooser_2.getCalendar()==null){
			JOptionPane.showMessageDialog(null, "Niste unijeli datum rođenja", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_4.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli telefon", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
}
