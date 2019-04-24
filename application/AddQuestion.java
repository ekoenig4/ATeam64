package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
    Font header1 = Font.font(24);
    Font header2 = Font.font(14);
    // Header
    Label addQHeader = new Label("Add Question");
    addQHeader.setFont(header1);
    root.getChildren().add(addQHeader);
    // Topic HBox
    HBox topicBox = new HBox(20);
    HBox topicHB = new HBox(10); // groups topic label with dropdown menu
    Label topicPrompt = new Label("Topic:");
    topicPrompt.setFont(header2);
    topicHB.getChildren().add(topicPrompt);
    topicHB.getChildren().add(new ComboBox());
    topicBox.getChildren().add(topicHB);
    VBox otherTopicVB = new VBox(10); // groups other topic label with textbox
    Label otherPrompt =
        new Label("If you selected \"Other\", please type the name of the new topic below:");
    otherPrompt.setFont(header2);
    otherTopicVB.getChildren().add(otherPrompt);
    TextArea otherText = new TextArea(); // other topic text area
    otherText.setPrefHeight(50);
    otherTopicVB.getChildren().add(otherText);
    topicBox.getChildren().add(otherTopicVB);
    root.getChildren().add(topicBox);
    // Question text area
    Label questionPrompt = new Label("Enter question text below:");
    questionPrompt.setFont(header2);
    TextArea question = new TextArea();
    question.setPrefHeight(100);
    root.getChildren().add(questionPrompt);
    root.getChildren().add(question);
    // Answer text area
    HBox correctBox = new HBox(20); // correct answer hbox with label and text area
    Label correctL = new Label("Enter correct answer:");
    correctL.setFont(header2);
    TextArea correctT = new TextArea();
    correctT.setPrefHeight(50);
    correctBox.getChildren().add(correctL);
    correctBox.getChildren().add(correctT);
    root.getChildren().add(correctBox);
    root.getChildren().add(new Button("back", Main.windows[0], stage));
    return scene;
  }

}
