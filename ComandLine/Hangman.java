
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private List<String> WordList = new ArrayList<>();
    private List<String> mean = new ArrayList<>();
    public final String filePath = "dictionaries.txt";
    private int count = 0;
    private String word;
    private String vnamese;
    private String guessWord;

    public Hangman() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Lấy chỉ phần tử đầu tiên từ mỗi dòng và thêm nó vào danh sách
                String firstWord = line.split("\t")[0]; // Lấy phần tử đầu tiên
                String nghia = line.split("\t")[1];
                WordList.add(firstWord); // Thêm vào danh sách
                mean.add(nghia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        int NumberOfWord=  random.nextInt(WordList.size());
        // Chọn từ
        word = WordList.get(NumberOfWord);
        vnamese = mean.get(NumberOfWord);
        guessWord = "-".repeat(word.length());
    }

    public int getCount() {
        return count;
    }

    public void increaseCount() {
        this.count++;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getVnamese() {
        return vnamese;
    }

    public void setVnamese(String vnamese) {
        this.vnamese = vnamese;
    }

    public String getGuessWord() {
        return guessWord;
    }

    public void setGuessWord(String guessWord) {
        this.guessWord = guessWord;
    }

    
}