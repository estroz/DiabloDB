import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class Poster {

	public String userName;
	public int reputation;
	public int numberOfPages;
	public Integer adminID;

	private Connection connection;

	public Poster(String userName, int reputation, int numberOfPages, Integer adminID) {
		this.userName = userName;
		this.reputation = reputation;
		this.numberOfPages = numberOfPages;
		if (adminID == null) {
			this.adminID = null;
		} else {
			this.adminID = adminID;
		}
	}

	public String toString() {
		return "Poster Object, name: " + userName + " rep: " + Integer.toString(reputation);
	}


	/*
	 * Returns true if a poster has an adminID
	 */
	public boolean isPosterAdmin(String posterName) throws SQLException {
		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT AdminID FROM Poster WHERE PosterName = '"+posterName+"'");

		String adminID = rs.getString("AdminID");
		if (!adminID.wasNull()) return true;
		return false;
	}

	/*
	 * Lock a thread
	 * TODO: figure out how to lock thread instance so no more comments can be added
	 */
	public void lockThread(int threadID, String posterName) throws SQLException {
		if (!isPosterAdmin(posterName)) {
			throw new SQLException("You are not an administrator and thus cannot lock a thread.");
		}
		Statement stmnt = this.connection.createStatement();
		int lockedThread = stmnt.executeUpdate("UPDATE UserThread SET isLockedFlag = 1 WHERE ThreadID = '"+threadID+"'");
		assert lockedThread == 0;
	}

}
