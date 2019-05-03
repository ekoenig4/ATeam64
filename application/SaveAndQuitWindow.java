package application;

import java.io.File;
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

public class SaveAndQuitWindow implements Window {
	private Stage stage;

	/**
	 * Constructor, sets current stage
	 * 
	 * @param stage
	 */
	public SaveAndQuitWindow(Stage stage) {
		this.stage = stage;
	}

	@Override
	public Scene getScene() {
		Label saveHeader = new Label("Enter File Name"); // prompt
		saveHeader.setFont(Config.SIZE24);

		Label extension = new Label(".json"); // file extension so user knows not to enter ".json"
		extension.setFont(Config.SIZE14);

		Label fileLabel = new Label("Filename:"); // prompt for filename
		fileLabel.setFont(Config.SIZE14);

		TextArea filename = new TextArea(); // where the user can enter the filename
		filename.setPrefHeight(10);

		Label msg = new Label(); // where an error message will go

		Button saveButton = new Button("Save and Quit");	
		saveButton.setOnAction(new EventHandler<ActionEvent>() { // set saveButton's behavior
			@Override
			public void handle(ActionEvent t) {
				if (filename.getText().trim().isEmpty()) // do not allow empty file name
					msg.setText("Please enter a file name.");
				else if (Main.questionList.getNumOfQuestions() == 0) // this shouldn't be reached
					msg.setText("No questions to save");
				else { // try to save the questions under given file name
					try {
						String fn = filename.getText().trim().concat(".json");
						File temp = new File(fn);
						if (temp.exists()) 
							msg.setText("File "+fn+" already exists. Please choose unique filename");
						else {
							Main.questionList.Save(fn);
							stage.close();
						}
					} catch (IOException e) {
						msg.setText("Unable to save to file: " + filename.getText().trim().concat(".json"));
					}
				}
			}
		});

		HBox fileBox = new HBox(20); // get all members of fileBox together
		fileBox.getChildren().add(fileLabel);
		fileBox.getChildren().add(filename);
		fileBox.getChildren().add(extension);
		fileBox.getChildren().add(saveButton);

		VBox root = new VBox(20); // main node, houses all other nodes
		root.setPadding(new Insets(10, 25, 25, 25));
		root.setSpacing(10);
		root.getChildren().add(saveHeader); // get all members of root together
		root.getChildren().add(fileBox); 
		root.getChildren().add(new SwapScreen("Back", Main.windows[4], stage));
		root.getChildren().add(msg);
		root.setBackground(Config.GRADIENT);

		Scene scene = new Scene(root, 800, 600);
		return scene;
	}
}
