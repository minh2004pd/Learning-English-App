package model;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Test extends Application {
    private MediaPlayer mediaPlayer;

    public void playAudio(String filePath) {
        File file = new File(filePath);

        if (!file.isFile()) {
            System.out.println("Invalid file: " + filePath);
            return;
        }

        System.out.println(file.getAbsolutePath());
        Media media = new Media(filePath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void speak(String lang) {
        TextToSpeech.language = lang;
        try {
            TextToSpeech.speakWord("this is the text to speech");
        } catch (Exception e) {
            e.printStackTrace();
        }
        playAudio("D:\\version2\\src\\main\\resources\\com\\example\\version2\\audio.mp3");
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        speak("en-gb");
    }
}