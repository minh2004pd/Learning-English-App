package model;

import java.util.ArrayList;
import java.util.Collections;

public class Dictionary {
    protected ArrayList<Word> wordList;
    protected ArrayList<Word> bookMark;

    public Dictionary() {
        wordList = new ArrayList<Word>();
        bookMark = new ArrayList<Word>();
    }

    /**
     * add word to dictionary and sort it.
     * @param word word to add
     */
    public void addWord(Word word, ArrayList<Word> list) {
        list.add(word);
        Collections.sort(list, (word1, word2) -> word1.getWord_target().compareTo(word2.getWord_target()));
    }

    /**
     * remove word from dictionary and sort it.
     * @param word word to remove
     */
    public void removeWord(Word word, ArrayList<Word> list) {
        list.remove(word);
        Collections.sort(list, (word1, word2) -> word1.getWord_target().compareTo(word2.getWord_target()));
    }

    /**
     * update word explain.
     * @param word word to update
     * @param newExplain new explain
     */
    public void updateWordExplain(Word word, String newExplain, ArrayList<Word> list) {
        word.setWord_explain(newExplain);
    }

    /**
     * update word target.
     * @param word word to update
     * @param newTarget new target
     */
    public void updateWordTarget(Word word, String newTarget) {
        word.setWord_target(newTarget);
        Collections.sort(wordList, (word1, word2) -> word1.getWord_target().compareTo(word2.getWord_target()));
    }

    public ArrayList<Word> getWordList() {
        return wordList;
    }

    public ArrayList<Word> getBookMark() {
        return bookMark;
    }
}
