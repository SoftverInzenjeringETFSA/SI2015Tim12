package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ba.unsa.etf.si.app.iTravel.BLL.HoteliService;
import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;
import ba.unsa.etf.si.app.iTravel.DAL.Repositories.HotelRepository;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Hoteli {

	private UnitOfWork uow = new UnitOfWork();

	private JFrame frmPrikazHotela;
	private JTable table_pregledHotela;
	private JScrollPane scrollPane;
	private JButton btnModifikujHotel;
	private JButton btnObriiHotel;
	private JButton btnDodajHotel;
	private JButton btnPregledajSobe;
	private JMenuBar menuBar;
	private JMenu mnMeni;
	private JMenuItem mntmPoetna;
	private JMenuItem mntmRezervacije;
	private JMenuItem mntmKlijenti;
	private JMenuItem mntmKorisnici;
	private JMenu mnRaun;
	private JMenuItem mntmPromijeniifru;
	private JMenuItem mntmOdjaviSe;
	private ArrayList<Hotel> hoteli;
	private HoteliService hoteliService = new HoteliService();;
	private Integer ID;
	private JMenu mnPomo;
	private JMenuItem mntmOFormi;

	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hoteli window = new Hoteli();
					window.frmPrikazHotela.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}

	public void NapuniHotele() {

		hoteli = new ArrayList<Hotel>();

		hoteli = hoteliService.VratiSveHotele();

		String header[] = new String[] { "Naziv", "Adresa", "Destinacija", "Broj Zvijezdica", "Lanac", "Pocetak visoke",
				"Kraj visoke", "broj" };

		TableModel model = new DefaultTableModel() {
			Class[] types = new Class[] {

					java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class,
					java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
			
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		((DefaultTableModel) model).setColumnIdentifiers(header);
		table_pregledHotela.setModel(model);
		Hotel hotel = new Hotel();

		for (int i = 0; i < hoteli.size(); i++) {
			hotel = hoteli.get(i);

			((DefaultTableModel) model).addRow(new Object[] { hotel.getNaziv(), hotel.getAdresa(),
					hotel.getDestinacija().getNaziv(), hotel.getBrojZvjezdica(), hotel.getNazivLanca(),
					hotel.getPocetakVisoka().toString(), hotel.getKrajVisoka().toString(), hotel.getBrojTelefona() });
		}

	}

	/**
	 * Create the application.
	 */
	public Hoteli() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
		frmPrikazHotela = new JFrame();
		frmPrikazHotela.setTitle("Pregled hotela");
		frmPrikazHotela.setBounds(100, 100, 784, 395);
		frmPrikazHotela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrikazHotela.getContentPane().setLayout(null);
		frmPrikazHotela.setLocationRelativeTo(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 41, 720, 218);
		frmPrikazHotela.getContentPane().add(scrollPane);
		
		table_pregledHotela = new JTable();
		table_pregledHotela.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table_pregledHotela);
		table_pregledHotela.setCellSelectionEnabled(true);
		table_pregledHotela.setBackground(Color.WHITE);
		table_pregledHotela.setBorder(new CompoundBorder());
		
		
		btnModifikujHotel = new JButton("Modifikuj hotel");
		btnModifikujHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_pregledHotela.getSelectedRow()!=-1){
					ID= new Integer(hoteli.get(table_pregledHotela.getSelectedRow()).getHotelId());
					
				

				ModifikacijaHotela.PrikaziFormu(ID);
				}
				else {
					JOptionPane.showMessageDialog(null, "Niste selektovali hotel", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});
		btnModifikujHotel.setBounds(343, 287, 150, 30);
		frmPrikazHotela.getContentPane().add(btnModifikujHotel);
		
			
		btnObriiHotel = new JButton("Obri\u0161i hotel");
		btnObriiHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 try {  
                           
                         Hotel hotel = new Hotel();  
                         if (table_pregledHotela.getSelectedRow() == -1) {  
                                 JOptionPane.showMessageDialog(null, "Niste selektovali hotel", "Info",  
                                                 JOptionPane.INFORMATION_MESSAGE);  
                         } else {  
                                 hotel = hoteli.get(table_pregledHotela.getSelectedRow());  
                                 
                                 int reply = JOptionPane.showConfirmDialog(null,"Jeste li sigurni da želite obrisati odabrani hotel?", "UPOZORENJE", JOptionPane.YES_NO_OPTION);
                     	      				    if (reply == JOptionPane.YES_OPTION)
                     	      				    {
                     	      				    	hoteliService.ObrisiJendaHotel(hotel); 
                     	      				    }
  
                               
                                         }  
                                         NapuniHotele();  
                                 } catch (Exception e2) {  
                                 UnitOfWork.logger.error(e2);  
                                 }  
                                                             
                 }                         

		});
		btnObriiHotel.setBounds(503, 287, 150, 30);
		frmPrikazHotela.getContentPane().add(btnObriiHotel);
		
		btnDodajHotel = new JButton("Dodaj hotel");
		btnDodajHotel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				DodavanjeHotela.PrikaziFormu();
			}
		});
		btnDodajHotel.setBounds(183, 287, 150, 30);
		frmPrikazHotela.getContentPane().add(btnDodajHotel);
		
		btnPregledajSobe = new JButton("Pregledaj sobe");
		btnPregledajSobe.setBounds(23, 287, 150, 30);
		frmPrikazHotela.getContentPane().add(btnPregledajSobe);
		
		btnPregledajSobe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_pregledHotela.getSelectedRow() == -1) {
  					JOptionPane.showMessageDialog(null, "Niste selektovali hotel", "Info",
  							JOptionPane.INFORMATION_MESSAGE);
  				} else {
				EditSoba.PrikaziFormu((Integer)hoteli.get(table_pregledHotela.getSelectedRow()).getHotelId());
			}}
		});
		menuBar = new JMenuBar();
		frmPrikazHotela.setJMenuBar(menuBar);
		
		mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		JMenu mnRefresh = new JMenu("Refresh ");  
	                    menuBar.add(mnRefresh);  
	     
	                     JMenuItem mnOsvjezi = new JMenuItem("Osvjezi");  
	                     mnOsvjezi.addActionListener(new ActionListener() {  
	                            public void actionPerformed(ActionEvent e) {  
	                                      
	                                     
	                                     frmPrikazHotela.setVisible(false);  
	                                    Hoteli.PrikaziFormu();  
	     
	                             }  
	      
	                     });  
	                    mnRefresh.add(mnOsvjezi); 

		mntmPoetna = new JMenuItem("Početna");
		mntmPoetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Pocetna(frmPrikazHotela);
			}
		});
		mnMeni.add(mntmPoetna);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Rezervacije(frmPrikazHotela);
			}
		});
		mnMeni.add(mntmRezervacije);
		mntmRezervacije.setEnabled(postavke[2]);
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Meni.Klijenti(frmPrikazHotela);
				}
			});
				mnMeni.add(mntmKlijenti);
				mntmKlijenti.setEnabled(postavke[3]);
			}
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
			mntmKorisnici.addActionListener(new ActionListener() {
						
				public void actionPerformed(ActionEvent e) {
					Meni.Korisnici(frmPrikazHotela);
				}
			});
			mnMeni.add(mntmKorisnici);
			mntmKorisnici.setEnabled(postavke[4]);
			}
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
		JMenuItem mntmIzvjestaji = new JMenuItem("Izvještaji");
		mntmIzvjestaji.addActionListener(new ActionListener() {
					
			public void actionPerformed(ActionEvent e) {
				Meni.Izvjestaj(frmPrikazHotela);
			}
		});
		mnMeni.add(mntmIzvjestaji);
		mntmIzvjestaji.setEnabled(postavke[5]);
		}
		
		mnRaun = new JMenu("Račun");
		menuBar.add(mnRaun);
		
		mntmPromijeniifru = new JMenuItem("Promijeni šifru");
		mntmPromijeniifru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PromjenaSifre.PrikaziFormu();
			}
		});
		mnRaun.add(mntmPromijeniifru);
		NapuniHotele();
		mntmOdjaviSe = new JMenuItem("Odjavi se");
		mntmOdjaviSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.OdjaviSe();
			}
		});
		mnRaun.add(mntmOdjaviSe);
		
		mnPomo = new JMenu("Pomoć");
		menuBar.add(mnPomo);
		
		mntmOFormi = new JMenuItem("O formi...");
		mntmOFormi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.HelpForma("/HelpImages/HoteliSlika.jpg");
			}
		});
		mnPomo.add(mntmOFormi);
		table_pregledHotela.getColumnModel().getColumn(0).setPreferredWidth(99);
		table_pregledHotela.getColumnModel().getColumn(1).setPreferredWidth(83);
		table_pregledHotela.getColumnModel().getColumn(2).setPreferredWidth(81);
		table_pregledHotela.getColumnModel().getColumn(3).setPreferredWidth(85);
		table_pregledHotela.getColumnModel().getColumn(4).setPreferredWidth(78);
		table_pregledHotela.getColumnModel().getColumn(5).setPreferredWidth(110);
		table_pregledHotela.getColumnModel().getColumn(6).setPreferredWidth(108);
		table_pregledHotela.getColumnModel().getColumn(7).setPreferredWidth(110);
	}

}
