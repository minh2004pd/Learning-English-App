package com.example.version2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Exam;
import model.Questions;
import model.Standings;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ExamController implements Initializable {
    protected int i = 0;
    @FXML
    protected AnchorPane content;
    @FXML
    protected Text user_name;
    @FXML
    protected Text finalScore;
    @FXML
    protected AnchorPane standingsPane;
    @FXML
    protected AnchorPane examPane;
    @FXML
    protected AnchorPane playerPane;
    @FXML
    protected TextArea question;
    @FXML
    protected Button answerA;
    @FXML
    protected Button answerB;
    @FXML
    protected Button answerC;
    @FXML
    protected Button answerD;
    @FXML
    protected Button next;
    @FXML
    protected Button previous;
    @FXML
    protected Text level;
    @FXML
    protected Text score;
    @FXML
    protected Text username1;
    @FXML
    protected Text time;
    @FXML
    protected Button submit_username;
    @FXML
    protected TextField username;
    @FXML
    protected ListView<String> standingsView;
    protected Standings standings;
    protected ObservableList<String> standingsList = FXCollections.observableArrayList();
    protected static Exam exam;

    private int minutes = 2;
    private int seconds = 0;

    private Timeline timeline;
    private boolean answerSelected = false;

    private String selectedAnswer;


    public void setUser_name() {
        System.out.println(exam.getSize());;
        user_name.setText(exam.getUserName());
        score.setText(exam.getScore() + "");
    }

    // Define the decrement() method to subtract one second from the time
    private void decrement() {
        // If the seconds are not zero, subtract one from the seconds
        if (seconds > 0) {
            seconds--;
        }
        // If the seconds are zero and the minutes are not zero, subtract one from the minutes and set the seconds to 59
        else if (seconds == 0 && minutes > 0) {
            minutes--;
            seconds = 59;
        }
        // If the seconds and minutes are both zero, do nothing
        else {

        }
    }

    @FXML
    public void createNewUser() {
        i = 0;
        level.setText(i+1 + "");
        String name = username.getText();
        if (name != null) {
            exam = new Exam(name, 0);
            exam.setExam();
            setUser_name();
            setQuestion();
            examPane.setVisible(true);
            standingsPane.setVisible(false);
            playerPane.setVisible(false);
        }
        time.setText("02:00");
        timeline.play();
    }

    public void setQuestion() {
        question.setText(exam.getQuestionsList().get(i).getQuestion());
        answerA.setText(exam.getQuestionsList().get(i).getOptions()[0]);
        answerB.setText(exam.getQuestionsList().get(i).getOptions()[1]);
        answerC.setText(exam.getQuestionsList().get(i).getOptions()[2]);
        answerD.setText(exam.getQuestionsList().get(i).getOptions()[3]);
    }

    public void clearAllChoice() {
        answerSelected = false;
        selectedAnswer = null;
        resetAllStyleChoice();
    }

    public void resetAllStyleChoice() {
        answerA.setStyle("");

        answerB.setStyle("");

        answerC.setStyle("");

        answerD.setStyle("");

    }

    public void setStandingsListView() {
        standingsList.clear();
        for (int i = 0; i < standings.getStandingsList().size(); i++) {
            standingsList.add((i+1) + ". " + standings.getStandingsList().get(i).getInfo());
        }
        standingsView.setItems(standingsList);
    }

    public void showStandings() {
        username1.setText(exam.getUserName());
        finalScore.setText(exam.getScore() + "");
        standings.addExam(exam);
        standings.exportToFile();
        setStandingsListView();
        standingsPane.setVisible(true);
        playerPane.setVisible(false);
        examPane.setVisible(false);
        System.out.println(exam.getScore());
        for (Exam e : standings.getStandingsList()) {
            System.out.println(e.getInfo());
        }
    }

    @FXML
    public void handleChoice(MouseEvent event) {
        if (answerSelected) return;

        answerSelected = true;
        Button clickedButton = (Button) event.getSource();
        selectedAnswer = clickedButton.getText();
        System.out.println("Selected answer: " + selectedAnswer);

        Questions currentQ = exam.getQuestionsList().get(i);
        String correctAnswer = currentQ.getOptions()[currentQ.getAnswerIndex()];
        if (answerA.getText().equals(correctAnswer)) {
            answerA.setStyle("-fx-text-fill: green;");
        } else if (answerB.getText().equals(correctAnswer)) {
            answerB.setStyle("-fx-text-fill: green;");
        } else if (answerC.getText().equals(correctAnswer)) {
            answerC.setStyle("-fx-text-fill: green;");
        } else if (answerD.getText().equals(correctAnswer)) {
            answerD.setStyle("-fx-text-fill: green;");
        }
        if (selectedAnswer.equals(correctAnswer)) {
            exam.increaseScore(currentQ.getMark());
            score.setText(exam.getScore() + "");
            System.out.println("Correct!");
        } else {
            clickedButton.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void hanldeSubmit() {
        if (selectedAnswer != null) {
            clearAllChoice();
            if (i + 1 < exam.getSize()) {
                i++;
                level.setText(i + 1 + "");
                setQuestion();
            } else {
                timeline.stop();
                showStandings();
            }
        }
    }

    public void setTimer() {
        minutes = 2;
        seconds = 0;
    }

    @FXML
    public void handlePlayagain() {
        setTimer();
        username.setText("");
        playerPane.setVisible(true);
        examPane.setVisible(false);
        standingsPane.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        standings = new Standings();
        System.out.println(standings.getStandingsList().size());

        time.setText("05:00");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            decrement();
            // Format the time as mm:ss
            String formattedTime = String.format("%02d:%02d", minutes, seconds);
            // Set the text of the Text object to the formatted time
            time.setText(formattedTime);
            if (minutes == 0 && seconds == 0) {
                System.out.println("the end");
                i = 9;
                showStandings();
                timeline.stop();
            }
        }));
        // Set the cycle count of the Timeline object to indefinite
        timeline.setCycleCount(Timeline.INDEFINITE);

        playerPane.setVisible(true);
        standingsPane.setVisible(false);
        examPane.setVisible(false);
    }
}
