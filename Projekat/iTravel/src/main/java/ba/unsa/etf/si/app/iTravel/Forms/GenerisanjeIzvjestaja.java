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

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;
import ba.unsa.etf.si.app.iTravel.BLL.UserContext;
import ba.unsa.etf.si.app.iTravel.DAL.Repository;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.beans.PropertyChangeEvent;

public class GenerisanjeIzvjestaja {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JFrame frmPrijava;
	
	private static UnitOfWork uow = new UnitOfWork();

	/**
	 * Launch the application.
	 */
	public static void PrikaziFormu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerisanjeIzvjestaja window = new GenerisanjeIzvjestaja();
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
	public GenerisanjeIzvjestaja() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		boolean[] postavke = uow.getPostavkeService().dajSvePostavke();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 621, 633);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		
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
		table.setEnabled(false);
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Destinacija", "Broj posjeta"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(284);
		table.getColumnModel().getColumn(1).setPreferredWidth(290);
		
		final JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(270, 52, 131, 20);
		frame.getContentPane().add(dateChooser_1);
		
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) 
			{
				dateChooser_1.setMinSelectableDate(dateChooser.getDate());
			}
		});
		dateChooser.setBounds(78, 55, 131, 20);
		frame.getContentPane().add(dateChooser);
		
	
		
		JButton btnNewButton = new JButton("Generi\u0161i");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(dateChooser.getDate()==null || dateChooser_1.getDate()==null) 
				{
					JOptionPane.showMessageDialog( null, "Datumi se moraju odabrati!");
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				List<Destinacija> id= uow.getIzvjestajService().VratiListuDestinacija();
				for(Destinacija i: id)
				{
					Integer rezervacija= uow.getIzvjestajService().PrebrojRezervacijeZaDestinaciju(i, dateChooser.getDate(), dateChooser_1.getDate());
					if(rezervacija==0) continue;
					Object[] row={i.getNaziv(), rezervacija}; 
					model.addRow(row);
				}
		}
		});
		btnNewButton.setBounds(436, 50, 150, 30);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("Od:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(32, 321, 36, 14);
		frame.getContentPane().add(label);

		final JDateChooser dateChooser_3 = new JDateChooser();
		dateChooser_3.setBounds(270, 315, 131, 20);
		frame.getContentPane().add(dateChooser_3);
		
		final JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) 
			{
				dateChooser_3.setMinSelectableDate(dateChooser_2.getDate());
			}
		});
		dateChooser_2.setBounds(78, 315, 131, 20);
		frame.getContentPane().add(dateChooser_2);

		JLabel lblIzvjetajOIskoritenosti = new JLabel("Izvje\u0161taj o iskori\u0161tenosti soba");
		lblIzvjetajOIskoritenosti.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIzvjetajOIskoritenosti.setBounds(32, 272, 206, 26);
		frame.getContentPane().add(lblIzvjetajOIskoritenosti);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(32, 365, 554, 139);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setEnabled(false);
		table_1.setColumnSelectionAllowed(true);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
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
		
		JButton button = new JButton("Generi\u0161i");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				 
				if(dateChooser_2.getDate()==null || dateChooser_3.getDate()==null) 
				{
					JOptionPane.showMessageDialog( null, "Datumi se moraju odabrati!");
				}
				
				
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				model.setRowCount(0);
				List<Destinacija> destinacija= uow.getIzvjestajService().VratiListuDestinacija();
				
				for(Destinacija d: destinacija)
				{
					String dest= d.getNaziv();
					List<Hotel> hotel= uow.getIzvjestajService().VratiListuHotela(d);
					for(Hotel i: hotel)
					{
						Integer rezervacija= uow.getIzvjestajService().brojIznajmljenihSoba(i, dateChooser_2.getDate(), dateChooser_3.getDate());
						String hot= i.getNaziv();
						Integer broj_soba= uow.getIzvjestajService().ukupanBrojSobaNaRaspolaganju(i);
						Integer broj_iznajmljenih_soba= uow.getIzvjestajService().brojIznajmljenihSoba(i, dateChooser_2.getDate(), dateChooser_3.getDate());
						double postotak;
						if(broj_soba==0) continue;
						else postotak= (100*broj_iznajmljenih_soba)/broj_soba ;
						String post= postotak + "%";
						Object[] row={d.getNaziv(), i.getNaziv(), broj_soba,broj_iznajmljenih_soba, post}; 
						model.addRow(row);
					}
					
				}
				
				
			}
		});
		button.setBounds(436, 313, 150, 30);
		frame.getContentPane().add(button);
		
		
		JLabel label_1 = new JLabel("Do:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(232, 321, 28, 14);
		frame.getContentPane().add(label_1);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnPovratak = new JMenu("Meni");
		menuBar.add(mnPovratak);
		
		JMenuItem mntmPoetna = new JMenuItem("Početna");
		mntmPoetna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Pocetna(frame);
			}
		});
		mnPovratak.add(mntmPoetna);
		
		JMenuItem mntmHoteli = new JMenuItem("Hoteli");
		mntmHoteli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Hoteli(frame);
			}
		});
		mnPovratak.add(mntmHoteli);
		mntmHoteli.setEnabled(postavke[1]);
		
		JMenuItem mntmRezervacije = new JMenuItem("Rezervacije");
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.Rezervacije(frame);
			}
		});
		mnPovratak.add(mntmRezervacije);
		mntmRezervacije.setEnabled(postavke[2]);
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKlijenti = new JMenuItem("Klijenti");
			mntmKlijenti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Meni.Klijenti(frame);
				}
			});
				mnPovratak.add(mntmKlijenti);
				mntmKlijenti.setEnabled(postavke[3]);
			}
		
		if(UserContext.getInstance().getRoleID() == 1 || UserContext.getInstance().getRoleID() == 3){
			JMenuItem mntmKorisnici = new JMenuItem("Korisnici");
			mntmKorisnici.addActionListener(new ActionListener() {
						
				public void actionPerformed(ActionEvent e) {
					Meni.Korisnici(frame);			
				}
			});
			mnPovratak.add(mntmKorisnici);
			mntmKorisnici.setEnabled(postavke[4]);
			}
		
		JMenu mnRaun = new JMenu("Račun");
		menuBar.add(mnRaun);
		
		JMenuItem mntmPromijeniifru = new JMenuItem("Promijeni šifru");
		mntmPromijeniifru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PromjenaSifre.PrikaziFormu();
			}
		});
		mnRaun.add(mntmPromijeniifru);
		
		JMenuItem mntmOdjaviSe = new JMenuItem("Odjavi se");
		
		mntmOdjaviSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.OdjaviSe();
			}
		});
		
		mnRaun.add(mntmOdjaviSe);
		
		JMenu mnPomo = new JMenu("Pomoć");
		menuBar.add(mnPomo);
		
		JMenuItem mntmOFormi = new JMenuItem("O formi...");
		mntmOFormi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Meni.HelpForma("/HelpImages/IzvjestajiSlika.jpg");
			}
		});
		mnPomo.add(mntmOFormi);
		
		
		
	}
	
}
