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

public class Korisnici {

	private JFrame frmPrikazKorisnika;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Korisnici window = new Korisnici();
					window.frmPrikazKorisnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Korisnici() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrikazKorisnika = new JFrame();
		frmPrikazKorisnika.setTitle("Prikaz korisnika");
		frmPrikazKorisnika.setBounds(100, 100, 876, 318);
		frmPrikazKorisnika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrikazKorisnika.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 29, 821, 175);
		frmPrikazKorisnika.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Kenan", "Prses", "1234567898745", "38LC11A", "Zmaja od Bosne bb", "061111111", "kprses1@gmail.com", "prso", "Supervizor"},
				{"Emina", "Prlja", "9876543211236", "12AE21A", "Zmaja od Bosne bb", "061222222", "eprlja1@gmail.com", "emina", "Putni\u010Dki agent"},
				{"\u0160ahin", "Repuh", "1111111111111", "3CLC12A", "Senada Mandi\u0107a 7", "063929365", "srepuh1@gmail.com", "sahin", "Putni\u010Dki agent"},
			},
			new String[] {
				"Ime", "Prezime", "JMBG", "Broj li\u010Dne karte", "Adresa", "Telefon", "E-mail", "Username", "Tip korisnika"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(89);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(99);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		JButton button_izlaz = new JButton("Izlaz");
		button_izlaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		button_izlaz.setBounds(676, 226, 150, 30);
		frmPrikazKorisnika.getContentPane().add(button_izlaz);
		
		JButton btnModifikujKorisnike = new JButton("Modifikuj korisnika");
		btnModifikujKorisnike.setBounds(180, 226, 150, 30);
		frmPrikazKorisnika.getContentPane().add(btnModifikujKorisnike);
		
		JButton btnObriiKorisnika = new JButton("Obri\u0161i korisnika");
		btnObriiKorisnika.setBounds(340, 226, 150, 30);
		frmPrikazKorisnika.getContentPane().add(btnObriiKorisnika);
		
		JButton btnDodajKorisnika = new JButton("Dodaj korisnika");
		btnDodajKorisnika.setBounds(20, 226, 150, 30);
		frmPrikazKorisnika.getContentPane().add(btnDodajKorisnika);
	}
}
