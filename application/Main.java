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
import javafx.scene.layout.BorderPane;

/**
 * Runs the program and deals with user input, displays different windows based on the user’s inputs
 *
 */
public class Main extends Application {
	public static Window[] windows;
	
	@Override
	public void start(Stage primaryStage) {
		windows = new Window[] {new StartScreen(primaryStage),new AddQuestion(primaryStage), new LoadQuestion(primaryStage)};
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
