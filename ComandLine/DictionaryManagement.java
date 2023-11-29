

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public static int binarySearchFirstEqual(ArrayList<Word> res, String target) {
        int low = 0;
        int high = res.size() - 1;
        int result = -1;
        int len = target.length();

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String cur = res.get(mid).getWord_target();

            // Check if the current word's substring matches the target
            if (cur.length() >= len && cur.substring(0, len).equals(target)) {
                result = mid;
                high = mid - 1;  // Look for the first occurrence to the left
            } else if (cur.compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    /**
     * search word in dictionary.
     * @param target target to search
     * @return list of word
     */
    public ArrayList<Word> dictionarySearcher(String target) {
        ArrayList<Word> res = new ArrayList<Word>();
        int start = binarySearchFirstEqual(dictionary.getWordList(), target);
        if (start == -1) return res;

        while (start < dictionary.getWordList().size() &&
                dictionary.getWordList().get(start).getWord_target().startsWith(target)) {
            res.add(dictionary.getWordList().get(start));
            start++;
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

        dictionary.removeWord(res);

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
