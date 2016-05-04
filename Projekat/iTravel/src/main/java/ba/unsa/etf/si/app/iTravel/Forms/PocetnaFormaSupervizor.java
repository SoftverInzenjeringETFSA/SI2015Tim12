package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PocetnaFormaSupervizor {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PocetnaFormaSupervizor window = new PocetnaFormaSupervizor();
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
	public PocetnaFormaSupervizor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 444, 379);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPrijavljeniSteKao = new JLabel("Prijavljeni ste kao supervizor, dobrodo\u0161li!");
		lblPrijavljeniSteKao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrijavljeniSteKao.setBounds(70, 27, 288, 22);
		frame.getContentPane().add(lblPrijavljeniSteKao);
		
		JButton btnHoteliUPonudi = new JButton("Hoteli u ponudi");
		btnHoteliUPonudi.setBounds(36, 69, 350, 35);
		frame.getContentPane().add(btnHoteliUPonudi);
		
		JButton btnRezervacije = new JButton("Rezervacije");
		btnRezervacije.setBounds(36, 115, 350, 35);
		frame.getContentPane().add(btnRezervacije);
		
		JButton btnKlijenti = new JButton("Klijenti");
		btnKlijenti.setBounds(36, 161, 350, 35);
		frame.getContentPane().add(btnKlijenti);
		
		JButton btnKorisnici = new JButton("Korisnici");
		btnKorisnici.setBounds(36, 207, 350, 35);
		frame.getContentPane().add(btnKorisnici);
		
		JButton btnIzvjetaji = new JButton("Izvje\u0161taji");
		btnIzvjetaji.setBounds(36, 253, 350, 35);
		frame.getContentPane().add(btnIzvjetaji);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Akcije");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Promijeni \u0161ifru");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Odjavi se");
		mnNewMenu.add(mntmNewMenuItem_1);
	}
}
