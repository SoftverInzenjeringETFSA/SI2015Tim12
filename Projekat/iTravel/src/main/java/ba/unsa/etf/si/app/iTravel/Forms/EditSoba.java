
package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import ba.unsa.etf.si.app.iTravel.BLL.DestinacijeService;
import ba.unsa.etf.si.app.iTravel.BLL.HoteliService;
import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.SobeService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;
import ba.unsa.etf.si.app.iTravel.Forms.ModifikacijeSoba.AkcijaDodavanja;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class EditSoba {

	private UnitOfWork uow = new UnitOfWork();

	private JFrame frmEditSoba;
	private JTable table_pregledSoba;
	private JScrollPane scrollPane;
	private JButton btnEditujSobu;
	private JButton btnObrisiSobu;
	private JButton btnDodajSobu;

	private JMenuBar menuBar;
	private JMenu mnMeni;
	private JMenuItem mntmPoetna;
	private JMenuItem mntmRezervacije;
	private JMenuItem mntmKlijenti;
	private JMenuItem mntmKorisnici;
	private JMenu mnRaun;
	private JMenuItem mntmPromijeniifru;
	private JMenuItem mntmOdjaviSe;
	private ArrayList<Soba> sobe;
	private ArrayList<Soba> sobesve;
	private Soba hotelsoba;
	private Hotel hoteldaj;
	private SobeService sobeService = new SobeService();
	private JTextField textFielde;
	private JTextField textField_1e;
	private JSpinner spinnere;
	private Integer hotelID = new Integer(0);
	
	private JTextField textField_2e;

	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu(final Integer ID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditSoba window = new EditSoba(ID);
					window.frmEditSoba.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}
	public static void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditSoba window = new EditSoba();
					window.frmEditSoba.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}
	void NapuniSobe() {
		
		sobe = new ArrayList<Soba>();
		sobesve = new ArrayList<Soba>();
		sobesve = sobeService.VratiSveSobe();
		for (int i = 0; i < sobesve.size(); i++) {
			if( sobesve.get(i).getHotel().getHotelId()==hotelID)
				sobe.add(sobesve.get(i));
		}
		String header[] = new String[] { "Hotel", "Broj kreveta", "Cijena niska sezona", "Cijena visoka sezona",
				"Opis" };

		TableModel model = new DefaultTableModel() {
			Class[] types = new Class[] {
					// COL. TYPES ARE HERE!!!
					java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class,
					java.lang.String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		};

		((DefaultTableModel) model).setColumnIdentifiers(header);
		table_pregledSoba.setModel(model);
		Soba sobaubaci = new Soba();
		String opismanji = new String();
		for (int i = 0; i < sobe.size(); i++) {
			sobaubaci = sobe.get(i);

			opismanji = sobaubaci.getOpis();

			((DefaultTableModel) model).addRow(new Object[] { sobaubaci.getHotel().getNaziv().toString(),
					sobaubaci.getBrojKreveta(), sobaubaci.getCijenaNiska(), sobaubaci.getCijenaVisoka(), opismanji });
		}

	}
    
	
	
	
	class AkcijaDodavanja implements ActionListener {

		public void actionPerformed(ActionEvent event) {
		

				
              try { 
            	  HoteliService hoteliService = new HoteliService();
            		if (ValidacijaPoljaZaDodavanjeSobe()) {
            	  hotelsoba.setBrojKreveta((Integer) spinnere.getValue());
  				hotelsoba.setCijenaNiska(Integer.parseInt(textField_1e.getText()));
  				hotelsoba.setCijenaVisoka(Integer.parseInt(textFielde.getText()));
  				hotelsoba.setOpis(textField_2e.getText());
  				
  				
                  hotelsoba.setHotel(hoteliService.VratiHotelId(hotelID));
                 
  				
  				sobeService.UbaciSobuUBazu(hotelsoba);
  				
                   NapuniSobe();
				
                   
                   JOptionPane.showMessageDialog(null, "Uspjesno ste kreirali sobu", "Info",
   						JOptionPane.INFORMATION_MESSAGE);
   			} else {
   				JOptionPane.showMessageDialog(null, ":(", "Info", JOptionPane.INFORMATION_MESSAGE);

   			}
			} catch (Exception e) {
				UnitOfWork.logger.error(e);
				JOptionPane.showMessageDialog(null, "Provjerite vas unos", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
				
				
		}

	}
	
	class AkcijaEditovanja implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			 try {
         		if (SelektovanRed()&&ValidacijaPoljaZaDodavanjeSobe()) {
         	  hotelsoba.setBrojKreveta((Integer) spinnere.getValue());
				hotelsoba.setCijenaNiska(Integer.parseInt(textField_1e.getText()));
				hotelsoba.setCijenaVisoka(Integer.parseInt(textFielde.getText()));
				hotelsoba.setOpis(textField_2e.getText());
				
				
               hotelsoba.setHotel(sobe.get(table_pregledSoba.getSelectedRow()).getHotel());
              
				
               sobeService.AzurirajiliUbaciSobu(hotelsoba);
				
                NapuniSobe();
				
                
                JOptionPane.showMessageDialog(null, "Uspjesno ste kreirali sobu", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, ":(", "Info", JOptionPane.INFORMATION_MESSAGE);

			}
			} catch (Exception e) {
				UnitOfWork.logger.error(e);
				JOptionPane.showMessageDialog(null, "Provjerite vas unos", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			
			
				
			
		}

	}

	private boolean ValidacijaPoljaZaDodavanjeSobe() {
		if (textField_1e.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli cijenu", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (textFielde.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli cijenu", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (textField_2e.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli opis", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
	private boolean SelektovanRed() {
		if (table_pregledSoba.getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "Niste selektovali sobu", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
	
	/**
	 * Create the application.
	 */
	public EditSoba() {
		initialize();
	}
	public EditSoba(Integer IDpom) {
		hotelID =IDpom;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
		frmEditSoba = new JFrame();
		frmEditSoba.setTitle("Edit soba");
		frmEditSoba.setBounds(100, 100, 778, 576);
		frmEditSoba.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditSoba.getContentPane().setLayout(null);
		frmEditSoba.setLocationRelativeTo(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 11, 720, 218);
		frmEditSoba.getContentPane().add(scrollPane);

		table_pregledSoba = new JTable();
		table_pregledSoba.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table_pregledSoba);
		table_pregledSoba.setCellSelectionEnabled(true);
		table_pregledSoba.setBackground(Color.WHITE);
		table_pregledSoba.setBorder(new CompoundBorder());
		
		
		
		
		

		JLabel lbBrojKreveta = new JLabel("Broj kreveta:");
		lbBrojKreveta.setHorizontalAlignment(SwingConstants.RIGHT);
		lbBrojKreveta.setBounds(216, 287, 161, 20);
		frmEditSoba.getContentPane().add(lbBrojKreveta);
		

		JLabel lblCijenaNiska = new JLabel("Cijena nikska sezona:");
		lblCijenaNiska.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCijenaNiska.setBounds(216, 318, 160, 20);
		frmEditSoba.getContentPane().add(lblCijenaNiska);

		JLabel lblCijenaVisoka = new JLabel("Cijena visoka sezona:");
		lblCijenaVisoka.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCijenaVisoka.setBounds(216, 349, 161, 20);
		frmEditSoba.getContentPane().add(lblCijenaVisoka);

		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOpis.setBounds(216, 380, 161, 20);
		frmEditSoba.getContentPane().add(lblOpis);

		textFielde = new JTextField();
		textFielde.setBounds(424, 349, 138, 20);
		frmEditSoba.getContentPane().add(textFielde);
		textFielde.setColumns(10);

		textField_1e = new JTextField();
		textField_1e.setColumns(10);
		textField_1e.setBounds(424, 318, 138, 20);
		frmEditSoba.getContentPane().add(textField_1e);

		spinnere = new JSpinner();
		spinnere.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinnere.setBounds(424, 287, 138, 20);
		frmEditSoba.getContentPane().add(spinnere);

		textField_2e = new JTextField();
		textField_2e.setColumns(10);
		textField_2e.setBounds(424, 393, 138, 66);
		frmEditSoba.getContentPane().add(textField_2e);
	
		btnDodajSobu = new JButton("Dodaj sobu");
		btnDodajSobu.addActionListener(new AkcijaDodavanja());
		btnDodajSobu.setBounds(21, 411, 150, 30);
		frmEditSoba.getContentPane().add(btnDodajSobu);

		btnObrisiSobu = new JButton("Obrisi sobu");
		btnObrisiSobu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
 
				   try {
	            	   Soba izbrisiSoba = new Soba();
	      				if (table_pregledSoba.getSelectedRow() == -1) {
	      					JOptionPane.showMessageDialog(null, "Niste selektovali sobu", "Info",
	      							JOptionPane.INFORMATION_MESSAGE);
	      				} else {
	      					izbrisiSoba = sobe.get(table_pregledSoba.getSelectedRow());
	      					int reply = JOptionPane.showConfirmDialog(null, "Da li ste sigurni ?", "UPOZORENJE", JOptionPane.YES_NO_OPTION);
	      				    if (reply == JOptionPane.YES_OPTION)
	      				    {
	      				    	sobeService.ObrisiJenduSobu(izbrisiSoba);
	      				    }

	      				}
	      				NapuniSobe();
	   				
	   			} catch (Exception e2) {
	   				UnitOfWork.logger.error(e2);
	   			}
	   				

	   			}
		});
		btnObrisiSobu.setBounds(21, 350, 150, 30);
		frmEditSoba.getContentPane().add(btnObrisiSobu);

		btnEditujSobu = new JButton("Edituj Sobu");
		btnEditujSobu.setBounds(23, 287, 150, 30);
		frmEditSoba.getContentPane().add(btnEditujSobu);
		btnEditujSobu.addActionListener(new AkcijaEditovanja());
	/*	btnEditujSobu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table_pregledSoba.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Niste selektovali sobu", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					hotelsoba = sobe.get(table_pregledSoba.getSelectedRow());
					ModifikacijeSoba novaForma = new ModifikacijeSoba(hotelsoba);
					novaForma.PrikaziFormu();
				}
				NapuniSobe();
			}
		});
*/
		menuBar = new JMenuBar();
		frmEditSoba.setJMenuBar(menuBar);

		mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);

		menuBar.add(mnMeni);
		JMenu mnRefresh = new JMenu("Refresh ");
		menuBar.add(mnRefresh);

		JMenuItem mnOsvjezi = new JMenuItem("Osvjezi");
		mnOsvjezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NapuniSobe();

			}

		});
		mnRefresh.add(mnOsvjezi);
		mntmPoetna = new JMenuItem("Početna");
		mntmPoetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Pocetna(frmEditSoba);	
			}
		});
		mnMeni.add(mntmPoetna);

		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Rezervacije(frmEditSoba);
			}
		});
		mnMeni.add(mntmRezervacije);
		mntmRezervacije.setEnabled(postavke[2]);

		if (UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3) {
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Meni.Klijenti(frmEditSoba);
				}
			});
			mnMeni.add(mntmKlijenti);
			mntmRezervacije.setEnabled(postavke[3]);
		}
		NapuniSobe();
		hotelsoba = new Soba();
		hoteldaj =new Hotel();
		if (UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3) {
			JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
			mntmKorisnici.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Meni.Korisnici(frmEditSoba);
				}
			});
			mnMeni.add(mntmKorisnici);
			mntmKorisnici.setEnabled(postavke[4]);
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

		mntmOdjaviSe = new JMenuItem("Odjavi se");
		mntmOdjaviSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.OdjaviSe();
			}
		});
		mnRaun.add(mntmOdjaviSe);
		table_pregledSoba.getColumnModel().getColumn(0).setPreferredWidth(99);
		table_pregledSoba.getColumnModel().getColumn(1).setPreferredWidth(83);
		table_pregledSoba.getColumnModel().getColumn(2).setPreferredWidth(81);
		table_pregledSoba.getColumnModel().getColumn(3).setPreferredWidth(85);
		table_pregledSoba.getColumnModel().getColumn(4).setPreferredWidth(78);

	}

	

}
