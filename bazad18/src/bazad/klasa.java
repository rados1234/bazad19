package bazad;

import java.sql.*;

class Klasa {
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testy", "root", "kazik");
//here sonoo is database name, root is username and password  
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from pracownicy");
//rs.beforeFirst();
			while (rs.next())
				System.out
						.println(rs.getInt(1) + "  " + rs.getString(2) + "  "
				+ rs.getString(3) + "  " + rs.getInt(4)+"  " + rs.getInt(5));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}