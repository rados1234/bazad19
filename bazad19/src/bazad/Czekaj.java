package bazad;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class Czekaj extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	void startWatek(ResizeWatek th) {
		th.setListener(new Ev_Resize() {
			public void OnLoad(boolean b) {
                if(!b) zamknij();
			}
		});
		th.start();
	}
    void zamknij() {
    	this.dispose();
    }
	public static void pokaz(JDialog parent, ResizeWatek th) {
		try {
			Czekaj dialog = new Czekaj(parent);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.startWatek(th);
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
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Proszę czekać...");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblNewLabel.setForeground(Color.RED);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblNewLabel_img = new JLabel("");
				lblNewLabel_img.setIcon(new ImageIcon("czekaj.gif"));
				buttonPane.add(lblNewLabel_img);
			}
		}
	}

}
