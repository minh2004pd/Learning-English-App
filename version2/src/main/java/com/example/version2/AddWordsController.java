package com.example.version2;

import database.WordDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import model.Word;

import java.net.URL;
import java.util.ResourceBundle;

public class AddWordsController extends Controller implements Initializable {
    @FXML
    protected TextArea word_target_text;
    @FXML
    protected TextArea word_explain_text;

    public void resetAll() {
        word_target_text.setText("");
        word_explain_text.setText("");
    }

    @FXML
    public void handleClickSave() {
        String word_target = word_target_text.getText();
        String word_explain = word_explain_text.getText();
        Word target = getCurrentDic().dictionaryLookup(word_target, getCurrentDic().getWordList());

        if (target == null) {
            Word newWord = new Word(word_target, word_explain);
            getCurrentDic().insertFromCommandline(newWord, getCurrentDic().getWordList());
            WordDAO.getInstance().insert(newWord);
            resetAll();

            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Word has been saved successfully!");
            alert.showAndWait();
        } else {
            // Show error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("The word already exists!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
    }
}
