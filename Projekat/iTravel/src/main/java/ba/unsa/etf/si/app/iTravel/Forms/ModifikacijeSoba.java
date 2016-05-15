
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
	private ArrayList<Destinacija> destinacije;
	private JFrame frmModifikacijeSoba;
	
	private JComboBox<String> comboBox;
	private Soba hotelsoba = new Soba();
	private JTextField textField;
	private JTextField textField_1;
	private JSpinner spinner;
	private JTextField textField_2;
	class AkcijaDodavanja implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (ValidacijaPoljaZaDodavanjeSobe()) {
				
				SobeService sobeService = new  SobeService();
				
                hotelsoba.setBrojKreveta((Integer) spinner.getValue());
                hotelsoba.setCijenaNiska(Integer.parseInt(textField_1.getText()));
                hotelsoba.setCijenaVisoka(Integer.parseInt(textField.getText()));
                hotelsoba.setOpis(textField_2.getText());
				
			
				
				
				sobeService.AzurirajiliUbaciSobu(hotelsoba);

				JOptionPane.showMessageDialog(null, "Uspjesno ste kreirali sobu", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, ":(", "Info", JOptionPane.INFORMATION_MESSAGE);

			}
		}
		

	}


	private boolean ValidacijaPoljaZaDodavanjeSobe() {
		if (textField_1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli lokaciju", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (textField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Niste unijeli naziv", "Info", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if (textField_2.getText().equals("")) {
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
					window.frmModifikacijeSoba.setVisible(true);
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
        hotelsoba=soba;
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
		
		frmModifikacijeSoba = new JFrame();
		frmModifikacijeSoba.setTitle("Modifikacije Soba");
		frmModifikacijeSoba.setBounds(100, 100, 383, 338);
		frmModifikacijeSoba.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmModifikacijeSoba.getContentPane().setLayout(null);
		frmModifikacijeSoba.setLocationRelativeTo(null);

		JLabel lbBrojKreveta = new JLabel("Broj kreveta:");
		lbBrojKreveta.setHorizontalAlignment(SwingConstants.RIGHT);
		lbBrojKreveta.setBounds(10, 68, 161, 20);
		frmModifikacijeSoba.getContentPane().add(lbBrojKreveta);

		JLabel lblCijenaNiska = new JLabel("Cijena nikska sezona:");
		lblCijenaNiska.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCijenaNiska.setBounds(10, 99, 160, 20);
		frmModifikacijeSoba.getContentPane().add(lblCijenaNiska);

		JLabel lblCijenaVisoka = new JLabel("Cijena visoka sezona:");
		lblCijenaVisoka.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCijenaVisoka.setBounds(10, 130, 161, 20);
		frmModifikacijeSoba.getContentPane().add(lblCijenaVisoka);

		JLabel lblOpis = new JLabel("Opis:");
		lblOpis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOpis.setBounds(10, 160, 161, 20);
		frmModifikacijeSoba.getContentPane().add(lblOpis);

		textField = new JTextField();
		textField.setBounds(191, 130, 138, 20);
		frmModifikacijeSoba.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(191, 99, 138, 20);
		frmModifikacijeSoba.getContentPane().add(textField_1);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinner.setBounds(191, 68, 138, 20);
		frmModifikacijeSoba.getContentPane().add(spinner);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(191, 160, 138, 66);
		frmModifikacijeSoba.getContentPane().add(textField_2);

		JButton btnNewButton = new JButton("Spasi");
		btnNewButton.setBounds(99, 237, 161, 30);
		frmModifikacijeSoba.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new AkcijaDodavanja());

		JMenuBar menuBar = new JMenuBar();
		frmModifikacijeSoba.setJMenuBar(menuBar);

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
					frmModifikacijeSoba.setVisible(false);
				} else if (UserContext.getInstance().getRoleID() == 2) {
					PocetnaFormaAgent.PrikaziFormu();
					frmModifikacijeSoba.setVisible(false);
				} else if (UserContext.getInstance().getRoleID() == 3) {
					PocetnaFormaSupervizor.PrikaziFormu();
					frmModifikacijeSoba.setVisible(false);
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
				frmModifikacijeSoba.setVisible(false);
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
				frmModifikacijeSoba.setVisible(false);
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
					frmModifikacijeSoba.setVisible(false);

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
					frmModifikacijeSoba.setVisible(false);

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
