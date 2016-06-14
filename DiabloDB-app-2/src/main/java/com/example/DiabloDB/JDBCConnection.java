package com.example.DiabloDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class JDBCConnection {
    private Connection connection;

    //	private Integer getNewID() throws SQLException {
    //		Statement stmt = this.connection.createStatement();
    //		ResultSet rs = stmt.executeQuery("SELECT * FROM Poster");
    //	}

    public static void main(String[] args) throws SQLException {
        //		JDBCConnection jd = new JDBCConnection();
        //		// Test each method, this is the getUsers()
        //		ArrayList<Poster> users = jd.getUsers();
        //		System.out.println(users.toString());
        //
        //		// Test getAllPages()
        //		ArrayList<String> pages = jd.getAllPages();
        //		System.out.println(pages.toString());
        //
        //		// Test getPageThreads(Topic)
        //		ArrayList<Thread> pageThreads = jd.getPageThreads(pages.get(0));
        //		System.out.println(pageThreads.toString());
        //
        //		// Test getSpecificUser(user)
        //		ArrayList<ArrayList<Object>> aUser = jd.getSpecificUser(users.get(0).getUserName());
        //		System.out.println(aUser);
        //
        //		// Test getThreadComments(thread)
        //		ArrayList<Comment> commentsOfThread = jd.getThreadComments(pageThreads.get(0).getThreadID());
        //		System.out.println(commentsOfThread);
        //
        //		// test createUser(name, admin)
        //		jd.createUser("testUser", false);
        //
        //		// test promote poster
        //		jd.promotePosterToAdmin("Sam");
        //		System.out.println("Sam is now the most powerful user on the site");
        //
        //		// test create thread(
        //		jd.createThread("api test thread", "el o el", "SamPage", "Diablo");
        //		System.out.println("success testing create thread");
        //
        //		// test create comment
        //		jd.createComment("nice meme", "Sam", pageThreads.get(0).getThreadID());
        //		System.out.println("success testing create comment");
        //
        //		// test create page
        //		jd.createPage("memes", "Sam");
        //		System.out.println("success testing create page");
        //
        //		// test create vote
        //		jd.createVote("Diablo", false, commentsOfThread.get(0).getCommID(), true);
        //		System.out.println("success testing creating vote");
        //
        //		//test update comment
        //		jd.updateCommentOnVote("Diablo", true, commentsOfThread.get(0).getCommID());
        //		System.out.println("success testing create commentvote");
        //
        //		// test update thread
        //		jd.updateThreadOnVote("Diablo", true, pageThreads.get(0).getThreadID());
        //		System.out.println("success testing create threadvote");
        //
        //		// test create suggestion
        //		jd.createSuggestion("try harder lol", "Sam", "DiabloPage");
        //		System.out.println("success testing create suggestion");
        //
        //		// TEST THE QUERY METODS ADDED -------------------------------------------------------
        //		ArrayList<String> repabove = jd.selectUsersAboveX("reputation", 0);
        //		System.out.println(repabove);
        //
        //		ArrayList<String> idAbove = jd.selectAdminIDAboveX("reputation", 0);
        //		System.out.println(idAbove);
        //
        //		// test suggestionsFromAdmins()
        //		ArrayList<String> suggs = jd.suggestionsFromAdmins();
        //		System.out.println(suggs);
        //
        //		// test usersInAllThreads()
        //		ArrayList<String> hasNoLife = jd.usersInAllThreads();
        //		System.out.println(hasNoLife);
        //
        //		// test min/max thread
        //		Thread min = jd.minOrMaxThread("<");
        //		Thread max = jd.minOrMaxThread(">");
        //		System.out.println(min);
        //		System.out.println(max);
        //
        //		// test commentInfo thread
        //		HashMap<String, Integer> map = jd.CommentInfo("avg");
        //		System.out.println(map);
    }

    public JDBCConnection() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Driver registered");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug",
                                                         "uname", "pw");
            this.connection = con;
        } catch (SQLException e) {
            System.out.println("Couldn't conenct to the database, are you tunneled?");
        }
        System.out.println("Connected to db");

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
    public ArrayList<Page> getAllPages() throws SQLException {
        ArrayList<Page> arr = new ArrayList<Page>();

        Statement stmnt = this.connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM Page");

        while (rs.next()) {
            String topicName = rs.getString("TopicName");
            String posterName = rs.getString("PosterName");

            Page p = new Page(topicName, posterName);
            arr.add(p);
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
        ArrayList<Poster> currentPosters = getUsers();
        int i = 0;
        // Implement later as catching an SQLException, as there should already be a check for uniqueness in the DDL
        int numberOfPosters = currentPosters.size();
        while (i < numberOfPosters){
            // Check if username was already taken
            if (currentPosters.get(i).getUserName().equals(userName)) {
                // TODO: send notice to GUI and disallow name creation
                System.err.println("Username already taken. Please choose another.");
            }
            i++;
        }
        if (i == numberOfPosters-1) {
            int newUser;
            if (!isAdmin) {
                newUser = stmnt.executeUpdate("INSERT INTO Poster VALUES ('"+userName+"', 0, 0, null");
            }
            else {
                newUser = stmnt.executeUpdate("INSERT INTO Poster VALUES ('"+userName+"', 0, 0, admin_seq.NEXTVAL");
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
        System.out.println("in create page");
        assert topicName != null;
        assert posterName != null;
        Statement stmnt = this.connection.createStatement();
        ArrayList<Page> allPages = getAllPages();
        int i = 0;
        // Implement the uniqueness check in main()?
        int numberOfPages = allPages.size();
        while (i < numberOfPages){
            // Check if topicname was already taken
            if (allPages.get(i).getTopicName().equals(topicName)) {
                // TODO: send notice to GUI and disallow page creation
                System.err.println("Topic name already exists for a page. Please choose another topic or use this page to post threads.");
            }
            i++;
        }
        if (i == numberOfPages) {
            int newUser = stmnt.executeUpdate("INSERT INTO Page VALUES ('"+topicName+"', '"+posterName+"')");
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
        int newThread = stmnt.executeUpdate("INSERT INTO Thread VALUES (id_seq.NEXTVAL, '" + threadTitle + "', '"+text+"', current_timestamp, 1, 0, '"+topicName+"', '"+posterName+"')");
        assert newThread == 0;
    }

    /*
     * Create a comment
     * @param: posterName and threadID should be found and passed in via query result
     */
    public void createComment(String text, String posterName, String threadID) throws SQLException {
        // TODO: Implement asserts in main(), should be checked upon insertion into db, by the db
        assert text != null;
        assert posterName != null;
        //		assert threadID != null;
        if (Thread.isThreadLocked(this.connection, threadID)) {
            throw new SQLException("Thread is locked. You cannot comment on a locked thread.");
        }
        Statement stmnt = this.connection.createStatement();
        int newComment = stmnt.executeUpdate("INSERT INTO UserComment VALUES (id_seq.NEXTVAL, '"+text+"', 1, current_timestamp, '"+posterName+"', "+threadID+")");
        assert newComment == 0;
    }

    /*
     * Update the comment voted on
     * @param: posterName, commID should be found and passed in via query result
     */
    public void updateCommentOnVote(String posterName, boolean isUpvote, int commID) throws SQLException {
        // TODO: Implement asserts in main(), should be checked upon insertion into db, by the db
        assert posterName != null;
        //		assert commID != null;
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
        //		assert threadID != null;
        Statement stmnt = this.connection.createStatement();
        int vote = -1;
        if (isUpvote) vote = 1;
        int updatedThread = stmnt.executeUpdate("UPDATE Thread SET voteNum = voteNum + "+vote+" WHERE PosterName = '"+posterName+"' AND ThreadID = '"+threadID+"'");
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
        //		assert id != null;
        //		assert isComment != null;
        Statement stmnt = this.connection.createStatement();
        // Convert isUpvote to int
        int vote = 0;
        if (isUpvote) vote = 1;
        // If vote is on a comment, update comments, else update threads
        if (isComment) {
            int commentVote = stmnt.executeUpdate("INSERT INTO CommentVote VALUES ('"+posterName+"', "+vote+", "+Integer.toString(id)+")");
            assert commentVote == 0;
            updateCommentOnVote(posterName, isUpvote, id);
        } else {
            int threadVote = stmnt.executeUpdate("INSERT INTO ThreadVote VALUES ('"+posterName+"', "+vote+", '"+Integer.toString(id)+")");
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
        int suggestion = stmnt.executeUpdate("INSERT INTO Suggestion VALUES (id_seq.NEXTVAL, '"+text+"', '"+posterName+"', '"+topicName+"')");
        assert suggestion == 0;
    }

    /*
     * -----------------------------------------------------------------------------------------
     * Other update methods and administrative functions
     */

    /*
     * Promote a poster to admin
     * @throws: SQLException thrown if AdminID is not NULL by db
     */
    public void promotePosterToAdmin(String posterName) throws SQLException {
        Statement stmnt = this.connection.createStatement();
        int promote = stmnt.executeUpdate("UPDATE Poster SET AdminID = admin_seq.NEXTVAL WHERE PosterName = '"+posterName+"' AND AdminID IS NULL");
        assert promote == 0;
    }

    public void adminLockThread(int threadID, String posterName) throws SQLException {
        Statement stmnt = this.connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT adminID FROM Poster " +
                                          "WHERE postername = '" + posterName + "'");
        boolean x = true;
        while (rs.next()) {
            x = rs.wasNull();
        }
        if (x) {
            throw new SQLException("This user is not an admin.");
        }

        int promote = stmnt.executeUpdate("UPDATE Thread SET isLockedFlag = 1");
        assert promote == 0;
    }

    // ADDITIONAL PROJECT DEMO METHODS, SPECIFIED ON TRELLO (PROJ, DELETE, ETC)

    // op is one of {"reputation", "numberofpages"}
    // takes the op and gives the names of all users above <arg> with that value
    public ArrayList<String> selectUsersAboveX(String op, int arg) throws SQLException {
        ArrayList<String> names = new ArrayList<String>();
        Statement stmnt = this.connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT posterName FROM Poster " +
                                          "WHERE " + op + " >= " + Integer.toString(arg) + "");
        while (rs.next()) {
            String pname = rs.getString(1);
            boolean x = rs.wasNull();
            if (!x) {
                names.add(pname);
            }
        }

        return names;

    }

    public ArrayList<String> selectAdminIDAboveX(String op, int arg) throws SQLException {
        ArrayList<String> ids = new ArrayList<String>();
        Statement stmnt = this.connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT adminID FROM Poster " +
                                          "WHERE " + op + " >= " + Integer.toString(arg) + "");
        while (rs.next()) {
            String pname = rs.getString(1);
            boolean x = rs.wasNull();
            if (!x) {
                ids.add(pname);
            }
        }
        return ids;

    }

    public ArrayList<String> suggestionsFromAdmins() throws SQLException {
        ArrayList<String> sugIDs = new ArrayList<String>();

        Statement stmnt = this.connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT sugID FROM Poster P, Suggestion S " +
                                          "WHERE P.adminID is not null AND P.postername = S.postername");
        while (rs.next()) {
            String id = rs.getString(1);
            sugIDs.add(id);
        }
        return sugIDs;
    }

    public ArrayList<String> usersInAllThreads() throws SQLException {
        ArrayList<String> posternames = new ArrayList<String>();

        Statement stmnt = this.connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT postername FROM poster p WHERE NOT EXISTS ((SELECT t.threadID FROM thread t) MINUS " +
                                          "(SELECT c.threadid FROM usercomment c, thread t WHERE p.postername = c.postername AND " +
                                          "c.threadid = t.threadid) )");
        while (rs.next()) {
            String id = rs.getString(1);
            posternames.add(id);
        }
        return posternames;

    }


    // Op is one of : {" < " or " > "}
    // if <, gives the min
    // if > gives the max
    public Thread minOrMaxThread(String op) throws SQLException {
        Statement stmnt = this.connection.createStatement();
        ResultSet rs = stmnt.executeQuery("select * from thread t where t.votenum " + op + "= (select max(t1.votenum) from thread t1)");

        Thread t = null;
        while (rs.next()) {
            int threadID= rs.getInt(1);
            String title = rs.getString(2);
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
            t = new Thread(threadID, title, text, time, voteNum, isLocked, topicName, posterName);
        }
        return t;

    }

    // get the min comment score op="min"
    // the max comment score op="max"
    // the avg comment score op="avg"
    // the count of comments op ="count"

    public HashMap<String, Integer> CommentInfo(String op) throws SQLException {
        HashMap<String, Integer> threadVoteMap = new HashMap<String, Integer>();
        Statement stmnt = this.connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT + c.threadid, " + op + "(c.voteNum) FROM UserComment c, Thread t GROUP BY c.threadID HAVING c.threadID = t.threadID");

        while (rs.next()) {
            String threadid = rs.getString(1);
            Integer votenum = rs.getInt(2);
            threadVoteMap.put(threadid, votenum);
        }

        return threadVoteMap;
    }

}
