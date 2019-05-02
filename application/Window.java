//
// Filename: Window.java
// Class: CS 400, Spring 2019
// Project: Final Team Project
// Due Date: May 2, 2019
// Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto
// Baier
//
package application;

import javafx.scene.Scene;

/**
 * This is the window interface that all of the window classes implement. This holds the scene for
 * all the windows and is used in the Main class to keep a list of all the windows together.
 *
 */
public interface Window {
  Scene getScene();
}
