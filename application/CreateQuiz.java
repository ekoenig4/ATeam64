/**
 * Filename: CreateQuiz.java Class: CS 400, Spring 2019 Project: Final Team Project Due Date: April
 * 25, 2019 Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer,
 * Otto Baier
 */
package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
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

  public CreateQuiz(Stage stage) {
    this.stage = stage;
  }

  @Override
  public Scene getScene() {
    VBox root = new VBox(20);
    root.setPadding(new Insets(10, 25, 25, 25));
    root.setSpacing(10);
    Scene scene = new Scene(root, 800, 600);
    // HEADER
    Label quizHeader = new Label("Create Quiz");
    quizHeader.setFont(Config.SIZE20);
    root.getChildren().add(quizHeader);
    root.getChildren().add(Main.topicBox);
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
    buttons.getChildren().add(new Button("Back", Main.windows[3], stage));
    buttons.getChildren().add(new Button("Generate Quiz", Main.windows[5], stage));
    root.getChildren().add(buttons);
    return scene;
  }

}
