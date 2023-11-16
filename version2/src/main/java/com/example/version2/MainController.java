package com.example.version2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane mainContent;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane bookmarkPane;
    @FXML
    protected AnchorPane examPane;
    @FXML
    private AnchorPane playerPane;
    private  SearchController searchController;
    private BookMarkController bookMarkController;
    private PlayerController examController;

    @FXML
    private Button searchButton;
    @FXML
    private Button translateButton;
    @FXML
    private Button bookmarkButton;
    @FXML
    private Button mainHistoryButton;
    @FXML
    private Button settingButton;
    @FXML
    private Button examButton;

    public void setMainContent(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }

    @FXML
    public void resetNav() {
        searchButton.getStyleClass().removeAll("active");
        translateButton.getStyleClass().removeAll("active");
        bookmarkButton.getStyleClass().removeAll("active");
        mainHistoryButton.getStyleClass().removeAll("active");
        settingButton.getStyleClass().removeAll("active");
        examButton.getStyleClass().removeAll("active");
    }

    @FXML
    public void showSearchPan() {
        resetNav();
        searchButton.getStyleClass().add("active");
        // SearchController.initSearchListView();
        setMainContent(searchPane);
    }

    @FXML
    public void showTranslatePane() {
        resetNav();
        translateButton.getStyleClass().add("active");
        setMainContent(translatePane);
    }

    @FXML
    public void showWordsMarkPane() {
        resetNav();
        bookmarkButton.getStyleClass().add("active");
        setMainContent(bookmarkPane);
        bookMarkController.setBookmarkListViewItem();
    }

    @FXML
    public void showPlayerPane() {
        resetNav();
        examButton.getStyleClass().add("active");
        setMainContent(playerPane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // search tab
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // translate tab
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("translate.fxml"));
            translatePane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // bookmark tab
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookmark.fxml"));
            bookmarkPane = loader.load();
            bookMarkController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Player tab
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("playWindow.fxml"));
            playerPane = loader.load();
            examController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Exam tab
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Exam.fxml"));
            examPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchButton.getStyleClass().add("active");
        mainContent.getChildren().setAll(searchPane);
    }
}