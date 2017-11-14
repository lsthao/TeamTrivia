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

@WebServlet
public class FormController extends HttpServlet {

    private Category category;
    private CategoryDAO categoryDAO;
    private TypeDAO typeDAO;
    private DifficultyDAO difficultyDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("question-category") != null) {
            session.setAttribute("question-categories", null);
        }
        if (session.getAttribute("question-type") != null) {
            session.setAttribute("question-types", null);
        }
        if (session.getAttribute("question-difficulty") != null) {
            session.setAttribute("question-difficulties", null);
        }

        categoryDAO = new CategoryDAO();
        typeDAO = new TypeDAO();
        difficultyDAO = new DifficultyDAO();

        session.setAttribute("question-categories", categoryDAO.getAllCategories());
        session.setAttribute("question-types", typeDAO.getAllTypes());
        session.setAttribute("question-difficulties", difficultyDAO.getAllDifficulties());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
