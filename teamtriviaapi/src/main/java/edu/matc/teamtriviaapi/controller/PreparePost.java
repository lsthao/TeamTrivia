package edu.matc.teamtriviaapi.controller;

import edu.matc.teamtriviaapi.entity.Category;
import edu.matc.teamtriviaapi.entity.Difficulty;
import edu.matc.teamtriviaapi.entity.Type;
import edu.matc.teamtriviaapi.persistence.CategoryDAO;
import edu.matc.teamtriviaapi.persistence.DifficultyDAO;
import edu.matc.teamtriviaapi.persistence.TypeDAO;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* Created by sarah 11/13/2107
 * This class dynamically retrieves type, category, and difficulty to populate the question form for posting
*/
@WebServlet(
    urlPatterns = {"/addQuestion"}
)
public class PreparePost extends HttpServlet {

    private Category category;
    private CategoryDAO categoryDAO;
    private TypeDAO typeDAO;
    private DifficultyDAO difficultyDAO;
    private final Logger log = Logger.getLogger(this.getClass());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //System.out.println("here");

        if (session.getAttribute("category") != null) {
            session.setAttribute("question_categories", null);
        }
        if (session.getAttribute("type") != null) {
            session.setAttribute("question_types", null);
        }
        if (session.getAttribute("difficulty") != null) {
            session.setAttribute("question_difficulties", null);
        }

        //System.out.println("before dao");
        categoryDAO = new CategoryDAO();
        typeDAO = new TypeDAO();
        difficultyDAO = new DifficultyDAO();


        //System.out.println("before get alls");

        List<Category> categoryList = categoryDAO.getAllCategories();
        List<Type> typeList = typeDAO.getAllTypes();
        List<Difficulty> difficultyList = difficultyDAO.getAllDifficulties();


        List<String> categoryNames = new ArrayList<String>();
        List<String> typeNames = new ArrayList<String>();
        List<String> difficultyNames = new ArrayList<String>();

        for (Category category: categoryList) {
            categoryNames.add(category.getCategoryName());
        }

        for (Type type: typeList) {
            typeNames.add(type.getTypeName());
        }

        for (Difficulty difficulty: difficultyList) {
            difficultyNames.add(difficulty.getDifficultyName());
        }


        session.setAttribute("question_categories_post", categoryList);
        session.setAttribute("question_types_post", typeList);
        session.setAttribute("question_difficulties_post", difficultyList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addQuestion.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
