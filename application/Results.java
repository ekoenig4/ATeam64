package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Results implements Window {
  private Stage stage;
  private int numCorrect;
  private int numQuestions;

  public Results(Stage stage, int numCorrect, int numQuestions) {
    this.stage = stage;
    this.numCorrect = numCorrect;
    this.numQuestions = numQuestions;

  }

  @Override
  public Scene getScene() {
    // initialize window
    VBox root = new VBox(20);
    root.setPadding(new Insets(10, 25, 25, 25));
    root.setSpacing(10);
    Scene scene = new Scene(root, 800, 600);
    // result header label
    Label resultHeader = new Label("Results");
    resultHeader.setFont(Config.SIZE24);
    root.getChildren().add(resultHeader);
    // create labels for % correct, num correct, and num questions
    Label correctPerc = new Label("Percentage correct: " + ((double) numCorrect / (double) numQuestions));
    Label numCorrectMsg = new Label("Number of Questions Correct: " + numCorrect);
    Label numQuestionMsg = new Label("Total Number of Questions in the Quiz: " + numQuestions);
    correctPerc.setFont(Config.BOLD18);
    numCorrectMsg.setFont(Config.BOLD18);
    numQuestionMsg.setFont(Config.BOLD18);
    root.getChildren().add(correctPerc);
    root.getChildren().add(numCorrectMsg);
    root.getChildren().add(numQuestionMsg);
    root.getChildren().add(new SwapScreen("Return to Home", Main.windows[0], stage));

    return scene;
  }

}
