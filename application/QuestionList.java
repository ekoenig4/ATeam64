package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
		this.addTopic(question.getTopic());
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
	
	public void Load(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
		JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(jsonFilepath));
		JSONArray ja = (JSONArray) jo.get("questionArray");
		for (Object q : ja) {
			JSONObject jq = (JSONObject) q;
			String metadata = (String) jq.get("meta-data");
			String question = (String) jq.get("questionText");
			String topic = (String) jq.get("topic");
			String image = (String) jq.get("image");
			JSONArray choiceArray = (JSONArray) jq.get("choiceArray");
			HashMap<String,Boolean> answers = new HashMap<>();
			for (Object ans : choiceArray) {
				JSONObject jans = (JSONObject) ans;
				String choice = (String) jans.get("choice");
				Boolean isCorrect = ((String) jans.get("isCorrect")).equals("T") ? true : false;
				answers.put(choice, isCorrect);
			}
			this.addQuestion(new Question(topic,question,answers,image,metadata));
		}
	}
	
	public void Save(String fname) {
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		for (String topicName : topicList.keySet()) {
			Topic t = topicList.get(topicName);
			for (Question q : t.getQuestions()) {
				JSONObject jq = new JSONObject();
				jq.put("meta-data", q.getMetaData());
				jq.put("questionText", q.getQuestion());
				jq.put("topic", topicName);
				jq.put("image",q.getImageFileName());
				JSONArray choiceArray = new JSONArray();
				HashMap<String,Boolean> answers = q.getAnswers();
				for (String ans : answers.keySet()) {
					JSONObject jans = new JSONObject();
					jans.put("isCorrect", answers.get(ans) ? "T" : "F");
					jans.put("choice", ans);
					choiceArray.add(jans);
				}
				jq.put("choiceArray", choiceArray);
				ja.add(jq);
			}
		}
		jo.put("questionArray", ja);
		
		try {
			FileWriter file = new FileWriter(fname);
			file.write(jo.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
