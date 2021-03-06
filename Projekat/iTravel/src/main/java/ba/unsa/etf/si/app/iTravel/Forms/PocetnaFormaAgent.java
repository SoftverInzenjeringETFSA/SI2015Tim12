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

import org.omg.CORBA.PRIVATE_MEMBER;

import ba.unsa.etf.si.app.iTravel.BLL.OdjavaService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PocetnaFormaAgent {

	private UnitOfWork uow = new UnitOfWork();
	
	private JFrame frame;
	
	private JButton btnHoteliUPonudi;
	private JButton btnRezervacije;

	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu() {
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		btnHoteliUPonudi = new JButton("Hoteli u ponudi");
		btnHoteliUPonudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hoteli.PrikaziFormu();
			}
		});
		btnHoteliUPonudi.setBounds(38, 81, 350, 35);
		frame.getContentPane().add(btnHoteliUPonudi);
		
		btnHoteliUPonudi.setEnabled(uow.getPostavkeService().modulOmogucen(1));
		
		JLabel lblPrijavljeniSteKao = new JLabel("Prijavljeni ste kao putni\u010Dki agent, dobrodo\u0161li!");
		lblPrijavljeniSteKao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrijavljeniSteKao.setBounds(55, 28, 312, 22);
		frame.getContentPane().add(lblPrijavljeniSteKao);
		
		btnRezervacije = new JButton("Rezervacije");
		btnRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rezervacije.PrikaziFormu();
				frame.setVisible(false);
			}
		});
		btnRezervacije.setBounds(38, 127, 350, 35);
		frame.getContentPane().add(btnRezervacije);
		
		btnRezervacije.setEnabled(uow.getPostavkeService().modulOmogucen(2));
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnAkcije = new JMenu("Račun");
		menuBar.add(mnAkcije);
		
		JMenuItem mntmPromijeniifru = new JMenuItem("Promijeni \u0161ifru");
		mntmPromijeniifru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PromjenaSifre.PrikaziFormu();
			}
		});
		mnAkcije.add(mntmPromijeniifru);
		
		JMenuItem mntmOdjaviSe = new JMenuItem("Odjavi se");
		mntmOdjaviSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.OdjaviSe();
			}
		});
		mnAkcije.add(mntmOdjaviSe);
		
		JMenu mnPomo = new JMenu("Pomoć");
		menuBar.add(mnPomo);
		JMenuItem mntmOFormi = new JMenuItem("O formi...");
		mntmOFormi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.HelpForma("/HelpImages/PutnickiAgentPocetnaSlika.jpg");
			}
		});
		mnPomo.add(mntmOFormi);
	}

}
