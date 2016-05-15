package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.Desktop;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.toedter.calendar.JDateChooser;


import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Klijent;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Rezervacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

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
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.beans.PropertyChangeEvent;

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
	private JTextField textField_brojLicneKarte;
	
	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KreiranjeRezervacije window = new KreiranjeRezervacije();
					window.frmKreiranjeRezervacije.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
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

	private void closeTheCurrentFrameAndOpenNew(java.awt.event.ActionEvent evt){

		 frmKreiranjeRezervacije.dispose();//To close the current window

		 Rezervacije.PrikaziFormu();
		}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKreiranjeRezervacije = new JFrame();
		frmKreiranjeRezervacije.setTitle("Kreiranje rezervacije");
		frmKreiranjeRezervacije.setBounds(100, 100, 901, 573);
		frmKreiranjeRezervacije.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
				UcitajHotele();
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
		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmKreiranjeRezervacije.dispose();
			}
		});
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
	        	if ("date".equals(e.getPropertyName()) && table.getSelectedRow()!=-1 && dateChooser_1.getCalendar()!=null){
		        	int row=table.getSelectedRow();
		        	if(row!=-1){
		        		int id=Integer.parseInt(table.getModel().getValueAt(row, 4).toString());	
						cijena.setText("");
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
	        	if ("date".equals(e.getPropertyName())&& table.getSelectedRow()!=-1 && dateChooser.getCalendar()!=null){
		        	int row=table.getSelectedRow();
		        	if(row!=-1){
						int id=Integer.parseInt(table.getModel().getValueAt(row, 4).toString());
						cijena.setText("");
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
				
				// Možda izmjesiti u metodu ili u BLL
				if(true) // ako je uspjesno kreirana rezervacija ide potvrda u pdf-u
				{
					// START PDF-a
					Document document = new Document();
			        try {
			        	
			        	String idRezervacije = "151"; // Ovdje dinamicki IDRezervacije
			        	
			            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Potvrda_rezervacije_" + idRezervacije +".pdf"));
			            document.open();	        
			            document.addTitle("POTVRDA O REZERVACIJI");

			            Image logoEuroByte;
			            Image logoEtfTravel;
						
			            try {
			            	// Postavljanje dva loga
							logoEuroByte = Image.getInstance("src/main/resources/Images/iTravelEuroByte.png");
							logoEtfTravel = Image.getInstance("src/main/resources/Images/etfTravel.png");
							logoEuroByte.setAbsolutePosition(380f, 750f);
							
							document.add(logoEtfTravel);
				            document.add(logoEuroByte);
				            
						} catch (MalformedURLException e1) {
							UnitOfWork.logger.error(e1);
						} catch (IOException e1) {
							UnitOfWork.logger.error(e1);
						}
			            
			            Paragraph p = new Paragraph("Ovaj dokument se printa u dva primjerka,"
			            		+ " jedan primjerak preuzima klijent, a drugi ostaje u agenciji ETFTravel."
			            		+ " Dokument služi kao potvrda da je klijent napravio rezervaciju za određenu"
			            		+ " destinaciju i služi kao dodatni dokaz za check-in u hotel.");

			            p.setAlignment(Element.ALIGN_JUSTIFIED);
			            document.add(p);
			            
			            PdfPTable tableFakt = new PdfPTable(2);
			            tableFakt.setWidthPercentage(100);
			            
			            PdfPCell cell = new PdfPCell();
			            cell.setPadding(3f);
			            cell.addElement(new Paragraph("Broj fakture:")); 
			            
			            PdfPCell cell1 = new PdfPCell();
			            cell1.setPadding(3f);
			            cell1.addElement(new Paragraph("1542")); // Ovdje dinamicki ID racuna
			            
			            tableFakt.addCell(cell);
			            tableFakt.addCell(cell1);

			            tableFakt.setSpacingBefore(10f);
			            tableFakt.setSpacingAfter(5f);
			            
			            document.add(tableFakt);
			            
			            Paragraph pocetniParagraf = new Paragraph("Podaci o rezervaciji:");
			            pocetniParagraf.setSpacingBefore(10f);
			            document.add(pocetniParagraf);
			            
			            PdfPTable table = new PdfPTable(2);
			            table.setSpacingBefore(5f);
			            table.setWidthPercentage(100);
			            table.setWidths(new int[]{100,200});
			            
			            table.addCell("Klijent:");
			            table.addCell(new Paragraph("Kenan Pršeš, 12KJKP1, Kamenica potok 11, "
			            		+ "+387 62 999 999")); // Ovdje dinamički podaci o klijentu
			            
			            table.addCell(new Paragraph("Destinacija:"));
			            table.addCell(new Paragraph("Sarajevo")); // Dinamički Destinacija
			            
			            table.addCell(new Paragraph("Podaci za hotel:"));
			            table.addCell(new Paragraph("Sveto drvo, Jenkins 12a")); // Dinamički hotel
			            
			            table.addCell(new Paragraph("Podaci za sobe:"));
			            table.addCell(new Paragraph("Soba 112, broj kreveta: 4, televizija, internet, pogled na industrijsku zonu."));
			            // Dinamički podaci za sobu
			            
			            table.addCell(new Paragraph("Cijena:"));
			            table.addCell(new Paragraph("350,00 KM"));   
			            // Dinamički cijena
			            
			            document.add(table);
			            
			            Paragraph potpisOvlasteneosobe = new Paragraph("Potpis ovlaštene osobe: ");
			            potpisOvlasteneosobe.setSpacingBefore(25f);
			            potpisOvlasteneosobe.setIndentationLeft(125f);
			            DottedLineSeparator dottedline = new DottedLineSeparator();
			            dottedline.setLineColor(BaseColor.GRAY);
			            dottedline.setGap(3f);
			            dottedline.setPercentage(50f);
			            dottedline.setAlignment(0);
			            potpisOvlasteneosobe.add(new Chunk(dottedline));
			            document.add(potpisOvlasteneosobe);
			            
			            Paragraph potpisKlijenta = new Paragraph("Potpis klijenta: ");
			            potpisKlijenta.setSpacingBefore(25f);
			            potpisKlijenta.setIndentationLeft(175f);
			            DottedLineSeparator dottedline1 = new DottedLineSeparator();
			            dottedline1.setLineColor(BaseColor.GRAY);
			            dottedline1.setGap(3f);
			            dottedline1.setPercentage(50f);
			            dottedline1.setAlignment(0);
			            potpisKlijenta.add(new Chunk(dottedline1));
			            document.add(potpisKlijenta);    
			            
			            document.close();
			            writer.close();
			            
			            if (Desktop.isDesktopSupported()) {
			                try {
			                    File myFile = new File("Potvrda_rezervacije_" + idRezervacije +".pdf");
			                    Desktop.getDesktop().open(myFile);
			                } catch (IOException ex) {
			                	UnitOfWork.logger.error(ex);
			                }
			            }
			            
			        } catch (DocumentException e1)
			        {
			        	UnitOfWork.logger.error(e1);
			        }
			        catch(FileNotFoundException e2)
			        {
			        	UnitOfWork.logger.error(e2);
			        }
					// KRAJ PDF-a
				}
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
					int row=table.rowAtPoint(e.getPoint());
					if(row!=-1){
						int id=Integer.parseInt(table.getModel().getValueAt(row, 4).toString());
						cijena.setText("");	
						UcitavanjeSoba(id);
					}
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
		
		textField_brojLicneKarte = new JTextField();
		textField_brojLicneKarte.setColumns(10);
		textField_brojLicneKarte.setBounds(111, 479, 152, 20);
		frmKreiranjeRezervacije.getContentPane().add(textField_brojLicneKarte);
		
		JLabel lblBrojLineKarte = new JLabel("Broj lične karte:");
		lblBrojLineKarte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrojLineKarte.setBounds(20, 482, 81, 14);
		frmKreiranjeRezervacije.getContentPane().add(lblBrojLineKarte);
		
		JMenuBar menuBar = new JMenuBar();
		frmKreiranjeRezervacije.setJMenuBar(menuBar);
		
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
					PocetnaFormaAdministrator.PrikaziFormu();
					frmKreiranjeRezervacije.setVisible(false);
				}
				else if(UserContext.getInstance().getRoleID() == 2){
					PocetnaFormaAgent.PrikaziFormu();
					frmKreiranjeRezervacije.setVisible(false);
				}
				else if(UserContext.getInstance().getRoleID() == 3){
					PocetnaFormaSupervizor.PrikaziFormu();
					frmKreiranjeRezervacije.setVisible(false);
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
				
				Hoteli.PrikaziFormu();
				frmKreiranjeRezervacije.setVisible(false);
			}
		});
		mnMeni.add(mntmHoteli);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 				
				Rezervacije.PrikaziFormu();
				frmKreiranjeRezervacije.setVisible(false);
			}
		});
		mnMeni.add(mntmRezervacije);
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					java.awt.Window win[] = java.awt.Window.getWindows(); 
					for(int i=0;i<win.length;i++){ 
					win[i].dispose(); 
					} 				
						Klijenti.PrikaziFormu();
						frmKreiranjeRezervacije.setVisible(false);
					
				}
			});
				mnMeni.add(mntmKlijenti);
			}
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
			mntmKorisnici.addActionListener(new ActionListener() {
						
				public void actionPerformed(ActionEvent e) {
					java.awt.Window win[] = java.awt.Window.getWindows(); 
					for(int i=0;i<win.length;i++){ 
					win[i].dispose(); 
					} 				
					Korisnici.PrikaziFormu();
					frmKreiranjeRezervacije.setVisible(false);			
				}
			});
			mnMeni.add(mntmKorisnici);
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
				OdjavaService odjava = new OdjavaService();
				odjava.OdjaviKorisnika();
							
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 
				Prijava.PrikaziFormu();
			}
		});
		mnRaun.add(mntmOdjaviSe);
		
		//ucitavanje destinacija u combobox
		frmKreiranjeRezervacije.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				UcitajDestinacije();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Rezervacije.PrikaziFormu();
				frmKreiranjeRezervacije.dispose();
			}
		});
		
	}
	private void UcitajHotele(){
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
				new Object[][] {},
				new String[] {"Broj kreveta", "Cijena u VS", "Cijena u NS", "Opis"}
			));
		
		TableColumnModel tcm = table.getColumnModel();
		if(tcm.getColumnCount()==5)
			tcm.removeColumn( tcm.getColumn(4) );
	};
	
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
		table_Sobe.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"Broj kreveta", "Cijena u VS", "Cijena u NS", "Opis","Id"}
			));
		Date odDatuma=(Date) dateChooser.getDate();
		Date doDatuma=(Date) dateChooser_1.getDate();
		
		if(odDatuma!=null && doDatuma!=null){
			Hotel h=new Hotel();
			h=uow.getHoteliService().VratiHotelId(idHotel);
			
	     	ArrayList<Soba>	sobe=uow.getSobeService().dajSlobodneSobeZaHotel(h,odDatuma,doDatuma);
	    	
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
			Date odDatuma=(Date) dateChooser.getCalendar().getTime();
			Date doDatuma=(Date) dateChooser_1.getCalendar().getTime();
			
			if(h.getPocetakNiska().after(odDatuma) && h.getKrajNiska().before(doDatuma))
				cijenaSobe=Integer.parseInt(table_Sobe.getModel().getValueAt(rowSobe, 1).toString());				
			else
				cijenaSobe=Integer.parseInt(table_Sobe.getModel().getValueAt(rowSobe, 2).toString());
			int brojDana=(int)getDifferenceDays(dateChooser.getDate(),dateChooser_1.getDate());
			double cijenaTmp=(cijenaSobe* brojDana)+ CijenaPrevoza;
			cijena.setText(Double.toString(cijenaTmp));				
		}else{
			JOptionPane.showMessageDialog(null, "Niste odabrali hotel ili sobu", "Info", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	private void Kreiranje(){
		if(ValidacijaPoljaZaPotvrduRezervacije() && table_Sobe.getSelectedRow()!=-1 && table.getSelectedRow()!=-1 && dateChooser.getCalendar()!=null && dateChooser_1.getCalendar()!=null){
			Date odDatuma=(Date) dateChooser.getCalendar().getTime();
			Date doDatuma=(Date) dateChooser_1.getCalendar().getTime();					
				Osoba osoba=new Osoba();
				osoba.setIme(textField_1.getText());
				osoba.setPrezime(textField.getText());
				
				osoba.setJmbg(textField_2.getText());
				osoba.setBrojPasosa(textField_3.getText());
				osoba.setBrojLicneKarte(textField_brojLicneKarte.getText());
				osoba.setEmail(textField_5.getText());
				osoba.setAdresa(textField_6.getText());
				osoba.setDatumRodjenja((Date) dateChooser_2.getCalendar().getTime());
				osoba.setBrojTelefona(textField_4.getText());
			Klijent klijent=new Klijent();
		    klijent.setOsoba(osoba);
		    
		    int idAgenta= UserContext.getInstance().getIdentification();
		    
		    Rezervacija rezerv=new Rezervacija();
		    rezerv.setKlijent(klijent);
		    rezerv.setDatumRezervacije(new Timestamp(new Date().getTime()));
		    rezerv.setUkljucenPrevoz(chckbxPrijevoz.isSelected());
		    
			Soba soba=new Soba();
			int row=table_Sobe.getSelectedRow();
			int id=Integer.parseInt(table_Sobe.getModel().getValueAt(row, 4).toString());
			soba.setSobaId(id);

			boolean provjera=uow.getRezervacijaService().kreirajRezervacijuSaSobom(rezerv,soba, odDatuma, doDatuma, idAgenta, (int)Double.parseDouble(cijena.getText()));
			if(provjera){
				JOptionPane.showMessageDialog(null, "Uspjesno ste kreirali rezervaciju", "Info", JOptionPane.INFORMATION_MESSAGE);
	        	int rowHotel=table.getSelectedRow();
				if(rowHotel!=-1){
		        	int idHotela=Integer.parseInt(table.getModel().getValueAt(row, 4).toString());
					UcitavanjeSoba(idHotela);
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Rezervacija nije kreirana, molimo pokusajte kasnije", "Info", JOptionPane.INFORMATION_MESSAGE);
			
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
		}else if(textField_2.getText().length()!=13 && textField_2.getText().matches("[0-9]+")){
			JOptionPane.showMessageDialog(null, "Format JMBG nije ispravan", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_3.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli broj pasoša", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if(textField_brojLicneKarte.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Niste unijeli broj lične karte", "Info", JOptionPane.INFORMATION_MESSAGE);
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
		}else if(cijena.getText()==""){
			JOptionPane.showMessageDialog(null, "Niste izabrali sobu!", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
	
	
}
