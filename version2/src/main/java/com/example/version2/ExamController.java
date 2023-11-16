package com.example.version2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import model.Questions;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ExamController extends PlayerController implements Initializable {
    protected static int i = 0;
    @FXML
    protected Text user_name;
    @FXML
    protected TextField question;
    @FXML
    protected RadioButton answerA;
    @FXML
    protected RadioButton answerB;
    @FXML
    protected RadioButton answerC;
    @FXML
    protected RadioButton answerD;
    @FXML
    protected Button next;
    @FXML
    protected Button previous;
    @FXML
    protected Text level;
    @FXML
    protected Text score;
    RadioButton selectedRadioButton = null;

    public void setUser_name() {
        exam.insertFromFile();
        System.out.println(exam.getSize());;
        user_name.setText(exam.getUserName());
        score.setText(exam.getScore() + "");
    }

    public void setQuestion() {
        question.setText(exam.getQuestionsList().get(i).getQuestion());
        answerA.setText(exam.getQuestionsList().get(i).getOptions()[0]);
        answerB.setText(exam.getQuestionsList().get(i).getOptions()[1]);
        answerC.setText(exam.getQuestionsList().get(i).getOptions()[2]);
        answerD.setText(exam.getQuestionsList().get(i).getOptions()[3]);
    }

    public void check() {
        if (i > 0 && i < exam.getSize() - 1) {
            next.getStyleClass().removeAll("visible");
            previous.getStyleClass().removeAll("visible");
        }
    }

    public void clearAllChoice() {
        answerA.setSelected(false);
        answerB.setSelected(false);
        answerC.setSelected(false);
        answerD.setSelected(false);
    }

    @FXML
    public void handleChoice() {
        if (answerA.isSelected()) {
            selectedRadioButton = answerA;
        } else if (answerB.isSelected()) {
            selectedRadioButton = answerB;
        } else if (answerC.isSelected()) {
            selectedRadioButton = answerC;
        }

        if (selectedRadioButton != null) {
            String selectedAnswer = selectedRadioButton.getText();
            System.out.println("Selected answer: " + selectedAnswer);
        }
    }

    @FXML
    public void hanldeSubmit() {
        if (selectedRadioButton != null) {
            Questions currentQ = exam.getQuestionsList().get(i);
            if (selectedRadioButton.getText().equals(currentQ.getOptions()[currentQ.getAnswerIndex()])) {
                exam.increaseScore(currentQ.getMark());
                score.setText(exam.getScore() + "");
                System.out.println("Correct!");
            }
            i++;
            setQuestion();
            clearAllChoice();
        }
    }


    @FXML
    public void handleNext() {
        clearAllChoice();
        if (i + 1 < exam.getSize()) {
            i++;
            level.setText(i+1 + "");
            setQuestion();
            if (i == exam.getSize() - 1) {
                next.getStyleClass().add("visible");
            }
        } else {
            next.getStyleClass().add("visible");
        }
        check();
    }

    @FXML
    public void handlePrevious() {
        clearAllChoice();
        if (i - 1 >= 0) {
            i--;
            level.setText(i+1 + "");
            setQuestion();
            if (i == 0) {
                previous.getStyleClass().add("visible");
            }
        } else {
            previous.getStyleClass().add("visible");
        }
        check();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        level.setText(i+1 + "");
        previous.getStyleClass().add("visible");
        // Create a ChangeListener for each RadioButton
        answerA.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Answer A is selected
                answerB.setSelected(false);
                answerC.setSelected(false);
                answerD.setSelected(false);
            }
        });

        answerB.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Answer B is selected
                answerA.setSelected(false);
                answerC.setSelected(false);
                answerD.setSelected(false);
            }
        });

        answerC.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Answer C is selected
                answerA.setSelected(false);
                answerB.setSelected(false);
                answerD.setSelected(false);
            }
        });

        answerD.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Answer D is selected
                answerA.setSelected(false);
                answerB.setSelected(false);
                answerC.setSelected(false);
            }
        });
    }


}
