package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PocetnaFormaAdministrator {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PocetnaFormaAdministrator window = new PocetnaFormaAdministrator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PocetnaFormaAdministrator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 453, 416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPrijavljeniSteKao = new JLabel("Prijavljeni ste kao administrator, dobrodo\u0161li!");
		lblPrijavljeniSteKao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrijavljeniSteKao.setBounds(67, 22, 313, 22);
		frame.getContentPane().add(lblPrijavljeniSteKao);
		
		JButton btnPonude = new JButton("Hoteli u ponudi");
		btnPonude.setBounds(44, 67, 350, 35);
		frame.getContentPane().add(btnPonude);
		
		JButton btnKorisnici = new JButton("Rezervacije");
		btnKorisnici.setBounds(44, 113, 350, 35);
		frame.getContentPane().add(btnKorisnici);
		
		JButton btnKlijenti = new JButton("Klijenti");
		btnKlijenti.setBounds(44, 158, 350, 35);
		frame.getContentPane().add(btnKlijenti);
		
		JButton btnIzvjetaji = new JButton("Izvje\u0161taji");
		btnIzvjetaji.setBounds(44, 250, 350, 35);
		frame.getContentPane().add(btnIzvjetaji);
		
		JButton btnPostavke = new JButton("Postavke");
		btnPostavke.setBounds(44, 296, 350, 35);
		frame.getContentPane().add(btnPostavke);
		
		JButton btnKorisnici_1 = new JButton("Korisnici");
		btnKorisnici_1.setBounds(44, 204, 350, 35);
		frame.getContentPane().add(btnKorisnici_1);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Akcije");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmPromijeniifru = new JMenuItem("Promijeni \u0161ifru");
		mnNewMenu.add(mntmPromijeniifru);
		
		JMenuItem mntmOdjaviSe = new JMenuItem("Odjavi se");
		mnNewMenu.add(mntmOdjaviSe);
	}
}
