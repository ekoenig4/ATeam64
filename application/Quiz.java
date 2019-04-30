/**
 * Filename:   StartScreen.java
 * Class:        CS 400, Spring 2019
 * Project:    Final Team Project
 * Due Date:   April 25, 2019
 * Authors:    Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Implements the welcoming screen
 */
public class Quiz implements Window {
	private Stage stage;
	private List<Question> questions;
	private Question currentQuestion;

	public Quiz(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {
		VBox root = new VBox(20);
		root.setPadding(new Insets(10, 25, 25, 25));
		root.setSpacing(10);
		Scene scene = new Scene(root, 800, 600);
		// Set Header with question number
//		Label questionNumber = new Label("Question Number #" + (questions.indexOf(currentQuestion) + 1));
		Label questionNumber = new Label("Question Number #1");
		questionNumber.setFont(Config.SIZE24);
		root.getChildren().add(questionNumber);
		// Give question text	
//		Label questionText = new Label(this.currentQuestion.getQuestion());
		Label questionText = new Label("Which icecream is best");
		questionText.setFont(Config.SIZE14);
		questionText.setWrapText(true);
		root.getChildren().add(questionText);
		
		// Create VBox to hold all possible answers
		VBox questions = new VBox(10);
		// get keys from questions answers hashmap, display these as questions
		// after getting the response, get the boolean associated with the selected
		// answer
		
//		Set<String> answerSet = this.currentQuestion.getAnswers().keySet();
		HashMap<String, Boolean> answerMap = new HashMap<String, Boolean>();
		answerMap.put("chocolate", false);
		answerMap.put("vanilla", false);
		answerMap.put("strawberry", false);
		answerMap.put("mint chip", true);
		Set<String> answerSet = answerMap.keySet();
		
		Button submit = new Button("Submit");
		submit.setDisable(true);
		List<RadioButton> radioButtons = new ArrayList<RadioButton>();
		
		ToggleGroup group = new ToggleGroup();
		// add a CheckBox and QuestionText for each question
		for (String question : answerSet) {
			RadioButton currentAnswer = new RadioButton(question);
			radioButtons.add(currentAnswer);
			currentAnswer.setToggleGroup(group);
			currentAnswer.setOnAction(e -> submit.setDisable(false));
			questions.getChildren().add(currentAnswer);
		}
		
		root.getChildren().add(questions);
		root.getChildren().add(submit);
		
		VBox responseBox = new VBox(20);
		Label response = new Label();
		
		submit.setOnAction(e -> {
			boolean answerIsCorrect = false;
			String correctAnswerText = "";
			// disable options
			for (RadioButton current : radioButtons) {
				current.setDisable(true);
				if (answerMap.get(current.getText())) {// determine whether question is correct or not
					answerIsCorrect = current.isSelected();
					correctAnswerText = current.getText();
				}
			}
			// display whether question is correct or not
			
			if (answerIsCorrect) {
				response.setText("Correct!");
				response.setFont(Config.BOLD18);
				response.setTextFill(Color.GREEN);
				responseBox.getChildren().add(response);
			} else {
				response.setText("Incorrect!");
				response.setFont(Config.BOLD18);
				response.setTextFill(Color.RED);
				responseBox.getChildren().add(response);
				
				Label correctAnswer = new Label("Correct answer was : " + correctAnswerText);
				correctAnswer.setFont(Config.SIZE14);
				responseBox.getChildren().add(correctAnswer);
			}
			
			responseBox.getChildren().add(new Button("Continue"));
			root.getChildren().add(responseBox);
			
			// disable submit
			submit.setDisable(true);
		});

		return scene;
	}
	
	public void setQuiz(List<Question> questions) {
		this.questions = questions;
	}

}
