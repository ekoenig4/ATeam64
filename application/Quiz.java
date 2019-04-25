/**
 * Filename:   StartScreen.java
 * Class:        CS 400, Spring 2019
 * Project:    Final Team Project
 * Due Date:   April 25, 2019
 * Authors:    Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Implements the welcoming screen
 */
public class Quiz implements Window{
    private Stage stage;
    
    public Quiz(Stage stage) {
        this.stage = stage;
    }

  @Override
  public Scene getScene() {
    VBox root = new VBox(100);
    root.setAlignment(Pos.CENTER);
    Insets border = new Insets(10);
    Scene scene = new Scene(root,800,600);
    
    Label quizHeader = new Label("QUIZ");
    quizHeader.setFont(Config.SIZE24);
   
    root.getChildren().add(quizHeader);
    VBox.setMargin(quizHeader, border);
    return scene;
  }
}
