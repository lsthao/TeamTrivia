package edu.matc.teamtriviaapi.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "question")

public class Question {

    @Id
    @GeneratedValue (generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "QuestionID")
    private int questionId;

    @Column (name = "Question")
    private String question;

    @Column(name = "Category_CategoryID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID", nullable = false)
    private Category category;

    @Column(name = "Type_TypeID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TypeID", nullable = false)
    private Type type;


    @Column(name = "Difficulty_DifficultyID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DifficultyID", nullable = false)
    private Difficulty difficulty;



    public Question() {
        this.questionId = questionId;
        this.question = question;
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
           this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion() {
        this.questionId = questionId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory() {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType() {
        this.type = type;
    }

    public Difficulty getDifficulty()   {
        return difficulty;
    }

    public void setDifficulty() {
        this.difficulty = difficulty;
    }



}
