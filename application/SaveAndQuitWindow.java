package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SaveAndQuitWindow implements Window{
	private Stage stage;

	/**
	 * Constructor, sets current stage
	 * @param stage
	 */
	public SaveAndQuitWindow(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {
		VBox root = new VBox(20); // main node, houses all other nodes
		root.setPadding(new Insets(10, 25, 25, 25));
		root.setSpacing(10);
		Scene scene = new Scene(root, 800, 600);
		// PROMPT
		Label saveHeader = new Label("Enter File Name");
		saveHeader.setFont(Config.SIZE24);
		root.getChildren().add(saveHeader);
		// FILE EXTENSION -- so user knows not to enter ".json"
		Label extension = new Label(".json");
		extension.setFont(Config.SIZE14);
		// LOAD QUESTION FILE
		HBox fileBox = new HBox(20); 
		Label fileLabel = new Label("Filename:"); // prompt for filename
		fileLabel.setFont(Config.SIZE14); 
		fileBox.getChildren().add(fileLabel);
		TextArea filename = new TextArea(); // where the user can enter the filename
		filename.setPrefHeight(10);
		fileBox.getChildren().add(filename);
		fileBox.setPrefHeight(10);
		fileBox.getChildren().add(extension);
		Button saveButton = new Button("Save and Quit");
		fileBox.getChildren().add(saveButton);
		root.getChildren().add(fileBox);
		Label msg = new Label();
		// save questions under filename
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent t) { 
				if (filename.getText().isEmpty())
					msg.setText("Please enter a file name.");
				else if (Main.questionList.getNumOfQuestions() == 0)
					msg.setText("No questions to save");
				else {
					try {
						Main.questionList.Save(filename.getText().concat(".json"));
						stage.close();
					} catch (IOException e) {
						msg.setText("Unable to save to file: "+filename.getText().concat(".json"));
					}
				}
			}
		});
		root.getChildren().add(new SwapScreen("Back",Main.windows[4],stage));
        root.getChildren().add(msg);
		return scene;
	}
}
