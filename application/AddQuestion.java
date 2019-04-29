/**
 * Filename:   AddQuestion.java
 * Class: 		 CS 400, Spring 2019
 * Project:    Final Team Project
 * Due Date:   April 25, 2019
 * Authors:    Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    // will eventually give the option of adding a greater amount of incorrect answers instead of max 4
    Button addAns = new Button("Add Incorrect Answer");
    incorrectTVB.getChildren().add(addAns);
    incorrectBox.getChildren().add(incorrectL);
    incorrectBox.getChildren().add(incorrectTVB);
    root.getChildren().add(incorrectBox);
    HBox lowerButtons = new HBox(20);
    lowerButtons.getChildren().add(new SwapScreen("Back", Main.windows[0], stage));
    lowerButtons.getChildren().add(new SwapScreen("Add Question", Main.windows[3], stage));
    root.getChildren().add(lowerButtons);
    return scene;
  }

}
