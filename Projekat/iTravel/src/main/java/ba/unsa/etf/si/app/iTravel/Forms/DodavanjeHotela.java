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
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;

public class DodavanjeHotela {
	private UnitOfWork uow = new UnitOfWork();
	private ArrayList<Destinacija> destinacije;
	private JFrame frmUnosHotela;
	private JTextField textField;
	private JTextField textField_1;
	private JSpinner spinner;
	private JDateChooser dateChooser_1;
	private JDateChooser dateChooser;
	private JTextField textField_2;
	private JComboBox<String> comboBox;
    private JTextField textField_3; 
    

	public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date) {
		if (date != null) {
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			return sqlDate;
		}
		return null;
	}

	class AkcijaDodavanja implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			try {
				if (ValidacijaPoljaZaDodavanjeHotela()) {
					HoteliService hoteliService = new HoteliService();

					Hotel hotel = new Hotel();
					hotel.setNaziv(textField.getText());
					hotel.setAdresa(textField_1.getText());
					hotel.setNazivLanca(textField_2.getText());
					hotel.setBrojTelefona(textField_3.getText());
					hotel.setBrojZvjezdica((Integer) spinner.getValue());
					hotel.setPocetakVisoka(convertUtilDateToSqlDate(dateChooser.getDate()));
					hotel.setKrajVisoka(convertUtilDateToSqlDate(dateChooser_1.getDate()));
					int i = comboBox.getSelectedIndex();
					Destinacija destinacijahotel = destinacije.get(i);
					hotel.setDestinacija(destinacijahotel);
					hoteliService.KreirajHotel(hotel);

					JOptionPane.showMessageDialog(null, "Uspjesno ste kreirali hotel", "Info",
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
		destinacije = new ArrayList<Destinacija>();
		destinacije = destinacijeService.DajSveDestinacije();
		for (int i = 0; i < destinacije.size(); i++) {
			comboBox.addItem(destinacije.get(i).getNaziv());
		}

	}

	private boolean ValidacijaPoljaZaDodavanjeHotela() {
		if (dateChooser.getCalendar() == null) {
			JOptionPane.showMessageDialog(null, "Niste unijeli datum 'Od'", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (dateChooser_1.getCalendar() == null) {
			JOptionPane.showMessageDialog(null, "Niste unijeli datum 'Do'", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (textField_1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli lokaciju", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (textField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli naziv", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else if (textField_3.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli broj hotela", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}  else if (textField_2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli lanac hotela", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (comboBox.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Niste selektovali destinaciju", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					DodavanjeHotela window = new DodavanjeHotela();
					window.frmUnosHotela.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public DodavanjeHotela() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
		frmUnosHotela = new JFrame();
		frmUnosHotela.setTitle("Unos hotela");
		frmUnosHotela.setBounds(100, 100, 481, 451);
		frmUnosHotela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmUnosHotela.getContentPane().setLayout(null);
		frmUnosHotela.setLocationRelativeTo(null);

		JLabel lblNazivHotela = new JLabel("Naziv hotela:");
		lblNazivHotela.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNazivHotela.setBounds(10, 68, 161, 20);
		frmUnosHotela.getContentPane().add(lblNazivHotela);

		JLabel lblBrojZvjezdica = new JLabel("Lokacija hotela:");
		lblBrojZvjezdica.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrojZvjezdica.setBounds(10, 99, 160, 20);
		frmUnosHotela.getContentPane().add(lblBrojZvjezdica);

		JLabel label = new JLabel("Broj zvjezdica:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 130, 161, 20);
		frmUnosHotela.getContentPane().add(label);

		JLabel lblPoetniDatum = new JLabel("Početak visoke sezone:");
		lblPoetniDatum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPoetniDatum.setBounds(10, 160, 161, 20);
		frmUnosHotela.getContentPane().add(lblPoetniDatum);

		JLabel lblKrajnjiDatum = new JLabel("Kraj visoke sezone:");
		lblKrajnjiDatum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKrajnjiDatum.setBounds(10, 190, 161, 20);
		frmUnosHotela.getContentPane().add(lblKrajnjiDatum);

		textField = new JTextField();
		textField.setBounds(191, 68, 138, 20);
		frmUnosHotela.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(191, 99, 138, 20);
		frmUnosHotela.getContentPane().add(textField_1);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinner.setBounds(191, 130, 42, 20);
		frmUnosHotela.getContentPane().add(spinner);

		JButton btnNewButton = new JButton("Dodaj Hotel");
		btnNewButton.setBounds(158, 337, 171, 43);
		frmUnosHotela.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new AkcijaDodavanja());

		JLabel lbDestinacija = new JLabel("Destinacija:");
		lbDestinacija.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDestinacija.setBounds(-57, 279, 161, 20);
		frmUnosHotela.getContentPane().add(lbDestinacija);

		JLabel lblNewLabel = new JLabel("Unesite podatke o novom hotelu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(88, 24, 213, 14);
		frmUnosHotela.getContentPane().add(lblNewLabel);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(191, 160, 140, 20);
		frmUnosHotela.getContentPane().add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(191, 190, 140, 20);
		frmUnosHotela.getContentPane().add(dateChooser_1);

		JButton btnNewButton_1 = new JButton("+ Destinacija");
		btnNewButton_1.setBounds(282, 278, 132, 23);
		frmUnosHotela.getContentPane().add(btnNewButton_1);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(116, 279, 150, 20);
		frmUnosHotela.getContentPane().add(comboBox);
		NapuniDestinacijeUCB();

		

		JLabel lblLanacHotela = new JLabel("Lanac hotela:");
		lblLanacHotela.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLanacHotela.setBounds(10, 223, 161, 20);
		frmUnosHotela.getContentPane().add(lblLanacHotela);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(191, 221, 138, 20);
		frmUnosHotela.getContentPane().add(textField_2);
		JLabel lblNewLabel_1 = new JLabel("Tel. broj:");
		lblNewLabel_1.setBounds(125, 249, 46, 14);
		frmUnosHotela.getContentPane().add(lblNewLabel_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(191, 248, 138, 20);
		frmUnosHotela.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DodavanjeDestinacije.PrikaziFormu();
			}
		});
        
		
		JMenuBar menuBar = new JMenuBar();
		frmUnosHotela.setJMenuBar(menuBar);

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
				java.awt.Window win[] = java.awt.Window.getWindows();
				for (int i = 0; i < win.length; i++) {
					win[i].dispose();
				}
				if (UserContext.getInstance().getRoleID() == 1) {
					PocetnaFormaAdministrator.PrikaziFormu();
					frmUnosHotela.setVisible(false);

				} else if (UserContext.getInstance().getRoleID() == 2) {
					PocetnaFormaAgent.PrikaziFormu();
					frmUnosHotela.setVisible(false);

				} else if (UserContext.getInstance().getRoleID() == 3) {
					PocetnaFormaSupervizor.PrikaziFormu();
					frmUnosHotela.setVisible(false);

				}
			}
		});
		mnMeni.add(mntmPoetna);

		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mntmHoteli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Window win[] = java.awt.Window.getWindows();
				for (int i = 0; i < win.length; i++) {
					win[i].dispose();
				}
				Hoteli.PrikaziFormu();
				frmUnosHotela.setVisible(false);
			}
		});
		mnMeni.add(mntmHoteli);
		mntmHoteli.setEnabled(postavke[1]);

		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.Window win[] = java.awt.Window.getWindows();
				for (int i = 0; i < win.length; i++) {
					win[i].dispose();
				}
				Rezervacije.PrikaziFormu();
				frmUnosHotela.setVisible(false);

			}
		});
		mnMeni.add(mntmRezervacije);
		mntmRezervacije.setEnabled(postavke[2]);

		if (UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3) {
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					java.awt.Window win[] = java.awt.Window.getWindows();
					for (int i = 0; i < win.length; i++) {
						win[i].dispose();
					}
					Klijenti.PrikaziFormu();
					frmUnosHotela.setVisible(false);

				}
			});
			mnMeni.add(mntmKlijenti);
			mntmRezervacije.setEnabled(postavke[3]);
		}

		if (UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3) {
			JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
			mntmKorisnici.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					java.awt.Window win[] = java.awt.Window.getWindows();
					for (int i = 0; i < win.length; i++) {
						win[i].dispose();
					}
					Korisnici.PrikaziFormu();
					frmUnosHotela.setVisible(false);
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
				for (int i = 0; i < win.length; i++) {
					win[i].dispose();
				}
				Prijava.PrikaziFormu();
			}
		});
		mnRaun.add(mntmOdjaviSe);
	}

	
}
