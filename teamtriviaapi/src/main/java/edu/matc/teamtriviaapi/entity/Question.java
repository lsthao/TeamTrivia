package edu.matc.teamtriviaapi.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "Question")

public class Question {

    @Id
    @GeneratedValue (generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "QuestionID")
    private int questionId;


    @Column (name = "Question")
    private String question;

    @Column (name = "Answer")
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Category_CategoryID", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Type_TypeID", nullable = false)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Difficulty_DifficultyID", nullable = false)
    private Difficulty difficulty;

    public Question() {
    }

    public Question(String question, String answer, Category category, Type type, Difficulty difficulty) {
        this.question = question;
        this.answer = answer;
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

    public void setQuestion(String question) {

        this.question= question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String toStringAllProperties() {
        return "Question{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", category=" + category.getCategoryName() +
                ", type=" + type.getTypeName() +
                ", difficulty=" + difficulty.getDifficultyName() +
                '}';
    }

    public String toStringJSON() {
        return "{\"QuestionId\":\"" + questionId + "\"" +
                ", \"Question\":\"" + question + '\"' +
                ", \"Answer\":\"" + answer + '\"' +
                '}';
    }

    public String toStringJSONAllProperties() {
        return "{\"QuestionId\":\"" + questionId + "\"" +
                ", \"Question\":\"" + question + '\"' +
                ", \"Answer\":\"" + answer + '\"' +
                ", \"Category\":\"" + category.getCategoryName() + '\"' +
                ", \"Type\":\"" + type.getTypeName() + '\"' +
                ", \"Difficulty\":\"" + difficulty.getDifficultyName() + '\"' +
                '}';
    }
}
