package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PocetnaFormaAgent {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PocetnaFormaAgent window = new PocetnaFormaAgent();
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
	public PocetnaFormaAgent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 444, 255);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnHoteliUPonudi = new JButton("Hoteli u ponudi");
		btnHoteliUPonudi.setBounds(38, 81, 350, 35);
		frame.getContentPane().add(btnHoteliUPonudi);
		
		JLabel lblPrijavljeniSteKao = new JLabel("Prijavljeni ste kao putni\u010Dki agent, dobrodo\u0161li!");
		lblPrijavljeniSteKao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrijavljeniSteKao.setBounds(55, 28, 312, 22);
		frame.getContentPane().add(lblPrijavljeniSteKao);
		
		JButton btnRezervacije = new JButton("Rezervacije");
		btnRezervacije.setBounds(38, 127, 350, 35);
		frame.getContentPane().add(btnRezervacije);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnAkcije = new JMenu("Akcije");
		menuBar.add(mnAkcije);
		
		JMenuItem mntmPromijeniifru = new JMenuItem("Promijeni \u0161ifru");
		mnAkcije.add(mntmPromijeniifru);
		
		JMenuItem mntmOdjaviSe = new JMenuItem("Odjavi se");
		mnAkcije.add(mntmOdjaviSe);
	}

}
