package com.example.version2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class giaodiencontroll {
    @FXML
    private Button searchbutton;
    @FXML
    private Button inforbutton;
    @FXML
    private Button gamebutton;

    @FXML
    private void onclick(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) searchbutton.getScene().getWindow();
        stage.setScene(scene);
    }
    @FXML
    private void onclickinfo(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("infor.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) inforbutton.getScene().getWindow();
        stage.setScene(scene);
    }
    @FXML
    private void onclickgame(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) gamebutton.getScene().getWindow();
        stage.setScene(scene);
    }
}
