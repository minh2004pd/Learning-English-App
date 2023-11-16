package com.example.version2;

import com.detectlanguage.errors.APIError;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.TextToSpeech;
import model.Translate;
import model.TranslationResult;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    private MediaPlayer mediaPlayer;
    private TranslationResult translationResult;
    private String langFrom = "";
    private String langTo = "vi";
    private String voiceFrom;
    private String speakFrom;
    private String voiceTo;
    private String speakTo;

    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private TextArea area1;
    @FXML
    private TextField area2;

    @FXML
    private Button langFromFirst;
    @FXML
    private Button langFromSecond;
    @FXML
    private Button langFromThird;

    @FXML
    private Button langToFirst;
    @FXML
    private Button langToSecond;

    public void resetStyleLangFrom() {
        langFromFirst.getStyleClass().removeAll("active");
        langFromSecond.getStyleClass().removeAll("active");
        langFromThird.getStyleClass().removeAll("active");
    }

    public void resetStyleLangTo() {
        langToFirst.getStyleClass().removeAll("active");
        langToSecond.getStyleClass().removeAll("active");
    }

    @FXML
    public void detect() {
        resetStyleLangFrom();
        langFromFirst.getStyleClass().add("active");
        langFrom = "";
        text1.setText("Phát hiện n.ngữ");
        voiceFrom = "Linda";
        speakFrom = "en-gb";
    }

    @FXML
    public void eng() {
        resetStyleLangFrom();
        langFromSecond.getStyleClass().add("active");
        langFrom = "en";
        text1.setText("Tiếng Anh");
        voiceFrom = "Linda";
        speakFrom = "en-gb";
    }

    @FXML
    public void eng1() throws IOException, APIError {
        resetStyleLangTo();
        langToSecond.getStyleClass().add("active");
        text2.setText("Tiếng Anh");
        langTo = "en";
        voiceTo = "Linda";
        speakTo = "en-gb";
        if (langFrom.equals("vi")) {
            if (!area1.getText().equals("")) {
                translationResult = Translate.googleTranslate(langFrom, langTo, area1.getText());
                area2.setText(translationResult.getTranslatedText());
            }
        } else {
            if (!area1.getText().equals("")) {
                area2.setText(area1.getText());
            }
        }
    }

    @FXML
    public void vie() {
        resetStyleLangFrom();
        langFromThird.getStyleClass().add("active");
        text1.setText("Tiếng Việt");
        langFrom = "vi";
        voiceFrom = "Chi";
        speakFrom = "vi-vn";
    }

    @FXML
    public void vie1() throws IOException, APIError {
        resetStyleLangTo();
        langToFirst.getStyleClass().add("active");
        text2.setText("Tiếng Việt");
        langTo = "vi";
        voiceTo = "Chi";
        speakTo = "vi-vn";
        if (langFrom.equals("en")) {
            if (!area1.getText().equals("")) {
                translationResult = Translate.googleTranslate(langFrom, langTo, area1.getText());
                area2.setText(translationResult.getTranslatedText());
            }
        } else {
            if (!area1.getText().equals("")) {
                area2.setText(area1.getText());
            }
        }
    }

    @FXML
    void translate() throws IOException, APIError {
        if (langFromFirst.getStyleClass().contains("active")) {
            if (!area1.getText().equals("")) {
                String lang = Translate.detectLang(area1.getText());
                System.out.println(lang);
                if (lang.equals("en")) {
                    text1.setText("Tiếng Anh");
                    text2.setText("Tiếng Việt");
                    voiceFrom = "Linda";
                    speakFrom = "en-gb";
                    langFrom = "en";
                } else if (lang.equals("vi")) {
                    text2.setText("Tiếng Anh");
                    text1.setText("Tiếng Việt");
                    voiceFrom = "Chi";
                    speakFrom = "vi-vn";
                    langFrom = "vi";
                } else {
                    area2.setText("Khong phat hien duoc ngon ngu!");
                }
            }
        }

        if (!area1.getText().equals("")) {
            if (langFrom.equals(langTo)) {
                area2.setText(area1.getText());
            } else {
                translationResult = Translate.googleTranslate(langFrom, langTo, area1.getText());
                area2.setText(translationResult.getTranslatedText());
            }
        }
    }

    public void speak(String lang, String text) {
        TextToSpeech.language = lang;
        try {
            TextToSpeech.speakWord(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(text);
    }

    @FXML
    void speak1() throws Exception {
        TextToSpeech.Name = voiceFrom;
        if (!area1.getText().equals("")) {
            speak(speakFrom, area1.getText());
        }
    }

    @FXML
    void speak2() throws Exception {
        TextToSpeech.Name = voiceTo;
        if (!area2.getText().equals("")) {
            speak(speakTo, area2.getText());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        langFromFirst.getStyleClass().add("active");
        langToFirst.getStyleClass().add("active");

        text1.setText("Phát hiện n.ngữ");
        area1.setText("");
        voiceFrom = "Linda";
        speakFrom = "en-gb";
        langFrom = "";

        text2.setText("Tiếng Việt");
        voiceTo = "Chi";
        speakTo = "vi-vn";
        langTo = "vi";
    }
}
