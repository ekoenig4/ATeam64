/**
 * Filename: AddQuestion.java Class: CS 400, Spring 2019 Project: Final Team Project Due Date: April
 * 25, 2019 Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer,
 * Otto Baier
 */
package application;

import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * allows for a question & topic to be added manually
 *
 */
public class AddQuestion implements Window {
  private Stage stage;
  HashMap<String, Boolean> answerMap = new HashMap<String, Boolean>();

  public AddQuestion(Stage stage) {
    this.stage = stage;
  }

  @Override
  public Scene getScene() {
    VBox root = new VBox(20);
    root.setPadding(new Insets(10, 25, 25, 25));
    root.setSpacing(10);
    Scene scene = new Scene(root, 800, 600);
    // Header
    Label addQHeader = new Label("Add Question");
    addQHeader.setFont(Config.SIZE24);
    root.getChildren().add(addQHeader);
    // Topic HBox
    HBox topicHB = new HBox(20);
    topicHB.getChildren().add(Main.topicBox);
    VBox otherTopicVB = new VBox(10); // groups other topic label with textbox
    Label otherPrompt =
        new Label("If you selected \"Other\", please type the name of the new topic below:");
    otherPrompt.setFont(Config.SIZE14);
    otherTopicVB.getChildren().add(otherPrompt);
    TextArea otherText = new TextArea(); // other topic text area
    otherText.setPrefHeight(50);
    otherTopicVB.getChildren().add(otherText);
    topicHB.getChildren().add(otherTopicVB);
    root.getChildren().add(topicHB);
    // Question text area
    Label questionPrompt = new Label("Enter question text below:");
    questionPrompt.setFont(Config.SIZE14);
    TextArea question = new TextArea();
    question.setPrefHeight(100);
    root.getChildren().add(questionPrompt);
    root.getChildren().add(question);
    // CORRECT ANSWER AREA
    HBox correctBox = new HBox(20); // correct answer hbox with label and text area
    Label correctL = new Label("Enter correct answer:");
    correctL.setFont(Config.SIZE14);
    TextArea correctT = new TextArea();
    correctT.setPrefHeight(50);
    correctBox.getChildren().add(correctL);
    correctBox.getChildren().add(correctT);
    root.getChildren().add(correctBox);
    // INCORRECT ANSWER AREA
    HBox incorrectBox = new HBox(20);
    Label incorrectL = new Label("Enter incorrect answers:");
    incorrectL.setFont(Config.SIZE14);
    VBox incorrectTVB = new VBox(10);
    TextArea incorrect = new TextArea();
    incorrect.setPrefHeight(50);
    incorrectTVB.getChildren().add(incorrect);
    // will eventually give the option of adding a greater amount of incorrect answers instead of
    // max 4
    Button addAns = new Button("Add Incorrect Answer");
    Button removeAns = new Button("Remove Incorrect Answer");
    Label incorrectAnswers = new Label("Incorrect Answers: ");
    addAns.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) {
        if (!incorrect.getText().replaceAll("\\s+", "").equals("")) {
          answerMap.put(incorrect.getText(), false);
          String text = incorrectAnswers.getText();
          incorrectAnswers.setText(text + incorrect.getText() + ", ");
          incorrect.clear();
        }
      }
    });
    removeAns.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) {
        if (!incorrect.getText().replaceAll("\\s+", "").equals("")) {
          answerMap.remove(incorrect.getText());
          String text = incorrectAnswers.getText();
          text = text.replace(incorrect.getText() + ", ", "");
          incorrectAnswers.setText(text);
          incorrect.clear();
        }
      }
    });
    HBox incorrectButtons = new HBox();
    incorrectTVB.getChildren().add(incorrectButtons);
    incorrectButtons.getChildren().add(addAns);
    incorrectButtons.getChildren().add(removeAns);
    incorrectTVB.getChildren().add(incorrectAnswers);
    incorrectBox.getChildren().add(incorrectL);
    incorrectBox.getChildren().add(incorrectTVB);
    root.getChildren().add(incorrectBox);
    HBox lowerButtons = new HBox(20);
    lowerButtons.getChildren().add(new SwapScreen("Back", Main.windows[0], stage));
    Button addQ = new Button("Add Question");
    addQ.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) {
        String questionTopic;
        try {
          if (!Main.topic.getValue().equals("Other")) // if the user has selected a topic
            questionTopic = Main.topic.getValue();
          else { // the user has put in a new topic
            questionTopic = otherText.getText();
          }
          answerMap.put(correctT.getText(), true);
          Question newQuestion = new Question(questionTopic, question.getText(), answerMap);
          Main.questionList.addQuestion(newQuestion);
          Main.numQ++;
          stage.setScene(Main.windows[3].getScene());
        } catch (Exception e) {
          Label error = new Label("Please enter a valid question");
          lowerButtons.getChildren().add(error);
        }
      }
    });
    lowerButtons.getChildren().add(addQ);
    root.getChildren().add(lowerButtons);
    return scene;
  }

}
