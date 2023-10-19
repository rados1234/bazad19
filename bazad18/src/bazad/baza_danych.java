package bazad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class baza_danych {
 static Connection con;

	private baza_danych() {
	}

	public static Connection getConnection() {
		if (con == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testy", "root", "kazik");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return con;
	}
}
