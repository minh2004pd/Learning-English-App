package model;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Exam {
    private String userName;
    private List<Questions> questionsList = new ArrayList<>();
    private File file = new File("D:\\version2\\src\\main\\resources\\com\\example\\version2\\data\\questions.txt");
    private BufferedReader br;
    private int score = 0;

    public Exam() {
    }
    public Exam(String userName, int score) {
        this.userName = userName;
        this.score = score;
    }

    public void addQuestions(Questions questions) {
        this.questionsList.add(questions);
    }

    public List<Questions> getQuestionsList() {
        return this.questionsList;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int points) {
        score += points;
    }

    public void decreaseScore(int points) {
        if (score - points >= 0) {
            score -= points;
        }
    }

    public boolean insertFromFile() {
        try {
            br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
            String line;
            String question = "";
            String[] options = null;
            int index;
            while (true) {
                line = br.readLine();
                if (line == null) break;
                if (line.charAt(0) == '#') {
                    question = line.split("#")[1].trim();
                } else if (line.charAt(0) == '*') {
                    options = line.split("\\*")[1].split(",");
                    for (int i = 0; i < options.length; i++) {
                        options[i] = options[i].trim();
                    }
                } else if (line.charAt(0) == '=') {
                    index = Integer.parseInt(line.split("=")[1].trim());
                    Questions newQuestion = new Questions(question, options, index);
                    questionsList.add(newQuestion);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    public void getAllQuestions() {
        for (Questions q : questionsList) {
            System.out.println(q.getQuestion());
            for (int i=0; i<4; i++) {
                System.out.println(q.getOptions()[i]);
            }
            System.out.println("Answer: " + q.getOptions()[q.getAnswerIndex()]);
        }
        System.out.println();
    }

    public int getSize() {
        return questionsList.size();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
