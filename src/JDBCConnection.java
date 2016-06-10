import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

public class JDBCConnection {
	private Connection connection;

//	private Integer getNewID() throws SQLException {
//		Statement stmt = this.connection.createStatement();
//		ResultSet rs = stmt.executeQuery("SELECT * FROM Poster");
//	}

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
				"username", "password");
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


	/*
	 * -----------------------------------------------------------------------------------------
	 * All the following are INSERT funtions, rather than SELECT Queries. USER BEWARE
	 */

	/*
	 * Create a poster with option for admin
	 */
	public void createUser(String userName, boolean isAdmin) throws SQLException {
		// TODO: Implement asserts in main(), should be checked upon insertion into db, by the db
		assert userName != null;
		Statement stmnt = this.connection.createStatement();
		ArrayList<Poster> currentPosters = getPoster();
		int i = 0;
		// Implement later as catching an SQLException, as there should already be a check for uniqueness in the DDL
		int numberOfPosters = currentPosters.size();
		while (i < numberOfPosters){
			// Check if username was already taken
			if (currentPosters[i].userName.equals(userName)) {
				// TODO: send notice to GUI and disallow name creation
				System.err.println("Username already taken. Please choose another.");
			}
			i++;
		}
		if (i == numberOfPosters-1) {
			if (!isAdmin) {
				int newUser = stmnt.executeUpdate("INSERT INTO Poster VALUES ('"+userName+"', 0, 0, null");
			}
			else {
				int newUser = stmnt.executeUpdate("INSERT INTO Poster VALUES ('"+userName+"', 0, 0, admin_seq.NEXTVAL");
			}
			assert newUser == 0;
		}
	}

	/*
	 * Create a page
	 * @param: posterName should be found and passed in via query result
	 */
	public void createPage(String topicName, String posterName) throws SQLException {
		// TODO: Implement asserts in main(), should be checked upon insertion into db, by the db
		assert topicName != null;
		assert posterName != null;
		Statement stmnt = this.connection.createStatement();
		ArrayList<String> allPages = getAllPages();
		int i = 0;
		// Implement the uniqueness check in main()?
		int numberOfPages = allPages.size();
		while (i < numberOfPages){
			// Check if topicname was already taken
			if (allPages[i].equals(topicName)) {
				// TODO: send notice to GUI and disallow page creation
				System.err.println("Topic name already exists for a page. Please choose another topic or use this page to post threads.");
			}
			i++;
		}
		if (i == numberOfPages-1) {
			int newUser = stmnt.executeUpdate("INSERT INTO Poster VALUES ('"+topicName+"', '"+posterName+"'");
			assert newUser == 0;
		}
	}

	/*
	 * Create a thread
	 * @param: topicName and posterName should be found and passed in via query result
	 */
	public void createThread(String threadTitle, String text, String topicName, String posterName) throws SQLException {
		// TODO: Implement asserts in main(), should be checked upon insertion into db, by the db
		assert threadTitle != null;
		assert topicName != null;
		assert posterName != null;
		Statement stmnt = this.connection.createStatement();
		int newThread = stmnt.executeUpdate("INSERT INTO Thread VALUES (thread_seq.NEXTVAL, '"+threadTitle+"', '"+text+"', generate_timestamp, 1, 0, '"+topicName+"', '"+posterName+"'");
		assert newThread == 0;
	}

	/*
	 * Create a comment
	 * @param: posterName and threadID should be found and passed in via query result
	 */
	public void createComment(String text, String posterName, int threadID) throws SQLException {
		// TODO: Implement asserts in main(), should be checked upon insertion into db, by the db
		assert text != null;
		assert posterName != null;
		assert threadID != null;
		Statement stmnt = this.connection.createStatement();
		int newComment = stmnt.executeUpdate("INSERT INTO UserComment VALUES (comment_seq.NEXTVAL, '"+text+"', 1, generate_timestamp, '"+posterName+"', '"+threadID+"'");
		assert newComment == 0;
	}

	/*
	 * Update the comment voted on
	 * @param: posterName, commID should be found and passed in via query result
	 */
	public void updateCommentOnVote(String posterName, boolean isUpvote, int commID) throws SQLException {
		// TODO: Implement asserts in main(), should be checked upon insertion into db, by the db
		assert posterName != null;
		assert commID != null;
		Statement stmnt = this.connection.createStatement();
		int vote = -1;
		if (isUpvote) vote = 1;
		int updatedComment = stmnt.executeUpdate("UPDATE UserComment SET voteNum = voteNum + "+vote+" WHERE PosterName = '"+posterName+"' AND CommID = '"+commID+"'");
		assert updatedComment == 0;
	}

	/*
	 * Update the thread voted on
	 * @param: posterName, threadID should be found and passed in via query result
	 */
	public void updateThreadOnVote(String posterName, boolean isUpvote, int threadID) throws SQLException {
		// TODO: Implement asserts in main(), should be checked upon insertion into db, by the db
		assert posterName != null;
		assert threadID != null;
		Statement stmnt = this.connection.createStatement();
		int vote = -1;
		if (isUpvote) vote = 1;
		int updatedThread = stmnt.executeUpdate("UPDATE UserThread SET voteNum = voteNum + "+vote+" WHERE PosterName = '"+posterName+"' AND CommID = '"+threadID+"'");
		assert updatedThread == 0;
	}

	/*
	 * Create a vote, either a comment or thread vote
	 * @param: isComment is a boolean set to true by default, as this is the most common vote type
	 * @param: posterName, ID should be found and passed in via query result
	 * @param: isUpvote is true for upvote, false for downvote
	 */
	public void createVote(String posterName, boolean isUpvote, int id, boolean isComment) throws SQLException {
		// TODO: Implement asserts in main(), should be checked upon insertion into db, by the db
		assert posterName != null;
		assert id != null;
		assert isComment != null;
		Statement stmnt = this.connection.createStatement();
		// Convert isUpvote to int
		int vote = 0;
		if (isUpvote) vote = 1;
		// If vote is on a comment, update comments, else update threads
		if (isComment) {
			int commentVote = stmnt.executeUpdate("INSERT INTO CommentVote VALUES ('"+posterName+"', '"+vote+"', '"+id+"'");
			assert commentVote == 0;
			updateCommentOnVote(posterName, isUpvote, id);
		} else {
			int threadVote = stmnt.executeUpdate("INSERT INTO ThreadVote VALUES (comment_seq.NEXTVAL, '"+text+"', 1, generate_timestamp, '"+posterName+"', '"+threadID+"'");
			assert threadVote == 0;
			updateThreadOnVote(posterName, isUpvote, id);
		}
	}

	/*
	 * Create a suggestion for a page
	 * @param: posterName, topicName should be found and passed in via query result
	 */
	public void createSuggestion(String text, String posterName, String topicName) throws SQLException {
		// TODO: Implement asserts in main(), should be checked upon insertion into db, by the db
		assert text != null;
		assert posterName != null;
		assert topicName != null;
		Statement stmnt = this.connection.createStatement();
		int suggestion = stmnt.executeUpdate("INSERT INTO Suggestion VALUES (suggestion_seq.NEXTVAL, '"+text+"', '"+posterName+"', '"+topicName+"'");
		assert suggestion == 0;
	}

}
