package com.example.version2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimeCounter extends Application {
    // A variable to store the elapsed time
    private int count = 0;
    private int minutes = 0;
    private int seconds = 5;
    private Timeline timeline;

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


    @Override
    public void start(Stage primaryStage) {
        // Create a label to display the time
        Label label = new Label("5:00");
        // Modify the KeyFrame constructor to update the label every second
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            // Format the time as mm:ss
            label.setText(String.format("%d:%02d", minutes, seconds));
            // Decrement the minutes and seconds by one
            decrement();
            // Stop the timeline when the time reaches 0:00
            if (minutes == 0 && seconds == 0) {
                System.out.println("the end");
                timeline.stop();
            }
        }));
        // Set the cycle count of the timeline to indefinite
        timeline.setCycleCount(Animation.INDEFINITE);
        // Start the timeline
        timeline.play();
        // Create a stack pane to hold the label
        StackPane root = new StackPane();
        root.getChildren().add(label);
        // Create a scene with the stack pane
        Scene scene = new Scene(root, 200, 200);
        // Set the title and scene of the stage
        primaryStage.setTitle("Time Counter");
        primaryStage.setScene(scene);
        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

