package application;

import java.util.ArrayList;

public class Topic {
  private String topicName;
  private ArrayList<Question> questions;
  public Topic (String topicName) { this.topicName = topicName; }
  public String getTopicName() { return topicName; }
  public void addQuestion(Question question) { questions.add(question); }
  public int getNumQuestions() { return questions.size(); }
}
