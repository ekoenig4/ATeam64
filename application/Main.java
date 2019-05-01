/**
 * Filename: Main.java Class: CS 400, Spring 2019 Project: Final Team Project Due Date: April 25,
 * 2019 Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto
 * Baier
 */

package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
  public static HBox topicBox;
  public static ArrayList<Button> buttonList;
  public static ObservableList<String> topics;
  public static ComboBox<String> topic;
  public static QuestionList questionList;
  // Button List:
  // 0: Add Question
  // 1: Load Question
  public static int numQ = 0;



  @Override
  public void start(Stage primaryStage) {
    windows = new Window[] {new StartScreen(primaryStage), new AddQuestion(primaryStage),
        new LoadQuestion(primaryStage), new QuestionAdded(primaryStage),
        new CreateQuiz(primaryStage),};

    topics = FXCollections.observableArrayList("Other");

    topicBox = new HBox(20);
    Label topicPrompt = new Label("Topic:");
    topicPrompt.setFont(Config.SIZE14);
    topicBox.getChildren().add(topicPrompt);
    topic = new ComboBox<String>(topics);
    topicBox.getChildren().add(topic);
    buttonList = new ArrayList<Button>();
    questionList = new QuestionList();

    try {
      BorderPane root = new BorderPane();

      Scene scene = new Scene(root, 400, 400);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(windows[0].getScene()); // set scene to start screen
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
