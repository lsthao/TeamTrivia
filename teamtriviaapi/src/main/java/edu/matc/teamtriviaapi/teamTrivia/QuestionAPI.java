package edu.matc.teamtriviaapi.teamTrivia;
/**
 * Created by sarah on 10/31/2017.
 */
import edu.matc.teamtriviaapi.entity.Question;
import edu.matc.teamtriviaapi.persistence.QuestionDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("questions")
public class QuestionAPI {
    QuestionDAO dao = new QuestionDAO();
    Formatter formatter = new Formatter();

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/html")
    public Response getMessage() {
        // Return a simple message
        String output = "Welcome to the questions API";
        return Response.status(200).entity(output).build();
    }

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

    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/all")
    public Response getManyQuestions(@QueryParam("type") String type, @QueryParam("category") String category,
                                     @QueryParam("amount") String amount, @QueryParam("difficulty") String difficulty) {

        List<Question> questions = dao.getAllQuestions();
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

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/JSON/{id}")
    public Response getQuestionJSON(@PathParam("id") String id) {

        if (!isNumeric(id)) {

            int status = 404;
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

    @GET
    @Produces({"application/json", "text/plain"})
    @Path("/JSON/all")
    public Response getAllQuestionsJSON() {

        List<Question> questions = dao.getAllQuestions();
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

    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createQuestionInJSON(String test) {

        String result = "Product created : " + test;
        return Response.status(201).entity(result).build();

    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}