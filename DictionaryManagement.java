import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private DictionaryAdvance dictionary;
    private File file;

    public DictionaryManagement() {
        dictionary = new DictionaryAdvance();
        file = new File("dictionaries.txt");
    }

    /**
     * check is integer or not.
     * 
     * @param s string have to check
     * @return true or false
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
     * nhap du lieu bang lenh.
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
                String word_target = scanner.nextLine();
                System.out.print("Nhap giai nghia: ");
                String word_explain = scanner.nextLine();
                Word word = new Word(word_target, word_explain);
                dictionary.addWord(word);
            }
        }
    }

    /**
     * nhap du lieu tu file.
     * 
     */
    public void insertFromFile() {
        dictionary.insertFromFile(file);
    }

    /**
     * Export to file.
     * 
     */
    public void dictionaryExportToFile() {
        dictionary.dictionaryExportToFile(file);
    }

    /**
     * tra cứu từ điển bằng dòng lệnh.
     * 
     */
    public void dictionaryLookup() {
        System.out.print("Nhap tu can tra: ");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        ArrayList<Word> res = dictionary.dictionaryLookup(word);
        if (res.isEmpty()) {
            System.out.println("Khong tim thay tu can tra!");
        }
        else {
            System.out.println("Ket qua: ");
            for (int i = 0; i < res.size(); i++) {
                System.out.println(res.get(i).getWord_target() + "\t" + res.get(i).getWord_explain());
            }
        }
    }

    /**
     * show all words in dictionary
     */
    public void showAllWords() {
        System.out.printf("%-4s | %-20s | %s%n", "No", "English", "Vietnamese");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < dictionary.getWordList().size(); i++) {
            Word word = dictionary.getWordList().get(i);
            System.out.printf("%-4d | %-20s | %s%n", (i + 1), word.getWord_target(), word.getWord_explain());
        }
    }

    public void setDictionary(DictionaryAdvance dictionary) {
        this.dictionary = dictionary;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
}
