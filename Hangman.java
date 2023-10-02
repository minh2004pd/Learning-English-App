import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void run() {
        
        List<String> WordList = new ArrayList<>();
         List<String> mean = new ArrayList<>();

        // Đường dẫn đến tệp văn bản chứa danh sách từ
       String filePath = "dictionaries.txt"; // Đặt đường dẫn đúng của tệp của bạn

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Lấy chỉ phần tử đầu tiên từ mỗi dòng và thêm nó vào danh sách
                String firstWord = line.split(" ")[0]; // Lấy phần tử đầu tiên
                String nghia = line.split(" ")[1];
                WordList.add(firstWord); // Thêm vào danh sách
                mean.add(nghia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    // Số từ
      
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
int NumberOfWord=  random.nextInt(WordList.size());
        // Chọn từ
        String word = WordList.get(NumberOfWord);
        String vnamese = mean.get(NumberOfWord);
        String guessWord = "-".repeat(word.length());
        int count = 0;
        System.out.println("Game Hangman ^_^");
        System.out.println("Secret word has "+word.length()+" chars");
        do { 
            renderGame(count, guessWord);
            char guess = answers(scanner);
            if (checkWord(word, guess)) {
                guessWord = update(guessWord, word, guess);
            } else {
                count++;
            }
        } while (count <= 6 && !word.equals(guessWord));

        renderGame(count, guessWord);

        if (count > 6) {
            System.out.println("You Lose!");
            System.out.println("The correct word is " + word+": "+ vnamese);
        } else {
            System.out.println("Congratulations, You win!");
            System.out.println(word+": "+ vnamese);
        }

        scanner.close();
    }

    // Xuất ra màn hình (đếm số từ)
    private static void renderGame(int count, String guessWord) {
        System.out.println(guessWord);
        System.out.println("Number of wrong guesses: " + count);
    }

    // Hàm đoán từ
    private static char answers(Scanner scanner) {
        System.out.print("Your Guess: "); 
        return scanner.next().charAt(0);
    }

    // Kiểm tra từ
    private static boolean checkWord(String word, char guess) {
        return word.indexOf(guess) >= 0;
    }

    // Cập nhật sau khi đoán
    private static String update(String guessWord, String word, char guess) {
        StringBuilder updatedGuessWord = new StringBuilder(guessWord);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                updatedGuessWord.setCharAt(i, guess);
            }
        }
        return updatedGuessWord.toString();
    }
}