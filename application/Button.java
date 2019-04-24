/**
 * Filename:   Button.java
 * Class: 		 CS 400, Spring 2019
 * Project:    Final Team Project
 * Due Date:   April 25, 2019
 * Authors:    Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */

package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Holds the name of the button and the state that the button leads to
 * 
 */
public class Button extends javafx.scene.control.Button{
	public Button(String name,Window state,Stage stage) {
		super(name);
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				stage.setScene(state.getScene());
			}
		});
	}
}
