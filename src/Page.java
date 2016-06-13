import java.util.ArrayList;

public class Page {
	
	String topicName;
	ArrayList<Thread> threads;
	String posterName;
	
	public Page(String topicName, String posterName) {
		this.topicName = topicName;
		this.threads = new ArrayList<Thread>();
		this.posterName = posterName;
	}
	
	public String toString() {
		return "Page; TopicName: " + this.topicName;
	}

	public String getTopicName() {
		return this.topicName;
	}
	public String getPosterName() {
		return this.posterName;
	}
	
}
