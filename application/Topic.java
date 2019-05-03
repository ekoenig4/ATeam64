//
// Filename: Topic.java
// Class: CS 400, Spring 2019
// Project: Final Team Project
// Due Date: May 2, 2019
// Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto
// Baier
//
package application;

import java.util.ArrayList;

/**
 * This class creates a topic with an ArrayList of questions associated with it.
 *
 */
public class Topic {
  private String topicName; // stores the topic's name
  private ArrayList<Question> questions; // stores all the questions with this topic's name

  /**
   * Constructor to create a new topic and initialize it to an empty list
   * 
   * @param topicName Name for the new topic
   */
  public Topic(String topicName) {
    this.topicName = topicName;
    questions = new ArrayList<>();
  }

  /**
   * Getter for the topic's name
   * 
   * @return The name of the topic
   */
  public String getTopicName() {
    return topicName;
  }

  /**
   * Add a question to the ArrayList of questions associated to the topic
   * 
   * @param question The question to add to the topic
   */
  public void addQuestion(Question question) {
    questions.add(question);
  }

  /**
   * Gets all the questions for the topic
   * 
   * @return An arrayList of questions
   */
  public ArrayList<Question> getQuestions() {
    return questions;
  }
  
  public Boolean hasQuestion(Question question) {
  	for (Question q : questions)
  		if (q.getQuestion().equals(question.getQuestion()))
  			return true;
  	return false;
  }
}
