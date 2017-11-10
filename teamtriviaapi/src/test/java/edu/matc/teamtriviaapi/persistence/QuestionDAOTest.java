package edu.matc.teamtriviaapi.persistence;

import edu.matc.teamtriviaapi.entity.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuestionDAOTest {

    Question question;
    QuestionDAO questionDAO;

    int newQuestion = 0;

    @Before
    public void setUp() throws Exception {
        questionDAO = new QuestionDAO();



        question = new Question();
        question.setAnswer("this is the answer!");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllQuestions() throws Exception {
    }

    @Test
    public void findByProperty() throws Exception {
    }

    @Test
    public void getQuestionById() throws Exception {
    }

    @Test
    public void getByCategoryID() throws Exception {
    }

    @Test
    public void getByTypeID() throws Exception {
    }

    @Test
    public void getByDifficultyID() throws Exception {
    }

    @Test
    public void updateQuestion() throws Exception {
    }

    @Test
    public void insertQuestion() throws Exception {
    }

    @Test
    public void deleteByQuestionId() throws Exception {
    }

    @Test
    public void getType() throws Exception {
    }

    @Test
    public void getCategory() throws Exception {
    }

    @Test
    public void getDifficulty() throws Exception {
    }


}
