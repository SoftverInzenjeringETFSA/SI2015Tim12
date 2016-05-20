
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
import ba.unsa.etf.si.app.iTravel.BLL.SobeService;
import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;

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

public class ModifikacijeSoba {
	private UnitOfWork uow = new UnitOfWork();
	private ArrayList<Destinacija> destinacijeModifikacija;
	private JFrame frmModifikacijeSobaModifikacija;
	
	private JComboBox<String> comboBoxModifikacija;
	private Soba hotelsobaModifikacija = new Soba();
	private JTextField textFieldModifikacija;
	private JTextField textField_1Modifikacija;
	private JSpinner spinnerModifikacija;
	private JTextField textField_2Modifikacija;
	class AkcijaDodavanja implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (ValidacijaPoljaZaDodavanjeSobe()) {
				
				SobeService sobeService = new  SobeService();
				
                hotelsobaModifikacija.setBrojKreveta((Integer) spinnerModifikacija.getValue());
                hotelsobaModifikacija.setCijenaNiska(Integer.parseInt(textField_1Modifikacija.getText()));
                hotelsobaModifikacija.setCijenaVisoka(Integer.parseInt(textFieldModifikacija.getText()));
                hotelsobaModifikacija.setOpis(textField_2Modifikacija.getText());
			
				sobeService.AzurirajiliUbaciSobu(hotelsobaModifikacija);

				JOptionPane.showMessageDialog(null, "Uspjesno ste kreirali sobu", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, ":(", "Info", JOptionPane.INFORMATION_MESSAGE);

			}
		}
		

	}


	private boolean ValidacijaPoljaZaDodavanjeSobe() {
		if (textField_1Modifikacija.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli lokaciju", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (textFieldModifikacija.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli naziv", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (textField_2Modifikacija.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli lanac hotela", "Info", JOptionPane.INFORMATION_MESSAGE);
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
                   
					ModifikacijeSoba window = new ModifikacijeSoba();
					window.frmModifikacijeSobaModifikacija.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});

	}
	/**
	 * Create the application
	 */
	public ModifikacijeSoba(Soba soba) {
        hotelsobaModifikacija=soba;
		initialize();
	}
	public ModifikacijeSoba() {
        
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
		frmModifikacijeSobaModifikacija = new JFrame();
		frmModifikacijeSobaModifikacija.setTitle("Modifikacije Soba");
		frmModifikacijeSobaModifikacija.setBounds(100, 100, 383, 338);
		frmModifikacijeSobaModifikacija.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModifikacijeSobaModifikacija.getContentPane().setLayout(null);
		frmModifikacijeSobaModifikacija.setLocationRelativeTo(null);

		JLabel lbBrojKreveta = new JLabel("Broj kreveta:");
		lbBrojKreveta.setHorizontalAlignment(SwingConstants.RIGHT);
		lbBrojKreveta.setBounds(10, 68, 161, 20);
		frmModifikacijeSobaModifikacija.getContentPane().add(lbBrojKreveta);

		JLabel lblCijenaNiska = new JLabel("Cijena nikska sezona:");
		lblCijenaNiska.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCijenaNiska.setBounds(10, 99, 160, 20);
		frmModifikacijeSobaModifikacija.getContentPane().add(lblCijenaNiska);

		JLabel lblCijenaVisoka = new JLabel("Cijena visoka sezona:");
		lblCijenaVisoka.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCijenaVisoka.setBounds(10, 130, 161, 20);
		frmModifikacijeSobaModifikacija.getContentPane().add(lblCijenaVisoka);

		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOpis.setBounds(10, 160, 161, 20);
		frmModifikacijeSobaModifikacija.getContentPane().add(lblOpis);

		textFieldModifikacija = new JTextField();
		textFieldModifikacija.setBounds(191, 130, 138, 20);
		frmModifikacijeSobaModifikacija.getContentPane().add(textFieldModifikacija);
		textFieldModifikacija.setColumns(10);

		textField_1Modifikacija = new JTextField();
		textField_1Modifikacija.setColumns(10);
		textField_1Modifikacija.setBounds(191, 99, 138, 20);
		frmModifikacijeSobaModifikacija.getContentPane().add(textField_1Modifikacija);

		spinnerModifikacija = new JSpinner();
		spinnerModifikacija.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinnerModifikacija.setBounds(191, 68, 138, 20);
		frmModifikacijeSobaModifikacija.getContentPane().add(spinnerModifikacija);

		textField_2Modifikacija = new JTextField();
		textField_2Modifikacija.setColumns(10);
		textField_2Modifikacija.setBounds(191, 160, 138, 66);
		frmModifikacijeSobaModifikacija.getContentPane().add(textField_2Modifikacija);

		JButton btnNewButton = new JButton("Spasi");
		btnNewButton.setBounds(99, 237, 161, 30);
		frmModifikacijeSobaModifikacija.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new AkcijaDodavanja());

		JMenuBar menuBar = new JMenuBar();
		frmModifikacijeSobaModifikacija.setJMenuBar(menuBar);

		JMenu mnMeni = new JMenu("Meni");
		menuBar.add(mnMeni);
		JMenu mnRefresh = new JMenu("Refresh ");
		menuBar.add(mnRefresh);

		JMenuItem mnOsvjezi = new JMenuItem("Osvjezi");
		mnOsvjezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
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
					frmModifikacijeSobaModifikacija.setVisible(false);
				} else if (UserContext.getInstance().getRoleID() == 2) {
					PocetnaFormaAgent.PrikaziFormu();
					frmModifikacijeSobaModifikacija.setVisible(false);
				} else if (UserContext.getInstance().getRoleID() == 3) {
					PocetnaFormaSupervizor.PrikaziFormu();
					frmModifikacijeSobaModifikacija.setVisible(false);
				}
			}
		});
		mnMeni.add(mntmPoetna);

		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mntmHoteli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Hoteli(frmModifikacijeSobaModifikacija);
			}
		});
		mnMeni.add(mntmHoteli);
		mntmHoteli.setEnabled(postavke[1]);

		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Rezervacije(frmModifikacijeSobaModifikacija);
			}
		});
		mnMeni.add(mntmRezervacije);
		mntmRezervacije.setEnabled(postavke[2]);

		if (UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3) {
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Meni.Klijenti(frmModifikacijeSobaModifikacija);
				}
			});
			mnMeni.add(mntmKlijenti);
			mntmRezervacije.setEnabled(postavke[3]);
		}

		if (UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3) {
			JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
			mntmKorisnici.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Meni.Korisnici(frmModifikacijeSobaModifikacija);
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
				Meni.HelpForma("/HelpImages/PregledSoba.jpg");
			}
		});
		mnPomo.add(mntmOFormi);
	}


}
