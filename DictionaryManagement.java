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
     * insert word from commandline.
     *
     */
    public void insertFromCommandline(Word word) {
        dictionary.addWord(word);
    }

    /**
     * remove word from commandline.
     */
    public boolean removeFromCommandline(String input) {
        ArrayList<Word> res = dictionary.dictionaryLookup(input);
        if (res.size() == 0) {
            return false;
        }
        for (Word w : res) {
            dictionary.removeWord(w);
        }
        return true;
    }

    /**
     * update word from commandline.
     */
    public boolean updateFromCommandline(String input, int choice, String newString) {
        ArrayList<Word> res = dictionary.dictionaryLookup(input);
        if (res.size() == 0) {
            return false;
        }
        if (choice == 1) {
            for (Word w : res) {
                dictionary.updateWordTarget(w, newString);
            }
        } else if (choice == 2) {
            for (Word w : res) {
                dictionary.updateWordExplain(w, newString);
            }
        }
        return true;
    }

    /**
     * insert from file.
     */
    public boolean insertFromFile() {
        return dictionary.insertFromFile(file);
    }

    /**
     * Export to file.
     * 
     */
    public boolean dictionaryExportToFile() {
        return dictionary.dictionaryExportToFile(file);
    }

    /**
     * lookup word from commandline.
     */
    public ArrayList<Word> dictionaryLookup(String word) {
        return dictionary.dictionaryLookup(word);
    }

    /**
     * search word from commandline.
     */
    public ArrayList<Word> dictionarySearcher(String word) {
        return dictionary.dictionarySearcher(word);
    }

    public void setDictionary(DictionaryAdvance dictionary) {
        this.dictionary = dictionary;
    }

    public DictionaryAdvance getDictionary() {
        return dictionary;
    }

    public ArrayList<Word> getWordList() {
        return dictionary.getWordList();
    }
}
