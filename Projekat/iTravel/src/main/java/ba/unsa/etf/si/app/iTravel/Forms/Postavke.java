package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Postavke {

	private JFrame frmPostavke;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Postavke window = new Postavke();
					window.frmPostavke.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Postavke() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPostavke = new JFrame();
		frmPostavke.setTitle("Postavke");
		frmPostavke.setBounds(100, 100, 228, 268);
		frmPostavke.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPostavke.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmPostavke.setJMenuBar(menuBar);
		
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
		mntmPromijeniifru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PromjenaSifre novaForma = new PromjenaSifre();
				novaForma.PrikaziFormu();
			}
		});
		mnRaun.add(mntmPromijeniifru);
		
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
		mnRaun.add(mntmOdjaviSe);
		frmPostavke.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Uključi/isključi module:");
		lblNewLabel.setBounds(28, 21, 131, 14);
		frmPostavke.getContentPane().add(lblNewLabel);
		
		JCheckBox chckbxHoteli = new JCheckBox("Hoteli");
		chckbxHoteli.setBounds(38, 42, 97, 23);
		frmPostavke.getContentPane().add(chckbxHoteli);
		
		JCheckBox chckbxRezervacije = new JCheckBox("Rezervacije");
		chckbxRezervacije.setBounds(38, 67, 97, 23);
		frmPostavke.getContentPane().add(chckbxRezervacije);
		
		JCheckBox chckbxKlijenti = new JCheckBox("Klijenti");
		chckbxKlijenti.setBounds(38, 93, 97, 23);
		frmPostavke.getContentPane().add(chckbxKlijenti);
		
		JCheckBox chckbxKorisnici = new JCheckBox("Korisnici");
		chckbxKorisnici.setBounds(38, 119, 97, 23);
		frmPostavke.getContentPane().add(chckbxKorisnici);
		
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setBounds(26, 162, 150, 30);
		frmPostavke.getContentPane().add(btnPotvrdi);
	}
}
