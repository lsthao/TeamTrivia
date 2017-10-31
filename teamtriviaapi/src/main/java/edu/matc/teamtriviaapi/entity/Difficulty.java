package edu.matc.teamtriviaapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Difficulty {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int DifficultyID;

    @Column(name = "TypeName")
    private String DifficultyName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Difficulty_DifficultyID")
    private Question question;

    public Difficulty() {
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Difficulty{" +
                "DifficultyID=" + DifficultyID +
                ", DifficultyName='" + DifficultyName + '\'' +
                ", question=" + question +
                '}';
    }

}
