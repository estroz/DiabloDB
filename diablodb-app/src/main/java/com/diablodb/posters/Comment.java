package com.diablodb.posters;
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
	
	public String toString() {
		return "CommentObject; id: " + Integer.toString(this.commID);
	}

	public int getCommID() {
		return this.commID;
	}

	public int getVoteNum() {
		return this.voteNum;
	}

	public String getText() {
		return this.text;
	}

	public String getPoster() {
		return this.poster;
	}

	public String getThreadID() {
		return this.threadID;
	}

	public String getTime() {
		return this.time.toString();
	}

}
