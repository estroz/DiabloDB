import java.sql.Time;

public class Comment {
	int commID;
	String text;
	int voteNum;
	Time time;
	String poster;
	String threadID;
	
	public Comment(int commID, String text, int voteNum, Time time, String poster, String threadID) {
		
		this.commID = commID;
		this.text = text;
		this.voteNum = voteNum;
		this.time = time;
		this.poster = poster;
		this.threadID = threadID;
	}

}
