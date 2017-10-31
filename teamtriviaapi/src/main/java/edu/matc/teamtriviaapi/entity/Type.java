package edu.matc.teamtriviaapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Type {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int TypeID;

    @Column(name = "TypeName")
    private String TypeName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Type_TypeID")
    private Question question;

    public Type() {

    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Type{" +
                "TypeID=" + TypeID +
                ", TypeName='" + TypeName + '\'' +
                '}';
    }
}
