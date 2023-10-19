package bazad;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.io.*;
public class Dodaj extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_imie;
	private JTextField textField_nazwisko;
	private JSpinner textField_wiek;
	boolean zmien;
	JCheckBox chckbxNewCheckBox_sep;
	int id;
	ImageCanvas panel_zdjecie;
	Combo_stranowisko comboBox_stanowisko;
	File selectedFile;

	/**
	 * Launch the application.
	 */
	public static void pokaz(JDialog parent) {
		try {
			Dodaj dialog = new Dodaj(parent, false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void zmien(JDialog parent, El_pracownik prac) {
		try {
			Dodaj dialog = new Dodaj(parent, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.fillData(prac);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dodaj(JDialog parent, boolean zmien) {
		super(parent, true);
		this.zmien = zmien;
		setBounds(100, 100, 650, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(5, 2, 0, 40));
		{
			JLabel lblNewLabel = new JLabel("Imię:");
			contentPanel.add(lblNewLabel);
		}
		{
			textField_imie = new JTextField();
			contentPanel.add(textField_imie);
			textField_imie.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nazwisko:");
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField_nazwisko = new JTextField();
			contentPanel.add(textField_nazwisko);
			textField_nazwisko.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Wiek:");
			contentPanel.add(lblNewLabel_2);
		}
		{
			textField_wiek = new JSpinner();
			contentPanel.add(textField_wiek);
			textField_wiek.setValue(20);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Stanowisko:");
			contentPanel.add(lblNewLabel_3);
		}
		{
			comboBox_stanowisko = new Combo_stranowisko(false);
			contentPanel.add(comboBox_stanowisko);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Sep:");
			contentPanel.add(lblNewLabel_4);
		}
		{
			chckbxNewCheckBox_sep = new JCheckBox("");
			contentPanel.add(chckbxNewCheckBox_sep);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton_dodaj = new JButton(zmien ? "Zmień" : "Dodaj");
				okButton_dodaj.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dodaj();
						dispose();
					}
				});
				okButton_dodaj.setActionCommand("OK");
				buttonPane.add(okButton_dodaj);
				getRootPane().setDefaultButton(okButton_dodaj);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton btnNewButton_zdjecie = new JButton("Zdjecie");
				btnNewButton_zdjecie.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pobierzZdjZPliku();
					}
				});
				panel.add(btnNewButton_zdjecie);
			}
			{
				panel_zdjecie = new ImageCanvas();
				panel.add(panel_zdjecie);
			}
		}
		// pokazStanowiska();
	}

	
	void pobierzZdjZPliku() {
		JFileChooser jfc = new JFileChooser("c://folder");
        File tmpZdj = new File("tmp_zdj.jpg"); 
		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			//System.out.println(selectedFile.getAbsolutePath());
			try {
	    	    ResizeWatek th = new ResizeWatek(selectedFile,tmpZdj);
	    	    
	    	    Czekaj.pokaz(this, th);
	    	    selectedFile = tmpZdj;
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
			panel_zdjecie.setPicture(selectedFile);
		}
	}

	void fillData(El_pracownik prac) {
		id = prac.id();

		textField_imie.setText(prac.name());
		textField_nazwisko.setText(prac.nazwisko());
		textField_wiek.setValue(prac.wiek());
		comboBox_stanowisko.setSelectedItem(prac.stanowisko());
		this.chckbxNewCheckBox_sep.setSelected((prac.sep() == 1) ? Boolean.TRUE : Boolean.FALSE);
		readImage(id);
	}
	/*
	 * void pokazStanowiska() { String sql = "SELECT * FROM testy.sl_stan;"; try {
	 * Connection con = baza_danych.getConnection(); Statement stmt =
	 * con.createStatement(); ResultSet rs = stmt.executeQuery(sql); while
	 * (rs.next()) { el_stanowiskR el = new el_stanowiskR(rs.getInt(1),
	 * rs.getString(2)); comboBox_stanowisko.addItem(el); } } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */
	int getId()throws Exception{
		if(zmien) {
			return id;
		}else {
			 String query = "select max(id) maxid from pracownicy";

			 Connection conn = baza_danych.getConnection();
		      Statement st = conn.createStatement();
		      
		      ResultSet rs = st.executeQuery(query);
		      rs.next();
		      return rs.getInt("maxid");
		}
	}
    void zapiszZdjToDB() throws Exception {
    	if(selectedFile==null) return;
    	int my_id = getId();
    	Connection con = baza_danych.getConnection();
    	String sql = "update pracownicy set zdjecie = ? where id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        InputStream in = new FileInputStream(selectedFile);
        pstmt.setBlob(1, in);
        pstmt.setInt(2, my_id);
        //Inserting Blob type
        
        //Executing the statement
        pstmt.execute();
    }
    public void readImage(int id) {
        BufferedImage bufImg = null;
        String sql = "select zdjecie from  pracownicy where id = ?";
        try {
        	Connection conn = baza_danych.getConnection();
           PreparedStatement pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, id);
          
           ResultSet rset = pstmt.executeQuery();
           rset.next();
           // Only one result expected
          // rset.next();
         //  System.out.println("rset: " + );
           // Read image via InputStream
           InputStream in = rset.getBinaryStream("zdjecie"); 
           // Decode the inputstream as BufferedImage
           if(in == null)return;
           bufImg = ImageIO.read(in); 
           panel_zdjecie.setPictureBI(bufImg);
        } catch(Exception ex) {
           ex.printStackTrace();
        }
       
     }
    
	void dodaj() {
		if (textField_imie.getText().equals("") || textField_nazwisko.getText().equals(""))
			return;
		String sql;
		if (zmien) {
			sql = " update testy.pracownicy set " + " imie = '" + textField_imie.getText() + "'," + " nazwisko = '"
					+ textField_nazwisko.getText() + "'," + " stanowisko = "
					+ ((el_stanowiskR) comboBox_stanowisko.getSelectedItem()).id() + "," + " wiek = "
					+ textField_wiek.getValue() + ", " + " sep = " + chckbxNewCheckBox_sep.isSelected() + " where id = "
					+ id;
		} else {
			sql = " insert into testy.pracownicy " + "(imie, nazwisko, wiek, skasowany, stanowisko, sep)" + " values('"
					+ textField_imie.getText() + "','" + textField_nazwisko.getText() + "'," + textField_wiek.getValue()
					+ ",null, " + ((el_stanowiskR) comboBox_stanowisko.getSelectedItem()).id() + ","
					+ ((chckbxNewCheckBox_sep.isSelected()) ? 1 : 0) + ")";
		}
		try {
			Connection con = baza_danych.getConnection();
			Statement stmt = con.createStatement();
			System.out.println(sql);
			stmt.executeUpdate(sql);
			zapiszZdjToDB();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class ImageCanvas extends Canvas {
	BufferedImage image;

	public ImageCanvas() {
		this.setSize(new Dimension(200,200));
	}
    public void setPicture(File zdjPath) {
    	try {
    	    image = ImageIO.read(zdjPath);
    	    repaint();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    public void setPictureBI(BufferedImage zdj) {
    	image = zdj;
    	 repaint();
    	
    }
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);

	}
}