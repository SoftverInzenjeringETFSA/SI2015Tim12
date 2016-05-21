package ba.unsa.etf.si.app.iTravel.Forms;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.itextpdf.awt.geom.Dimension;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork;

public class HelpForma {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	static String putanjaSlike = "/HelpImages/UnosDestinacijeSlika.jpg";
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpForma window = new HelpForma();
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
	public HelpForma(){
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(300, 10, (int)screenSize.getWidth()/2, ((int)screenSize.getHeight())-100);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Rectangle r = frame.getBounds();
		ImageIcon li = new ImageIcon(getClass().getResource(putanjaSlike));
		JLabel label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(r);
		label.setIcon(li);
		frame.getContentPane().add(label);		

	}

	public static void PrikaziFormu(final String putanja) {
		EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						putanjaSlike = putanja;
						HelpForma window = new HelpForma();
						window.frame.setVisible(true);
					} catch (Exception e) {
						UnitOfWork.logger.error(e);
					}
				}
			});
		}
		
}
