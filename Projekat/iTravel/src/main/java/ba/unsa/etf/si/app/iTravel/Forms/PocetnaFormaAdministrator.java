package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class PocetnaFormaAdministrator {

	private UnitOfWork uow = new UnitOfWork();
	
	private JFrame frame;
	
	JButton btnPonude;	
	JButton btnRezervacije;	
	JButton btnKlijenti;		
	JButton btnKorisnici; 	
	JButton btnIzvjetaji;

	private void otvoriPostavke()
	{
		Postavke forma = new Postavke();
		forma.PrikaziFormu();
	}
		
	/**
	 * Launch the application.
	 */
	public void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PocetnaFormaAdministrator window = new PocetnaFormaAdministrator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PocetnaFormaAdministrator window = new PocetnaFormaAdministrator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}
	
	private void OsvjeziPostavkeFormu()
	{
		/*
		boolean prvi = uow.getPostavkeService().modulOmogucen(1);
		boolean drugi = uow.getPostavkeService().modulOmogucen(2);
		boolean treci = uow.getPostavkeService().modulOmogucen(3);
		boolean cet = uow.getPostavkeService().modulOmogucen(4);
		boolean pet = uow.getPostavkeService().modulOmogucen(5);
		
		System.out.println("#########################################################");
		System.out.println(prvi + " " + drugi + " " + treci + " " + cet + " " + pet);
		System.out.println("#########################################################");
		*/
		btnPonude.setEnabled(uow.getPostavkeService().modulOmogucen(1));
		btnRezervacije.setEnabled(uow.getPostavkeService().modulOmogucen(2));	
		btnKlijenti.setEnabled(uow.getPostavkeService().modulOmogucen(3));		
		btnKorisnici.setEnabled(uow.getPostavkeService().modulOmogucen(4)); 	
		btnIzvjetaji.setEnabled(uow.getPostavkeService().modulOmogucen(5));
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
	      frame.addWindowFocusListener(new WindowFocusListener() {
			
			public void windowLostFocus(WindowEvent e) {

			}
			
			public void windowGainedFocus(WindowEvent e) {
				System.out.println("Dobio fokus");
				OsvjeziPostavkeFormu();	
			}
		});
		
		JLabel lblPrijavljeniSteKao = new JLabel("Prijavljeni ste kao administrator, dobrodo\u0161li!");
		lblPrijavljeniSteKao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrijavljeniSteKao.setBounds(67, 22, 313, 22);
		frame.getContentPane().add(lblPrijavljeniSteKao);
		
		btnPonude = new JButton("Hoteli u ponudi");
		btnPonude.setEnabled(uow.getPostavkeService().modulOmogucen(1));
		
		btnPonude.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hoteli novaForma = new Hoteli();
				novaForma.PrikaziFormu();
			}
		});
		btnPonude.setBounds(44, 67, 350, 35);
		frame.getContentPane().add(btnPonude);
		
		btnRezervacije = new JButton("Rezervacije");
		btnRezervacije.setEnabled(uow.getPostavkeService().modulOmogucen(2));
		
		btnRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rezervacije novaForma = new Rezervacije();
						novaForma.PrikaziFormu();
			}
		});
		btnRezervacije.setBounds(44, 113, 350, 35);
		frame.getContentPane().add(btnRezervacije);
		
		btnKlijenti = new JButton("Klijenti");
		btnKlijenti.setEnabled(uow.getPostavkeService().modulOmogucen(3));
		
		btnKlijenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Klijenti forma = new Klijenti();
				forma.PrikaziFormu();
			}
		});
		btnKlijenti.setBounds(44, 158, 350, 35);
		frame.getContentPane().add(btnKlijenti);
				
		btnKorisnici = new JButton("Korisnici");
		btnKorisnici.setEnabled(uow.getPostavkeService().modulOmogucen(4));
		
		btnKorisnici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Korisnici forma = new Korisnici();
				forma.PrikaziFormu();
			}
		});
		btnKorisnici.setBounds(44, 204, 350, 35);
		frame.getContentPane().add(btnKorisnici);
		
		btnIzvjetaji = new JButton("Izvještaji");
		btnIzvjetaji.setEnabled(uow.getPostavkeService().modulOmogucen(5));
		
		btnIzvjetaji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerisanjeIzvjestaja forma = new GenerisanjeIzvjestaja();
				forma.PrikaziFormu();
			}
		});
		btnIzvjetaji.setBounds(44, 250, 350, 35);
		frame.getContentPane().add(btnIzvjetaji);
		
		JButton btnPostavke = new JButton("Postavke");
		btnPostavke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				otvoriPostavke();
			}
		});
		btnPostavke.setBounds(44, 296, 350, 35);
		frame.getContentPane().add(btnPostavke);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Račun");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmPromijeniifru = new JMenuItem("Promijeni \u0161ifru");
		mntmPromijeniifru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PromjenaSifre novaForma = new PromjenaSifre();
				novaForma.PrikaziFormu();
			}
		});
		mnNewMenu.add(mntmPromijeniifru);
		
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
		mnNewMenu.add(mntmOdjaviSe);
	}
}
