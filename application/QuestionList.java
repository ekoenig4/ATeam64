package application;

import java.util.HashMap;
import java.util.Set;

public class QuestionList {
	HashMap<String,Topic> topicList;

	public QuestionList() {
		topicList = new HashMap<>();
	}

	public void addTopic(String topicName) {
		if (!topicList.containsKey(topicName)) {
			topicList.put(topicName, new Topic(topicName));
		}
	}

	public void addQuestion(Question question) {
		if (!topicList.containsKey(question.getTopic())) {
			topicList.put(question.getTopic(), new Topic(question.getTopic()));
		}
		Topic topic = topicList.get(question.getTopic());
		topic.addQuestion(question);	
	}
	
	public Topic[] getTopic(String...topicNames) {
		Topic[] topics = new Topic[topicNames.length];
		for (int i = 0; i < topics.length; i++)
			topics[i] = topicList.get(topicNames[i]);
		return topics;
	}
	
	public Set<String> allTopicNames() { return topicList.keySet(); }
	
	public Topic[] getAllTopics() {
		return getTopic(allTopicNames().toArray(new String[0]));
	}

}
