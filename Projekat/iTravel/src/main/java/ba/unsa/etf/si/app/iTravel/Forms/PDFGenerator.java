package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

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

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Racun;
import ba.unsa.etf.si.app.iTravel.DBModels.Rezervacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class PDFGenerator {
	private static UnitOfWork uow=new UnitOfWork();
	
	public static void GenerisiPdf(Integer idRezervacijeInt, String tip){
		// START PDF-a
		Document document = new Document();
        try {
        	String idRezervacije = idRezervacijeInt.toString();
        	
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(tip + "_rezervacije_" + idRezervacije +".pdf"));
            document.open();	        
            document.addTitle(tip + " O REZERVACIJI");

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
            
            Paragraph p=null;
            if(tip=="Faktura"){            
	            p = new Paragraph("Ovaj dokument se printa u dva primjerka,"
	            		+ " jedan primjerak preuzima klijent, a drugi ostaje u agenciji ETFTravel."
	            		+ " Dokument služi kao potvrda da je klijent platio rezervaciju za određenu"
	            		+ " destinaciju i služi kao dodatni dokaz za check-in u hotel.");
            }else{
	            p = new Paragraph("Ovaj dokument se printa u dva primjerka,"
	            		+ " jedan primjerak preuzima klijent, a drugi ostaje u agenciji ETFTravel."
	            		+ " Dokument služi kao potvrda da je klijent napravio rezervaciju za određenu"
	            		+ " destinaciju i služi kao dodatni dokaz za check-in u hotel.");
            	
            }
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(p);
            
            PdfPTable tableFakt = new PdfPTable(2);
            tableFakt.setWidthPercentage(100);
            
            PdfPCell cell = new PdfPCell();
            cell.setPadding(3f);
            
            if(tip=="Faktura")
            	cell.addElement(new Paragraph("Broj fakture:")); 
            else
            	cell.addElement(new Paragraph("Broj potvrde:"));
            
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
                    File myFile = new File(tip + "_rezervacije_" + idRezervacije +".pdf");
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
}/*// START PDF-a
		Document document = new Document();
        try {
        	String idRezervacije = idKreiraneRezervacije.toString();
        	
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
            Racun racun= uow.getRacunService().dajRacunPoIdRezervacije(idKreiraneRezervacije);
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
            
            Rezervacija rezTmp=uow.getRezervacijaService().dajRezervaciju(idKreiraneRezervacije);
            Osoba osoba=uow.getKlijentiService().dajOsobuPoKlijentId(rezTmp.getKlijent().getKlijentId());
            table.addCell("Klijent:");
            table.addCell(new Paragraph(osoba.getIme()+ " "+osoba.getPrezime()+", "+osoba.getBrojLicneKarte()+ ", "+osoba.getAdresa()+", "+osoba.getBrojTelefona())); // Ovdje dinamički podaci o klijentu uradjeno xD
            
            Soba s= uow.getRezervacijaService().dajRezervisaneTermineZaRezervaciju(idKreiraneRezervacije).get(0).getSoba();
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
		 * 
		 * */
