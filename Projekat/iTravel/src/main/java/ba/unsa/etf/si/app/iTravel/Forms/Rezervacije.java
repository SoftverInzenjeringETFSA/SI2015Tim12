package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
//import java.security.cert.PKIXRevocationChecker.Option;
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
	public static void PrikaziFormu() {
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
				KreiranjeRezervacije.PrikaziFormu();
				frmPrikazRezervacija.dispose();
			}
		});
		btnDodajKorisnika.setBounds(20, 226, 150, 30);
		frmPrikazRezervacija.getContentPane().add(btnDodajKorisnika);
		
		JButton btnIzdajFakturu = new JButton("Izdaj fakturu");
		btnIzdajFakturu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){
					int idRezervacije=Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 9).toString());
					//table.getModel().getValueAt(table.getSelectedRow(), 8).toString().equals("Potvrđeno")
					Racun rac=uow.getRacunService().dajRacunPoIdRezervacije(idRezervacije);
					if(rac.getDatumUplate()!=null){
						KreirajRacun(idRezervacije);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Rezervacija nije potvrdjena, prvo potvrdite rezervaciju", "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Rezervacija nije izabrana", "Info", JOptionPane.INFORMATION_MESSAGE);				}
			}
		});
		btnIzdajFakturu.setBounds(500, 226, 150, 30);
		frmPrikazRezervacija.getContentPane().add(btnIzdajFakturu);
		
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
					PocetnaFormaAdministrator.PrikaziFormu();
					frmPrikazRezervacija.setVisible(false);
				}
				else if(UserContext.getInstance().getRoleID() == 2){
					PocetnaFormaAgent.PrikaziFormu();
					frmPrikazRezervacija.setVisible(false);
				}
				else if(UserContext.getInstance().getRoleID() == 3){
					PocetnaFormaSupervizor.PrikaziFormu();
					frmPrikazRezervacija.setVisible(false);
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
				frmPrikazRezervacija.setVisible(false);
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
						Klijenti.PrikaziFormu();
						frmPrikazRezervacija.setVisible(false);
					
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
					Korisnici.PrikaziFormu();
					frmPrikazRezervacija.setVisible(false);			
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
	}


	
	
	private void UcitajSveRezervacije(){
		ArrayList<Rezervacija> rezervacije=uow.getRezervacijaService().dajSveRezervacije();

		DefaultTableModel model=(DefaultTableModel) table.getModel();
		model.setRowCount(0);
		table.setModel(model);
		if(rezervacije!=null) {
			for(int i=0; i<rezervacije.size(); i++){
				Klijent k= rezervacije.get(i).getKlijent();
				Osoba osoba=uow.getOsobaService().dajOsobuPoId(k.getOsoba().getOsobaId());
				
				Racun r=null;
				Rezervacija razer=rezervacije.get(i);
				Object[] racuniList=razer.getRacuns().toArray();
				r=(Racun) racuniList[0];
				RezervisaniTerminSoba termin=uow.getRezervacijaService().dajRezervisaneTermineZaRezervaciju(rezervacije.get(i).getRezervacijaId()).get(0);
				Soba s= uow.getSobeService().VratiSobaId(termin.getSoba().getSobaId());
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
	
	private void KreirajRacun(Integer idRezervacijeInt){
		// START PDF-a
		Document document = new Document();
        try {
        	String idRezervacije = idRezervacijeInt.toString();
        	
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Faktura_rezervacije_" + idRezervacije +".pdf"));
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
            		+ " Dokument služi kao potvrda da je klijent platio rezervaciju za određenu"
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
            Racun racun= uow.getRacunService().dajRacunPoIdRezervacije(idRezervacijeInt);
            cell1.addElement(new Paragraph(racun.getRacunId().toString())); // Ovdje dinamicki ID racuna
            
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
            
            Rezervacija rezTmp=uow.getRezervacijaService().dajRezervaciju(idRezervacijeInt);
            Osoba osoba=uow.getKlijentiService().dajOsobuPoKlijentId(rezTmp.getKlijent().getKlijentId());
            table.addCell("Klijent:");
            table.addCell(new Paragraph(osoba.getIme()+ " "+osoba.getPrezime()+", "+osoba.getBrojLicneKarte()+ ", "+osoba.getAdresa()+", "+osoba.getBrojTelefona())); // Ovdje dinamički podaci o klijentu uradjeno xD
            
            Soba s= uow.getRezervacijaService().dajRezervisaneTermineZaRezervaciju(idRezervacijeInt).get(0).getSoba();
            Hotel h=uow.getHoteliService().VratiHotelId(s.getHotel().getHotelId());
            Destinacija d=uow.getDestinacijeService().VratiDestinacijuPoId(h.getDestinacija().getDestinacijaId());
            table.addCell(new Paragraph("Destinacija:"));
            table.addCell(new Paragraph(d.getNaziv())); // Dinamički Destinacija
            
            table.addCell(new Paragraph("Podaci za hotel:"));
            table.addCell(new Paragraph(h.getNaziv()+", "+h.getAdresa())); // Dinamički hotel
            
            table.addCell(new Paragraph("Podaci za sobe:"));
            table.addCell(new Paragraph("Soba "+s.getSobaId()+", broj kreveta: "+s.getBrojKreveta()+", "+ s.getOpis()+"."));
            // Dinamički podaci za sobu
            
            table.addCell(new Paragraph("Cijena:"));
            table.addCell(new Paragraph(racun.getCijena()+ " KM"));   
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
                    File myFile = new File("Faktura_rezervacije_" + idRezervacije +".pdf");
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
