import java.sql.Time;

public class Thread {

	int threadID;
	String title;
	String text;
	Time time;
	int voteNum;
	boolean isLocked;
	String topicName;
	String posterName;
	
	public Thread(int threadID, String title, String text, Time time, int voteNum, boolean isLocked, String topicName, String posterName) {
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
	
}
