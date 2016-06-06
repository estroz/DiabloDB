import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		System.out.println("Yay it worked");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug",
				"ora_i6x9a", "a27453142");
		System.out.println("conencted to db");
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM Poster");
		
		
		System.out.println(rs.toString());
		
		while (rs.next()) {
			String name = rs.getString("PosterName");
			int rep = rs.getInt(2);
			int numPages = rs.getInt(3);
			int adminID = rs.getInt(4);
			System.out.println(name);
			System.out.print(rep);
			System.out.print(numPages);
			System.out.print(adminID);
			System.out.println("");
		}

		con.close();
		System.out.println("dc'd from db");

	}

}
