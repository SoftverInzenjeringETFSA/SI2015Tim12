package ba.unsa.etf.si.app.iTravel.Forms;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hoteli {

	private JFrame frmPrikazHotela;
	private JTable table_pregledHotela;
	private JScrollPane scrollPane;
	private JButton btnModifikujHotel;
	private JButton btnObriiHotel;
	private JButton btnDodajHotel;
	private JButton btnPregledajSobe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hoteli window = new Hoteli();
					window.frmPrikazHotela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Hoteli() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrikazHotela = new JFrame();
		frmPrikazHotela.setTitle("Pregled hotela");
		frmPrikazHotela.setBounds(100, 100, 784, 382);
		frmPrikazHotela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrikazHotela.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 41, 720, 218);
		frmPrikazHotela.getContentPane().add(scrollPane);
		
		table_pregledHotela = new JTable();
		table_pregledHotela.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table_pregledHotela);
		table_pregledHotela.setCellSelectionEnabled(true);
		table_pregledHotela.setBackground(Color.WHITE);
		table_pregledHotela.setBorder(new CompoundBorder());
		table_pregledHotela.setModel(new DefaultTableModel(
			new Object[][] {
				{"The Marmara Taksi", "Istanbul, Turkey", new Float(5.0f), new Integer(19), new Integer(9), new Float(150.0f), new Float(135.0f)},
				{"Sura Hagia Sophia", "Istanbul, Turkey", new Float(5.0f), new Integer(15), new Integer(15), new Float(150.0f), new Float(130.0f)},
				{"Grand Hyatt Dubai", "Dubai, UAE", new Float(4.5f), new Integer(15), new Integer(7), new Float(140.0f), new Float(120.0f)},
				{"Marina Byblos", "Dubai, UAE", new Float(4.0f), new Integer(24), new Integer(10), new Float(130.0f), new Float(110.0f)},
				{"Ambassador", "Istanbul, Turkey", new Float(1.0f), new Integer(15), new Integer(14), new Float(70.0f), new Float(50.0f)},
			},
			new String[] {
				"Ime", "Destinacija", "Broj zvjezdica", "Slobodne sobe", "Zauzete sobe", "Cijena visoke sezone", "Cijena niske sezone"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Float.class, Integer.class, Integer.class, Float.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		btnModifikujHotel = new JButton("Modifikuj hotel");
		btnModifikujHotel.setBounds(343, 287, 150, 30);
		frmPrikazHotela.getContentPane().add(btnModifikujHotel);
		
		btnObriiHotel = new JButton("Obri\u0161i hotel");
		btnObriiHotel.setBounds(503, 287, 150, 30);
		frmPrikazHotela.getContentPane().add(btnObriiHotel);
		
		btnDodajHotel = new JButton("Dodaj hotel");
		btnDodajHotel.setBounds(183, 287, 150, 30);
		frmPrikazHotela.getContentPane().add(btnDodajHotel);
		
		btnPregledajSobe = new JButton("Pregledaj sobe");
		btnPregledajSobe.setBounds(23, 287, 150, 30);
		frmPrikazHotela.getContentPane().add(btnPregledajSobe);
		table_pregledHotela.getColumnModel().getColumn(0).setPreferredWidth(99);
		table_pregledHotela.getColumnModel().getColumn(1).setPreferredWidth(83);
		table_pregledHotela.getColumnModel().getColumn(2).setPreferredWidth(81);
		table_pregledHotela.getColumnModel().getColumn(3).setPreferredWidth(85);
		table_pregledHotela.getColumnModel().getColumn(4).setPreferredWidth(78);
		table_pregledHotela.getColumnModel().getColumn(5).setPreferredWidth(110);
		table_pregledHotela.getColumnModel().getColumn(6).setPreferredWidth(108);
	}
}