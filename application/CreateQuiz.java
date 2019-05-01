/**
 * Filename: CreateQuiz.java
 * Class: CS 400, Spring 2019 
 * Project: Final Team Project 
 * Due Date: May 2, 2019 
 * Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Implements the welcoming screen
 */
public class CreateQuiz implements Window {
	private Stage stage;
	private ArrayList<String> quizTopics;
	private int nQuestions;
	private List<Question> quiz;

	public CreateQuiz(Stage stage) {
		this.stage = stage;
		quizTopics = new ArrayList<String>();
		quiz = new ArrayList<Question>();
		// setFieldsForTesting();
	}
	
	@Override
	public Scene getScene() {
		VBox root = new VBox(20);
		root.setPadding(new Insets(10, 25, 25, 25));
		root.setSpacing(10);
		Scene scene = new Scene(root, 800, 600);
		// HEADER
		Label quizHeader = new Label("Create Quiz");
		quizHeader.setFont(Config.SIZE24);
		root.getChildren().add(quizHeader);
		// TOPIC SELECTION
		HBox topicHB = new HBox(20);
		topicHB.getChildren().add(Main.topicBox);
		// allows user to add multiple topics to the quiz
		Button addTopic = new Button("Add Topic to Quiz");
		Label topicsAdded = new Label("Topics currently added: ");
		addTopic.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				// can add any topic that is not "Other" and that is not already added
				if (!Main.topic.getValue().equals("Other") && !quizTopics.contains(Main.topic.getValue())) {
					String addedTopic = Main.topic.getValue();
					quizTopics.add(addedTopic);
					String topicsText = topicsAdded.getText();
					if (topicsText.equals("Topics currently added: "))
						topicsAdded.setText(topicsText + addedTopic);
					else
						topicsAdded.setText(topicsText + ", " + addedTopic);
				}
			}
		});
		topicHB.getChildren().add(addTopic);
		topicHB.getChildren().add(topicsAdded);
		root.getChildren().add(topicHB);

		// NUMBER OF QUESTIONS AREA
		HBox numQsHB = new HBox(10);
		Label numQsLabel = new Label("Enter number of questions for the quiz:");
		numQsLabel.setFont(Config.SIZE14);
		TextArea numQsTA = new TextArea();
		numQsTA.setPrefSize(100, 30);
		numQsHB.getChildren().add(numQsLabel);
		numQsHB.getChildren().add(numQsTA);
		root.getChildren().add(numQsHB);
		// decision buttons area
		HBox buttons = new HBox(20);
		buttons.getChildren().add(new SwapScreen("Back", Main.windows[0], stage));
		Button genQuiz = new Button("Generate Quiz");
		genQuiz.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				if (quizTopics.size() > 0 && isInteger(numQsTA.getText())) {
					nQuestions = Integer.parseInt(numQsTA.getText());
					if (nQuestions >= Main.questionList.getNumOfQuestions())
						nQuestions = Main.questionList.getNumOfQuestions();
					makeQuiz();
				}
			}
		});
		buttons.getChildren().add(genQuiz);

		root.getChildren().add(buttons);
		return scene;
	}
	
	private boolean isInteger(String s) {
		try { Integer.parseInt(s); } 
		catch (NumberFormatException | NullPointerException e) { return false; }
		return true;
	}

	public void setQuizTopics(ArrayList<String> topicList) {
		this.quizTopics = topicList;
	}

  public void makeQuiz() {
		ArrayList<ArrayList<Question>> allQuestionLists = new ArrayList<ArrayList<Question>>();
		// Get list of topics from question list
		Topic[] topicList = Main.questionList.getTopic(quizTopics.toArray(new String[0]));
		for (Topic topic : topicList)
			allQuestionLists.add(topic.getQuestions());
		// Get List of Questions from the topics selected
		List<Question> allQuestions = allQuestionLists.stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		// Use random stream of numbers to get random stream of questions
		Random rand = new Random();
		List<Question> quizQuestions = rand
				.ints(0,allQuestions.size()) // random stream of integers between 0 and allQuestions - 1
				.distinct()
				.limit(nQuestions) // random numbers will be distinct and limited to numQuestions
				.mapToObj(allQuestions::get) // maps numbers to get method of allQuestions
				.collect(Collectors.toList()); // collects questions in a list
		this.quiz = quizQuestions;
		
		Window takeQuiz = new Quiz(stage,this.quiz);
		stage.setScene(takeQuiz.getScene());
	}
}
