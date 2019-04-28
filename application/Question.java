package application;

import javafx.scene.image.Image;

public class Question {
  private String topic;
  private String question;
  private Image pic;
  private String[] correctAnswers;
  private String[] incorrectAnswers;
  private String metaData;
 
  public Question(String topic, String question, String[] correctAnswers, 
      String[] incorrectAnswers) {
    this.topic = topic;
    this.question = question;
    this.correctAnswers = correctAnswers;
    this.incorrectAnswers = incorrectAnswers;
    pic = null;
  }
  
  public Question(String topic, String question, String[] correctAnswers, String[] incorrectAnswers, 
      String imageFileName) {
    this.topic = topic;
    this.question = question;
    this.correctAnswers = correctAnswers;
    this.incorrectAnswers = incorrectAnswers;
    this.pic = new Image(imageFileName);
  }
  
  public Question(String topic, String question, String[] correctAnswers, String[] incorrectAnswers, 
      String imageFileName, String metaData) {
    this.topic = topic;
    this.question = question;
    this.correctAnswers = correctAnswers;
    this.incorrectAnswers = incorrectAnswers;
    this.pic = new Image(imageFileName);
    this.metaData = metaData;
  }
}
