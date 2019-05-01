/**
 * Filename: QuestionAdded.java
 * Class: CS 400, Spring 2019 
 * Project: Final Team Project 
 * Due Date: May 2, 2019 
 * Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
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
public class Home implements Window {
  private Stage stage;

  public Home (Stage stage) {
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
    
    SwapScreen AddQButton = new SwapScreen("Add Questions", Main.windows[1], stage);
    AddQButton.setPrefSize(200, 100);
    AddQButton.setFont(Config.SIZE14);
    
    SwapScreen LoadQButton = new SwapScreen("Load Questions", Main.windows[2], stage);
    LoadQButton.setPrefSize(200, 100);
    LoadQButton.setFont(Config.SIZE14);
    
    HBox buttons = new HBox(50);
    SwapScreen TakeQButton = new SwapScreen("Take a Quiz", Main.windows[3], stage);
    TakeQButton.setPrefSize(200, 100);
    TakeQButton.setFont(Config.SIZE14);
    buttons.setAlignment(Pos.CENTER);
    
    SwapScreen quitButton = new SwapScreen("Quit", Main.windows[4], stage);
    quitButton.setPrefSize(100, 50);
    quitButton.setFont(Config.SIZE14);
    
    buttons.getChildren().add(AddQButton);
    buttons.getChildren().add(LoadQButton);
    buttons.getChildren().add(TakeQButton);
    root.getChildren().add(buttons);
    root.getChildren().add(quitButton);
    return scene;
  }

}
