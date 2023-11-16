package com.example.version2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Exam;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerController implements Initializable  {
    @FXML
    private Button submit_username;
    @FXML
    private TextField username;
    @FXML
    private AnchorPane examPane;
    @FXML
    private AnchorPane mainContent;
    private ExamController examController;

    protected static Exam exam;

    @FXML
    public void createNewUser() {
        String name = username.getText();
        if (name != null) {
            exam = new Exam(name, 0);
            mainContent.getChildren().setAll(examPane);
            examController.setUser_name();
            examController.setQuestion();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Exam tab
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Exam.fxml"));
            examPane = loader.load();
            examController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
