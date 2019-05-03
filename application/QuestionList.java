/**
 * Filename: QuestionList.java
 * Class: CS 400, Spring 2019 
 * Project: Final Team Project 
 * Due Date: May 2, 2019 
 * Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class holds a HashMap of topics
 * This is the class where the program stores new questions
 * and can load and save questions from and to json files.
 *
 */
public class QuestionList {
	private HashMap<String,Topic> topicList;
	private int numberOfQuestions;

	public QuestionList() {
		topicList = new HashMap<>();
		numberOfQuestions = 0;
	}

	/**
	 * Add a topic to the QuestionList
	 * @param topicName the name of the topic
	 */
	public void addTopic(String topicName) {
		if (!topicList.containsKey(topicName)) {
			topicList.put(topicName, new Topic(topicName));
		}
	}

	/** Add a question to the list of current questions
	 * @param question The question to add to the list
	 */
	public void addQuestion(Question question) {
		this.addTopic(question.getTopic());
		Topic topic = topicList.get(question.getTopic());
		if (!topic.hasQuestion(question)) {
			topic.addQuestion(question);	
			++numberOfQuestions;
		}
	}

	public int getNumOfQuestions() {
		return numberOfQuestions;
	}

	/**
	 * @param topicNames a string array of names of topics that the user wants
	 * @return an array of Topic classes corresponding to the topicNames given
	 */
	public Topic[] getTopic(String...topicNames) {
		Topic[] topics = new Topic[topicNames.length];
		for (int i = 0; i < topics.length; i++)
			topics[i] = topicList.get(topicNames[i]);
		return topics;
	}

	public ObservableList<String> getAllTopicNames() {
		Set<String> key = topicList.keySet();
		List<String> keylist = new ArrayList<>(key);
		Collections.sort(keylist);
		return FXCollections.observableArrayList(keylist);
	}

	/**
	 * Loads a json file into the QuestionList class 
	 * @param jsonFilepath is the path to the json file to be loaded
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void Load(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
		JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(jsonFilepath));
		// All proper jsons should have a questionArray 
		JSONArray ja = (JSONArray) jo.get("questionArray");
		// Loop through the array and extract the question information
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
			// Once all info has been extracted for the question, add it to the QuestionList
			this.addQuestion(new Question(topic,question,answers,image,metadata));
		}
	}

	/**
	 * Saves the current QuestionList to a json file
	 * @param fname is the name of the json file to be created
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public void Save(String fname) throws IOException {
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		for (String topicName : topicList.keySet()) {
			Topic t = topicList.get(topicName);
			for (Question q : t.getQuestions()) {
				JSONObject jq = new JSONObject();
				if (q.getMetaData() == null) jq.put("meta-data", "unused");
				else                         jq.put("meta-data", q.getMetaData());
				jq.put("questionText", q.getQuestion());
				jq.put("topic", topicName);
				if (q.getImageFileName() == null) jq.put("image", "none");
				else                              jq.put("image",q.getImageFileName());
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

		FileWriter file = new FileWriter(fname);
		file.write(jo.toJSONString());
		file.flush();
		file.close();
	}
}
