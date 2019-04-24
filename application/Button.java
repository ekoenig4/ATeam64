package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

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
