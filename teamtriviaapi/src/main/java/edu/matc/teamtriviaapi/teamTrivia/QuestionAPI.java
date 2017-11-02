package edu.matc.teamtriviaapi.teamTrivia;
/**
 * Created by sarah on 10/31/2017.
 */
import edu.matc.teamtriviaapi.entity.Question;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/questions")
public class QuestionAPI {
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
    public Response getQuestion(@PathParam("id") String id) {
        if (!isNumeric(id)) {
            return Response.status(400).entity("Ids should be numeric").build();
        }

        Question question = new Question(); // TODO get question from database
        String output ="Question with " + id + " here";

        if (question != null) {
            // TODO question to string
            return Response.status(200).entity(output).build();

        } else {
            output = "Question does not exist";
            return Response.status(404).entity(output).build();
        }
    }

    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/query")
    public Response getManyQuestions(@QueryParam("type") String type, @QueryParam("category") String category,
                                     @QueryParam("amount") String amount, @QueryParam("difficulty") String difficulty) {

        // TODO check inputs

        List<Question> questions = new ArrayList<Question>();
        // TODO get questions from database
        String output ="Questions here : amount-" + amount + "; difficulty- " +difficulty+ "; type- " +type +  "; category- " + category;

        if (questions != null) {
            // TODO question to string
            return Response.status(200).entity(output).build();

        } else {
            output = "Question does not exist";
            return Response.status(404).entity(output).build();
        }
    }


    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createProductInJSON(String test) {

        String result = "Product created : " + test;
        return Response.status(201).entity(result).build();

    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}