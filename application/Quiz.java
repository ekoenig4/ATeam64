/**
 * Filename:   StartScreen.java
 * Class:        CS 400, Spring 2019
 * Project:    Final Team Project
 * Due Date:   April 25, 2019
 * Authors:    Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Implements the welcoming screen
 */
public class Quiz implements Window {
	private Stage stage;
	private ArrayList<Topic> topicList;
	private ArrayList<Question> quiz;

	public Quiz(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {
		VBox root = new VBox(20);
		root.setPadding(new Insets(10, 25, 25, 25));
		root.setSpacing(10);
		Scene scene = new Scene(root, 800, 600);

		Label quizHeader = new Label("QUIZ");
		quizHeader.setFont(Config.SIZE24);

		return scene;
	}
	
	
	public List<Question> makeQuiz(QuestionList questionList) {
		
		
		return null;
	}
	
	public void chooseTopics(Topic[] topicList) {
		
		
		
	}
	
}
