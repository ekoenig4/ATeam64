//
// Filename: AddQuestion.java
// Class: CS 400, Spring 2019
// Project: Final Team Project
// Due Date: May 2, 2019
// Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto
// Baier
//

package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This is the final window of the quiz. This displays the percentage correct from the quiz, the
 * number of correct questions, and the total number of questions. The user then has the option to
 * go back to the Home page where they can create a new quiz, add/load questions, save, or quit.
 */
public class Results implements Window {
  private Stage stage;
  private int numCorrect; // the number of correct answers from the quiz
  private int numQuestions; // the total number of questions from the quiz

  /**
   * Constructor that initializes the window's stage to the stage parameter passed int, and receives
   * the number of correct answers and total number of questions from the quiz class.
   * 
   * @param stage
   * @param numCorrect The user's number of correct answers
   * @param numQuestions The total number of questions for the current quiz
   */
  public Results(Stage stage, int numCorrect, int numQuestions) {
    this.stage = stage;
    this.numCorrect = numCorrect;
    this.numQuestions = numQuestions;

  }

  /**
   * The main display of the window. This displays the user's results from the most recent quiz they
   * finished. The option to go back to the Home screen is available as a button at the bottom of
   * the screen.
   * 
   * @see application.Window#getScene()
   */
  @Override
  public Scene getScene() {
    // initialize window
    VBox root = new VBox(20);
    root.setPadding(new Insets(10, 25, 25, 25));
    root.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    root.setSpacing(10);
    Scene scene = new Scene(root, 800, 600);
    // result header label
    Label resultHeader = new Label("Results");
    resultHeader.setFont(Config.SIZE24);
    root.getChildren().add(resultHeader);
    // create labels for % correct, num correct, and num questions
    Label correctPerc = new Label(
        "Percentage correct: " + ((double) numCorrect / (double) numQuestions * (100.0)) + "%");
    Label numCorrectMsg = new Label("Number of Questions Correct: " + numCorrect);
    Label numQuestionMsg = new Label("Total Number of Questions in the Quiz: " + numQuestions);
    // make all labels bold (except header)
    correctPerc.setFont(Config.BOLD18);
    numCorrectMsg.setFont(Config.BOLD18);
    numQuestionMsg.setFont(Config.BOLD18);
    // add labels to be displayed on the screen
    root.getChildren().add(correctPerc);
    root.getChildren().add(numCorrectMsg);
    root.getChildren().add(numQuestionMsg);
    // button to return to the home screen
    root.getChildren().add(new SwapScreen("Return to Home", Main.windows[0], stage));
    return scene;
  }
}
