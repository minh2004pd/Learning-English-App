import java.io.File;
import java.util.ArrayList;

public class DictionaryManagement {
    private DictionaryAdvance dictionary;
    private File file;

    public DictionaryManagement() {
        dictionary = new DictionaryAdvance();
        file = new File("dictionaries.txt");
    }

    /**
     * lookup word in dictionary.
     * @param target word to lookup
     * @return list of word
     */
    public ArrayList<Word> dictionaryLookup(String target) {
        ArrayList<Word> res = new ArrayList<>();
        for (Word w : dictionary.getWordList()) {
            if (w.getWord_target().equals(target)) {
                res.add(w);
            }
        }
        return res;
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
        ArrayList<Word> res = dictionaryLookup(input);
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
        ArrayList<Word> res = dictionaryLookup(input);
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
