import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class Thread {

	private int threadID;
	private String title;
	private String text;
	private Time time;
	private int voteNum;
	private boolean isLocked;
	private String topicName;
	private String posterName;

	public Thread(int threadID, String title, String text, Time time, int voteNum, boolean isLocked, String topicName,
			String posterName) {
		this.threadID = threadID;
		this.title = title;
		this.text = text;
		this.time = time;
		this.voteNum = voteNum;
		this.isLocked = isLocked;
		this.topicName = topicName;
		this.posterName = posterName;
	}

	public String toString() {
		return "ThreadObject: " + this.title + " & votecount: " + Integer.toString(this.voteNum);
	}

	/*
	 * Returns true if a thread is locked
	 */
	public static boolean isThreadLocked(Connection con, String threadID) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT isLockedFlag FROM Thread WHERE ThreadID = '" + threadID + "'");

		rs.next();
		int flag = rs.getInt(1);
		if (flag == 1)
			return true;
		return false;
	}

	public int getThreadID() {
		return this.threadID;
	}

	public String getTitle() {
		return this.title;
	}

	public String getText() {
		return this.text;
	}

	public String getTime() {
		return this.time.toString();
	}

	public int getVoteNum() {
		return this.voteNum;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public String getPosterName() {
		return this.posterName;
	}
}