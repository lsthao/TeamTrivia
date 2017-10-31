package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "question")

public class Question {

    @Id
    @GeneratedValue (generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "QuestionID")
    private int QuestionID;

    @Column
    private String Question;


}
