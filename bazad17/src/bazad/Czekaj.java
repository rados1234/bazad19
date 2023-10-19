package bazad;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Czekaj extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	private void runThread(ZmienRozmiar th) {
		th.setListener(new Ev_Thread() {
			public void onLoad(boolean b) {
               if(!b)dispose();
			}
		});
		th.start();
	}

	public static void start(JDialog parent, ZmienRozmiar cz) {
		try {

			Czekaj dialog = new Czekaj(parent);
			dialog.runThread(cz);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Czekaj(JDialog parent) {
		super(parent, true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			JLabel lblNewLabel = new JLabel("Proszę czekać...");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lblNewLabel.setForeground(Color.RED);
			contentPanel.add(lblNewLabel);
		}
		
		
		Icon icon = new ImageIcon("czekaj.gif");
		JLabel lblNewLabel_img = new JLabel(icon);
		getContentPane().add(lblNewLabel_img, BorderLayout.CENTER);
		 
	        
	}
	
}
