package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import antlr.collections.List;
import ba.unsa.etf.si.app.iTravel.BLL.DestinacijeService;
import ba.unsa.etf.si.app.iTravel.BLL.HoteliService;
import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.SobeService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;

public class ModifikacijaHotela 
{
	private UnitOfWork uow = new UnitOfWork();
	private ArrayList<Destinacija> destinacijeModifikacije;
	private JFrame frmModifikacijaHotela;
	private JTextField textFieldModifikacija;
	private JTextField textField_1Modifikacija;
	private JSpinner spinnerModifikacija;
	private JDateChooser dateChooser_1Modifikacija;
	private JDateChooser dateChooserModifikacija;
	private JTextField textField_2Modifikacija;
	private JComboBox<String> comboBoxModifikacija;
    private JTextField textField_3Modifikacija; 
    private Integer hotelIDModifikacija= new Integer(0);
    private Integer pomocniIDModifikacija= new Integer(0);

	public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date) {
		if (date != null) {
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			return sqlDate;
		}
		return null;
	}
	
	public static java.sql.Date addDays(java.sql.Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
                 
        return convertUtilDateToSqlDate (cal.getTime());
    }
     
   
    public static java.sql.Date subtractDays(java.sql.Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
                 
        return convertUtilDateToSqlDate (cal.getTime());
    }

	class AkcijaDodavanja implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			try {
				if (ValidacijaPoljaZaDodavanjeHotela()) {
					HoteliService hoteliService = new HoteliService();

					Hotel hotel = new Hotel();
					hotel = hoteliService.VratiHotelId((int)hotelIDModifikacija);
					
					hotel.setNaziv(textFieldModifikacija.getText());
					hotel.setAdresa(textField_1Modifikacija.getText());
					hotel.setNazivLanca(textField_2Modifikacija.getText());
					hotel.setBrojTelefona(textField_3Modifikacija.getText());
					hotel.setBrojZvjezdica((Integer) spinnerModifikacija.getValue());
					hotel.setPocetakVisoka(convertUtilDateToSqlDate(dateChooserModifikacija.getDate()));
					hotel.setKrajVisoka(convertUtilDateToSqlDate(dateChooser_1Modifikacija.getDate()));
					int i = comboBoxModifikacija.getSelectedIndex();
					Destinacija destinacijahotel = destinacijeModifikacije.get(i);
					hotel.setDestinacija(destinacijahotel);
					hoteliService.AzurirajHotel(hotel);
					
					java.sql.Date krajNiska = subtractDays(convertUtilDateToSqlDate (dateChooserModifikacija.getDate()),1);
					java.sql.Date pocetakNiska = addDays(convertUtilDateToSqlDate( dateChooser_1Modifikacija.getDate()), 1);
					hotel.setKrajNiska(krajNiska);
					hotel.setPocetakNiska(pocetakNiska);

					JOptionPane.showMessageDialog(null, "Uspjesno ste modifikovali hotel", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, ":(", "Info", JOptionPane.INFORMATION_MESSAGE);

				}

			} catch (Exception e) {
				UnitOfWork.logger.error(e);
				JOptionPane.showMessageDialog(null, "Provjerite unesene podatke", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}

	}

	void NapuniDestinacijeUCB() {
		DestinacijeService destinacijeService = new DestinacijeService();
		destinacijeModifikacije = new ArrayList<Destinacija>();
		destinacijeModifikacije = destinacijeService.DajSveDestinacije();
		for (int i = 0; i < destinacijeModifikacije.size(); i++) {
			comboBoxModifikacija.addItem(destinacijeModifikacije.get(i).getNaziv());
		}

	}

	private boolean ValidacijaPoljaZaDodavanjeHotela() {
		if (dateChooserModifikacija.getCalendar() == null) {
			JOptionPane.showMessageDialog(null, "Niste unijeli datum 'Od'", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (dateChooser_1Modifikacija.getCalendar() == null) {
			JOptionPane.showMessageDialog(null, "Niste unijeli datum 'Do'", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (textField_1Modifikacija.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli lokaciju", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (textFieldModifikacija.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli naziv", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if (textField_3Modifikacija.getText().equals("")|| !textField_3Modifikacija.getText().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli broj hotela 6 cifara min", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}  else if (textField_2Modifikacija.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli lanac hotela", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (comboBoxModifikacija.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Niste selektovali destinaciju", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu(final Integer pomocniID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    
					ModifikacijaHotela window = new ModifikacijaHotela(pomocniID);
					window.frmModifikacijaHotela.setVisible(true);
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
                    
					ModifikacijaHotela window = new ModifikacijaHotela();
					window.frmModifikacijaHotela.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});

	}
	/**
	 * Create the application.
	 */
	public ModifikacijaHotela(Integer ID) {
	     hotelIDModifikacija =ID;
			initialize();
		}
	
	public ModifikacijaHotela() {
	  
			initialize();
		}
		

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
		frmModifikacijaHotela = new JFrame();
		frmModifikacijaHotela.setTitle("Unos hotela");
		frmModifikacijaHotela.setBounds(100, 100, 481, 451);
		frmModifikacijaHotela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModifikacijaHotela.getContentPane().setLayout(null);
		frmModifikacijaHotela.setLocationRelativeTo(null);

		JLabel lblNazivHotela = new JLabel("Naziv hotela:");
		lblNazivHotela.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNazivHotela.setBounds(10, 68, 161, 20);
		frmModifikacijaHotela.getContentPane().add(lblNazivHotela);

		JLabel lblBrojZvjezdica = new JLabel("Lokacija hotela:");
		lblBrojZvjezdica.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrojZvjezdica.setBounds(10, 99, 160, 20);
		frmModifikacijaHotela.getContentPane().add(lblBrojZvjezdica);

		JLabel label = new JLabel("Broj zvjezdica:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 130, 161, 20);
		frmModifikacijaHotela.getContentPane().add(label);

		JLabel lblPoetniDatum = new JLabel("Početak visoke sezone:");
		lblPoetniDatum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPoetniDatum.setBounds(10, 160, 161, 20);
		frmModifikacijaHotela.getContentPane().add(lblPoetniDatum);

		JLabel lblKrajnjiDatum = new JLabel("Kraj visoke sezone:");
		lblKrajnjiDatum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKrajnjiDatum.setBounds(10, 190, 161, 20);
		frmModifikacijaHotela.getContentPane().add(lblKrajnjiDatum);

		textFieldModifikacija = new JTextField();
		textFieldModifikacija.setBounds(191, 68, 138, 20);
		frmModifikacijaHotela.getContentPane().add(textFieldModifikacija);
		textFieldModifikacija.setColumns(10);

		textField_1Modifikacija = new JTextField();
		textField_1Modifikacija.setColumns(10);
		textField_1Modifikacija.setBounds(191, 99, 138, 20);
		frmModifikacijaHotela.getContentPane().add(textField_1Modifikacija);

		spinnerModifikacija = new JSpinner();
		spinnerModifikacija.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinnerModifikacija.setBounds(191, 130, 42, 20);
		frmModifikacijaHotela.getContentPane().add(spinnerModifikacija);

		JButton btnNewButton = new JButton("Modifikuj Hotel");
		btnNewButton.setBounds(221, 325, 161, 30);
		frmModifikacijaHotela.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new AkcijaDodavanja());

		JLabel lbDestinacija = new JLabel("Destinacija:");
		lbDestinacija.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDestinacija.setBounds(-57, 279, 161, 20);
		frmModifikacijaHotela.getContentPane().add(lbDestinacija);

		JLabel lblNewLabel = new JLabel("Unesite podatke o novom hotelu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(88, 24, 213, 14);
		frmModifikacijaHotela.getContentPane().add(lblNewLabel);

		dateChooserModifikacija = new JDateChooser();
		dateChooserModifikacija.setBounds(191, 160, 140, 20);
		frmModifikacijaHotela.getContentPane().add(dateChooserModifikacija);

		dateChooser_1Modifikacija = new JDateChooser();
		dateChooser_1Modifikacija.setBounds(191, 190, 140, 20);
		frmModifikacijaHotela.getContentPane().add(dateChooser_1Modifikacija);

		JButton btnNewButton_1 = new JButton("+ Destinacija");
		btnNewButton_1.setBounds(282, 278, 132, 23);
		frmModifikacijaHotela.getContentPane().add(btnNewButton_1);

		comboBoxModifikacija = new JComboBox<String>();
		comboBoxModifikacija.setBounds(116, 279, 150, 20);
		frmModifikacijaHotela.getContentPane().add(comboBoxModifikacija);
		NapuniDestinacijeUCB();

		

		JLabel lblLanacHotela = new JLabel("Lanac hotela:");
		lblLanacHotela.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLanacHotela.setBounds(10, 223, 161, 20);
		frmModifikacijaHotela.getContentPane().add(lblLanacHotela);

		textField_2Modifikacija = new JTextField();
		textField_2Modifikacija.setColumns(10);
		textField_2Modifikacija.setBounds(191, 221, 138, 20);
		frmModifikacijaHotela.getContentPane().add(textField_2Modifikacija);
		JLabel lblNewLabel_1 = new JLabel("Tel. broj:");
		lblNewLabel_1.setBounds(125, 249, 46, 14);
		frmModifikacijaHotela.getContentPane().add(lblNewLabel_1);
		
		textField_3Modifikacija = new JTextField();
		textField_3Modifikacija.setBounds(191, 248, 138, 20);
		frmModifikacijaHotela.getContentPane().add(textField_3Modifikacija);
		textField_3Modifikacija.setColumns(10);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DodavanjeDestinacije.PrikaziFormu();
			}
		});
        
		
		JMenuBar menuBar = new JMenuBar();
		frmModifikacijaHotela.setJMenuBar(menuBar);

		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		JMenu mnRefresh = new JMenu("Refresh ");
		menuBar.add(mnRefresh);

		
		
		JMenuItem mnOsvjezi = new JMenuItem("Osvjezi");
		mnOsvjezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NapuniDestinacijeUCB();
			}
			
			
		});
		mnRefresh.add(mnOsvjezi);
		

		JMenuItem mntmPoetna = new JMenuItem("Početna");
		mntmPoetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Pocetna(frmModifikacijaHotela);
			}
		});
		mnMeni.add(mntmPoetna);

		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mntmHoteli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Hoteli(frmModifikacijaHotela);
			}
		});
		mnMeni.add(mntmHoteli);
		mntmHoteli.setEnabled(postavke[1]);

		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Rezervacije(frmModifikacijaHotela);
			}
		});
		mnMeni.add(mntmRezervacije);
		mntmRezervacije.setEnabled(postavke[2]);

		if (UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3) {
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Meni.Klijenti(frmModifikacijaHotela);
				}
			});
			mnMeni.add(mntmKlijenti);
			mntmRezervacije.setEnabled(postavke[3]);
		}

		if (UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3) {
			JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
			mntmKorisnici.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Meni.Korisnici(frmModifikacijaHotela);
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
				Meni.OdjaviSe();
			}
		});
		mnRaun.add(mntmOdjaviSe);
		
		JMenu mnPomo = new JMenu("Pomoć");
		menuBar.add(mnPomo);
		JMenuItem mntmOFormi = new JMenuItem("O formi...");
		mntmOFormi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.HelpForma("/HelpImages/KreiranjeHotela.jpg");
			}
		});
		mnPomo.add(mntmOFormi);
	}

	
}
