package edu.matc.teamtriviaapi.persistence;

import edu.matc.teamtriviaapi.entity.Category;
import edu.matc.teamtriviaapi.entity.Difficulty;
import edu.matc.teamtriviaapi.entity.Question;
import edu.matc.teamtriviaapi.entity.Type;
import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class QuestionDAOTest {

    private Logger logger = Logger.getLogger(this.getClass());

    Category category;
    CategoryDAO categoryDAO;

    Difficulty difficulty;
    DifficultyDAO difficultyDAO;

    Type type;
    TypeDAO typeDAO;

    Question question;
    QuestionDAO questionDAO;

    int newCategory = 0;
    int newDifficulty = 0;
    int newType = 0;
    int newQuestion = 0;

    @Before
    public void setUp() throws Exception {
        categoryDAO = new CategoryDAO();
        difficultyDAO = new DifficultyDAO();
        typeDAO = new TypeDAO();
        questionDAO = new QuestionDAO();

        category = new Category();
        category.setCategoryName("Java");

        difficulty = new Difficulty();
        difficulty.setDifficultyName("Easy");

        type = new Type();
        type.setTypeName("T/F");

        question = new Question();
        question.setQuestion("What is the answer?");
        question.setAnswer("this is the answer!");
        question.setCategory(category);
        question.setDifficulty(difficulty);
        question.setType(type);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllQuestions() throws Exception {
        List<Question> questionList = questionDAO.getAllQuestions();

        assertTrue(questionList.size() > 0);
    }

    @Test
    public void findByProperty() throws Exception {
        List<Question> items = questionDAO.findByProperty("Short Answer", "Model, View, Controller (MVC)", "Hard", "10" );

        assertTrue(items.size() > 0);

    }

    @Test
    public void getQuestionById() throws Exception {
        newCategory = categoryDAO.addCategory(category);
        newDifficulty = difficultyDAO.addDifficulty(difficulty);
        newType = typeDAO.addType(type);
        newQuestion = questionDAO.insertQuestion(question);

        assertEquals("Error getting question by Id", question.getQuestionId(), questionDAO.getQuestionById(newQuestion).getQuestionId());
    }

    @Test
    public void getByCategoryID() throws Exception {
        newCategory = categoryDAO.addCategory(category);
        newDifficulty = difficultyDAO.addDifficulty(difficulty);
        newType = typeDAO.addType(type);
        newQuestion = questionDAO.insertQuestion(question);

        List<Question> questions = questionDAO.getByCategoryID(category);

        assertTrue(questions.size() > 0);

    }

    @Test
    public void getByTypeID() throws Exception {
        newCategory = categoryDAO.addCategory(category);
        newDifficulty = difficultyDAO.addDifficulty(difficulty);
        newType = typeDAO.addType(type);
        newQuestion = questionDAO.insertQuestion(question);

        List<Question> questions = questionDAO.getByTypeID(type);

        assertTrue(questions.size() > 0);
    }

    @Test
    public void getByDifficultyID() throws Exception {
        newCategory = categoryDAO.addCategory(category);
        newDifficulty = difficultyDAO.addDifficulty(difficulty);
        newType = typeDAO.addType(type);
        newQuestion = questionDAO.insertQuestion(question);

        List<Question> questions = questionDAO.getByDifficultyID(difficulty);

        assertTrue(questions.size() > 0);

    }

    @Test
    public void updateQuestion() throws Exception {
        newCategory = categoryDAO.addCategory(category);
        category.setCategoryID(categoryDAO.getCategoryById(1).getCategoryID());
        category.setCategoryName(categoryDAO.getCategoryById(1).getCategoryName());

        newDifficulty = difficultyDAO.addDifficulty(difficulty);
        difficulty.setDifficultyID(difficultyDAO.getDifficultyById(1).getDifficultyID());
        difficulty.setDifficultyName(difficultyDAO.getDifficultyById(1).getDifficultyName());

        newType = typeDAO.addType(type);
        type.setTypeID(typeDAO.getTypeById(1).getTypeID());
        type.setTypeName(typeDAO.getTypeById(1).getTypeName());

        newQuestion = questionDAO.insertQuestion(question);
        question.setQuestion("What is the new question?");
        question.setAnswer("This is the new answer.");
        question.setCategory(category);
        question.setDifficulty(difficulty);
        question.setType(type);

        questionDAO.updateQuestion(question);

        assertEquals("Error updating question ID", question.getQuestionId(), questionDAO.getQuestionById(newQuestion).getQuestionId());
        assertEquals("Error updating question", question.getQuestion(), questionDAO.getQuestionById(newQuestion).getQuestion());
        assertEquals("Error updating answer", question.getAnswer(), questionDAO.getQuestionById(newQuestion).getAnswer());
        assertEquals("Error updating category Id", question.getCategory().getCategoryID(), questionDAO.getQuestionById(newQuestion).getCategory().getCategoryID());
        assertEquals("Error updating category Name", question.getCategory().getCategoryName(), questionDAO.getQuestionById(newQuestion).getCategory().getCategoryName());
        assertEquals("Error updating difficulty Id", question.getDifficulty().getDifficultyID(), questionDAO.getQuestionById(newQuestion).getDifficulty().getDifficultyID());
        assertEquals("Error updating difficulty Name", question.getDifficulty().getDifficultyName(), questionDAO.getQuestionById(newQuestion).getDifficulty().getDifficultyName());
        assertEquals("Error updating type Id", question.getType().getTypeID(), questionDAO.getQuestionById(newQuestion).getType().getTypeID());
        assertEquals("Error updating type Name", question.getType().getTypeName(), questionDAO.getQuestionById(newQuestion).getType().getTypeName());

    }

    @Test
    public void insertQuestion() throws Exception {
        newCategory = categoryDAO.addCategory(category);
        newDifficulty = difficultyDAO.addDifficulty(difficulty);
        newType = typeDAO.addType(type);
        newQuestion = questionDAO.insertQuestion(question);

        assertEquals("Error getting question ID", question.getQuestionId(), questionDAO.getQuestionById(newQuestion).getQuestionId());
        assertEquals("Error getting question", question.getQuestion(), questionDAO.getQuestionById(newQuestion).getQuestion());
        assertEquals("Error getting answer", question.getAnswer(), questionDAO.getQuestionById(newQuestion).getAnswer());
        assertEquals("Error getting category Id", question.getCategory().getCategoryID(), questionDAO.getQuestionById(newQuestion).getCategory().getCategoryID());
        assertEquals("Error getting category Name", question.getCategory().getCategoryName(), questionDAO.getQuestionById(newQuestion).getCategory().getCategoryName());
        assertEquals("Error getting difficulty Id", question.getDifficulty().getDifficultyID(), questionDAO.getQuestionById(newQuestion).getDifficulty().getDifficultyID());
        assertEquals("Error getting difficulty Name", question.getDifficulty().getDifficultyName(), questionDAO.getQuestionById(newQuestion).getDifficulty().getDifficultyName());
        assertEquals("Error getting type Id", question.getType().getTypeID(), questionDAO.getQuestionById(newQuestion).getType().getTypeID());
        assertEquals("Error getting type Name", question.getType().getTypeName(), questionDAO.getQuestionById(newQuestion).getType().getTypeName());
    }

    @Test
    public void deleteByQuestionId() throws Exception {
        newCategory = categoryDAO.addCategory(category);
        newDifficulty = difficultyDAO.addDifficulty(difficulty);
        newType = typeDAO.addType(type);
        newQuestion = questionDAO.insertQuestion(question);

        assertNotNull("Question is null", questionDAO.getQuestionById(question.getQuestionId()));

        questionDAO.deleteByQuestionId(question.getQuestionId());
    }

    @Test
    public void getType() throws Exception {
        String typeN = "S";
        List<Type> types = typeDAO.findByProperty("TypeName", typeN, MatchMode.ANYWHERE);

        assertEquals(1, types.size());
    }

    @Test
    public void getCategory() throws Exception {
        String categoryN = "M";
        List<Category> categories = categoryDAO.findByProperty("CategoryName", categoryN, MatchMode.ANYWHERE);

        assertEquals(1, categories.size());
    }

    @Test
    public void getDifficulty() throws Exception {
        String difficultyN = "H";
        List<Difficulty> difficulties = difficultyDAO.findByProperty("DifficultyName", difficultyN, MatchMode.ANYWHERE);

        assertEquals(1, difficulties.size());
    }


}
