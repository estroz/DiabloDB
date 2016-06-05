import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class JDBCConnection {
	Connection connection;
	
	public static void main(String[] args) {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		System.out.println("Driver registered");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug",
				"INSERTNAME", "INSERTPASSWORD");
		System.out.println("Conencted to db");
	}
	
	public ArrayList<String> getUsers() throws SQLException {
		ArrayList<String> arr = new ArrayList<String>();

		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT PosterName FROM Poster");

		while (rs.next()) {
			String curName = rs.getString("PosterName");
			arr.add(curName);

		}
		return arr;
	}

	public HashMap<int, String> getPageThreads(String pageTopic) throws SQLException {
		HashMap<int, String> dict = new HashMap<int, String>();
		
		Statement stmnt = this.connection.createStatement();
		ResultSet rs = stmnt.executeQuery("SELECT ThreadID, Title FROM Thread, Page" +
											"WHERE Thread.TopicName = " + pageTopic);
		while (rs.next()) {
			int id = rs.getInt(1);
			String title = rs.getString("Title");
			dict.
		}
		
		return dict;

	}
}
