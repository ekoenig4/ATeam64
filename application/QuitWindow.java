package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class QuitWindow implements Window{
  private Stage stage;
  
  /**
   * Constructor, sets current stage
   * @param stage
   */
  public QuitWindow(Stage stage) {
      this.stage = stage;
  }
  
  @Override
  public Scene getScene() {
    VBox root = new VBox(80);
    root.setAlignment(Pos.CENTER);
    HBox options = new HBox(40);
    options.setAlignment(Pos.CENTER);
    Button quit = new Button("Quit Without Saving");
    quit.setPrefSize(150, 75);
    quit.setFont(Config.SIZE14);
    quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override 
      public void handle(MouseEvent e) { 
         stage.close();
      }
    });
    
    SwapScreen saveAndQuit = new SwapScreen("Save and Quit", Main.windows[5], stage);
    saveAndQuit.setPrefSize(150, 75);
    saveAndQuit.setFont(Config.SIZE14);
    
    SwapScreen back = new SwapScreen("Back", Main.windows[0], stage);
    back.setPrefSize(150, 75);
    back.setFont(Config.SIZE14);
    
    options.getChildren().add(quit);
    options.getChildren().add(saveAndQuit);
    options.getChildren().add(back);
    Label sure = new Label("Are you sure you want to quit?");
    sure.setFont(Font.font(20));
    root.getChildren().add(sure);
    root.getChildren().add(options);
    Scene scene = new Scene(root, 800, 600);  
    
    return scene;
  }

}
