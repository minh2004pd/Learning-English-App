package com.example.version2;

import database.WordDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import model.DictionaryAdvance;
import model.DictionaryManagement;
import model.TextToSpeech;
import model.Word;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Controller extends MainController implements Initializable {
    private MediaPlayer mediaPlayer;
    protected DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private DictionaryAdvance dictionaryAdvance = new DictionaryAdvance();
    private static final String EV_PATH = "../data/dictionaries.txt";

    protected final ObservableList<String> searchList = FXCollections.observableArrayList();

    @FXML
    protected ListView<String> wordListView;
    @FXML
    protected WebView definitionView;
    @FXML
    protected HTMLEditor editDefinition;
    @FXML
    protected Button saveChangeButton;
    @FXML
    protected TextField searchField;
    @FXML
    protected Button bookmarkTrue;
    @FXML
    protected Button bookmarkFalse;
    @FXML
    protected Button removeButton;
    @FXML
    protected Button editButton;
    @FXML
    protected Button speaker1;
    @FXML
    protected Button speaker2;
    @FXML
    protected Button transLanguageEV;
    @FXML
    protected Button transLanguageVE;
    @FXML
    protected Label headText;
    @FXML
    protected Label speaker1Language;
    @FXML
    protected Label speaker2Language;
    @FXML
    protected ChoiceBox<String> choiceBoxUK;
    @FXML
    protected ChoiceBox<String> choiceBoxUS;
    @FXML
    protected Slider slider;

    protected boolean isOnEditDefinition = false;
    public static int choice = 0;
    protected static boolean isEVDic = true;


    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    public void showWarningAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Canh bao");
        alert.setHeaderText(null);
        alert.setContentText("Lua chon khong hop le!");
        alert.showAndWait();
    }

    public void speak(String lang) {
        TextToSpeech.language = lang;
        try {
            TextToSpeech.speakWord(headText.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(headText.getText());
        // playAudio("audio.wav");
    }

    @FXML
    public void handleClickSpeaker1() throws Exception {
        if (isEVDic) {
            TextToSpeech.Name = TextToSpeech.voiceNameUK;
            speak("en-gb");

        } else {
            TextToSpeech.Name = "Chi";
            speak("vi_vn");
        }
    }

    @FXML
    public void handleClickSpeaker2() throws Exception {
        TextToSpeech.Name = TextToSpeech.voiceNameUS;
        speak("en-us");
    }

    public void clearPane() {
        searchField.clear();
        definitionView.getEngine().loadContent("");
        searchList.clear();
        headText.setText("Nghĩa của từ");
        wordListView.getItems().clear();
        for (Word temp : getCurrentDic().getWordList()) {
            searchList.add(temp.getWord_target());
        }
        wordListView.setItems(searchList);
    }

    public void setLanguage() {
        if (!isEVDic) {
            transLanguageEV.setVisible(false);
            transLanguageVE.setVisible(true);
            if (!headText.getText().equals("Nghĩa của từ")) {
                speaker1Language.setText("VIE");
                speaker1Language.setVisible(true);
                speaker1.setVisible(true);
                speaker2.setVisible(false);
                speaker2Language.setVisible(false);
            } else {
                speaker1.setVisible(false);
                speaker1Language.setVisible(false);
                speaker2.setVisible(false);
                speaker2Language.setVisible(false);
            }
        } else {
            transLanguageEV.setVisible(true);
            transLanguageVE.setVisible(false);
            if (!headText.getText().equals("Nghĩa của từ")) {
                speaker1Language.setText("UK");
                speaker1.setVisible(true);
                speaker1Language.setVisible(true);
                speaker2.setVisible(true);
                speaker2Language.setVisible(true);
            } else {
                speaker1.setVisible(false);
                speaker1Language.setVisible(false);
                speaker2.setVisible(false);
                speaker2Language.setVisible(false);
            }
        }
    }

    @FXML
    public void handleClickTransButton() {
        isEVDic = !isEVDic;
        clearPane();
        setLanguage();
    }

//    public  void updateWordListView(String word, ArrayList<Word>) {
//        ArrayList<Word> res = dictionaryManagement.dictionarySearcher(word);
//        for ()
//    }

    public DictionaryManagement getCurrentDic() {
        return dictionaryManagement;
    }

    @FXML
    public void handleClickRemoveButton(String word) {
        String spelling = word;
        if (spelling.equals("")) {
            showWarningAlert();
            return;
        }
        ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xoá từ này không?", yes, no);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == yes) {
            Word target = getCurrentDic().dictionaryLookup(spelling);
            getCurrentDic().removeFromCommandline(spelling);
            WordDAO.getInstance().delete(target);
//            getCurrentDic().getWordList().clear();
//            getCurrentDic().insertFromFile();
            headText.setText("Nghĩa của từ");
            searchField.clear();
            definitionView.getEngine().loadContent("");
        }
    }

    @FXML
    public void handleClickListView() {
        String spelling = wordListView.getSelectionModel().getSelectedItem();
        if (spelling == null) {
            return;
        }
        searchField.setText(spelling);
        headText.setText(spelling);
        setLanguage();
    }

    @FXML
    public void handleClickEditButton() {
        String spelling = searchField.getText();
        if (spelling.equals("")) {
            showWarningAlert();
            return;
        }
        if (isOnEditDefinition) {
            isOnEditDefinition = false;
            editDefinition.setVisible(false);
            saveChangeButton.setVisible(false);
            return;
        }
        isOnEditDefinition = true;
        saveChangeButton.setVisible(true);
        editDefinition.setVisible(true);
        Word w = getCurrentDic().dictionaryLookup(spelling);
        String meaning = w.getWord_explain();
        editDefinition.setHtmlText(meaning);
    }

    @FXML
    public void handleClickSaveButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Sửa từ thành công!");
        alert.showAndWait();

        editDefinition.setVisible(false);
        isOnEditDefinition = false;
        saveChangeButton.setVisible(false);

        String newMeaning = editDefinition.getHtmlText().replace(" dir=\"ltr\"", "");
        String spelling = searchField.getText();
        Word word = getCurrentDic().dictionaryLookup(spelling);
        getCurrentDic().updateFromCommandline(word, newMeaning);
        getCurrentDic().updateToDB(word);
//        getCurrentDic().getWordList().clear();
//        getCurrentDic().insertFromFile();
//        saveWordToFile(getCurrentDic().getPATH(), getCurrentDic().getVocab(), spelling, newMeaning);
//        saveWordToFile(getCurrentDic().getHISTORY_PATH(), getCurrentDic().getHistoryVocab(), spelling, newMeaning);
//        saveWordToFile(getCurrentDic().getBOOKMARK_PATH(), getCurrentDic().getBookmarkVocab(), spelling, newMeaning);
        definitionView.getEngine().loadContent(newMeaning, "text/html");
    }

}
