package bazad;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Logowanie extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_user;
	private JTextField textField_haslo;
	public static Uprawnienia uprawnienia;
	private JLabel textField_info;
	int proba = 0;

	/**
	 * Launch the application.
	 */
	public static void pokaz() {
		try {
			Logowanie dialog = new Logowanie();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	Uprawnienia getUprawnienia(String userName, String haslo) {
		String sql = "";
		try {
			Connection conn = baza_danych.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs;
			sql = "SELECT * FROM testy.sys_users where name='" + userName + "' and password = '" + haslo + "'";

			rs = stmt.executeQuery(sql);
			if (!rs.next())
				return null;
			boolean bEdit = rs.getInt("edit") == 0 ? false : true;
			boolean bAdd = rs.getInt("add") == 0 ? false : true;
			boolean bDel = rs.getInt("delete") == 0 ? false : true;
			boolean bHtml = rs.getInt("html") == 0 ? false : true;
			boolean bExcell = rs.getInt("excell") == 0 ? false : true;
			boolean bAdmin = rs.getInt("admin") == 0 ? false : true;
			return new Uprawnienia(userName, haslo, bEdit, bAdd, bDel, bHtml, bExcell, bAdmin);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(sql);
			return null;
		}

	}

	/**
	 * Create the dialog.
	 */
	public Logowanie() {

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 2, 0, 0));
		{
			JLabel lblNewLabel_1 = new JLabel("user:");
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField_user = new JTextField();
			contentPanel.add(textField_user);
			textField_user.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("password:");
			contentPanel.add(lblNewLabel);
		}
		{
			textField_haslo = new JTextField();
			contentPanel.add(textField_haslo);
			textField_haslo.setColumns(10);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.LEADING));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loguj();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				textField_info = new JLabel();
				textField_info.setForeground(Color.RED);
				buttonPane.add(textField_info);

			}
		}
		this.setModal(true);
	}

	void loguj() {
		proba++;
		uprawnienia = getUprawnienia(this.textField_user.getText(), this.textField_haslo.getText());
		if (uprawnienia == null)
			this.textField_info.setText("Niewłaściwe dane logowania. Pozostała " + (3 - proba) + " próba.");
		;
		if (proba > 2 || uprawnienia != null)
			dispose();
	}
}
