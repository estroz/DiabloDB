package com.example.DiabloDB;

public class Page {
	private String topicName;
	private String posterName;

	public Page(String topicName, String posterName) {
		this.topicName = topicName;
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
