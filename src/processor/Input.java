package processor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.*;

public class Input {
	public static String getName(String name) {
		String geom = "";
		try (Connection connection = DriverManager.getConnection(
        		"jdbc:postgresql://hustmap.postgres.database.azure.com:5432/bkmap", "hustmap@hustmap", "Admin123")) {
			Statement statement = connection.createStatement();
			
	        String query = "SELECT geom" + "\n" +
	            			"FROM polygon" + "\n" +
	            			"WHERE name='"+name+"'";
	        ResultSet resultSet = statement.executeQuery(query);
	        while (resultSet.next()) {
	            geom = resultSet.getString(1);
	        }
	        System.out.println("Checked!");
		}
		catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
		return geom;
	}
}
