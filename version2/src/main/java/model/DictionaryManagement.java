package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DictionaryManagement {
    private DictionaryAdvance dictionary;
    private File file;

    public DictionaryManagement() {
        dictionary = new DictionaryAdvance();
        file = new File("D:\\version2\\src\\main\\resources\\com\\example\\version2\\data\\dictionaries.txt");
    }

    /**
     * lookup word in dictionary.
     * @param target word to lookup
     * @return list of word
     */
    public Word dictionaryLookup(String target) {
        // Perform binary search
        int index = Collections.binarySearch(dictionary.getWordList(), new Word(target, ""), new Comparator<Word>() {
            @Override
            public int compare(Word w1, Word w2) {
                return w1.getWord_target().compareTo(w2.getWord_target());
            }
        });

        if (index >= 0) {
            return dictionary.getWordList().get(index);
        } else {
            return null;  // Word not found
        }
    }

    /**
     * search word in dictionary.
     * @param target target to search
     * @return list of word
     */
    public ArrayList<Word> dictionarySearcher(String target) {
        ArrayList<Word> res = new ArrayList<Word>();
        for (Word w : dictionary.getWordList()) {
            if (w.getWord_target().startsWith(target)) {
                res.add(w);
            }
        }
        return res;
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
        Word res = dictionaryLookup(input);
        if (res == null) {
            return false;
        }
        else {
            dictionary.removeWord(res);
        }
        return true;
    }

    /**
     * update word from commandline.
     */
    public boolean updateFromCommandline(String input, int choice, String newString) {
        Word res = dictionaryLookup(input);
        if (choice == 1) {
            dictionary.updateWordTarget(res, newString);
        } else if (choice == 2) {
            dictionary.updateWordExplain(res, newString);

        }
        return true;
    }

    public boolean insertFromDB() {
        return dictionary.insertFromDB();
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
