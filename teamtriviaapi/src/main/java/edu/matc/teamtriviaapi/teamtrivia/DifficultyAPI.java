package edu.matc.teamtriviaapi.teamTrivia;
/**
 * Created by sarah on 10/31/2017.
 */
import edu.matc.teamtriviaapi.entity.Difficulty;
import edu.matc.teamtriviaapi.entity.Question;
import edu.matc.teamtriviaapi.persistence.DifficultyDAO;
import edu.matc.teamtriviaapi.persistence.QuestionDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/difficulty")
public class DifficultyAPI {
    DifficultyDAO dao = new DifficultyDAO();

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/html")
    public Response getMessage() {
        // Return a simple message
        String output = "Difficulty is randomly based on our opinions! Sorry for any issues that may cause.<br />" +
                "To view all available difficulties use /teamTrivia/difficulty/all<br />" +
                "To view a specific difficulty use /teamTrivia/difficulty/< id ><br />" +
                "To access JSON values, use /teamTrivia/difficulty/JSON/all or difficulty/JSON/< id >";
        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/{id}")
    public Response getDifficultyHTML(@PathParam("id") String id) {
        if (!isNumeric(id)) {
            return Response.status(400).entity("Ids should be numeric").build();
        }

        Difficulty difficulty = dao.getDifficultyById(Integer.parseInt(id));
        String output = "";

        if (difficulty != null) {
            output = difficulty.toString(); // difficulty to string
            return Response.status(200).entity(output).build();

        } else {
            output = "That difficulty does not exist";
            return Response.status(404).entity(output).build();
        }
    }

    @GET
    @Produces({"text/html", "text/plain"})
    @Path("/all")
    public Response getAllDifficultiesHTML() {

        List<Difficulty> difficulties = dao.getAllDifficulties();
        String output = "";

        if (difficulties.size() > 0) {
            for (Difficulty difficulty: difficulties) {

                output += difficulty.toString(); // difficulty to string
            }
            return Response.status(200).entity(output).build();

        } else {
            output = "There are no difficulties here";
            return Response.status(404).entity(output).build();
        }

    }


    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}