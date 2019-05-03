/**
 * Filename: Config.java
 * Class: CS 400, Spring 2019 
 * Project: Final Team Project 
 * Due Date: May 2, 2019 
 * Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
package application;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * This class contains the constants used in the JavaFX screens.
 * 
 * @author Angelique Stepanenkov and Otto Baier
 */
public class Config {
  // Font sizes used in program
  public static final Font SIZE24 = Font.font(20); // font size of title headers
  public static final Font SIZE14 = Font.font(14); // font size of subheaders
  public static final Font BOLD18 = Font.font("Arial", FontWeight.BOLD, 18); // bold subheader
  public static final Font TITLE = Font.font("Rockwell", FontWeight.EXTRA_BOLD, 
      FontPosture.ITALIC, 80);
  // Backgrounds
  // Gradient background for all windows
  public static final Background GRADIENT  = new Background(new BackgroundFill(new LinearGradient
      (0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop[] { new Stop(0, Color.LIGHTSKYBLUE), new 
          Stop(1, Color.AQUAMARINE)}), CornerRadii.EMPTY, Insets.EMPTY));
  // Greyed out button color
  public static final Background DISABLED_BUTTON = new Background(new BackgroundFill(Color.DARKGRAY, 
      null, null));
}

