package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

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
    public boolean insertFromFile(File file) {
        try {
            br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
            String line;
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                } else {
                    String[] word = line.split("\t|  +");
                    Word neWord = new Word(word[0].toLowerCase(), word[1].toLowerCase());
                    this.addWord(neWord);
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
    public boolean dictionaryExportToFile(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fileWriter, true); // Enable auto-flush

            for (int i = 0; i < wordList.size(); i++) {
                Word w = wordList.get(i);
                pw.println(w.getWord_target() + "\t" + w.getWord_explain());
            }

            pw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
