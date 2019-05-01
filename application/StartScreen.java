/**
 * Filename:   StartScreen.java
 * Class: 		 CS 400, Spring 2019
 * Project:    Final Team Project
 * Due Date:   April 25, 2019
 * Authors:    Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    // HEADER
    Label welcome = new Label("Welcome! Please add or load questions.");
    welcome.setFont(Config.SIZE24);
    root.getChildren().add(welcome);
    VBox.setMargin(welcome, border);
    HBox buttons = new HBox(200);
    buttons.setAlignment(Pos.CENTER);
    SwapScreen AddQButton = new SwapScreen("Add Questions", Main.windows[1], stage);
    AddQButton.setPrefSize(200, 100);
    AddQButton.setFont(Config.SIZE14);
    Main.buttonList.add(AddQButton);
    SwapScreen LoadQButton = new SwapScreen("Load Questions", Main.windows[2], stage);
    LoadQButton.setPrefSize(200, 100);
    LoadQButton.setFont(Config.SIZE14);
    Main.buttonList.add(LoadQButton);
    Button quitButton = new Button("Quit");
    quitButton.setPrefSize(100, 50);
    quitButton.setFont(Config.SIZE14);
    quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override 
      public void handle(MouseEvent e) { 
         stage.close();
      }
    });
    Main.buttonList.add(quitButton);
    buttons.getChildren().add(Main.buttonList.get(0));
    buttons.getChildren().add(Main.buttonList.get(1));
    root.getChildren().add(buttons);
    root.getChildren().add(Main.buttonList.get(2));
    return scene;
  }

}
