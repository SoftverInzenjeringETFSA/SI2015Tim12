package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PocetnaFormaSupervizor {

	private UnitOfWork uow = new UnitOfWork();
	
	private JFrame frame;
	
	JButton btnHoteliUPonudi;	
	JButton btnRezervacije;	
	JButton btnKlijenti;		
	JButton btnKorisnici; 	
	JButton btnIzvjetaji;

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
					UnitOfWork.logger.error(e);
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
		frame.setLocationRelativeTo(null);
		
		JLabel lblPrijavljeniSteKao = new JLabel("Prijavljeni ste kao supervizor, dobrodo\u0161li!");
		lblPrijavljeniSteKao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrijavljeniSteKao.setBounds(70, 27, 288, 22);
		frame.getContentPane().add(lblPrijavljeniSteKao);
		
		btnHoteliUPonudi = new JButton("Hoteli u ponudi");
		btnHoteliUPonudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hoteli.PrikaziFormu();
			}
		});
		btnHoteliUPonudi.setBounds(36, 69, 350, 35);
		frame.getContentPane().add(btnHoteliUPonudi);
		
		btnHoteliUPonudi.setEnabled(uow.getPostavkeService().modulOmogucen(1));
		
		btnRezervacije = new JButton("Rezervacije");
		btnRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rezervacije.PrikaziFormu();
			}
		});
		btnRezervacije.setBounds(36, 115, 350, 35);
		frame.getContentPane().add(btnRezervacije);
		
		btnRezervacije.setEnabled(uow.getPostavkeService().modulOmogucen(2));
		
		btnKlijenti = new JButton("Klijenti");
		btnKlijenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Klijenti.PrikaziFormu();
			}
		});
		btnKlijenti.setBounds(36, 161, 350, 35);
		frame.getContentPane().add(btnKlijenti);
		
		btnKlijenti.setEnabled(uow.getPostavkeService().modulOmogucen(3));
		
		btnKorisnici = new JButton("Korisnici");
		btnKorisnici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Korisnici.PrikaziFormu();
			}
		});
		btnKorisnici.setBounds(36, 207, 350, 35);
		frame.getContentPane().add(btnKorisnici);
		
		btnKorisnici.setEnabled(uow.getPostavkeService().modulOmogucen(4));
		
		btnIzvjetaji = new JButton("Izvje\u0161taji");
		btnIzvjetaji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerisanjeIzvjestaja.PrikaziFormu();
			}
		});
		btnIzvjetaji.setBounds(36, 253, 350, 35);
		frame.getContentPane().add(btnIzvjetaji);
		
		btnIzvjetaji.setEnabled(uow.getPostavkeService().modulOmogucen(5));
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Račun");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Promijeni \u0161ifru");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PromjenaSifre.PrikaziFormu();

			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Odjavi se");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				uow.getOdjavaService().OdjaviKorisnika();
				
				java.awt.Window win[] = java.awt.Window.getWindows(); 
				for(int i=0;i<win.length;i++){ 
				win[i].dispose(); 
				} 
				Prijava.PrikaziFormu();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
	}
}
