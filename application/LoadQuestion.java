/**
 * Filename:   LoadQuestion.java
 * Class: 		 CS 400, Spring 2019
 * Project:    Final Team Project
 * Due Date:   April 25, 2019
 * Authors:    Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
package application;

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
		Scene scene = new Scene(root,800,600);
		root.getChildren().add(new Label("Load Question From File"));
		HBox fileBox = new HBox(20);
		fileBox.getChildren().add(new Label("     Filename:"));
		fileBox.getChildren().add(new TextArea());
		fileBox.setPrefHeight(10);
		root.getChildren().add(fileBox);
		fileBox.getChildren().add(new Button("Load",Main.windows[0],stage));
		root.getChildren().add(new Button("Back",Main.windows[0],stage));
		return scene;
	}

}
