/**
 * Filename: Main.java
 * Class: CS 400, Spring 2019 
 * Project: Final Team Project 
 * Due Date: May 2, 2019 
 * Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */

package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

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
  public static HBox topicBox; // HBox to display the topic label and combobox 
  public static ObservableList<String> topics; // list of topics to display
  public static ComboBox<String> topic; // the ComboBox that shows all the topics
  public static QuestionList questionList; // stores all topics, which themselves store questions

  
  @Override
  public void start(Stage primaryStage) {
    windows = new Window[] {new Home(primaryStage), new AddQuestion(primaryStage),
        new LoadQuestion(primaryStage), 
        new CreateQuiz(primaryStage), new QuitWindow(primaryStage), 
        new SaveAndQuitWindow(primaryStage), new SaveWindow(primaryStage)};
    
    topics = FXCollections.observableArrayList(); // empty topic list, "Other" is added when used

    topicBox = new HBox(20); // HBox to display the topic label and combobox
    Label topicPrompt = new Label("Topic:");
    topicPrompt.setFont(Config.SIZE14);
    topicBox.getChildren().add(topicPrompt);
    topic = new ComboBox<String>(topics);
    topicBox.getChildren().add(topic);
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
