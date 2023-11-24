package model;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Test{
    public static void main(String[] args) {
        Exam exam = new Exam();
        exam.insertFromFile();
        exam.getAllQuestions();
        System.out.println(exam.getSize());
    }
}