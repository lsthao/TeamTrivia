package edu.matc.teamtriviaapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Type")
public class Type {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int TypeID;

    @Column(name = "TypeName")
    private String TypeName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private Set<Question> question;

    public Type() {

    }

    public Type(String typeName) {
        TypeName = typeName;
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

    public Set<Question> getQuestion() {
        return question;
    }

    public void setQuestion(Set<Question> question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Type{" +
                "TypeID=" + TypeID +
                ", TypeName='" + TypeName + '\'' +
                '}';
    }

    public String toStringJSON() {
        return "{\"TypeID\":\"" + TypeID + "\"" +
                ", \"TypeName\":\"" + TypeName + '\"' +
                '}';
    }
}
