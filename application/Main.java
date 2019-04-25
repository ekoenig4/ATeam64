/**
 * Filename:   Main.java
 * Class: 		 CS 400, Spring 2019
 * Project:    Final Team Project
 * Due Date:   April 25, 2019
 * Authors:    Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */

package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Runs the program and deals with user input, displays different windows based on the user�s inputs
 *
 */
public class Main extends Application {
	public static Window[] windows;
	public static HBox topicBox;

	
	@Override
	public void start(Stage primaryStage) {
		windows = new Window[] {new StartScreen(primaryStage),
		    new AddQuestion(primaryStage), 
		    new LoadQuestion(primaryStage),
		    new QuestionAdded(primaryStage),
		    new CreateQuiz(primaryStage),
		    new Quiz(primaryStage)};
		topicBox = new HBox(20);
	    Label topicPrompt = new Label("Topic:");
	    topicPrompt.setFont(Config.SIZE14);
	    topicBox.getChildren().add(topicPrompt);
	    topicBox.getChildren().add(new ComboBox());
		try {
			BorderPane root = new BorderPane();
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(windows[0].getScene());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
