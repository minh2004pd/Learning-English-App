import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryComandline {
    private DictionaryManagement dictionaryManagement;
    private Scanner scanner;

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

    private void clearScreen() throws IOException {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    private void waitForKeyPress() {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
            scanner.nextLine();
        } catch (IOException e) {
            System.out.println("Failed to read input: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    /**
     * insert word from commandline.
     *
     */
    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong tu: ");
        String input = scanner.nextLine();
        if (!isInteger(input)) {
            System.out.println("dau vao khong hop le!");
            insertFromCommandline();
        }
        else {
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
        }
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
    }

    /**
     * update word from commandline.
     */
    public void updateFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu can sua: ");
        String input = scanner.nextLine().toLowerCase();
        if (dictionaryManagement.dictionaryLookup(input).isEmpty()) {
            System.out.println("don't find word!");
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
        ArrayList<Word> res = dictionaryManagement.dictionaryLookup(word);
        if (res.isEmpty()) {
            System.out.println("Khong tim thay tu can tra!");
        } else {
            System.out.println("Ket qua: ");
            for (Word w : res) {
                System.out.println(w.getWord_target() + "\t" + w.getWord_explain());
            }
        }
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
                waitForKeyPress();
                try {
                    clearScreen();
                } catch (IOException e) {
                    System.out.println("Failed to clear screen: " + e.getMessage());
                    e.printStackTrace();
                }
            } else if (action == 2) {
                removeFromCommandline();
                waitForKeyPress();
                try {
                    clearScreen();
                } catch (IOException e) {
                    System.out.println("Failed to clear screen: " + e.getMessage());
                    e.printStackTrace();
                }
            } else if (action == 3) {
                updateFromCommandline();
                waitForKeyPress();
                try {
                    clearScreen();
                } catch (IOException e) {
                    System.out.println("Failed to clear screen: " + e.getMessage());
                    e.printStackTrace();
                }
            } else if (action == 4) {
                showAllWords();
                waitForKeyPress();
                try {
                    clearScreen();
                } catch (IOException e) {
                    System.out.println("Failed to clear screen: " + e.getMessage());
                    e.printStackTrace();
                }
            } else if (action == 5) {
                dictionaryLookup();
                waitForKeyPress();
                try {
                    clearScreen();
                } catch (IOException e) {
                    System.out.println("Failed to clear screen: " + e.getMessage());
                    e.printStackTrace();
                }
            } else if (action == 6) {
                dictionarySearcher();
                waitForKeyPress();
                try {
                    clearScreen();
                } catch (IOException e) {
                    System.out.println("Failed to clear screen: " + e.getMessage());
                    e.printStackTrace();
                }
            } else if (action == 7) {
                // Game
            } else if (action == 8) {
                insertFromFile();
                waitForKeyPress();
                try {
                    clearScreen();
                } catch (IOException e) {
                    System.out.println("Failed to clear screen: " + e.getMessage());
                    e.printStackTrace();
                }
            } else if (action == 9) {
                dictionaryExportToFile();
                waitForKeyPress();
                try {
                    clearScreen();
                } catch (IOException e) {
                    System.out.println("Failed to clear screen: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid action!");
                waitForKeyPress();
                try {
                    clearScreen();
                } catch (IOException e) {
                    System.out.println("Failed to clear screen: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        scanner.close();
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