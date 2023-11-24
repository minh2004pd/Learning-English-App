package com.example.version2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class inforcontrol {
    @FXML
    private Button backbutton;
    @FXML
    private void onclick2(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("giaodien.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) backbutton.getScene().getWindow();
        stage.setScene(scene);
    }






}
