/**
 * Filename: Main.java
 * Class: CS 400, Spring 2019 
 * Project: Final Team Project 
 * Due Date: May 2, 2019 
 * Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */

package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Runs the program and deals with user input, displays different windows based on the user's inputs
 *
 */
public class Main extends Application {
  public static Window[] windows; // stores all windows that will be used
  // 0: Home
  // 1: AddQuestion
  // 2: LoadQuestion
  // 3: CreateQuiz
  // 4: QuitWindow
  // 5: SaveAndQuitWindow
  public static QuestionList questionList; // stores all topics, which themselves store questions

  
  @Override
  public void start(Stage primaryStage) {
    windows = new Window[] {new HomeWindow(primaryStage), new AddQuestionWindow(primaryStage),
        new LoadQuestionWindow(primaryStage), 
        new CreateQuizWindow(primaryStage), new QuitWindow(primaryStage), 
        new SaveAndQuitWindow(primaryStage), new SaveWindow(primaryStage)};
    
    questionList = new QuestionList();

    try {
      BorderPane root = new BorderPane();
      Scene scene = new Scene(root, 400, 400);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(windows[0].getScene()); // set scene to start screen
      primaryStage.setTitle("Quiz Maker");
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
