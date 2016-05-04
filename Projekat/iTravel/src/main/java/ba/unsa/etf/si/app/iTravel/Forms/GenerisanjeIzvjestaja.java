package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.Box;
import com.toedter.calendar.JDateChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GenerisanjeIzvjestaja {

	private JFrame frame;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerisanjeIzvjestaja window = new GenerisanjeIzvjestaja();
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
	public GenerisanjeIzvjestaja() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 625, 618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Izvještaj o top destinacijama");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(32, 11, 206, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblOd = new JLabel("Od:");
		lblOd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOd.setBounds(32, 58, 36, 14);
		frame.getContentPane().add(lblOd);
		
		JLabel lblNewLabel_1 = new JLabel("Do:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(232, 58, 28, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 102, 554, 139);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"Destinacija", "Broj posjeta"
			}
		));
		
		JButton btnNewButton = new JButton("Generi\u0161i");
		btnNewButton.setBounds(436, 50, 150, 30);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("Od:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(32, 321, 36, 14);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("Generi\u0161i");
		button.setBounds(436, 313, 150, 30);
		frame.getContentPane().add(button);
		
		JLabel lblIzvjetajOIskoritenosti = new JLabel("Izvje\u0161taj o iskori\u0161tenosti soba");
		lblIzvjetajOIskoritenosti.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIzvjetajOIskoritenosti.setBounds(32, 272, 206, 26);
		frame.getContentPane().add(lblIzvjetajOIskoritenosti);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(32, 365, 554, 139);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Destinacija", "Hotel", "Broj iznajmljenih soba", "Broj iskori\u0161tenih soba", "Iskori\u0161tenost (%)"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(93);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(79);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(136);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(126);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(104);
		scrollPane_1.setViewportView(table_1);
		
		JLabel label_1 = new JLabel("Do:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(232, 321, 28, 14);
		frame.getContentPane().add(label_1);
		
		JButton btnIzlaz = new JButton("Izlaz");
		btnIzlaz.setBounds(436, 530, 150, 30);
		frame.getContentPane().add(btnIzlaz);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(78, 55, 131, 20);
		frame.getContentPane().add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(270, 52, 131, 20);
		frame.getContentPane().add(dateChooser_1);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(78, 315, 131, 20);
		frame.getContentPane().add(dateChooser_2);
		
		JDateChooser dateChooser_3 = new JDateChooser();
		dateChooser_3.setBounds(270, 315, 131, 20);
		frame.getContentPane().add(dateChooser_3);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnPovratak = new JMenu("Meni");
		menuBar.add(mnPovratak);
		
		JMenuItem mntmPoetna = new JMenuItem("Početna");
		mnPovratak.add(mntmPoetna);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Hoteli");
		mnPovratak.add(mntmNewMenuItem);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mnPovratak.add(mntmRezervacije);
		
		JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
		mnPovratak.add(mntmKlijenti);
		
		JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
		mnPovratak.add(mntmKorisnici);
		
		JMenu mnRaun = new JMenu("Račun");
		menuBar.add(mnRaun);
		
		JMenuItem mntmPromijeniifru = new JMenuItem("Promijeni šifru");
		mnRaun.add(mntmPromijeniifru);
		
		JMenuItem mntmOdjaviSe = new JMenuItem("Odjavi se");
		mnRaun.add(mntmOdjaviSe);
		table.getColumnModel().getColumn(0).setPreferredWidth(284);
		table.getColumnModel().getColumn(1).setPreferredWidth(290);
	}
}
