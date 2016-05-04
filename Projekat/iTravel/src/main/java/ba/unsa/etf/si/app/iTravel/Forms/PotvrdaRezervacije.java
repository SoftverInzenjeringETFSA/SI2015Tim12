package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PotvrdaRezervacije {

	private JFrame frmPotvrdaRezervacije;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PotvrdaRezervacije window = new PotvrdaRezervacije();
					window.frmPotvrdaRezervacije.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PotvrdaRezervacije() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPotvrdaRezervacije = new JFrame();
		frmPotvrdaRezervacije.setTitle("Potvrda rezervacije");
		frmPotvrdaRezervacije.setBounds(100, 100, 317, 195);
		frmPotvrdaRezervacije.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPotvrdaRezervacije.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Izdaj potvrdu");
		btnNewButton.setBounds(35, 40, 230, 30);
		frmPotvrdaRezervacije.getContentPane().add(btnNewButton);
		
		JButton btnIzdajFakturu = new JButton("Izdaj fakturu");
		btnIzdajFakturu.setBounds(35, 81, 230, 30);
		frmPotvrdaRezervacije.getContentPane().add(btnIzdajFakturu);
		
		JMenuBar menuBar = new JMenuBar();
		frmPotvrdaRezervacije.setJMenuBar(menuBar);
		
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
		mnRaun.add(mntmPromijeniifru);
		
		JMenuItem mntmOdjaviSe = new JMenuItem("Odjavi se");
		mnRaun.add(mntmOdjaviSe);
	}

}
