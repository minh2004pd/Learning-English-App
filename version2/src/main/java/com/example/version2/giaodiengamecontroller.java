package com.example.version2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class giaodiengamecontroller {
    @FXML
    private Button startbutton;
    @FXML
    private Button howtoplaybutton;
    @FXML
    private Button quitbutton;

    @FXML
    private void onclickquit(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("giaodien.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) quitbutton.getScene().getWindow();
        stage.setScene(scene);
    }
    public void onclickhow(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rule.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) howtoplaybutton.getScene().getWindow();
        stage.setScene(scene);
    }
    public void onclickplay(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hangman.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) startbutton.getScene().getWindow();
        stage.setScene(scene);
    }


}
