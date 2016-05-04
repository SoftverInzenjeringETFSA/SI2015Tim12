package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class PromjenaSifre {

	private JFrame frmPromjenaife;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PromjenaSifre window = new PromjenaSifre();
					window.frmPromjenaife.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PromjenaSifre() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPromjenaife = new JFrame();
		frmPromjenaife.setTitle("Promjena \u0161ifre");
		frmPromjenaife.setBounds(100, 100, 289, 279);
		frmPromjenaife.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPromjenaife.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Unesite podatke");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(74, 22, 115, 31);
		frmPromjenaife.getContentPane().add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 79, 100, 23);
		frmPromjenaife.getContentPane().add(passwordField);
		
		JButton btnSpasi = new JButton("Spasi");
		btnSpasi.setBounds(25, 189, 100, 30);
		frmPromjenaife.getContentPane().add(btnSpasi);
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.setBounds(147, 189, 100, 30);
		frmPromjenaife.getContentPane().add(btnNazad);
		
		JLabel lblNewLabel_1 = new JLabel("Trenutna šifra:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(25, 79, 100, 14);
		frmPromjenaife.getContentPane().add(lblNewLabel_1);
		
		JLabel lblTrenutnaifra = new JLabel("Nova šifra:");
		lblTrenutnaifra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTrenutnaifra.setBounds(25, 113, 100, 14);
		frmPromjenaife.getContentPane().add(lblTrenutnaifra);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(135, 113, 100, 23);
		frmPromjenaife.getContentPane().add(passwordField_1);
		
		JLabel lblNovaifra = new JLabel("Potvrdi šifru:");
		lblNovaifra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNovaifra.setBounds(25, 147, 100, 14);
		frmPromjenaife.getContentPane().add(lblNovaifra);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(135, 147, 100, 23);
		frmPromjenaife.getContentPane().add(passwordField_2);
	}
}
