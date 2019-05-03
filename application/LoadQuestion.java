/**
 * Filename: LoadQuestion.java
 * Class: CS 400, Spring 2019 
 * Project: Final Team Project 
 * Due Date: May 2, 2019 
 * Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A place where questions can be loaded in from a file
 *
 */
public class LoadQuestion implements Window{
	private Stage stage;
	
	/**
	 * Constructor, sets current stage
	 * @param stage
	 */
	public LoadQuestion(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {
		VBox root = new VBox(20);
	    root.setPadding(new Insets(10, 25, 25, 25));
	    root.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
	    root.setSpacing(10);
	    Scene scene = new Scene(root, 800, 600);
	    // HEADER
	    Label loadHeader = new Label("Load Questions From File");
	    loadHeader.setFont(Config.SIZE24);
		root.getChildren().add(loadHeader);
		// DESCRIPTION
		Label loadDesc = new Label(".json");
		loadDesc.setFont(Config.SIZE14);
		// LOAD QUESTION FILE
		HBox fileBox = new HBox(20);
		Label fileLabel = new Label("Filename:");
		fileLabel.setFont(Config.SIZE14);
		fileBox.getChildren().add(fileLabel);
		TextArea filename = new TextArea();
		fileBox.getChildren().add(filename);
		fileBox.setPrefHeight(10);
		fileBox.getChildren().add(loadDesc);
		root.getChildren().add(fileBox);
		Button loadButton = new Button("Load");
		Label msg = new Label();
		// Attempting to load the entered filename
		loadButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) {
				try {
					String fn = filename.getText();
					if (!fn.endsWith(".json"))
						fn += ".json";
					Main.questionList.Load(fn);
					msg.setText(fn+" successfully loaded!");
				} catch(Exception e) {
					msg.setText("Please enter a valid filename");
				}
			}
		});
		fileBox.getChildren().add(loadButton);
		root.getChildren().add(new SwapScreen("Back",Main.windows[0],stage));
		root.getChildren().add(msg);
		return scene;
	}

}
