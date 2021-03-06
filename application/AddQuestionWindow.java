//
// Filename: AddQuestion.java
// Class: CS 400, Spring 2019
// Project: Final Team Project
// Due Date: May 2, 2019
// Authors: Alexandra Borukhovetskaya, Evan Koenig, Angelique Stepanenkov, Matthew Palmer, Otto
// Baier
//
package application;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Allows for a question & topic to be added manually. Questions can only be added if "Add question"
 * at the button of the page was selected.
 */
public class AddQuestionWindow implements Window {
  private Stage stage;
  HashMap<String, Boolean> answerMap = new HashMap<String, Boolean>();

  /**
   * Initialize this window's stage
   * 
   * @param stage
   */
  public AddQuestionWindow(Stage stage) {
    this.stage = stage;
  }

  /**
   * Displays the screen and interacts with the user the get new question input. The user can use a
   * topic already available or can make a new topic by selecting "Other". Different numbers of
   * incorrect answers can be created when pressing the "Add incorrect answer button" and answers
   * can be removed by selecting "remove answer" and typing in the answer to remove. After pressing
   * "add question", the question is created. After pressing "back", no question is created.
   * 
   * @see application.Window#getScene()
   */
  @Override
  public Scene getScene() {
    // initialize the display window
    VBox root = new VBox(20);
    root.setPadding(new Insets(10, 25, 25, 25));
    root.setBackground(Config.GRADIENT);
    root.setSpacing(10);
    Scene scene = new Scene(root, 800, 600);
    // Header label
    Label addQHeader = new Label("Add Question");
    addQHeader.setFont(Config.SIZE24);
    root.getChildren().add(addQHeader);
    // Topic HBox holds topic box selection and text area for adding new topics
    HBox topicHB = new HBox(20);

    HBox topicBox = new HBox(20); // HBox to display the topic label and combobox
    Label topicPrompt = new Label("Topic:");
    topicPrompt.setFont(Config.SIZE14);
    topicBox.getChildren().add(topicPrompt);
    ObservableList<String> topics = Main.questionList.getAllTopicNames();
    topics.add("Other"); // add "Other" to be at end of list
    ComboBox<String> topic = new ComboBox<String>(topics);
    topicBox.getChildren().add(topic);
    topicHB.getChildren().add(topicBox);
    VBox otherTopicVB = new VBox(10); // groups other topic label with textbox
    // prompt and text area for a new topic
    Label otherPrompt =
        new Label("If you selected \"Other\", please type the name of the new topic below:");
    otherPrompt.setFont(Config.SIZE14);
    otherTopicVB.getChildren().add(otherPrompt);
    TextArea otherText = new TextArea(); // other topic text area
    otherText.setPrefHeight(50);
    // adds the topic area to the display
    otherTopicVB.getChildren().add(otherText);
    topicHB.getChildren().add(otherTopicVB);
    root.getChildren().add(topicHB);
    // Question text area
    Label questionPrompt = new Label("Enter question text below:");
    questionPrompt.setFont(Config.SIZE14);
    TextArea question = new TextArea();
    question.setPrefHeight(100);
    root.getChildren().add(questionPrompt);
    root.getChildren().add(question);
    // CORRECT ANSWER AREA
    HBox correctBox = new HBox(20); // correct answer hbox with label and text area
    Label correctL = new Label("Enter correct answer:");
    correctL.setFont(Config.SIZE14);
    TextArea correctT = new TextArea();
    correctT.setPrefHeight(50);
    correctBox.getChildren().add(correctL);
    correctBox.getChildren().add(correctT);
    root.getChildren().add(correctBox);
    // INCORRECT ANSWER AREA
    HBox incorrectBox = new HBox(20);
    Label incorrectL = new Label("Enter incorrect answers:");
    incorrectL.setFont(Config.SIZE14);
    VBox incorrectTVB = new VBox(10);
    TextArea incorrect = new TextArea();
    incorrect.setPrefHeight(50);
    incorrectTVB.getChildren().add(incorrect);
    // will eventually give the option of adding a greater amount of incorrect answers instead of
    // max 4
    Button addAns = new Button("Add Incorrect Answer");
    Button removeAns = new Button("Remove Incorrect Answer");
    ArrayList<String> incorrectAnswers = new ArrayList<>();
    Label incorrectLabel = new Label("Incorrect Answers: ");
    Label error = new Label();
    addAns.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) {
        String ans = incorrect.getText().trim().replaceAll("\\s+", "");
        if (!ans.equals("")) {
          if (incorrectAnswers.contains(ans))
            error.setText("No duplicate answers");
          else {
            answerMap.put(ans, false);
            incorrectAnswers.add(ans);
            incorrect.clear();
            String prompt = "Incorrect Answers: ";
            for (int i = 0; i < incorrectAnswers.size(); i++)
              if (i + 1 == incorrectAnswers.size())
                prompt += incorrectAnswers.get(i);
              else
                prompt += incorrectAnswers.get(i) + ", ";
            incorrectLabel.setText(prompt);
          }
        }
      }
    });
    // allows user to remove incorrect answers they have already added
    removeAns.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) {
        if (!incorrect.getText().replaceAll("\\s+", "").equals("")) {
          answerMap.remove(incorrect.getText().trim());
          incorrectAnswers.remove(incorrect.getText().trim());
          incorrect.clear();
          String prompt = "Incorrect Answers: ";
          for (int i = 0; i < incorrectAnswers.size(); i++)
            if (i + 1 == incorrectAnswers.size())
              prompt += incorrectAnswers.get(i);
            else
              prompt += incorrectAnswers.get(i) + ", ";
          incorrectLabel.setText(prompt);
        }
      }
    });
    // add all buttons and labels into a horizontal box just above the back/add buttons
    HBox incorrectButtons = new HBox();
    incorrectTVB.getChildren().add(incorrectButtons);
    incorrectButtons.getChildren().add(addAns);
    incorrectButtons.getChildren().add(removeAns);

    incorrectTVB.getChildren().add(incorrectLabel);
    incorrectBox.getChildren().add(incorrectL);
    incorrectBox.getChildren().add(incorrectTVB);
    root.getChildren().add(incorrectBox);
    // Back button allows user to get to Home screen without updating questions
    HBox lowerButtons = new HBox(20);
    lowerButtons.getChildren().add(new SwapScreen("Back", Main.windows[0], stage));
    Button addQ = new Button("Add Question");
    addQ.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) {
        String questionTopic;
        try {
          if (!topic.getValue().equals("Other")) // if the user has selected a topic
            questionTopic = topic.getValue();
          else { // the user has put in a new topic
            questionTopic = otherText.getText().trim();
          }
          if (questionTopic.equals(""))
            error.setText("Please enter a topic");
          else if (question.getText().trim().equals(""))
            error.setText("Please enter a question");
          else if (correctT.getText().trim().equals(""))
            error.setText("Please enter a correct answer");
          else if (answerMap.size() == 0)
            error.setText("Please enter at least 1 incorrect answer");
          else {
            answerMap.put(correctT.getText().trim(), true);
            Question newQuestion =
                new Question(questionTopic, question.getText().trim(), answerMap);
            Main.questionList.addQuestion(newQuestion);
            stage.setScene(Main.windows[0].getScene());
          }
        } catch (Exception e) {
          error.setText("Please enter a valid question");
        }
      }
    });
    lowerButtons.getChildren().add(addQ);
    lowerButtons.getChildren().add(error);
    root.getChildren().add(lowerButtons);
    return scene;
  }
}
