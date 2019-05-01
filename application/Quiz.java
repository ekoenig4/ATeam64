/**
 * Filename: Quiz.java Class: CS 400, Spring 2019 Project: Final Team Project Due Date: May 2, 2019
 * Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto
 * Baier
 */
package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 */
public class Quiz implements Window {
  private Stage stage;
  private List<Question> questions;
  private int numCorrectAnswers;
  private int questionNumber;

  public Quiz(Stage stage, List<Question> questions) {
    this.stage = stage;
    this.questions = questions;
    this.numCorrectAnswers = 0;
    this.questionNumber = 0;
  }

  @Override
  public Scene getScene() {
    // Set root box
    Question currentQuestion = questions.get(questionNumber);
    questionNumber++;
    VBox root = new VBox(20);
    root.setPadding(new Insets(10, 25, 25, 25));
    root.setSpacing(10);
    Scene scene = new Scene(root, 800, 600);
    // Set Header with question number
    Label numLabel = new Label("Question Number #" + (this.questionNumber));
    numLabel.setFont(Config.SIZE24);
    root.getChildren().add(numLabel);
    // Give question text
    Label questionText = new Label(currentQuestion.getQuestion());
    questionText.setFont(Config.SIZE14);
    questionText.setWrapText(true);
    root.getChildren().add(questionText);
    
    ImageView img = new ImageView();
    if (currentQuestion.getPic() != null)
    	img = new ImageView(currentQuestion.getPic());
  	img.setFitHeight(200);
  	img.setFitWidth(200);
  	root.getChildren().add(img);
    // Create VBox to hold all possible answers
    VBox questions = new VBox(10);
    // get keys from questions answers hashmap, display these as questions
    // after getting the response, get the boolean associated with the selected
    // answer

    HashMap<String, Boolean> answerMap = currentQuestion.getAnswers();
    Set<String> answerSet = answerMap.keySet();

    Button submit = new Button("Submit");
    submit.setDisable(true);
    List<RadioButton> radioButtons = new ArrayList<RadioButton>();

    ToggleGroup group = new ToggleGroup();
    // add a CheckBox and QuestionText for each question
    for (String ans : answerSet) {
      RadioButton currentAnswer = new RadioButton(ans);
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

      if (this.questionNumber < this.questions.size()) {
        Button cont = new Button("Continue");
        cont.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent t) {
            stage.setScene(getScene());
          }
        });
        responseBox.getChildren().add(cont);
      } else {
        // THIS IS WHERE THE RESULT SCREEN SHOULD GO
        SwapScreen resultB = new SwapScreen("See Results",
            new Results(stage, this.numCorrectAnswers, this.questionNumber), stage);
        root.getChildren().add(resultB);

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
