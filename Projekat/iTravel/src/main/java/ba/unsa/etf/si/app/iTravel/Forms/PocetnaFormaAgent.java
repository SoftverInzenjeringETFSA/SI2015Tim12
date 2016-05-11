package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PocetnaFormaAgent {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PocetnaFormaAgent window = new PocetnaFormaAgent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JButton btnHoteliUPonudi = new JButton("Hoteli u ponudi");
		btnHoteliUPonudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hoteli forma = new Hoteli();
				forma.PrikaziFormu();
			}
		});
		btnHoteliUPonudi.setBounds(38, 81, 350, 35);
		frame.getContentPane().add(btnHoteliUPonudi);
		
		JLabel lblPrijavljeniSteKao = new JLabel("Prijavljeni ste kao putni\u010Dki agent, dobrodo\u0161li!");
		lblPrijavljeniSteKao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrijavljeniSteKao.setBounds(55, 28, 312, 22);
		frame.getContentPane().add(lblPrijavljeniSteKao);
		
		JButton btnRezervacije = new JButton("Rezervacije");
		btnRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rezervacije forma=new Rezervacije();
				frame.setVisible(false);
				forma.PrikaziFormu();
			}
		});
		btnRezervacije.setBounds(38, 127, 350, 35);
		frame.getContentPane().add(btnRezervacije);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnAkcije = new JMenu("Račun");
		menuBar.add(mnAkcije);
		
		JMenuItem mntmPromijeniifru = new JMenuItem("Promijeni \u0161ifru");
		mntmPromijeniifru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PromjenaSifre novaForma = new PromjenaSifre();
				novaForma.PrikaziFormu();
			}
		});
		mnAkcije.add(mntmPromijeniifru);
		
		JMenuItem mntmOdjaviSe = new JMenuItem("Odjavi se");
		mntmOdjaviSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OdjavaService odjava = new OdjavaService();
				odjava.OdjaviKorisnika();
							
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 
				Prijava prijava = new Prijava();
				prijava.PrikaziFormu();
			}
		});
		mnAkcije.add(mntmOdjaviSe);
	}

}
