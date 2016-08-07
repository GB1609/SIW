package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	private String dburi;
	private String username;
	private String password;

	public DataSource(String dburi, String username, String password) {
		this.dburi = dburi;
		this.username = username;
		this.password = password;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(this.dburi, this.username, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
