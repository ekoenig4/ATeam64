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

public class QuitWindow implements Window {
  private Stage stage;

  /**
   * Constructor, sets current stage
   * 
   * @param stage
   */
  public QuitWindow(Stage stage) {
    this.stage = stage;
  }

  @Override
  public Scene getScene() {
    Label sure = new Label("Are you sure you want to quit?"); // prompt
    sure.setFont(Font.font(20));
    
    Button quit = new Button("Quit Without Saving"); // button that closes the window w/o saving
    quit.setPrefSize(150, 75);
    quit.setFont(Config.SIZE14);
    quit.setOnMouseClicked(new EventHandler<MouseEvent>() { // set button behavior
      @Override
      public void handle(MouseEvent e) {
        stage.close();
      }
    });
    
    SwapScreen saveAndQuit = new SwapScreen("Save and Quit", Main.windows[5], stage);
    saveAndQuit.setPrefSize(150, 75);
    saveAndQuit.setFont(Config.SIZE14);
    if (Main.questionList.getNumOfQuestions() == 0) { // disable this button if no questions
      saveAndQuit.setBackground(Config.DISABLED_BUTTON);
      saveAndQuit.setOnAction(null);
    }

    SwapScreen back = new SwapScreen("Back", Main.windows[0], stage); // back button
    back.setPrefSize(150, 75);
    back.setFont(Config.SIZE14);

    HBox options = new HBox(40); // houses all buttons on this screen
    options.setAlignment(Pos.CENTER);
    options.getChildren().add(quit);
    options.getChildren().add(saveAndQuit);
    options.getChildren().add(back);
    
    VBox root = new VBox(80); // houses prompt and buttons
    root.setAlignment(Pos.CENTER);
    root.getChildren().add(sure);
    root.getChildren().add(options);
    root.setBackground(Config.GRADIENT);
    
    Scene scene = new Scene(root, 800, 600);
    return scene;
  }

}
