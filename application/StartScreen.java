/**
 * Filename:   StartScreen.java
 * Class: 		 CS 400, Spring 2019
 * Project:    Final Team Project
 * Due Date:   April 25, 2019
 * Authors:    Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
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
 * Implements the welcoming screen
 */
public class StartScreen implements Window{
	private Stage stage;
	
	public StartScreen(Stage stage) {
		this.stage = stage;
	}

  @Override
  public Scene getScene() {
    VBox root = new VBox(100);
    root.setAlignment(Pos.CENTER);
    Insets border = new Insets(10);
    Scene scene = new Scene(root,800,600);
    
    Label welcome = new Label("Welcome! Please add or load questions.");
    welcome.setFont(Font.font(20));
   
    root.getChildren().add(welcome);
    VBox.setMargin(welcome, border);
    HBox buttons = new HBox(200);
    buttons.setAlignment(Pos.CENTER);
    buttons.getChildren().add(new Button("Add Questions",Main.windows[1],stage));
    buttons.getChildren().add(new Button("Load Questions",Main.windows[2],stage));
    root.getChildren().add(buttons);
    return scene;
  }

}
