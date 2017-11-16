package edu.matc.teamtriviaapi.controller;

import edu.matc.teamtriviaapi.entity.Category;
import edu.matc.teamtriviaapi.entity.Difficulty;
import edu.matc.teamtriviaapi.entity.Type;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

@WebServlet(
        urlPatterns = {"/question-generator"}

)
public class QuestionGenerator extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ArrayList<Category> categoriesArrayList = new ArrayList<Category>();
        ArrayList<Type> typesArrayList = new ArrayList<Type>();
        ArrayList<Difficulty> difficultyArrayList = new ArrayList<Difficulty>();

        URI baseURI = UriBuilder.fromUri("http://localhost:8080/teamtriviaapi/teamTrivia/categories/").build();
        URI typeBaseURI = UriBuilder.fromUri("http://localhost:8080/teamtriviaapi/teamTrivia/types/").build();
        URI difficultyBaseURI = UriBuilder.fromUri("http://localhost:8080/teamtriviaapi/teamTrivia/difficulties/").build();

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(baseURI);
        WebTarget typeTarget = client.target(typeBaseURI);
        WebTarget difficultyTarget = client.target(difficultyBaseURI);

        String allCategories = target.path("JSON/all").request().accept(MediaType.APPLICATION_JSON).get(String.class);
        String allTypes = typeTarget.path("JSON/all").request().accept(MediaType.APPLICATION_JSON).get(String.class);
        String allDifficulties = difficultyTarget.path("JSON/all").request().accept(MediaType.APPLICATION_JSON).get(String.class);

        try {
            JSONArray jsonArray = new JSONArray(allCategories);
            JSONArray typeJsonArray = new JSONArray(allTypes);
            JSONArray difficultyJsonArray = new JSONArray(allDifficulties);

            categoriesArrayList = parseJSON(jsonArray);
            typesArrayList = parseJSONType(typeJsonArray);
            difficultyArrayList = parseJSONDifficulty(difficultyJsonArray);


        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }


        if (session.getAttribute("category") != null) {
            session.setAttribute("question_categories", null);
        }
        if (session.getAttribute("type") != null) {
            session.setAttribute("question_types", null);
        }
        if (session.getAttribute("difficulty") != null) {
            session.setAttribute("question_difficulties", null);
        }

        session.setAttribute("question_categories", categoriesArrayList);
        session.setAttribute("question_types", typesArrayList);
        session.setAttribute("question_difficulties", difficultyArrayList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }

    public ArrayList<Difficulty> parseJSONDifficulty(JSONArray jsonArray) throws JSONException {
        int jsonArrayLength = jsonArray.length();
        ArrayList<Difficulty> difficultyArrayList = new ArrayList<Difficulty>();

        String difficultyValue = null;
        int difficultyID = 0;

        for (int index = 0; index < jsonArrayLength; index++) {
            JSONObject difficulty = jsonArray.getJSONObject(index);
            difficultyValue = difficulty.getString("DifficultyName");
            difficultyID = difficulty.getInt("DifficultyID");

            Difficulty newDifficulty = new Difficulty();
            newDifficulty.setDifficultyID(difficultyID);
            newDifficulty.setDifficultyName(difficultyValue);

            difficultyArrayList.add(newDifficulty);

        }

        return difficultyArrayList;
    }

    public ArrayList<Type> parseJSONType(JSONArray jsonArray) throws JSONException {
        int jsonArrayLength = jsonArray.length();
        ArrayList<Type> typeArrayList = new ArrayList<Type>();

        String typeValue = null;
        int typeID = 0;


        for (int index = 0; index < jsonArrayLength; index++) {
            JSONObject type = jsonArray.getJSONObject(index);
            typeValue = type.getString("TypeName");
            typeID= type.getInt("TypeID");

            Type newType = new Type();
            newType.setTypeName(typeValue);
            newType.setTypeID(typeID);
            typeArrayList.add(newType);

        }
        return typeArrayList;
    }

    public ArrayList<Category> parseJSON(JSONArray jsonArray) throws JSONException {
        int jsonArrayLength = jsonArray.length();
        ArrayList<Category> categoryArrayList = new ArrayList<Category>();

        String categoryValue = null;
        int categoryID = 0;


        for (int index = 0; index < jsonArrayLength; index++) {
            JSONObject category = jsonArray.getJSONObject(index);
            categoryValue = category.getString("CategoryName");
            categoryID= category.getInt("CategoryID");

            Category newCategory = new Category();
            newCategory.setCategoryName(categoryValue);
            newCategory.setCategoryID(categoryID);
            categoryArrayList.add(newCategory);

        }
        return categoryArrayList;
    }
}
