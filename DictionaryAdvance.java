import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class DictionaryAdvance extends Dictionary {
    private BufferedReader br;
    private PrintWriter pw;

    public DictionaryAdvance() {
        super();
    }

    /**
     * nhap du lieu tu file.
     * 
     */
    public void insertFromFile(File file) {
        try {
            br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
            String line;
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                } else {
                    String[] word = line.split("\t");
                    Word neWord = new Word(word[0], word[1]);
                    this.addWord(neWord);
                }
            }
            
            System.out.println("Them tu moi thanh cong!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Export to file.
     * 
     */
    public void dictionaryExportToFile(File file) {
        try {
            pw = new PrintWriter(file, "UTF-8");
            for (int i = 0; i < wordList.size(); i++) {
                Word w = wordList.get(i);
                pw.println(w.getWord_target() + "\t" + w.getWord_explain());
            }
            pw.flush();
            pw.close();
            System.out.println("xuat du lieu thanh cong");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
