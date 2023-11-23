package com.example.version2;

import database.WordDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import model.Word;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookMarkController extends Controller implements Initializable {
    private ArrayList<Word> bookmarkTemp = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        bookmarkTemp.addAll(getCurrentDic().getBookMark());
        System.out.println(getCurrentDic().getBookMark().size());
        setBookmarkListViewItem();
    }

    protected void initBookmarkListViewItem() {
        bookmarkSearch.clear();
        bookmarkTemp.clear();
        bookmarkTemp.addAll(getCurrentDic().getBookMark());
        if (wordListView1 != null) {
            wordListView1.getItems().clear();
            for (int i = 0; i < bookmarkTemp.size(); i++) {
                bookmarkSearch.add(bookmarkTemp.get(i).getWord_target());
            }
            wordListView1.setItems(bookmarkSearch);
        } else {
            System.out.println("wordListView1 is null"); // Optional error message or handling
        }
    }


    protected void setBookmarkListViewItem() {
        bookmarkSearch.clear();
        if (wordListView1 != null) {
            wordListView1.getItems().clear();
            for (int i = 0; i < bookmarkTemp.size(); i++) {
                bookmarkSearch.add(bookmarkTemp.get(i).getWord_target());
            }
            wordListView1.setItems(bookmarkSearch);
        } else {
            System.out.println("wordListView1 is null"); // Optional error message or handling
        }
    }

    public void clearPane() {
        searchField.clear();
        definitionView.getEngine().loadContent("");
        bookmarkSearch.clear();
        headText.setText("Nghĩa của từ");
        wordListView.getItems().clear();
        for (Word temp : getCurrentDic().getBookMark()) {
            bookmarkSearch.add(temp.getWord_target());
        }
        wordListView.setItems(bookmarkSearch);
    }

    @FXML
    protected void handleBookmarkSearchBar() {
        bookmarkTemp.clear();
        bookmarkSearch.clear();
        String target = searchField.getText();
        bookmarkTemp = getCurrentDic().dictionarySearcher(target, getCurrentDic().getBookMark());
        setBookmarkListViewItem();
    }

    @FXML
    protected void showBookmarkWordDefinition() {
        String selectedWord = wordListView1.getSelectionModel().getSelectedItem();
        if (selectedWord == null) {
            return;
        }
        Word word = getCurrentDic().dictionaryLookup(selectedWord, getCurrentDic().getBookMark());
        String meaning = word.getWord_explain();
        headText.setText(word.getWord_target());
        searchField.setText(word.getWord_target());
        definitionView.getEngine().loadContent(meaning, "text/html");
    }

    @FXML
    public void handleClickRemoveButton() {
        String spelling = headText.getText();
        if (spelling.equals("")) {
            showWarningAlert();
            return;
        }
        ButtonType yes = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Bạn có chắc chắn muốn xoá từ\n " +
                "khỏi danh sách từ đã lưu này không?", yes, no);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == yes) {
            getCurrentDic().removeFromCommandline(spelling, getCurrentDic().getBookMark());

            getCurrentDic().dictionaryExportToFile(getCurrentDic().getBookMarkFile(),
                                                   getCurrentDic().getBookMark());
            headText.setText("Nghĩa của từ");
            searchField.clear();
            definitionView.getEngine().loadContent("");
            bookmarkTemp.clear();
            bookmarkTemp.addAll(getCurrentDic().getBookMark());
            setBookmarkListViewItem();
        }
    }

    @FXML
    public void handleClickListView() {
        String spelling = wordListView1.getSelectionModel().getSelectedItem();
        if (spelling == null) {
            return;
        }
        searchField.setText(spelling);
        headText.setText(spelling);
        setLanguage();
    }


}
