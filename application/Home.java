//
// Filename: QuestionAdded.java
// Class: CS 400, Spring 2019
// Project: Final Team Project
// Due Date: May 2, 2019
// Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto
// Baier
//
package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class is the start screen of the program. This window displays how many questions have been
 * currently added to the program and in addition to the "Add Questions" and "Load Questions"
 * buttons, a "Generate Quiz" button is also available. Below these buttons are buttons that allow
 * the user the save their current questions to a file or to exit the program.
 */
public class Home implements Window {
  private Stage stage;

  /**
   * Initializes Home's stage field.
   * 
   * @param stage
   */
  public Home(Stage stage) {
    this.stage = stage;
  }

  /**
   * Displays the window for the user to interact with. Shows the number of current questions
   * loaded. Also has 5 buttons available for the user to interact with. Add Question brings the
   * user to the Add Question screen, Load Questions brings the user to a screen to load questions
   * from a file, create Quiz allows the user to begin creating a quiz from the current questions,
   * the save allows the user to save their questions and exit exits the program.
   * 
   * @see application.Window#getScene()
   */
  @Override
  public Scene getScene() {
    // window is a VBox root layout
    VBox root = new VBox(50);
    root.setAlignment(Pos.CENTER);
    Scene scene = new Scene(root, 800, 600);
    root.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    // Title for the home page
    Label title = new Label("Quiz Generator");
    title.setFont(Config.TITLE);
    Paint cyan = Color.DARKCYAN;
    title.setTextFill(cyan);
    root.getChildren().add(title);
    // The header that displays number of current questions
    Label currentNum = new Label(
        "Currently you have " + Main.questionList.getNumOfQuestions() + " question(s) loaded");
    currentNum.setFont(Font.font(20));
    root.getChildren().add(currentNum);
    //VBox.setMargin(currentNum, border); // formats question to be in center
    HBox buttons = new HBox(50); // layout for the buttons
    // creates "Add Questions" button for the screen
    SwapScreen AddQButton = new SwapScreen("Add Questions", Main.windows[1], stage);
    AddQButton.setPrefSize(200, 100);
    AddQButton.setFont(Config.SIZE14);
    // creates "Load Questions" button for the screen
    SwapScreen LoadQButton = new SwapScreen("Load Questions", Main.windows[2], stage);
    LoadQButton.setPrefSize(200, 100);
    LoadQButton.setFont(Config.SIZE14);
    // dark grey background for disabled buttons
    BackgroundFill grey = new BackgroundFill(Color.DARKGRAY,null,null);
    Background disable = new Background(grey); 
    // creates "Take Quiz" button for the screen
    SwapScreen TakeQButton = new SwapScreen("Take a Quiz", Main.windows[3], stage);
    TakeQButton.setPrefSize(200, 100);
    TakeQButton.setFont(Config.SIZE14);
    if(Main.questionList.getNumOfQuestions() == 0) {
      TakeQButton.setBackground(disable);
      TakeQButton.setOnAction(null);
    }
    buttons.setAlignment(Pos.CENTER);
    // makes another row below for the save and quit buttons
    HBox options = new HBox(50);
    // create quit button
    SwapScreen quitButton = new SwapScreen("Quit", Main.windows[4], stage);
    quitButton.setPrefSize(100, 50);
    quitButton.setFont(Config.SIZE14);
    // create save button
    SwapScreen saveButton = new SwapScreen("Save", Main.windows[6], stage);
    saveButton.setPrefSize(100, 50);
    saveButton.setFont(Config.SIZE14);
    if(Main.questionList.getNumOfQuestions() == 0) {
      saveButton.setBackground(disable);
      saveButton.setOnAction(null);
    }
    // add save and quit in same HBox
    options.getChildren().add(saveButton);
    options.getChildren().add(quitButton);
    options.setAlignment(Pos.CENTER);
    // display buttons on screen
    buttons.getChildren().add(AddQButton);
    buttons.getChildren().add(LoadQButton);
    buttons.getChildren().add(TakeQButton);
    root.getChildren().add(buttons);
    root.getChildren().add(options);
    return scene;
  }
}
