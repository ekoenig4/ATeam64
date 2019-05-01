/**
 * Filename: CreateQuiz.java Class: CS 400, Spring 2019 Project: Final Team Project Due Date: April
 * 25, 2019 Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer,
 * Otto Baier
 */
package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Implements the welcoming screen
 */
public class CreateQuiz implements Window {
  private Stage stage;
  private ArrayList<String> quizTopics;
  private List<Question> quiz;

  public CreateQuiz(Stage stage) {
    this.stage = stage;
    quizTopics = new ArrayList<String>();
    quiz = new ArrayList<Question>();
    // setFieldsForTesting();
  }

  @Override
  public Scene getScene() {
    VBox root = new VBox(20);
    root.setPadding(new Insets(10, 25, 25, 25));
    root.setSpacing(10);
    Scene scene = new Scene(root, 800, 600);
    // HEADER
    Label quizHeader = new Label("Create Quiz");
    quizHeader.setFont(Config.SIZE24);
    root.getChildren().add(quizHeader);
    // TOPIC SELECTION
    HBox topicHB = new HBox(20);
    topicHB.getChildren().add(Main.topicBox);
    // allows user to add multiple topics to the quiz
    Button addTopic = new Button("Add Topic to Quiz");
    Label topicsAdded = new Label("Topics currently added: ");
    addTopic.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) {
        // can add any topic that is not "Other" and that is not already added
        if (!Main.topic.getValue().equals("Other") && !quizTopics.contains(Main.topic.getValue())) {
          String addedTopic = Main.topic.getValue();
          quizTopics.add(addedTopic);
          String topicsText = topicsAdded.getText();
          if (topicsText.equals("Topics currently added: "))
            topicsAdded.setText(topicsText + addedTopic);
          else
            topicsAdded.setText(topicsText + ", " + addedTopic);
        }
      }
    });
    topicHB.getChildren().add(addTopic);
    topicHB.getChildren().add(topicsAdded);
    root.getChildren().add(topicHB);


    // NUMBER OF QUESTIONS AREA
    HBox numQsHB = new HBox(10);
    Label numQsLabel = new Label("Enter number of questions for the quiz:");
    numQsLabel.setFont(Config.SIZE14);
    TextArea numQsTA = new TextArea();
    numQsTA.setPrefSize(100, 30);
    numQsHB.getChildren().add(numQsLabel);
    numQsHB.getChildren().add(numQsTA);
    root.getChildren().add(numQsHB);
    // decision buttons area
    HBox buttons = new HBox(20);
    buttons.getChildren().add(new SwapScreen("Back", Main.windows[3], stage));
    // buttons.getChildren().add(new SwapScreen("Generate Quiz", new Quiz(stage, this.quiz,
    // this.quiz.get(0), 0), stage));

    Button genQuiz = new Button("Generate Quiz");
    genQuiz.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) {
        // String questionTopic;
        // try {
        // if(!Main.topic.getValue().equals("Other"))
        // questionTopic = Main.topic.getValue();
        // else {
        // questionTopic = otherText.getText();
        // }
        // answerMap.put(correctT.getText(), true);
        // Question newQuestion = new Question(questionTopic, question.getText(), answerMap);
        // Main.questionList.addQuestion(newQuestion);
        // Main.numQ++;
        // stage.setScene(Main.windows[3].getScene());
        // } catch(Exception e) {
        // Label error = new Label("Please enter a valid question");
        // lowerButtons.getChildren().add(error);
        // }
      }
    });



    root.getChildren().add(buttons);
    return scene;
  }
  //
  //
  // public void setQuizTopics(ArrayList<String> topicList) {
  // this.quizTopics = topicList;
  // }
  //
  // public void makeQuiz(QuestionList questionList, int numQuestions) {
  //
  // ArrayList<ArrayList<Question>> allQuestionLists = new ArrayList<ArrayList<Question>>();
  // // Get list of topics from question list
  // for (String topic : questionList.allTopicNames()) {
  // if (this.quizTopics.contains(topic))
  // allQuestionLists.add(questionList.topicList.get(topic).getQuestions());
  // }
  // // Get List of Questions from the topics selected
  // List<Question> allQuestions = allQuestionLists.stream()
  // .flatMap(Collection::stream)
  // .collect(Collectors.toList());
  // // Use random stream of numbers to get random stream of questions
  // Random rand = new Random();
  // List<Question> quizQuestions = rand
  // .ints(0,allQuestions.size()) // random stream of integers between 0 and allQuestions - 1
  // .distinct()
  // .limit(numQuestions) // random numbers will be distinct and limited to numQuestions
  // .mapToObj(allQuestions::get) // maps numbers to get method of allQuestions
  // .collect(Collectors.toList()); // collects questions in a list
  //
  // this.quiz = quizQuestions;
  // }

  // private void setFieldsForTesting() {
  // HashMap<String, Boolean> answers1 = new HashMap<String, Boolean>();
  // answers1.put("chocolate", false);
  // answers1.put("vanilla", false);
  // answers1.put("strawberry", false);
  // answers1.put("mint chip", true);
  // Question question1 = new Question("Ice Cream", "Which Type of Ice Cream is Best", answers1);
  //
  // HashMap<String, Boolean> answers2 = new HashMap<String, Boolean>();
  // answers2.put("MBDTF", true);
  // answers2.put("LTOP", false);
  // answers2.put("College Drop Out", false);
  // Question question2 = new Question("Music", "Which Kanye West Album is Best", answers2);
  //
  // HashMap<String, Boolean> answers3 = new HashMap<String, Boolean>();
  // answers3.put("Dogs", false);
  // answers3.put("Cats", false);
  // answers3.put("Fish", false);
  // answers3.put("Pet Rock", true);
  // Question question3 = new Question("Animals", "Which Pet is Best", answers3);
  //
  // HashMap<String, Boolean> answers4 = new HashMap<String, Boolean>();
  // answers4.put("True", false);
  // answers4.put("False", true);
  // Question question4 = new Question("Ecology", "Grass is not Green", answers4);
  //
  // List<Question> questionList = new ArrayList<Question>();
  // questionList.add(question1);
  // questionList.add(question2);
  // questionList.add(question3);
  // questionList.add(question4);
  //
  // this.quiz = questionList;
  // }


}
