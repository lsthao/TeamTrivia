package edu.matc.teamtriviaapi.controller;


import edu.matc.teamtriviaapi.entity.Question;
import org.apache.log4j.Logger;
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
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

@WebServlet(
        urlPatterns = {"/all-questions"}
)
public class AllQuestions extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ArrayList<Question> questionsArrayList = new ArrayList<Question>();

        HttpSession session = request.getSession();

        URI baseURI = UriBuilder.fromUri("http://localhost:8080/teamTrivia/questions/").build();

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(baseURI);

        String allQuestions = target.path("JSON/all").request().accept(MediaType.APPLICATION_JSON).get(String.class);

        try {
            JSONArray jsonArray = new JSONArray(allQuestions);
            questionsArrayList = parseJSON(jsonArray);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }

        session.setAttribute("allQuestions", questionsArrayList);

        String url = "/allquestions.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public ArrayList<Question> parseJSON(JSONArray jsonArray) throws JSONException {
        int jsonArrayLength = jsonArray.length();
        ArrayList<Question> questionArrayList = new ArrayList<Question>();

        String questionValue = null;
        String answer = null;


        for (int index = 0; index < jsonArrayLength; index++) {
            JSONObject question = jsonArray.getJSONObject(index);
            questionValue = question.getString("Question");
            answer= question.getString("Answer");

            Question newQuestion = new Question();
            newQuestion.setQuestion(questionValue);
            newQuestion.setAnswer(answer);
            questionArrayList.add(newQuestion);

        }
        return questionArrayList;
    }
}
