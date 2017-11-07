package edu.matc.teamtriviaapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Difficulty {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int DifficultyID;

    @Column(name = "DifficultyName")
    private String DifficultyName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="difficulty")
    private Set<Question> question;

    public Difficulty() {
    }

    public Difficulty(String difficultyName) {
        DifficultyName = difficultyName;
    }

    public int getDifficultyID() {
        return DifficultyID;
    }

    public void setDifficultyID(int difficultyID) {
        DifficultyID = difficultyID;
    }

    public String getDifficultyName() {
        return DifficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        DifficultyName = difficultyName;
    }

    public Set<Question> getQuestion() {
        return question;
    }

    public void setQuestion(Set<Question> question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Difficulty{" +
                "DifficultyID=" + DifficultyID +
                ", DifficultyName='" + DifficultyName + '\'' +
                '}';
    }

    public String toStringJSON() {
        return "{\"DifficultyID\":\"" + DifficultyID + "\"" +
                ", \"DifficultyName\":\"" + DifficultyName + '\"' +
                '}';
    }

}
