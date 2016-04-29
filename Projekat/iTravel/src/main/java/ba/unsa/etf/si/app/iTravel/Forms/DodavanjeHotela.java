package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

public class DodavanjeHotela {

	private JFrame frmUnosHotela;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeHotela window = new DodavanjeHotela();
					window.frmUnosHotela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodavanjeHotela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUnosHotela = new JFrame();
		frmUnosHotela.setTitle("Unos hotela");
		frmUnosHotela.setBounds(100, 100, 400, 428);
		frmUnosHotela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUnosHotela.getContentPane().setLayout(null);
		
		JLabel lblNazivHotela = new JLabel("Naziv hotela:");
		lblNazivHotela.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNazivHotela.setBounds(10, 68, 161, 20);
		frmUnosHotela.getContentPane().add(lblNazivHotela);
		
		JLabel lblBrojZvjezdica = new JLabel("Lokacija hotela:");
		lblBrojZvjezdica.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrojZvjezdica.setBounds(10, 99, 160, 20);
		frmUnosHotela.getContentPane().add(lblBrojZvjezdica);
		
		JLabel label = new JLabel("Broj zvjezdica:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 161, 161, 20);
		frmUnosHotela.getContentPane().add(label);
		
		JLabel lblBroj = new JLabel("Broj unajmljenih soba:");
		lblBroj.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBroj.setBounds(10, 130, 161, 20);
		frmUnosHotela.getContentPane().add(lblBroj);
		
		JLabel lblPoetniDatum = new JLabel("Po\u010Detni datum:");
		lblPoetniDatum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPoetniDatum.setBounds(10, 192, 161, 20);
		frmUnosHotela.getContentPane().add(lblPoetniDatum);
		
		JLabel lblKrajnjiDatum = new JLabel("Krajnji datum:");
		lblKrajnjiDatum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKrajnjiDatum.setBounds(10, 223, 161, 20);
		frmUnosHotela.getContentPane().add(lblKrajnjiDatum);
		
		textField = new JTextField();
		textField.setBounds(191, 68, 138, 20);
		frmUnosHotela.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(191, 99, 138, 20);
		frmUnosHotela.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(191, 130, 138, 20);
		frmUnosHotela.getContentPane().add(textField_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinner.setBounds(191, 161, 42, 20);
		frmUnosHotela.getContentPane().add(spinner);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.setBounds(118, 327, 150, 30);
		frmUnosHotela.getContentPane().add(btnNewButton);
		
		JLabel lblCijena = new JLabel("Cijena (visoka sezona):");
		lblCijena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCijena.setBounds(10, 254, 161, 20);
		frmUnosHotela.getContentPane().add(lblCijena);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(191, 254, 138, 20);
		frmUnosHotela.getContentPane().add(textField_3);
		
		JLabel lblCijenaUNiskoj = new JLabel("Cijena (niska sezona):");
		lblCijenaUNiskoj.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCijenaUNiskoj.setBounds(10, 286, 161, 20);
		frmUnosHotela.getContentPane().add(lblCijenaUNiskoj);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(191, 286, 138, 20);
		frmUnosHotela.getContentPane().add(textField_6);
		
		JLabel lblNewLabel = new JLabel("Unesite podatke o novom hotelu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(88, 24, 213, 14);
		frmUnosHotela.getContentPane().add(lblNewLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(189, 192, 140, 20);
		frmUnosHotela.getContentPane().add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(189, 223, 140, 20);
		frmUnosHotela.getContentPane().add(dateChooser_1);
	}
}

