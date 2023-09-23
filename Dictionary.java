import java.util.ArrayList;
import java.util.Collections;

public class Dictionary {
    protected ArrayList<Word> wordList;

    public Dictionary() {
        wordList = new ArrayList<Word>();
    }

    public void addWord(Word word) {
        wordList.add(word);
        Collections.sort(wordList, (word1, word2) -> word1.getWord_target().compareTo(word2.getWord_target()));
    }

    public void removeWord(Word word) {
        wordList.remove(word);
        Collections.sort(wordList, (word1, word2) -> word1.getWord_target().compareTo(word2.getWord_target()));
    }

    public void updateWordExplain(Word word, String newExplain) {
        word.setWord_explain(newExplain);
    }

    public void updateWordTarget(Word word, String newTarget) {
        word.setWord_target(newTarget);
    }

    public ArrayList<Word> dictionaryLookup(String target) {
        ArrayList<Word> res = new ArrayList<>();
        for (Word w : wordList) {
            if (w.getWord_target().equals(target)) {
                res.add(w);
            } else if (w.getWord_explain().equals(target)) {
                res.add(w);
            }
        }
        return res;
    }

    public ArrayList<Word> getWordList() {
        return wordList;
    }
}
