/**
 * Filename: Question.java
 * Class: CS 400, Spring 2019 
 * Project: Final Team Project 
 * Due Date: May 2, 2019 
 * Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto Baier
 */
package application;

import java.util.HashMap;

import javafx.scene.image.Image;

/**
 * This class holds all the information for a specific question
 */
public class Question {
  private String topic;
  private String question;
  private String imageFileName;
  private Image pic;
  private HashMap<String,Boolean> answers;
  private String metaData;
 
  /**
   * Create a question without an image
   * @param topic is the topic of the question
   * @param question is the question's text
   * @param answers is a HashMap of answers
   */
  public Question(String topic, String question, HashMap<String,Boolean> answers) {
    this.topic = topic;
    this.question = question;
    this.answers = answers;
    this.imageFileName = "none";
    pic = null;
  }
  
  /**
   * Create a question with an image
   * @param topic is the topic of the question
   * @param question is the question's text
   * @param answers is a HashMap of answers
   * @param imageFileName is the path to the image
   * @param metaData
   */
  public Question(String topic, String question, HashMap<String,Boolean> answers, 
      String imageFileName, String metaData) {
    this.topic = topic;
    this.question = question;
    this.answers = answers;
    this.imageFileName = imageFileName;
    try { // Try to load the image
    this.pic = !this.imageFileName.equals("none") ? new Image(imageFileName) : null;
    } catch(IllegalArgumentException e) { // If unable, set pic to null
    	this.pic = null;
    }
    this.metaData = metaData;
  }

	public String getTopic() {
		return topic;
	}

	public String getQuestion() {
		return question;
	}

	public Image getPic() {
		return pic;
	}

	public String getMetaData() {
		return metaData;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public HashMap<String,Boolean> getAnswers() {
		return answers;
	}
}
