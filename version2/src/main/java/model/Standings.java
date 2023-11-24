package model;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Standings {
    private List<Exam> standingsList = new ArrayList<>();
    private File file = new File("D:\\version2\\src\\main\\resources\\com\\example\\version2\\data\\standings.txt");

    public Standings() {
        insertFromFile();
    }

    public void addExam(Exam exam) {
        standingsList.add(exam);
        Collections.sort(standingsList, (exam1, exam2) -> exam1.compareTo(exam2));
    }

    public boolean exportToFile() {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fileWriter, true); // Enable auto-flush

            for (int i = 0; i < standingsList.size(); i++) {
                Exam e = standingsList.get(i);
                pw.println(e.getUserName() + "\t" + e.getScore());
            }

            pw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean insertFromFile() {
        try {
            BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
            String line;
            while(true) {
                line = br.readLine();
                if (line == null) {
                    break;
                } else {
                    String[] word = line.split("\t");
                    Exam newExam = new Exam(word[0], Integer.parseInt(word[1]));
                    addExam(newExam);
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public List<Exam> getStandingsList() {
        return standingsList;
    }

}
