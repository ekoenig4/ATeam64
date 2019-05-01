package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Results implements Window {
  private Stage stage;

  public Results(Stage stage) {
    this.stage = stage;
  }
  
  @Override
  public Scene getScene() {
    // initialize window
    VBox root = new VBox(20);
    root.setPadding(new Insets(10, 25, 25, 25));
    root.setSpacing(10);
    Scene scene = new Scene(root, 800, 600);   
    // result header label
    Label resultHeader = new Label("Results");
    resultHeader.setFont(Config.SIZE24);
    root.getChildren().add(resultHeader);
    //
    
    
//    buttons.getChildren().add(AddQButton);
//    buttons.getChildren().add(LoadQButton);
//    buttons.getChildren().add(TakeQButton);
//    root.getChildren().add(buttons);
//    root.getChildren().add(quitButton);
    return scene;
  }
  
}
