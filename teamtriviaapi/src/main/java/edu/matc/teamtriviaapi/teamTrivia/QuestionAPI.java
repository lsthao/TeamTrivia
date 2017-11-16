package edu.matc.teamtriviaapi.teamTrivia;
/**
 * Created by sarah on 10/31/2017.
 * This class serves as a repository for the addQuestion and generate question applications, also contains all the GET
 * and POST functions for these classes
 */
import edu.matc.teamtriviaapi.entity.Category;
import edu.matc.teamtriviaapi.entity.Difficulty;
import edu.matc.teamtriviaapi.entity.Question;
import edu.matc.teamtriviaapi.entity.Type;
import edu.matc.teamtriviaapi.persistence.CategoryDAO;
import edu.matc.teamtriviaapi.persistence.DifficultyDAO;
import edu.matc.teamtriviaapi.persistence.QuestionDAO;
import edu.matc.teamtriviaapi.persistence.TypeDAO;
import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("questions")
public class QuestionAPI {
    private final Logger log = Logger.getLogger(this.getClass());
    private QuestionDAO dao = new QuestionDAO();
    private Formatter formatter = new Formatter();


    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/html")
    public Response getMessage() {
        // Return a simple message
        String output = "Welcome to the questions API";
        return Response.status(200).entity(output).build();
    }

    //This HTTP method gets a question via its DB ID and returns it in HTML text format
    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/{id}")
    public Response getQuestionHTML(@PathParam("id") String id) {
        if (!isNumeric(id)) {
            return Response.status(400).entity("Status 400: Ids should be numeric").build();
        }

        Question question = dao.getQuestionById(Integer.parseInt(id));
        String output = "";

        if (question != null) {
            output = question.toStringAllProperties(); // question to string
            return Response.status(200).entity(output).build();

        } else {
            output = "Status 404: Question does not exist";
            return Response.status(404).entity(output).build();
        }
    }

    //This HTTP method gets many/all questions from the DB based on a query parameter and returns it via HTML text format
    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/all")
    public Response getManyQuestions(@QueryParam("type") String type, @QueryParam("category") String category,
                                     @QueryParam("amount") String amount, @QueryParam("difficulty") String difficulty) {

        List<Question> questions = new ArrayList<Question>();


        if (type == null && category == null&& amount == null && difficulty == null) {

            questions = dao.getAllQuestions();
        } else {
            questions = dao.findByProperty(type, category, difficulty, amount);
        }

        String output = "";

        if (questions.size() > 0) {

            for (Question question: questions) {

                output += question.toString(); // questions to string
            }
            return Response.status(200).entity(output).build();

        } else {
            output = "Status 404: There are no questions here";
            return Response.status(404).entity(output).build();
        }
    }

    //This method gets a question via ID from the DB and returns it in JSON format
    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/JSON/{id}")
    public Response getQuestionJSON(@PathParam("id") String id) {

        if (!isNumeric(id)) {

            int status = 400;
            String error = formatter.formatJSONMessage(status, "Ids should be numeric");
            return Response.status(status).entity(error).build();
        }

        Question question = dao.getQuestionById(Integer.parseInt(id));
        String output = "";

        if (question != null) {
            output = question.toStringJSONAllProperties(); // question to json string
            return Response.status(200).entity(output).build();

        } else {
            String error = formatter.formatJSONMessage(404, "That question does not exist");
            return Response.status(404).entity(error).build();
        }

    }

    //This HTTP method gets all questions based on query parameters and returns them as JSON
    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/JSON/all")
    public Response getAllQuestionsJSON(@QueryParam("type") String type, @QueryParam("category") String category,
                                        @QueryParam("amount") String amount, @QueryParam("difficulty") String difficulty) {

        List<Question> questions = new ArrayList<Question>();

        if (type == null && category == null && amount == null && difficulty == null) {

            questions = dao.getAllQuestions();
        } else {

            questions = dao.findByProperty(type, category, difficulty, amount);
        }

        String output = "";

        if (questions.size() > 0) {

            output += "[";
            for (Question question: questions) {

                output += question.toStringJSON(); // questions to string
                output += ",";
            }
            output = output.substring(0, output.length() - 1);
            output += "]";


            return Response.status(200).entity(output).build();

        } else {
            int status = 404;
            output = "There are no questions here";
            output = formatter.formatJSONMessage(status, output);
            return Response.status(status).entity(output).build();
        }

    }

    //This HTTP method gets form data via addQuestion.jsp as form parameters and checks type, based on type it sets new
    //question parameter's answer based on its type. it then sets all parameters and passes it into the API database
    //on completion, it will post the inputted data as HTML and status code 201
    @POST
    @Path("HTML/create")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createQuestionInHTML(@FormParam("question") String question,
                                         @FormParam("answerTF") String answerTF,
                                         @FormParam("answerShort") String answerShort,
                                         @FormParam("type") String type,
                                         @FormParam("category") String category,
                                         @FormParam("difficulty") String difficulty) {

        String result = "";
        int status = 200;
        int id = 0;

        String answer = "";
        if (answerTF != null && type.equals("T/F")) {
            answer = answerTF;
        }

        if (answerShort != null && type.equals("Short Answer")) {
            answer = answerShort;
        }

        if (question.length() > 0 && answer.length() > 0 && type.length() > 0 && category.length() > 0 && difficulty.length() > 0) {
            QuestionDAO questionDAO = new QuestionDAO();

            Category categoryObj = questionDAO.getSingleCategoryObjectFromName(category);
            Type typeObj = questionDAO.getSingleTypeObjectFromName(type);
            Difficulty difficultyObj = questionDAO.getSingleDifficultyObjectFromName(difficulty);

            Question questionObj = new Question(question, answer, categoryObj, typeObj, difficultyObj);
            id = questionDAO.insertQuestion(questionObj);
            questionObj.setQuestionId(id);

            if (id > 0) { // Question was created probably
                // format confirm output
                result = questionObj.toString();
                status = 201;
            } else {
                // format error output
                result = "Status 500: Something wonky happened";
                status = 500;
            }
        } else {

            // we do not have all the required data
            result = "Status 400: invalid parameters";
            status = 400;

        }

        return Response.status(status).entity(result).build();
    }

    //This HTTP method gets form data via addQuestion.jsp as form parameters and checks type, based on type it sets new
    //question parameter's answer based on its type. it then sets all parameters and passes it into the API database
    //on completion, it will post the inputted data as JSON and status code 201
    @POST
    @Path("JSON/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createQuestionInJSON(@FormParam("question") String question,
                                         @FormParam("answerTF") String answerTF,
                                         @FormParam("answerShort") String answerShort,
                                         @FormParam("type") String type,
                                         @FormParam("category") String category,
                                         @FormParam("difficulty") String difficulty) {

        String result = "";
        int status = 200;
        int id = 0;

        String answer = "";
        if (answerTF != null && type.equals("T/F")) {
            answer = answerTF;
        }

        if (answerShort != null && type.equals("Short Answer")) {
            answer = answerShort;
        }

        if (question.length() > 0 && answer.length() > 0 && type.length() > 0 && category.length() > 0 && difficulty.length() > 0) {
            QuestionDAO questionDAO = new QuestionDAO();

            Category categoryObj = questionDAO.getSingleCategoryObjectFromName(category);
            Type typeObj = questionDAO.getSingleTypeObjectFromName(type);
            Difficulty difficultyObj = questionDAO.getSingleDifficultyObjectFromName(difficulty);

            Question questionObj = new Question(question, answer, categoryObj, typeObj, difficultyObj);
            id = questionDAO.insertQuestion(questionObj);
            questionObj.setQuestionId(id);


            if (id > 0) { // Question was created probably
                // format confirm output
                result = questionObj.toStringJSON();
                status = 201;
            } else {
                // format error output
                result = "Status 500: Something wonky happened";
                status = 500;

                String error = formatter.formatJSONMessage(status, result);

                return Response.status(status).entity(error).build();
            }
        } else {

            // we do not have all the required data
            result = "Those are invalid parameters";

            status = 400;

            String error = formatter.formatJSONMessage(status, result);

            return Response.status(status).entity(error).build();

        }
        return Response.status(status).entity(result).build();
    }

    //This method checks for numeric integrity.
    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }


}