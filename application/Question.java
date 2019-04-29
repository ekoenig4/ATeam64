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

	public String[] getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(String[] correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public String[] getIncorrectAnswers() {
		return incorrectAnswers;
	}

	public void setIncorrectAnswers(String[] incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}
}
