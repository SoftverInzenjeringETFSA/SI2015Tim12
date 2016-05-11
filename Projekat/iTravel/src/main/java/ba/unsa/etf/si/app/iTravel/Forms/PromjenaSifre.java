package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import ba.unsa.etf.si.app.iTravel.BLL.PromjenaSifreService;
import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;

public class PromjenaSifre {

	private JFrame frmPromjenaSifre;
	private JPasswordField passwordField_trenutna;
	private JPasswordField passwordField_nova;
	private JPasswordField passwordField_potvrda;

	
	class AkcijaIzlaz implements ActionListener {
		public void actionPerformed(ActionEvent event){
			
			
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PromjenaSifre window = new PromjenaSifre();
					window.frmPromjenaSifre.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
				}
			}
		});
	}
	
	public void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PromjenaSifre window = new PromjenaSifre();
					window.frmPromjenaSifre.setVisible(true);
				} catch (Exception e) {
					UnitOfWork.logger.error(e);
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
		frmPromjenaSifre = new JFrame();
		frmPromjenaSifre.setTitle("Promjena \u0161ifre");
		frmPromjenaSifre.setBounds(100, 100, 289, 279);
		frmPromjenaSifre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPromjenaSifre.getContentPane().setLayout(null);
		frmPromjenaSifre.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Unesite podatke");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(74, 22, 115, 31);
		frmPromjenaSifre.getContentPane().add(lblNewLabel);
		
		passwordField_trenutna = new JPasswordField();
		passwordField_trenutna.setBounds(135, 79, 100, 23);
		frmPromjenaSifre.getContentPane().add(passwordField_trenutna);
		
		JButton btnSpasi = new JButton("Spasi");
		btnSpasi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Arrays.equals(passwordField_potvrda.getPassword(), passwordField_nova.getPassword())){
					PromjenaSifreService p = new PromjenaSifreService();
					String passStari = new String(passwordField_trenutna.getPassword());
					String passNovi = new String(passwordField_nova.getPassword());
				
					if(p.PromijeniPristupnePodatke(passStari, passNovi)){
						JOptionPane.showMessageDialog(null, "Uspješno promijenjena šifra!");
						frmPromjenaSifre.dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "Niste unijeli ispravnu trenutnu šifru", "Greška", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Šifra za potvrdu nije identična novoj šifri.", "Greška", JOptionPane.OK_OPTION);
				}
				
			}
		});
		btnSpasi.setBounds(25, 189, 100, 30);
		frmPromjenaSifre.getContentPane().add(btnSpasi);
		
		JButton btnNazad = new JButton("Nazad");
		btnNazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPromjenaSifre.dispose();
			}
		});
		btnNazad.setBounds(147, 189, 100, 30);
		frmPromjenaSifre.getContentPane().add(btnNazad);
		
		JLabel lblNewLabel_1 = new JLabel("Trenutna šifra:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(25, 79, 100, 14);
		frmPromjenaSifre.getContentPane().add(lblNewLabel_1);
		
		JLabel lblTrenutnaifra = new JLabel("Nova šifra:");
		lblTrenutnaifra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTrenutnaifra.setBounds(25, 113, 100, 14);
		frmPromjenaSifre.getContentPane().add(lblTrenutnaifra);
		
		passwordField_nova = new JPasswordField();
		passwordField_nova.setBounds(135, 113, 100, 23);
		frmPromjenaSifre.getContentPane().add(passwordField_nova);
		
		JLabel lblNovaifra = new JLabel("Potvrdi šifru:");
		lblNovaifra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNovaifra.setBounds(25, 147, 100, 14);
		frmPromjenaSifre.getContentPane().add(lblNovaifra);
		
		passwordField_potvrda = new JPasswordField();
		passwordField_potvrda.setBounds(135, 147, 100, 23);
		frmPromjenaSifre.getContentPane().add(passwordField_potvrda);
	}
}
