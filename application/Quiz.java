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
import java.util.List;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 */
public class Quiz implements Window {
	private Stage stage;
	private List<Question> questions;
	private Question currentQuestion;
	private int questionNumber;
	private int numCorrectAnswers;

	public Quiz(Stage stage, List<Question> questions, Question currentQuestion, int numCorrectAnswers) {
		this.stage = stage;
		this.questions = questions;
		this.currentQuestion = currentQuestion;
		this.questionNumber = questions.indexOf(currentQuestion) + 1;
		this.numCorrectAnswers = numCorrectAnswers;
	}

	@Override
	public Scene getScene() {
		// Set root box
		VBox root = new VBox(20);
		root.setPadding(new Insets(10, 25, 25, 25));
		root.setSpacing(10);
		Scene scene = new Scene(root, 800, 600);
		// Set Header with question number
		Label questionNumber = new Label("Question Number #" + this.questionNumber);
		questionNumber.setFont(Config.SIZE24);
		root.getChildren().add(questionNumber);
		// Give question text	
		Label questionText = new Label(this.currentQuestion.getQuestion());
		questionText.setFont(Config.SIZE14);
		questionText.setWrapText(true);
		root.getChildren().add(questionText);
		
		// Create VBox to hold all possible answers
		VBox questions = new VBox(10);
		// get keys from questions answers hashmap, display these as questions
		// after getting the response, get the boolean associated with the selected
		// answer
		
		HashMap<String, Boolean> answerMap = this.currentQuestion.getAnswers();
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
				this.numCorrectAnswers++;
			} else {
				response.setText("Incorrect!");
				response.setFont(Config.BOLD18);
				response.setTextFill(Color.RED);
				responseBox.getChildren().add(response);
				
				Label correctAnswer = new Label("Correct answer was : " + correctAnswerText);
				correctAnswer.setFont(Config.SIZE14);
				responseBox.getChildren().add(correctAnswer);
			}
			
			if (this.questionNumber < this.questions.size())
				responseBox.getChildren().add(new SwapScreen("Continue", 
						new Quiz(stage, this.questions, this.questions.get(this.questionNumber), this.numCorrectAnswers)
						, stage));
			else {
				Label quizCompleteMessage = new Label("Quiz Complete! Results: " + this.numCorrectAnswers + 
						" correct answer(s) out of " + this.questions.size());
				quizCompleteMessage.setFont(Config.BOLD18);
				quizCompleteMessage.setWrapText(true);
				responseBox.getChildren().add(quizCompleteMessage);
				responseBox.getChildren().add(new SwapScreen("Return to Home", Main.windows[0], stage));
			}
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
