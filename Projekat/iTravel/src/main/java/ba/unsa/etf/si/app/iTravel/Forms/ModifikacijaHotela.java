package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ModifikacijaHotela {

	private JFrame frmModifikacijaHotela;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifikacijaHotela window = new ModifikacijaHotela();
					window.frmModifikacijaHotela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModifikacijaHotela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModifikacijaHotela = new JFrame();
		frmModifikacijaHotela.setTitle("Modifikacija hotela");
		frmModifikacijaHotela.setBounds(100, 100, 400, 300);
		frmModifikacijaHotela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModifikacijaHotela.getContentPane().setLayout(null);
		
		JLabel lblNazivHotela = new JLabel("Naziv hotela:");
		lblNazivHotela.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNazivHotela.setBounds(10, 68, 161, 20);
		frmModifikacijaHotela.getContentPane().add(lblNazivHotela);
		
		JLabel lblBrojZvjezdica = new JLabel("Lokacija hotela:");
		lblBrojZvjezdica.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrojZvjezdica.setBounds(10, 99, 160, 20);
		frmModifikacijaHotela.getContentPane().add(lblBrojZvjezdica);
		
		JLabel label = new JLabel("Broj zvjezdica:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 161, 161, 20);
		frmModifikacijaHotela.getContentPane().add(label);
		
		JLabel lblBroj = new JLabel("Broj unajmljenih soba:");
		lblBroj.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBroj.setBounds(10, 130, 161, 20);
		frmModifikacijaHotela.getContentPane().add(lblBroj);
		
		textField = new JTextField();
		textField.setBounds(191, 68, 138, 20);
		frmModifikacijaHotela.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(191, 99, 138, 20);
		frmModifikacijaHotela.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(191, 130, 138, 20);
		frmModifikacijaHotela.getContentPane().add(textField_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinner.setBounds(191, 161, 42, 20);
		frmModifikacijaHotela.getContentPane().add(spinner);
		
		JButton btnNewButton = new JButton("Modifikuj");
		btnNewButton.setBounds(122, 208, 150, 30);
		frmModifikacijaHotela.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Unesite nove podatke o hotelu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(88, 24, 213, 14);
		frmModifikacijaHotela.getContentPane().add(lblNewLabel);
	}
}

