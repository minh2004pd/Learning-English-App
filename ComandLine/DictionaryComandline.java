

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryComandline {
    private DictionaryManagement dictionaryManagement;
    private Scanner scanner;
    private Hangman hangman;

    public DictionaryComandline() {
        dictionaryManagement = new DictionaryManagement();
        scanner = new Scanner(System.in);
    }

    /**
     * check if string is integer.
     * @param s string to check
     * @return true if string is integer, false otherwise
     */
    public static boolean isInteger(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    
    /**
     * insert word from commandline.
     *
     */
    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong tu: ");
        String input = scanner.nextLine();
        while (!isInteger(input)) {
            System.out.println("dau vao khong hop le!");
            System.out.print("Nhap so luong tu: ");
            input = scanner.nextLine();
        }
        int n = Integer.parseInt(input);
        // scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Nhap tu tieng anh: ");
            String word_target = scanner.nextLine().toLowerCase();
            System.out.print("Nhap giai nghia: ");
            String word_explain = scanner.nextLine().toLowerCase();
            Word word = new Word(word_target, word_explain);
            dictionaryManagement.insertFromCommandline(word);
        }
        // scanner.close();
    }

    /**
     * remove word from commandline.
     */
    public void removeFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu can xoa: ");
        String input = scanner.nextLine().toLowerCase();
        if (!dictionaryManagement.removeFromCommandline(input)) {
            System.out.println("Khong tim thay tu can xoa!");
        } else {
            System.out.println("Xoa thanh cong!");
        }
        // scanner.close();
    }

    /**
     * update word from commandline.
     */
    public void updateFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu can sua: ");
        String input = scanner.nextLine().toLowerCase();
        if (dictionaryManagement.dictionaryLookup(input) == null) {
            System.out.println("don't find word!");
            // scanner.close();
            return;
        }
        System.out.println("1. change target");
        System.out.println("2. change explain");
        System.out.print("choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("change to: ");
        String newString = scanner.nextLine();
        if (!dictionaryManagement.updateFromCommandline(input, choice, newString)) {
            System.out.println("failed to change!");
        } else {
            System.out.println("change success!");
        }
        // scanner.close();
    }

    /**
     * insert from file.
     */
    public void insertFromFile() {
        if (dictionaryManagement.insertFromFile()) {
            System.out.println("Import success!");
        } else {
            System.out.println("Import failed!");
        }
    }

    /**
     * Export to file.
     * 
     */
    public void dictionaryExportToFile() {
        if (dictionaryManagement.dictionaryExportToFile()) {
            System.out.println("Export success!");
        } else {
            System.out.println("Export failed!");
        }
    }

    /**
     * lookup word from commandline.
     */
    public void dictionaryLookup() {
        System.out.print("Nhap tu can tra: ");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        Word res = dictionaryManagement.dictionaryLookup(word);
        if (res == null) {
            System.out.println("Khong tim thay tu can tra!");
        } else {
            System.out.println("Ket qua: ");
            System.out.println(res.getWord_target() + "\t" + res.getWord_explain());
        }
        // scanner.close();
    }

    /**
     * search word from commandline.
     */
    public void dictionarySearcher() {
        System.out.print("Nhap: ");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        ArrayList<Word> res = dictionaryManagement.dictionarySearcher(word);
        if (res.isEmpty()) {
            System.out.println("Khong tim thay tu can tra!");
        } else {
            System.out.println("Ket qua: ");
            for (Word w : res) {
                System.out.println(w.getWord_target() + "\t" + w.getWord_explain());
            }
        }
        // // scanner.close();scanner.close();
    }

    /**
     * show all words.
     */
    public void showAllWords() {
        System.out.printf("%-4s | %-20s | %s%n", "No", "English", "Vietnamese");
        System.out.println("-------------------------------------------");
        ArrayList<Word> wordList = dictionaryManagement.getWordList();
        for (int i = 0; i < wordList.size(); i++) {
            Word word = wordList.get(i);
            System.out.printf("%-4d | %-20s | %s%n", (i + 1), word.getWord_target(), word.getWord_explain());
        }
    }

    // Cập nhật sau khi đoán
    private String update(String guessWord, String word, char guess) {
        StringBuilder updatedGuessWord = new StringBuilder(guessWord);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                updatedGuessWord.setCharAt(i, guess);
            }
        }
        return updatedGuessWord.toString();
    }

    public void gameHangMan() {
        hangman = new Hangman();
        if (!hangman.check) {
            System.out.println("Game failed");
            return;
        } 
        System.out.println("Game Hangman ^_^");
        System.out.println("Secret word has "+hangman.getWord().length()+" chars");
        do { 
            System.out.println(hangman.getGuessWord());
            System.out.println("Number of wrong guesses: " + hangman.getCount());
            System.out.print("Your Guess: "); 
            String guess = scanner.nextLine();
            if (hangman.getWord().indexOf(guess) >= 0) {
                hangman.setGuessWord(update(hangman.getGuessWord(), hangman.getWord(), guess.charAt(0)));
            } else {
                hangman.increaseCount();
            }
        } while (hangman.getCount() <= 6 && !hangman.getWord().equals(hangman.getGuessWord()));

        if (hangman.getCount() > 6) {
            System.out.println("You Lose!");
            System.out.println("The correct word is " + hangman.getWord()+
                                ": "+ hangman.getVnamese());
        } else {
            System.out.println("Congratulations, You win!");
            System.out.println(hangman.getWord()+": "+ hangman.getVnamese());
        }
    }

    public void run() {
        System.out.println("Welcome to My Application!");

        boolean isRunning = true;
        while (isRunning) {
            showMenu();
            int action = getUserAction();

            if (action == 0) {
                isRunning = false;
                System.out.println("Goodbye!");
            } else if (action == 1) {
                insertFromCommandline();
            } else if (action == 2) {
                removeFromCommandline();
            } else if (action == 3) {
                updateFromCommandline();
            } else if (action == 4) {
                showAllWords();
            } else if (action == 5) {
                dictionaryLookup();
            } else if (action == 6) {
                dictionarySearcher();
            } else if (action == 7) {
                gameHangMan();
            } else if (action == 8) {
                insertFromFile();
            } else if (action == 9) {
                dictionaryExportToFile();
            } else {
                System.out.println("Invalid action!");
            }
        }

        // scanner.close();
    }

    private void showMenu() {
        System.out.println("[0] Exit");
        System.out.println("[1] Add Word");
        System.out.println("[2] Remove Word");
        System.out.println("[3] Update Word");
        System.out.println("[4] Display All Words");
        System.out.println("[5] Lookup Word");
        System.out.println("[6] Search Words");
        System.out.println("[7] Game");
        System.out.println("[8] Import from file");
        System.out.println("[9] Export to file");

        System.out.print("Your action: ");
    }

    private int getUserAction() {
        int action;
        try {
            action = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            action = -1;
        }
        return action;
    }
}