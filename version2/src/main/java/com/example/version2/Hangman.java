package com.example.version2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Dictionary;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URL;
import java.util.ResourceBundle;
public class Hangman extends Dictionary implements Initializable {
    @FXML
    private Label tuhientai;
    @FXML
    private Label sokitu;
    @FXML
    private TextField nhapkitu;
    @FXML
    private ImageView image;
    @FXML
    private Button quitbutton;
    @FXML
    private Button resetbutton;
    @FXML
    private Label goiy;
    @FXML
    private Label labelnhap;
    @FXML
    private Label soluot;
    private String mean;
    private static final String JDBC_URL = "jdbc:mySQL://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private String getRandomWordFromDatabase() {
        String randomWord = null;
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            String query = "SELECT word,SUBSTRING_INDEX(SUBSTRING_INDEX(detail, '>- ', -1), '<', 1) as meann FROM tbl_edict where idx>=65 and word not like '% %' and word not like '%-%' ORDER BY RAND() LIMIT 1";
            //String query1 =" SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(detail, '>- ', -1), '<', 1)  FROM tbl_edict;"
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {

                randomWord = resultSet.getString("word");
                mean = resultSet.getString("meann");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return randomWord;
    }
    private String selectedWord;
    private StringBuilder currentWordState;
    private int remainingAttempts = 6;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedWord = getRandomWordFromDatabase();
        // Initialize the current word state with underscores
        currentWordState = new StringBuilder();
         for(int i = 0; i < selectedWord.length(); i++) {
            currentWordState.append("*");
        }

        // Reset remaining attempts
        remainingAttempts = 6;
        tuhientai.setText(currentWordState.toString());
        sokitu.setText("The secret word has "+currentWordState.length() +" characters.");
        soluot.setText("RemainingAttempts: " + remainingAttempts);

    }


    public void clickenter(ActionEvent event) throws Exception {
        String guess = nhapkitu.getText().toLowerCase();
        if (guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
            processGuess(guess.charAt(0), tuhientai);
            labelnhap.setText("       Enter a character.");
        } else {
            // Display an error message for invalid input
            labelnhap.setText("Invalid input. Please enter a single letter.");

        }
        nhapkitu.clear();
    }

    public void processGuess(char guess, Label tuhientai) {
        boolean correctGuess = false;

        // Update the current word state based on the guess
        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == guess) {
                currentWordState.setCharAt(i, guess);
                correctGuess = true;
            }
        }
        if (!correctGuess) {
            remainingAttempts--;
        }
        if(remainingAttempts == 5){
            image.setImage( new Image("file:/Users/nguyenhoangdiep/Desktop/Learning-English-App/version2/src/main/resources/com/example/version2/icon/normal/hangman2.png")) ;
            soluot.setText("RemainingAttempts: " + remainingAttempts);
        }
        if(remainingAttempts == 4){
            image.setImage( new Image("file:/Users/nguyenhoangdiep/Desktop/Learning-English-App/version2/src/main/resources/com/example/version2/icon/normal/hangman3.png")) ;
            soluot.setText("RemainingAttempts: " + remainingAttempts);
        }
        if(remainingAttempts == 3){
            image.setImage( new Image("file:/Users/nguyenhoangdiep/Desktop/Learning-English-App/version2/src/main/resources/com/example/version2/icon/normal/hangman4.png")) ;
            soluot.setText("RemainingAttempts: " + remainingAttempts);
            goiy.setText("Gợi ý nè! Nghĩa của từ khoá: " + mean);

        }
        if(remainingAttempts == 2){
            image.setImage( new Image("file:/Users/nguyenhoangdiep/Desktop/Learning-English-App/version2/src/main/resources/com/example/version2/icon/normal/hangman5.png")) ;
            soluot.setText("RemainingAttempts: " + remainingAttempts);
        }
        if(remainingAttempts == 1){
            image.setImage( new Image("file:/Users/nguyenhoangdiep/Desktop/Learning-English-App/version2/src/main/resources/com/example/version2/icon/normal/hangman6.png")) ;
            soluot.setText("RemainingAttempts: " + remainingAttempts);
        }
        if(remainingAttempts == 0){
            image.setImage( new Image("file:/Users/nguyenhoangdiep/Desktop/Learning-English-App/version2/src/main/resources/com/example/version2/icon/normal/hangman7.png")) ;
            soluot.setText("RemainingAttempts: " + remainingAttempts);
        }

        // Update the UI
        tuhientai.setText(currentWordState.toString());
        if (currentWordState.toString().equals(selectedWord)) {


            showEndGameMessage("Congratulations! You guessed the word.");
        } else if (remainingAttempts == 0) {
            showEndGameMessage("You lose! The word is 「" + selectedWord +"」");
        }

    }
    private void showEndGameMessage(String message) {
        Stage endGameStage = new Stage();
        Label endGameLabel = new Label(message);
        Label meantext = new Label(selectedWord+ ": " +mean);
        Button closeButton = new Button("New game");
        closeButton.setOnAction(event ->{ endGameStage.close();
        reset();});

        VBox endGameLayout = new VBox(10);
        endGameLayout.getChildren().addAll(endGameLabel,meantext, closeButton);
        endGameLayout.setAlignment(Pos.CENTER);

        Scene endGameScene = new Scene(endGameLayout, 500, 150);
        endGameStage.setScene(endGameScene);

        endGameStage.showAndWait();

    }
    public void reset(){

        selectedWord = getRandomWordFromDatabase();

        // Initialize the current word state with underscores
        currentWordState = new StringBuilder();
        for(int i = 0; i < selectedWord.length(); i++) {
            currentWordState.append("*");
        }

        // Reset remaining attempts
        remainingAttempts = 6;
        tuhientai.setText(currentWordState.toString());
        sokitu.setText("The secret word has "+currentWordState.length() +" characters.");
        image.setImage( new Image("file:/Users/nguyenhoangdiep/Desktop/Learning-English-App/version2/src/main/resources/com/example/version2/icon/normal/hangman1.png")) ;
        soluot.setText("RemainingAttempts: " + remainingAttempts);
        goiy.setText(" ");
    }
    public void quitclick(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) quitbutton.getScene().getWindow();
        stage.setScene(scene);
    }
    public void resetclick(ActionEvent event) throws Exception {
        reset();
    }

}
