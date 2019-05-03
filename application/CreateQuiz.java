//
// Filename: CreateQuiz.java
// Class: CS 400, Spring 2019
// Project: Final Team Project
// Due Date: May 2, 2019
// Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto
// Baier
//
package application;

import java.util.ArrayList;
import java.util.Collection;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This is the window that interacts with the user to create a quiz. After displaying the screen, a
 * dropdown menu of all the topics is used to add topics to the list of topics to generate questions
 * from. The user can add multiple topics to a quiz. The user then types how many questions they
 * want in the quiz.
 */
public class CreateQuiz implements Window {
	private Stage stage;
	private ArrayList<String> quizTopics;
	private int nQuestions;
	private List<Question> quiz;

	/**
	 * Constructor that initializes the stage field to the passed in parameter and creates initializes
	 * quizTopics and quiz.
	 * 
	 * @param stage The stage to initialize this.stage to
	 */
	public CreateQuiz(Stage stage) {
		this.stage = stage;
		quizTopics = new ArrayList<String>();
		quiz = new ArrayList<Question>();
	}

	/**
	 * This method does all the display of the window's functions and gets user input. Other methods
	 * use the input stored from this class to create a quiz.
	 * 
	 * @see application.Window#getScene()
	 */
	@Override
	public Scene getScene() {
		VBox root = new VBox(20);
		root.setPadding(new Insets(10, 25, 25, 25));
		root.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		root.setSpacing(10);
		Scene scene = new Scene(root, 800, 600);
		quizTopics = new ArrayList<String>(); // used to make a new empty list
		quiz = new ArrayList<Question>(); // makes new empty list
		// HEADER
		Label quizHeader = new Label("Create Quiz");
		quizHeader.setFont(Config.SIZE24);
		root.getChildren().add(quizHeader);
		// TOPIC SELECTION
		HBox topicHB = new HBox(20);
		if (Main.topics.contains("Other")) // removes "Other"
			Main.topics.remove("Other");
		Main.topics.sorted(); // alphabetize topics
		topicHB.getChildren().add(Main.topicBox);
		// allows user to add multiple topics to the quiz
		Button addTopic = new Button("Add Topic to Quiz");
		Label topicsAdded = new Label("Topics currently added: ");
		// message label used to notify user of errors
		Label msg = new Label();
		addTopic.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				// can add any topic that is not already added
				if (Main.topic.getValue() == null)
					msg.setText("No topics to add");
				else if (quizTopics.contains(Main.topic.getValue()))
					msg.setText("Topic already added");
				else {
					String addedTopic = Main.topic.getValue(); // stores the topic name that was selected
					quizTopics.add(addedTopic); // add topic to list of topics to quiz
					String prompt = "Topics currently added: ";
					for (int i = 0; i < quizTopics.size(); i++)
						if (i + 1 == quizTopics.size())
							prompt += quizTopics.get(i);
						else
							prompt += quizTopics.get(i) + ", ";
					topicsAdded.setText(prompt);
				}
			}
		});
		
		Button removeTopic = new Button("Remove Topic from Quiz");
		removeTopic.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				// can add any topic that is not already added
				if (Main.topic.getValue() == null)
					msg.setText("No topics to remove");
				else if (!quizTopics.contains(Main.topic.getValue()))
					msg.setText("Topic not yet added");
				else {
					String addedTopic = Main.topic.getValue(); // stores the topic name that was selected
					quizTopics.remove(addedTopic); // add topic to list of topics to quiz
					String prompt = "Topics currently added: ";
					for (int i = 0; i < quizTopics.size(); i++)
						if (i + 1 == quizTopics.size())
							prompt += quizTopics.get(i);
						else
							prompt += quizTopics.get(i) + ", ";
					topicsAdded.setText(prompt);
				}
			}
		});
		
		VBox topicButtons = new VBox(20);
		topicButtons.getChildren().add(addTopic);
		topicButtons.getChildren().add(removeTopic);
		
		topicHB.getChildren().add(topicButtons);
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
			public void handle(ActionEvent t) { // only makes quiz if given valid input
					if (quizTopics.size() == 0)
						msg.setText("Please choose a topic");
					else if (!isInteger(numQsTA.getText().trim()))
						msg.setText("Please entire an integer greater than 0");
					else {
						nQuestions = Integer.parseInt(numQsTA.getText().trim());
						if (nQuestions > 0) // there must be a non-zero number of questions to make a quiz
							makeQuiz();
					}
			}
		});
		buttons.getChildren().add(genQuiz);
		buttons.getChildren().add(msg);
		root.getChildren().add(buttons);
		return scene;
	}

	/**
	 * This method checks if the data inputed into the number of questions box is an integer 
	 *  above 0 before trying to make a quiz using it.
	 * 
	 * @param s The string to parse
	 * @return True if it is an integer and above 0, false otherwise
	 */
	private boolean isInteger(String s) {
		try {
			int i = Integer.parseInt(s);
			if (i <= 0) return false;
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method to make a quiz with the given input. Gets the questions from each topic and adds them to
	 * a list. This question collection is then mapped to a stream and the collected into a list. It
	 * keeps a barrier on the number of questions so if the user asks for more questions than
	 * available, the method modifies the number and only gives the max number of questions available.
	 * The list is then matched with a generated list of random numbers and the respective questions
	 * are taken from the list to be used in the quiz. After this has all been done, a new Quiz screen
	 * is created for the user to take a quiz.
	 */
	public void makeQuiz() {
		ArrayList<ArrayList<Question>> allQuestionLists = new ArrayList<ArrayList<Question>>();
		// Get list of topics from question list
		Topic[] topicList = Main.questionList.getTopic(quizTopics.toArray(new String[0]));
		for (Topic topic : topicList)
			allQuestionLists.add(topic.getQuestions());
		// Get List of Questions from the topics selected
		List<Question> allQuestions =
				allQuestionLists.stream().flatMap(Collection::stream).collect(Collectors.toList());
		if (nQuestions > allQuestions.size())
			nQuestions = allQuestions.size();
		// Use random stream of numbers to get random stream of questions
		Random rand = new Random();
		List<Question> quizQuestions = rand.ints(0, allQuestions.size()) // random stream of integers
				// between 0 and allQuestions -
				// 1
				.distinct().limit(nQuestions) // random numbers will be distinct and limited to numQuestions
				.mapToObj(allQuestions::get) // maps numbers to get method of allQuestions
				.collect(Collectors.toList()); // collects questions in a list
		this.quiz = quizQuestions;

		Window takeQuiz = new Quiz(stage, this.quiz);
		stage.setScene(takeQuiz.getScene());
	}
}
