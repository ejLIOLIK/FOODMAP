package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DB;

public class DAO {
	
	public Connection con=null;
	public Statement st=null;
	public ResultSet rs;
	
	public void openDB() {
		try {
			Class.forName(DB.serverforName);
			con = DriverManager.getConnection(DB.serverConnection, DB.DB_ID, DB.DB_PW);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeDB() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
