package model;

import database.WordDAO;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DictionaryManagement {
    private DictionaryAdvance dictionary;
    private File wordFile;
    private File bookMarkFile;

    public DictionaryManagement() {
        dictionary = new DictionaryAdvance();
        URL bookmarkUrl = getClass().getResource("/com/example/version2/data/bookmark.txt");
        System.out.println(bookmarkUrl);
        if (bookmarkUrl != null) {
            try {
                bookMarkFile = new File(bookmarkUrl.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            // Rest of your code
        } else {
            System.out.println("Bookmark resource not found.");
        }
        try {
            wordFile = new File(getClass().getResource("/com/example/version2/data/dictionaries.txt").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * lookup word in dictionary.
     * @param target word to lookup
     * @return list of word
     */
    public Word dictionaryLookup(String target, ArrayList<Word> list) {
        // Perform binary search
        int index = Collections.binarySearch(list, new Word(target, ""), new Comparator<Word>() {
            @Override
            public int compare(Word w1, Word w2) {
                return w1.getWord_target().compareTo(w2.getWord_target());
            }
        });

        if (index >= 0) {
            return list.get(index);
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
    public ArrayList<Word> dictionarySearcher(String target, ArrayList<Word> list) {
        ArrayList<Word> res = new ArrayList<Word>();
        int start = binarySearchFirstEqual(list, target);
        if (start == -1) return res;

        while (start < list.size() &&
                list.get(start).getWord_target().startsWith(target)) {
            res.add(list.get(start));
            start++;
        }
        return res;
    }

    /**
     * insert word from commandline.
     *
     */
    public void insertFromCommandline(Word word, ArrayList<Word> list) {
        dictionary.addWord(word, list);
    }

    /**
     * remove word from commandline.
     */
    public boolean removeFromCommandline(String input, ArrayList<Word> list) {
        Word res = dictionaryLookup(input, list);
        if (res == null) {
            return false;
        }
        else {
            dictionary.removeWord(res, list);
        }
        return true;
    }

    /**
     * update word from commandline.
     */
    public void updateFromCommandline(Word res, String newString, ArrayList<Word> list) {
        dictionary.updateWordExplain(res, newString, list);
    }

    public boolean updateToDB(Word word) {
        WordDAO.getInstance().update(word);
        return true;
    }

    public boolean insertFromDB() throws Exception {
        return dictionary.insertFromDB();
    }

    /**
     * insert from file.
     */
    public boolean insertFromFile(File file, ArrayList<Word> list) {
        return dictionary.insertFromFile(file, list);
    }

    /**
     * Export to file.
     *
     */
    public boolean dictionaryExportToFile(File file, ArrayList<Word> list) {
        return dictionary.dictionaryExportToFile(file, list);
    }

    public File getBookMarkFile() {
        return bookMarkFile;
    }

    public File getWordFile() {
        return wordFile;
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
    public ArrayList<Word> getBookMark() {
        return dictionary.getBookMark();
    }

}
