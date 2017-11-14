package edu.matc.teamtriviaapi.persistence;

import edu.matc.teamtriviaapi.entity.Difficulty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DifficultyDAOTest {

    Difficulty difficulty;
    DifficultyDAO difficultyDAO;

    int newDifficulty = 0;

    @Before
    public void setUp() throws Exception {
        difficultyDAO = new DifficultyDAO();

        difficulty = new Difficulty();
        difficulty.setDifficultyName("Easy");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllDifficulties() throws Exception {
    }

    @Test
    public void getDifficultyById() throws Exception {
    }

    @Test
    public void addDifficulty() throws Exception {
    }

    @Test
    public void deleteDifficulty() throws Exception {
    }

    @Test
    public void updateDifficulty() throws Exception {
    }

    @Test
    public void findByProperty() throws Exception {
    }

}
