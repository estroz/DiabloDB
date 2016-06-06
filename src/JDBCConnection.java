import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

public class JDBCConnection {
	private Connection connection;
	
	public static void main(String[] args) throws SQLException {
		JDBCConnection jd = new JDBCConnection();
		// Test each method, this is the getUsers()
		ArrayList<Poster> users = jd.getUsers();
		System.out.println(users.toString());
		
		// Test getAllPages()
		ArrayList<String> pages = jd.getAllPages();
		System.out.println(pages.toString());
		
		// Test getPageThreads(Topic)
		ArrayList<Thread> pageThreads = jd.getPageThreads(pages.get(0));
		System.out.println(pageThreads.toString());
		
		// Test getSpecificUser(user)
		ArrayList<ArrayList<Object>> aUser = jd.getSpecificUser(users.get(0).userName);
		System.out.println(aUser);

	}
	public JDBCConnection() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Driver registered");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug",
				"USERNAME", "PASSWORD");
			this.connection = con;
		} catch (SQLException e) {
			System.out.println("Couldn't conenct to the database, are you tunneled?");
		}
		System.out.println("Conencted to db");

	}
	
	/*
	 * Returns all users on the site in Poster objects.
	 */
	public ArrayList<Poster> getUsers() throws SQLException {
		ArrayList<Poster> arr = new ArrayList<Poster>();

		Statement stmt = this.connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Poster");

		while (rs.next()) {
			String curName = rs.getString("PosterName");
			int rep = rs.getInt(2);
			int numPages = rs.getInt(3);
			arr.add(new Poster(curName, rep, numPages, null));

		}
		return arr;
	}
	
	/*
	 * This method gets a specific user's:
	 * List of all Pages belong to them (Array with pages as each element)
	 * List of all threads belong to them (Array with 0, 1, 2 as title, threadID, voteNum respectively, then continuted)
	 * List of all comments belong to them
	 */
	public ArrayList<ArrayList<Object>> getSpecificUser(String userName) throws SQLException {
		ArrayList<ArrayList<Object>> arr = new ArrayList<ArrayList<Object>>();
		arr.add(new ArrayList<Object>()); // One for the pages
		arr.add(new ArrayList<Object>()); // One for the Threads
		arr.add(new ArrayList<Object>()); // One for the comments
		Statement stmt = this.connection.createStatement();
		ResultSet topicsRS = stmt.executeQuery("SELECT TopicName FROM PAGE WHERE PAGE.POSTERNAME = '" +
											userName + "'");

		// Get all the pages
		while (topicsRS.next()) {
			String topic = topicsRS.getString(1);
			Page p = new Page(topic, userName);
			arr.get(0).add(p);
		}
		System.out.println("got the pages");
		
		// Now get all threads
		ResultSet threadsRS = stmt.executeQuery("SELECT * FROM Thread " + 
												"WHERE Thread.PosterName = '" + userName + "'");
		
		while (threadsRS.next()) {
			int id = threadsRS.getInt(1);
			String title = threadsRS.getString("Title");
			String text = threadsRS.getString(3);
			Time time = threadsRS.getTime(4);
			int voteNum = threadsRS.getInt(5);
			int lockedNum = threadsRS.getInt(6);
			boolean isLocked;
			if (lockedNum == 0) {
				isLocked = false;
			} else {
				isLocked = true;
			}
			String topicName = threadsRS.getString(7);

			Thread t = new Thread(id, title, text, time, voteNum, isLocked, topicName, userName);
			arr.get(1).add(t);
		}
		System.out.println("got the threads");
		
		// Now get all comments belonging
		ResultSet commentsRS = stmt.executeQuery("SELECT * FROM UserComment " + 
												"WHERE Usercomment.PosterName = '" + userName + "'");
		
		while (commentsRS.next()) {
			int id = commentsRS.getInt(1);
			String text = commentsRS.getString("Text");
			int voteNum = commentsRS.getInt(3);
			Time time = commentsRS.getTime(4);
			String poster = commentsRS.getString(5);
			String ThreadID = commentsRS.getString(6);

			Comment c = new Comment(id, text, voteNum, time, poster, ThreadID);
			arr.get(2).add(c);

		}
		
		return arr;
	}

	/*
	 * Returns an array of Threads for a given Page
	 */
	public ArrayList<Thread> getPageThreads(String pageTopic) throws SQLException {
		ArrayList<Thread> arr = new ArrayList<Thread>();
		
		Statement stmnt = this.connection.createStatement();
		ResultSet rs = stmnt.executeQuery("SELECT * FROM Thread " +
											"WHERE Thread.TopicName = '" + pageTopic + "'");
		while (rs.next()) {
			int id = rs.getInt(1);
			String title = rs.getString("Title");
			String text = rs.getString(3);
			Time time = rs.getTime(4);
			int voteNum = rs.getInt(5);
			int lockedNum = rs.getInt(6);
			boolean isLocked;
			if (lockedNum == 0) {
				isLocked = false;
			} else {
				isLocked = true;
			}
			String topicName = rs.getString(7);
			String posterName = rs.getString(8);

			Thread t = new Thread(id, title, text, time, voteNum, isLocked, topicName, posterName);
			arr.add(t);
			
		}
		
		return arr;

	}
	
	/*
	 * Returns a list of all pages
	 */
	public ArrayList<String> getAllPages() throws SQLException {
		ArrayList<String> arr = new ArrayList<String>();
		
		Statement stmnt = this.connection.createStatement();
		ResultSet rs = stmnt.executeQuery("SELECT * FROM Page");
		
		while (rs.next()) {
			String topicName = rs.getString("TopicName");
			arr.add(topicName);
		}
		return arr;
	}
	
	/*
	 * Returns a list of comment maps
	 * TODO
	 */
	public ArrayList<Comment> getThreadComments(String ThreadID) throws SQLException {
		ArrayList<Comment> arr = new ArrayList<Comment>();

		Statement stmnt = this.connection.createStatement();
		ResultSet rs = stmnt.executeQuery("SELECT * FROM UserComment " +
											"WHERE UserComment.ThreadID = '" + ThreadID + "'");
		while (rs.next()) {
			int id = rs.getInt(1);
			String text = rs.getString("Text");
			int voteNum = rs.getInt(3);
			Time time = rs.getTime(4);
			String poster = rs.getString(5);

			Comment c = new Comment(id, text, voteNum, time, poster, ThreadID);
			arr.add(c);
		}
		
		return arr;
	}
}
