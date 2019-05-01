package application;

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
    VBox root = new VBox(20);
    root.setPadding(new Insets(10, 25, 25, 25));
    root.setSpacing(10);
    Scene scene = new Scene(root, 500, 150);
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
    root.getChildren().add(fileBox);
    Button saveButton = new Button("Save and Quit");
    // Attempting to load the entered filename
    saveButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent t) { 
          Main.questionList.Save(filename.getText());
          stage.close();
        }
    });
    fileBox.getChildren().add(saveButton);
    root.getChildren().add(new SwapScreen("Back",Main.windows[4],stage));
    return scene;
  }
}
