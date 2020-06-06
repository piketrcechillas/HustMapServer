package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreConnection {
	
	Connection conn = null;
	
	public PostgreConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://hustmap.postgres.database.azure.com:5432/bkmap?loggerLevel=OFF";
		conn = DriverManager.getConnection(url, "hustmap@hustmap", "Admin123");
		System.out.println("Database Connected");
	}
	
	public ResultSet runSql(String sql) throws SQLException {
		Statement stm = conn.createStatement();
		return stm.executeQuery(sql);
	}
 
	public boolean runSql2(String sql) throws SQLException {
		Statement stm = conn.createStatement();
		return stm.execute(sql);
	}
 
	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}
}
