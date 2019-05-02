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

public class SaveWindow implements Window{
  private Stage stage;
  
  /**
   * Constructor, sets current stage
   * @param stage
   */
  public SaveWindow(Stage stage) {
      this.stage = stage;
  }

  @Override
  public Scene getScene() {
    VBox root = new VBox(20);
    root.setPadding(new Insets(10, 25, 25, 25));
    root.setSpacing(10);
    Scene scene = new Scene(root, 800, 600);
    // HEADER
    Label saveHeader = new Label("Enter File Name");
    saveHeader.setFont(Config.SIZE24);
    root.getChildren().add(saveHeader);
    // DESCRIPTION
    Label loadDesc = new Label(".json");
    loadDesc.setFont(Config.SIZE14);
    // LOAD QUESTION FILE
    HBox fileBox = new HBox(20);
    Label fileLabel = new Label("Filename:");
    fileLabel.setFont(Config.SIZE14);
    fileBox.getChildren().add(fileLabel);
    TextArea filename = new TextArea();
    filename.setPrefWidth(250);
    fileBox.getChildren().add(filename);
    fileBox.setPrefHeight(10);
    fileBox.getChildren().add(loadDesc);
    Button saveButton = new Button("Save");
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
  				    stage.setScene(Main.windows[0].getScene());
  					} catch (IOException e) {
  						msg.setText("Unable to save to file: "+filename.getText().concat(".json"));
  					}
  				}
        }
    });
    root.getChildren().add(new SwapScreen("Back",Main.windows[0],stage));
    root.getChildren().add(msg);
    return scene;
  }
}
