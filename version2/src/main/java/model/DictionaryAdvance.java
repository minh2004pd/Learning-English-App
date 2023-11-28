package model;

import database.WordDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

public class DictionaryAdvance extends Dictionary {
    private BufferedReader br;
    private PrintWriter pw;

    public DictionaryAdvance() {
        super();
    }

    /**
     * insert from file.
     * @param file file to insert
     */
    public boolean insertFromFile(File file, ArrayList<Word> list) {
        try {
            br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
            String line;
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                } else {
                    String[] word = line.split("\t");
                    Word newWord = new Word(word[0].toLowerCase(), word[1].toLowerCase());
                    this.addWord(newWord, list);
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * remove from file.
     * @param file file to remove
     */
    public boolean dictionaryExportToFile(File file, ArrayList<Word> list) {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fileWriter, true); // Enable auto-flush

            for (int i = 0; i < list.size(); i++) {
                Word w = list.get(i);
                pw.println(w.getWord_target() + "\t" + w.getWord_explain());
            }

            pw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertFromDB() throws Exception {
        wordList = WordDAO.getInstance().selectAll();
        Collections.sort(wordList, (word1, word2) -> word1.getWord_target().compareTo(word2.getWord_target()));
        if (wordList.size() == 0) {
            return false;
        }
        return true;
    }

    public int deleteFromDB(Word word) {
        int res = WordDAO.getInstance().delete(word);
        Collections.sort(wordList, (word1, word2) -> word1.getWord_target().compareTo(word2.getWord_target()));
        return  res;
    }

    public int updateFromDB(Word word) {
        int res = WordDAO.getInstance().update(word);
        Collections.sort(wordList, (word1, word2) -> word1.getWord_target().compareTo(word2.getWord_target()));
        return res;
    }
}
