package bazad;

import java.awt.BorderLayout;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//test111tescik
public class Okno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private TextWiek textField_wiek;
	Combo_stranowisko comboBoxStanowisko;
	JComboBox comboBox_Sep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Okno dialog = new Okno();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Okno() {
		// baza_danych.getConnection();
		setBounds(100, 100, 650, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			table = new JTable();
			table.getTableHeader().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					getLp();
				}
			});
			table.setAutoCreateRowSorter(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setViewportBorder(null);
			contentPanel.add(scrollPane);
		}
		{

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton_html = new JButton("Html");
				okButton_html.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						toHtml();
					}
				});
				okButton_html.setActionCommand("OK");
				buttonPane.add(okButton_html);
				getRootPane().setDefaultButton(okButton_html);
			}
			{
				JButton btnExcel = new JButton("Excel");
				btnExcel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						toExcel();
					}
				});
				btnExcel.setActionCommand("Cancel");
				buttonPane.add(btnExcel);
			}
			{
				JButton btnNewButton_usun = new JButton("Usuń");
				btnNewButton_usun.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
						if (a == JOptionPane.OK_OPTION)
							usun();
					}
				});
				buttonPane.add(btnNewButton_usun);
			}
			{
				JButton btnNewButton_add = new JButton("Dodaj");
				btnNewButton_add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dodaj();
					}
				});
				buttonPane.add(btnNewButton_add);
			}
			{
				JButton btnNewButton_zmien = new JButton("Zmień");
				btnNewButton_zmien.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						zmien();
					}
				});
				buttonPane.add(btnNewButton_zmien);
			}
		}
		{
			JPanel panel = new JPanel();

			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblNewLabel = new JLabel("Wiek:");
					panel_1.add(lblNewLabel);
				}
				{
					textField_wiek = new TextWiek();
					textField_wiek.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							pobierz();
						}
					});
					panel_1.add(textField_wiek);
					textField_wiek.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblNewLabel_1 = new JLabel("Stanowisko:");
					panel_1.add(lblNewLabel_1);
				}
				{
					comboBoxStanowisko = new Combo_stranowisko(true);
					panel_1.add(comboBoxStanowisko);
					{
						JPanel panel_2 = new JPanel();
						panel.add(panel_2);
						{
							JLabel lblNewLabel_2 = new JLabel("Sep:");
							panel_2.add(lblNewLabel_2);
						}
						{
							comboBox_Sep = new JComboBox();
							comboBox_Sep.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {
									pobierz();
								}
							});
							comboBox_Sep.addItem("Wszyscy");
							comboBox_Sep.addItem("Sep");
							comboBox_Sep.addItem("Bez Sep");
							panel_2.add(comboBox_Sep);
						}
					}
					comboBoxStanowisko.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent event) {
							if (event.getStateChange() == ItemEvent.SELECTED) {
								Object item = event.getItem();
								// System.out.println("test");
								pobierz();
								// do something with object
							}
						}
					});
				}

			}
		}
		pobierz();
	}

	void toHtml() {
		Object[][] dane = getData();
		String file = "c:\\folder\\" + System.currentTimeMillis() + "excel.html";
		String html = """
				      <html>
				<head>
				<style>
				table {
				  font-family: arial, sans-serif;
				  border-collapse: collapse;
				  width: 100%;
				}

				td, th {
				  border: 1px solid #dddddd;
				  text-align: left;
				  padding: 8px;
				}

				tr:nth-child(even) {
				  background-color: #dddddd;
				}
				</style>
				</head>
				<body>

				<h2>HTML Table</h2>

				<table>""";
		html += "<tr>";
		html += "<th>" + table.getColumnName(0) + "</th>";
		html += "<th>" + table.getColumnName(1) + "</th>";
		html += "<th>" + table.getColumnName(2) + "</th>";
		html += "<th>" + table.getColumnName(3) + "</th>";
		html += "<th>" + table.getColumnName(4) + "</th>";
		html += "<th>" + table.getColumnName(5) + "</th>";
		html += "<th>" + table.getColumnName(6) + "</th>";
		html += "</tr>";
		for (int i = 0; i < dane.length; i++) {
			html += "<tr>";
			html += "<td>" + dane[i][0] + "</td>";
			html += "<td>" + dane[i][1] + "</td>";
			html += "<td>" + dane[i][2] + "</td>";
			html += "<td>" + dane[i][3] + "</td>";
			html += "<td>" + dane[i][4] + "</td>";
			html += "<td>" + dane[i][5] + "</td>";
			html += "<td>" + dane[i][6] + "</td>";
			html += "</tr>";
		}

		// for (int i = 0; i < array.length; i++) {

		// }

		html += "</table></body></html>";
		try {
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(html);
			myWriter.close();
			Runtime rt = Runtime.getRuntime();
			rt.exec("cmd.exe /C start " + file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	void getLp() {
		int rows = table.getRowCount();
		for (int i = 0; i < rows; i++) {
			table.setValueAt(i + 1, i, 0);
		}
	}

	Object[][] getData() {
		int rows = table.getRowCount();
		Object[][] dane = new Object[rows][7];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < 7; j++) {
				dane[i][j] = table.getValueAt(i, j);
			}
		}
		return dane;
	}

	void toExcel() {

		String file = "c:\\folder\\" + System.currentTimeMillis() + "excel.xls";
		Object[][] dane = getData();

		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Excel Sheet");
			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell((short) 0).setCellValue(table.getColumnName(0));
			rowhead.createCell((short) 1).setCellValue(table.getColumnName(1));
			rowhead.createCell((short) 2).setCellValue(table.getColumnName(2));
			rowhead.createCell((short) 3).setCellValue(table.getColumnName(3));
			rowhead.createCell((short) 4).setCellValue(table.getColumnName(4));
			rowhead.createCell((short) 5).setCellValue(table.getColumnName(5));
			rowhead.createCell((short) 6).setCellValue(table.getColumnName(6));
			for (int i = 0; i < dane.length; i++) {
				HSSFRow row = sheet.createRow((short) (i + 1));
				row.createCell((short) 0).setCellValue("" + dane[i][0]);
				row.createCell((short) 1).setCellValue("" + dane[i][1]);
				row.createCell((short) 2).setCellValue("" + dane[i][2]);
				row.createCell((short) 3).setCellValue("" + dane[i][3]);
				row.createCell((short) 4).setCellValue("" + dane[i][4]);
				row.createCell((short) 5).setCellValue("" + dane[i][5]);
				row.createCell((short) 6).setCellValue("" + dane[i][6]);
				// row.createCell((short) 5).setCellValue(dane[i][5]==Boolean.TRUE?"TAK":"");
			}
			FileOutputStream fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			Runtime rt = Runtime.getRuntime();
			rt.exec("cmd.exe /C start " + file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void zmien() {
		if (table.getSelectionModel().isSelectionEmpty()) {
			JOptionPane.showMessageDialog(this, "Proszę zaznaczyć wiersz.");
			return;
		}
		int id = (int) table.getValueAt(table.getSelectedRow(), 1);
		String imie = (String) table.getValueAt(table.getSelectedRow(), 2);
		String nazwisko = (String) table.getValueAt(table.getSelectedRow(), 3);
		int wiek = (int) table.getValueAt(table.getSelectedRow(), 4);
		String stanowisko = (String) table.getValueAt(table.getSelectedRow(), 5);
		System.out.println(table.getValueAt(table.getSelectedRow(), 6));
		int sep = (table.getValueAt(table.getSelectedRow(), 6) == Boolean.TRUE) ? 1 : 0;
		String sql = "SELECT * FROM testy.sl_stan where nazwa = '" + stanowisko + "'";
		el_stanowiskR el = null;
		try {
			Connection con = baza_danych.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			el = new el_stanowiskR(rs.getInt(1), rs.getString(2));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(sql);
		}
		// int id = (int)table.getValueAt(table.getSelectedRow(), 0);
		El_pracownik prac = new El_pracownik(id, imie, nazwisko, wiek, el, sep);
		Dodaj.zmien(this, prac);
		pobierz();
	}

	void dodaj() {
		Dodaj.pokaz(this);
		pobierz();
	}

	void usun() {
		int id = (int) table.getValueAt(table.getSelectedRow(), 0);
		// System.out.println(id);
		String sql = "update testy.pracownicy\r\n" + "set skasowany = 1\r\n" + "where id = " + id;
		try {
			Connection con = baza_danych.getConnection();
			Statement stmt = con.createStatement();
			System.out.println(sql);
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			pobierz();
		}

	}

	void pobierz() {
		int stanowisko = ((el_stanowiskR) comboBoxStanowisko.getSelectedItem()).id();
		int sep;
		if (this.comboBox_Sep.getSelectedItem().equals("Sep"))
			sep = 1;
		else
			sep = 0;
		String sql = " select p.id, p.imie, p.nazwisko, p.wiek, s.nazwa, p.sep ";
		sql += "\n from pracownicy p, sl_stan s ";
		sql += "\n where p.stanowisko = s.id ";
		if (stanowisko != -1)
			sql += "\n and p.stanowisko = " + stanowisko;
		if (!this.comboBox_Sep.getSelectedItem().equals("Wszyscy"))
			sql += "\n and p.sep = " + sep;
		sql += textField_wiek.getSqlString();
		sql += "\n and skasowany is null";
		//System.out.println(sql);
		try {
			Connection con = baza_danych.getConnection();
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sql);
			int rows = 0;
			while (rs.next())
				rows++;
			rs.beforeFirst();
			Object[][] dane = new Object[rows][7];
			int i = 0;
			while (rs.next()) {
				dane[i][0] = i + 1;
				dane[i][1] = rs.getInt(1);
				dane[i][2] = rs.getString(2);
				dane[i][3] = rs.getString(3);
				dane[i][4] = rs.getInt(4);
				dane[i][5] = rs.getString(5);
				dane[i][6] = (rs.getInt(6) == 1) ? Boolean.TRUE : Boolean.FALSE;// rs.getInt(6);
				i++;
			}
			table.setModel(new MyTableModel(dane));
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(sql);
		}
	}
}

class MyTableModel extends AbstractTableModel {
	private String[] columnNames = { "Lp", "Id", "Imię", "Nazwisko", "Wiek", "Stanowisko", "Sep" };
	public Object[][] data;

	public MyTableModel(Object[][] data1) {
		data = data1;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for each
	 * cell. If we didn't implement this method, then the last column would contain
	 * text ("true"/"false"), rather than a check box.
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {

		data[row][col] = value;

	}
}
