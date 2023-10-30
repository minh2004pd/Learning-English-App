package model;

import java.util.ArrayList;

public class WordType {
    private String word_type;
    private ArrayList<String> word_define = new ArrayList<String>();
    private ArrayList<String> word_usage = new ArrayList<String>();

    public WordType() {
    }
    public WordType(String word_type) {
        this.word_type = word_type;
    }

    public WordType(String word_type, ArrayList<String> word_define, ArrayList<String> word_usage) {
        this.word_type = word_type;
        this.word_define = word_define;
        this.word_usage = word_usage;
    }

    public String getWord_type() {
        return word_type;
    }

    public void setWord_type(String word_type) {
        this.word_type = word_type;
    }

    /**
     * add word define.
     * @param text text
     * @return true or false
     */
    public boolean addWordDefine(String text) {
        if (!word_define.contains(text)) {
            word_define.add(text);
            return true; // Indicate successful addition
        }
        return false; // Indicate that the text already exists in the list
    }

    /**
     * remove word define.
     * @param text text
     */
    public int removeWordDefine(String text) {
        for (int i = 0; i<word_define.size(); i++) {
            if (word_define.get(i).equals(text)) {
                word_define.remove(i);
                return 1;
            }
        }
        return 0;
    }

    /**
     * update word define.
     * @param text old define
     * @param newText new define
     * @return 1 or 0
     */
    public int updateWordDefine(String text, String newText) {
        for (int i = 0; i<word_define.size(); i++) {
            if (word_define.get(i).equals(text)) {
                word_define.set(i, newText);
                return 1;
            }
        }
        return 0;
    }

    /**
     * add word usage.
     * @param text text
     * @return true or false
     */
    public boolean addWordUsage(String text) {
        if (!word_usage.contains(text)) {
            word_usage.add(text);
            return true; // Indicate successful addition
        }
        return false; // Indicate that the text already exists in the list
    }

    /**
     * remove word usage.
     * @param text text
     */
    public int removeWordUsage(String text) {
        for (int i = 0; i<word_usage.size(); i++) {
            if (word_usage.get(i).equals(text)) {
                word_usage.remove(i);
                return 1;
            }
        }
        return 0;
    }

    /**
     * update word usage.
     * @param text old usage
     * @param newText new usage
     * @return 1 or 0
     */
    public int updateWordUsage(String text, String newText) {
        for (int i = 0; i<word_usage.size(); i++) {
            if (word_usage.get(i).equals(text)) {
                word_usage.set(i, newText);
                return 1;
            }
        }
        return 0;
    }

    public ArrayList<String> getWord_define() {
        return word_define;
    }

    public void setWord_define(ArrayList<String> word_define) {
        this.word_define = word_define;
    }

    public ArrayList<String> getWord_usage() {
        return word_usage;
    }

    public void setWord_usage(ArrayList<String> word_usage) {
        this.word_usage = word_usage;
    }
}
