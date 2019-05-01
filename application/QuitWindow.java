package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
    HBox root = new HBox(20);
    Button quit = new Button("Quit Without Saving");
    quit.setPrefSize(100, 50);
    quit.setFont(Config.SIZE14);
    quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override 
      public void handle(MouseEvent e) { 
         stage.close();
      }
    });
    
    SwapScreen saveAndQuit = new SwapScreen("Save and Quit", )
    
    root.getChildren().add(quit);
    
    return root;
  }

}
