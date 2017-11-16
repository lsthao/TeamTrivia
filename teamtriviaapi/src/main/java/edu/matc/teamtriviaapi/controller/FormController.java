package edu.matc.teamtriviaapi.controller;

import edu.matc.teamtriviaapi.entity.Category;
import edu.matc.teamtriviaapi.persistence.CategoryDAO;
import edu.matc.teamtriviaapi.persistence.DifficultyDAO;
import edu.matc.teamtriviaapi.persistence.TypeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        urlPatterns = {"/index"}

)
public class FormController extends HttpServlet {

    private Category category;
    private CategoryDAO categoryDAO;
    private TypeDAO typeDAO;
    private DifficultyDAO difficultyDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("category") != null) {
            session.setAttribute("question_categories", null);
        }
        if (session.getAttribute("type") != null) {
            session.setAttribute("question_types", null);
        }
        if (session.getAttribute("difficulty") != null) {
            session.setAttribute("question_difficulties", null);
        }

        categoryDAO = new CategoryDAO();
        typeDAO = new TypeDAO();
        difficultyDAO = new DifficultyDAO();

        List<Category> categoryList = categoryDAO.getAllCategories();

        session.setAttribute("question_categories", categoryList);
        session.setAttribute("question_types", typeDAO.getAllTypes());
        session.setAttribute("question_difficulties", difficultyDAO.getAllDifficulties());

        //System.out.println("category: " + categoryList.get(0).getCategoryName());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
