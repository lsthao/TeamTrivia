package edu.matc.teamtriviaapi.persistence;

import edu.matc.teamtriviaapi.entity.Difficulty;
import org.hibernate.criterion.MatchMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
        difficultyDAO.addDifficulty(difficulty);

        List<Difficulty> difficulties = difficultyDAO.getAllDifficulties();

        assertTrue(difficulties.size() > 0);
    }

    @Test
    public void getDifficultyById() throws Exception {
        newDifficulty = difficultyDAO.addDifficulty(difficulty);

        assertEquals("Error matching difficulty by Id: ", difficulty.getDifficultyID(), difficultyDAO.getDifficultyById(newDifficulty).getDifficultyID());

    }

    @Test
    public void addDifficulty() throws Exception {
        newDifficulty = difficultyDAO.addDifficulty(difficulty);

        assertEquals("Error matching difficulty by Id: ", difficulty.getDifficultyID(), difficultyDAO.getDifficultyById(newDifficulty).getDifficultyID());
        assertEquals("Error matching difficulty name: ", difficulty.getDifficultyName(), difficultyDAO.getDifficultyById(newDifficulty).getDifficultyName());

    }

    @Test
    public void deleteDifficulty() throws Exception {
        newDifficulty = difficultyDAO.addDifficulty(difficulty);

        assertEquals("Error matching difficulty by Id: ", difficulty.getDifficultyID(), difficultyDAO.getDifficultyById(newDifficulty).getDifficultyID());
        assertEquals("Error matching difficulty name: ", difficulty.getDifficultyName(), difficultyDAO.getDifficultyById(newDifficulty).getDifficultyName());


        difficultyDAO.deleteDifficulty(difficultyDAO.getDifficultyById(newDifficulty).getDifficultyID());

        assertNull("Difficulty not deleted: ", difficultyDAO.getDifficultyById(newDifficulty));
    }

    @Test
    public void updateDifficulty() throws Exception {
        newDifficulty = difficultyDAO.addDifficulty(difficulty);

        assertEquals("Error matching difficulty by Id: ", difficulty.getDifficultyID(), difficultyDAO.getDifficultyById(newDifficulty).getDifficultyID());
        assertEquals("Error matching difficulty name: ", difficulty.getDifficultyName(), difficultyDAO.getDifficultyById(newDifficulty).getDifficultyName());

        Difficulty updateDifficulty = new Difficulty();
        updateDifficulty.setDifficultyID(1);
        updateDifficulty.setDifficultyName("Hard");

        difficultyDAO.updateDifficulty(updateDifficulty);

        assertEquals("Error updating difficulty", updateDifficulty.getDifficultyName(), difficultyDAO.getDifficultyById(1).getDifficultyName());


    }

    @Test
    public void findByProperty() throws Exception {
        List<Difficulty> difficulties = difficultyDAO.findByProperty("DifficultyName", "Hard", MatchMode.EXACT);

        assertEquals(1, difficulties.size());
        assertEquals("Hard", difficulties.get(0).getDifficultyName());

    }

}
