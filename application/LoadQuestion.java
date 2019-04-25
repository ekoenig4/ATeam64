/**
 * Filename:   LoadQuestion.java
 * Class: 		 CS 400, Spring 2019
 * Project:    Final Team Project
 * Due Date:   April 25, 2019
 * Authors:    Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
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
	    root.setSpacing(10);
	    Scene scene = new Scene(root, 800, 600);
	    // HEADER
	    Label loadHeader = new Label("Load Questions From File");
	    loadHeader.setFont(Config.SIZE24);
		root.getChildren().add(loadHeader);
		// DESCRIPTION
		Label loadDesc = new Label("Please load a valid JSON format file");
		loadDesc.setFont(Config.SIZE14);
		root.getChildren().add(loadDesc);
		// LOAD QUESTION FILE
		HBox fileBox = new HBox(20);
		Label fileLabel = new Label("Filename:");
		fileLabel.setFont(Config.SIZE14);
		fileBox.getChildren().add(fileLabel);
		fileBox.getChildren().add(new TextArea());
		fileBox.setPrefHeight(10);
		root.getChildren().add(fileBox);
		fileBox.getChildren().add(new Button("Load",Main.windows[3],stage));
		root.getChildren().add(new Button("Back",Main.windows[0],stage));
		return scene;
	}

}
