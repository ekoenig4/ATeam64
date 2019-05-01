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

public class Question {
  private String topic;
  private String question;
  private String imageFileName;
  private Image pic;
  private HashMap<String,Boolean> answers;
  private String metaData;
 
  public Question(String topic, String question, HashMap<String,Boolean> answers) {
    this.topic = topic;
    this.question = question;
    this.answers = answers;
    pic = null;
  }
  
  public Question(String topic, String question, 
  		HashMap<String,Boolean> answers ,String imageFileName) {
    this.topic = topic;
    this.question = question;
    this.answers = answers;
    this.imageFileName = imageFileName;
    this.pic = new Image(imageFileName);
  }
  
  public Question(String topic, String question, HashMap<String,Boolean> answers, 
      String imageFileName, String metaData) {
    this.topic = topic;
    this.question = question;
    this.answers = answers;
    this.imageFileName = imageFileName;
    this.pic = !this.imageFileName.equals("none") ? new Image(imageFileName) : null;
    this.metaData = metaData;
  }

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Image getPic() {
		return pic;
	}

	public void setPic(Image pic) {
		this.pic = pic;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public HashMap<String,Boolean> getAnswers() {
		return answers;
	}

	public void setAnswers(HashMap<String,Boolean> answers) {
		this.answers = answers;
	}
}
