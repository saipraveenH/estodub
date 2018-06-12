package com.otsi.estodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcSQLServerConnection {
	public static void main(String[] args) {

		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=MAUD;user=root;password=root";

		// Declare the JDBC objects.
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List fields = new ArrayList();

		try {
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);

			// Create and execute an SQL statement that returns some data.
			java.sql.DatabaseMetaData metadata = con.getMetaData();
			ResultSet resultSet = metadata.getColumns(null, null, "complaint", null);
			while (resultSet.next()) {
				String name = resultSet.getString("COLUMN_NAME");
				String type = resultSet.getString("TYPE_NAME");
				int size = resultSet.getInt("COLUMN_SIZE");
				fields.add(name);
				// System.out.println("Column name: [" + name + "]; type: [" + type + "]; size:
				// [" + size + "]");
			}
			System.out.println(fields.size());
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
				}
		}
	}
}
