package ba.unsa.etf.si.app.iTravel.Forms;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class Klijenti {

	private JFrame frmPrikazKlijenata;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Klijenti window = new Klijenti();
					window.frmPrikazKlijenata.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Klijenti() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrikazKlijenata = new JFrame();
		frmPrikazKlijenata.setTitle("Prikaz klijenata");
		frmPrikazKlijenata.setBounds(100, 100, 876, 318);
		frmPrikazKlijenata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrikazKlijenata.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 29, 821, 175);
		frmPrikazKlijenata.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Predrag", "Simani\u0107", "1203993896541", "123456", "061789654", "psimanic1@etf.unsa.ba", "Neka Adresa 5", "12.03.1993."},
				{"Emina", "Prlja", "1405993174515", "124512", "062845854", "eprlja1@etf.unsa.ba", "Adresa 6", "14.05.1993."},
				{"Adna", "Tahi\u0107", "1001993123323", "321321", "062365456", null, "Neko Mjesto 12", null},
			},
			new String[] {
				"Ime", "Prezime", "JMBG", "Broj paso\u0161a", "Broj telefona", "E-mail", "Adresa", "Datum ro\u0111enja"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(88);
		table.getColumnModel().getColumn(6).setPreferredWidth(95);
		scrollPane.setViewportView(table);
		
		JButton btnModifikujKorisnike = new JButton("Modifikuj klijenta");
		btnModifikujKorisnike.setBounds(20, 222, 150, 30);
		frmPrikazKlijenata.getContentPane().add(btnModifikujKorisnike);
		
		JButton btnObriiKorisnika = new JButton("Obri\u0161i klijenta");
		btnObriiKorisnika.setBounds(180, 222, 150, 30);
		frmPrikazKlijenata.getContentPane().add(btnObriiKorisnika);
		
		JButton btnIzlaz = new JButton("Izlaz");
		btnIzlaz.setBounds(691, 222, 150, 30);
		frmPrikazKlijenata.getContentPane().add(btnIzlaz);
	}
}