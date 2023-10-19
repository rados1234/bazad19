package bazad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;

public class Combo_stranowisko extends JComboBox {
  public Combo_stranowisko(boolean wszyscy) {
	  pokazStanowiska(wszyscy);
  }
  
  void pokazStanowiska(boolean wszyscy) {
		String sql = "SELECT * FROM testy.sl_stan;";
		try {
			Connection con = baza_danych.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			el_stanowiskR el;
			if(wszyscy)this.addItem(new el_stanowiskR(-1, "Wszyscy"));
			while (rs.next()) {
				el = new el_stanowiskR(rs.getInt(1), rs.getString(2));
				this.addItem(el);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
