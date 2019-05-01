//
// Filename: QuestionAdded.java
// Class: CS 400, Spring 2019
// Project: Final Team Project
// Due Date: April 25, 2019
// Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto
// Baier
//
package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class is similar to the start screen but with more features. This window only shows once a
 * question has been added to the program. This window displays how many questions have been
 * currently added to the program and in addition to the "Add Questions" and "Load Questions"
 * buttons, a "Generate Quiz" button is also available.
 */
public class QuestionAdded implements Window {
  private Stage stage;

  public QuestionAdded(Stage stage) {
    this.stage = stage;
  }

  @Override
  public Scene getScene() {
    VBox root = new VBox(100);
    root.setAlignment(Pos.CENTER);
    Insets border = new Insets(10);
    Scene scene = new Scene(root, 800, 600);    
    Label currentNum = new Label("Currently you have "+ Main.numQ +" question(s) loaded");
    currentNum.setFont(Font.font(20));

    root.getChildren().add(currentNum);
    VBox.setMargin(currentNum, border);
    HBox buttons = new HBox(50);
    SwapScreen TakeQButton = new SwapScreen("Take a Quiz", Main.windows[4], stage);
    TakeQButton.setPrefSize(200, 100);
    TakeQButton.setFont(Config.SIZE14);
    Main.buttonList.add(TakeQButton);
    buttons.setAlignment(Pos.CENTER);
    buttons.getChildren().add(Main.buttonList.get(0));
    buttons.getChildren().add(Main.buttonList.get(1));
    buttons.getChildren().add(Main.buttonList.get(2));
    root.getChildren().add(buttons);
    return scene;
  }

}
